package com.example.dungeon.core;

import com.example.dungeon.model.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SaveLoad {
    private static final Path SAVE = Paths.get("save.txt");
    private static final Path SCORES = Paths.get("scores.csv");

    public static void save(GameState s) {
        try (BufferedWriter w = Files.newBufferedWriter(SAVE)) {
            Player p = s.getPlayer();
            w.write("player;" + p.getName() + ";" + p.getHp() + ";" + p.getAttack());
            w.newLine();

            //String inv = p.getInventory().stream().map(i -> i.getClass().getSimpleName() + ":" + i.getName()).collect(Collectors.joining(","));
            //w.write("inventory;" + inv);
            //w.newLine();
            //w.write("room;" + s.getCurrent().getName());
            //w.newLine();

            ArrayList<Room> rooms = new ArrayList<>();
            rooms.add(s.getCurrent());
            int lastSize = -1;
            while (rooms.size()!=lastSize)
            {
                lastSize = rooms.size();
                ArrayList<Room> tempRooms = new ArrayList<>();
                tempRooms.addAll(rooms);
                for (Room room : tempRooms)
                {
                    for (Room roomLink : room.getNeighbors().values())
                    {
                        if (!rooms.contains(roomLink))
                        {
                            rooms.add(roomLink);
                        }
                    }
                }
            }
            for (int i = 0; i< rooms.size();i++)
            {
                Room northLink = rooms.get(i).getNeighbors().get("north");
                int northInt = -1;
                Room southLink = rooms.get(i).getNeighbors().get("south");
                int southInt = -1;
                Room eastLink = rooms.get(i).getNeighbors().get("east");
                int eastInt = -1;
                Room westLink = rooms.get(i).getNeighbors().get("west");
                int westInt = -1;

                if (northLink!=null)
                {
                    northInt = rooms.indexOf(northLink);
                }
                if (southLink!=null)
                {
                    southInt = rooms.indexOf(southLink);
                }
                if (eastLink!=null)
                {
                    eastInt = rooms.indexOf(eastLink);
                }
                if (westLink!=null)
                {
                    westInt = rooms.indexOf(westLink);
                }
                w.write("room;" + i + ";" + rooms.get(i).getName() + ";" + rooms.get(i).getDescription() + ";" + northInt + ";" + southInt + ";" + eastInt + ";" + westInt + ";" + rooms.get(i).isNeedKey());
                w.newLine();
            }

            for (int i = 0; i< rooms.size();i++)
            {
                for (Item item : rooms.get(i).getItems())
                {
                    if (item instanceof Potion) {
                        Potion pot = (Potion) item;
                        w.write("item;Potion;" + i + ";" + pot.getName() + ";" + pot.getHeal());
                        w.newLine();
                    }
                    if (item instanceof Key) {
                        Key key = (Key) item;
                        w.write("item;Key;" + i + ";" + key.getName());
                        w.newLine();
                    }
                    if (item instanceof Weapon) {
                        Weapon weapon = (Weapon) item;
                        w.write("item;Weapon;" + i + ";" + weapon.getName() + ";" + weapon.getBonus());
                        w.newLine();
                    }
                };
            }

            for (int i = 0; i< s.getPlayer().getInventory().size();i++)
            {
                for (Item item : s.getPlayer().getInventory())
                {
                    if (item instanceof Potion) {
                        Potion pot = (Potion) item;
                        w.write("item;Potion;" + "-1" + ";" + pot.getName() + ";" + pot.getHeal());
                        w.newLine();
                    }
                    if (item instanceof Key) {
                        Key key = (Key) item;
                        w.write("item;Key;" + "-1" + ";" + key.getName());
                        w.newLine();
                    }
                    if (item instanceof Weapon) {
                        Weapon weapon = (Weapon) item;
                        w.write("item;Weapon;" + "-1" + ";" + weapon.getName() + ";" + weapon.getBonus());
                        w.newLine();
                    }
                };
            }

            for (int i = 0; i< rooms.size();i++)
            {
                Monster monster = rooms.get(i).getMonster();

                if (monster!=null) {
                    w.write("monster;" + i + ";" + monster.getName() + ";" + monster.getLevel() + ";" + monster.getHp());
                    w.newLine();
                }
            }

            System.out.println("Сохранено в " + SAVE.toAbsolutePath());
            writeScore(p.getName(), s.getScore());
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось сохранить игру", e);
        }
    }

    public static void load(GameState s) {
        if (!Files.exists(SAVE)) {
            System.out.println("Сохранение не найдено.");
            return;
        }
        try (BufferedReader r = Files.newBufferedReader(SAVE)) {

            ArrayList<String> playerStr = new ArrayList<>();
            ArrayList<String> itemStr = new ArrayList<>();
            ArrayList<String> monsterStr = new ArrayList<>();
            ArrayList<String> roomStr = new ArrayList<>();
            ArrayList<Room> roomArr = new ArrayList<>();

            for (String line; (line = r.readLine()) != null; ) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    if (parts[0].equals("player"))
                    {
                        playerStr.add(parts[1]);
                    }
                    if (parts[0].equals("item"))
                    {
                        itemStr.add(parts[1]);
                    }
                    if (parts[0].equals("monster"))
                    {
                        monsterStr.add(parts[1]);
                    }
                    if (parts[0].equals("room"))
                    {
                        roomStr.add(parts[1]);
                    }
                }
            }

            if (playerStr.size()==0)
            {
                playerStr.add("Hero;10;3");
            }

            Player p = s.getPlayer();
            String[] pl = playerStr.get(0).split(";");
            p.setName(pl[0]);
            p.setHp(Integer.parseInt(pl[1]));
            p.setAttack(Integer.parseInt(pl[2]));
            p.getInventory().clear();

            for (String str : roomStr)
            {
                String[] pp = str.split(";");
                Room room = new Room(pp[1],pp[2]);
                room.setNeedKey(pp[7].equalsIgnoreCase("true"));
                roomArr.add(room);

            }
            for (String str : roomStr)
            {
                String[] pp = str.split(";");
                if (Integer.parseInt(pp[3]) >=0)
                {
                    roomArr.get(Integer.parseInt(pp[0])).getNeighbors().put("north",roomArr.get(Integer.parseInt(pp[3])));
                }
                if (Integer.parseInt(pp[4]) >=0)
                {
                    roomArr.get(Integer.parseInt(pp[0])).getNeighbors().put("south",roomArr.get(Integer.parseInt(pp[4])));
                }
                if (Integer.parseInt(pp[5]) >=0)
                {
                    roomArr.get(Integer.parseInt(pp[0])).getNeighbors().put("east",roomArr.get(Integer.parseInt(pp[5])));
                }
                if (Integer.parseInt(pp[6]) >=0)
                {
                    roomArr.get(Integer.parseInt(pp[0])).getNeighbors().put("west",roomArr.get(Integer.parseInt(pp[6])));
                }
                Room room = new Room(pp[1],pp[2]);
                roomArr.add(room);
            }

            s.setCurrent(roomArr.get(0));

            for (String str : monsterStr)
            {
                String[] pp = str.split(";");
                roomArr.get(Integer.parseInt(pp[0])).setMonster(new Monster(pp[1],Integer.parseInt(pp[2]),Integer.parseInt(pp[3])));
            }

            for (String str : itemStr)
            {
                String[] pp = str.split(";");

                Item item;

                if (pp[0].equals("Potion"))
                {
                    item = new Potion(pp[2],Integer.parseInt(pp[3]));
                }
                if (pp[0].equals("Key"))
                {
                    item = new Key(pp[2]);
                }
                else //Weapon
                {
                    item = new Weapon(pp[2],Integer.parseInt(pp[3]));
                }

                if (Integer.parseInt(pp[1])==-1)
                {
                    s.getPlayer().getInventory().add(item);
                }
                else
                {
                    roomArr.get(Integer.parseInt(pp[1])).getItems().add(item);
                }
            }

            System.out.println("Игра загружена (упрощённо).");
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось загрузить игру", e);
        }
    }

    public static void printScores() {
        if (!Files.exists(SCORES)) {
            System.out.println("Пока нет результатов.");
            return;
        }
        try (BufferedReader r = Files.newBufferedReader(SCORES)) {
            System.out.println("Таблица лидеров (топ-10):");
            r.lines().skip(1).map(l -> l.split(",")).map(a -> new Score(a[1], Integer.parseInt(a[2])))
                    .sorted(Comparator.comparingInt(Score::score).reversed()).limit(10)
                    .forEach(s -> System.out.println(s.player() + " — " + s.score()));
        } catch (IOException e) {
            System.err.println("Ошибка чтения результатов: " + e.getMessage());
        }
    }

    private static void writeScore(String player, int score) {
        try {
            boolean header = !Files.exists(SCORES);
            try (BufferedWriter w = Files.newBufferedWriter(SCORES, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                if (header) {
                    w.write("ts,player,score");
                    w.newLine();
                }
                w.write(LocalDateTime.now() + "," + player + "," + score);
                w.newLine();
            }
        } catch (IOException e) {
            System.err.println("Не удалось записать очки: " + e.getMessage());
        }
    }

    private record Score(String player, int score) {
    }
}
