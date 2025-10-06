package com.example.dungeon.core;

import com.example.dungeon.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final GameState state = new GameState();
    private final Map<String, Command> commands = new LinkedHashMap<>();

    static {
        WorldInfo.touch("Game");
    }

    public Game() {
        registerCommands();
        bootstrapWorld();
    }

    private void registerCommands() {
        commands.put("help", (ctx, a) -> System.out.println("Команды: " + String.join(", ", commands.keySet())));
        commands.put("gc-stats", (ctx, a) -> {
            Runtime rt = Runtime.getRuntime();
            long free = rt.freeMemory(), total = rt.totalMemory(), used = total - free;
            System.out.println("Память: used=" + used + " free=" + free + " total=" + total);
        });
        commands.put("name", (ctx, a) ->
        {
            if (a.isEmpty())
            {
                throw new InvalidCommandException("Нужно имя");
            }
            else {
                String playerName = "";
                for (String partName : a)
                {
                    playerName = playerName + partName + " ";
                }
                playerName = playerName.trim();

                ctx.getPlayer().setName(playerName);
            }
        });

        commands.put("look", (ctx, a) -> System.out.println(ctx.getCurrent().describe()));
        commands.put("move", (ctx, a) -> {

            if (a.isEmpty())
            {
                throw new InvalidCommandException("Нужно направление");
            }
            if (!ctx.getCurrent().getNeighbors().containsKey(a.getFirst()))
            {
                throw new InvalidCommandException("Нет такого направления " + a.getFirst());
            }
            Room nextRoom = ctx.getCurrent().getNeighbors().get(a.getFirst());
            if (nextRoom.isNeedKey())
            {
                for (Item i : ctx.getPlayer().getInventory())
                {
                    if (i.getClass().getSimpleName().equalsIgnoreCase("Key"))
                    {
                        ctx.setCurrent(nextRoom);
                        ctx.getPlayer().getInventory().remove(i);
                        ctx.setCurrent(nextRoom);
                        System.out.println("Вы использовали " + i.getName() + " и перешли в: " + nextRoom.getName());
                        break;
                    }
                }
                if (nextRoom!=ctx.getCurrent())
                {
                    System.out.println("Вам нужен ключ");
                }
            }
            else {
                ctx.setCurrent(nextRoom);
                System.out.println("Вы перешли в: " + nextRoom.getName());
            }

        });
        commands.put("take", (ctx, a) -> {
            if (a.isEmpty())
            {
                throw new InvalidCommandException("Нужно название предмета");
            }

            String itemName = "";
            for (String partName : a)
            {
                itemName = itemName + partName + " ";
            }
            itemName = itemName.trim();

            boolean itemNotFound = true;

            for (int i = 0; i< ctx.getCurrent().getItems().size();i++)
            {
                Item item = ctx.getCurrent().getItems().get(i);
                if (item.getName().equalsIgnoreCase(itemName))
                {
                    ctx.getPlayer().getInventory().add(item);
                    ctx.getCurrent().getItems().remove(i);
                    itemNotFound = false;
                    break;
                }
            }
            if (itemNotFound)
            {
                throw new InvalidCommandException("Нет такого предмета");
            }

        });


        commands.put("inventory", (ctx, a) -> {

            Comparator<Item> itemComparator= (s1, s2) -> s2.getClass().getSimpleName().concat(s2.toString()).compareTo(s1.getClass().getSimpleName().concat(s1.toString()) );
            List<Item> itemList = ctx.getPlayer().getInventory().stream().sorted(itemComparator).toList();

            String lastClassName = "";
            for (Item item : itemList)
            {
                if (!lastClassName.equalsIgnoreCase(item.getClass().getSimpleName()))
                {
                    lastClassName=item.getClass().getSimpleName();
                    System.out.println("* "+lastClassName+":");
                }
                System.out.println(item.getName());
            }

        });
        commands.put("use", (ctx, a) -> {

            if (a.isEmpty())
            {
                throw new InvalidCommandException("Нужно название предмета");
            }

            String itemName = "";
            for (String partName : a)
            {
                itemName = itemName + partName + " ";
            }
            itemName = itemName.trim();

            boolean itemNotFound = true;

            for (int i = 0; i< ctx.getPlayer().getInventory().size();i++)
            {
                Item item = ctx.getPlayer().getInventory().get(i);
                if (item.getName().equalsIgnoreCase(itemName))
                {
                    item.apply(ctx);
                    itemNotFound = false;
                    break;
                }
            }
            if (itemNotFound)
            {
                throw new InvalidCommandException("Нет такого предмета");
            }

        });
        commands.put("fight", (ctx, a) -> {

            Monster monster = ctx.getCurrent().getMonster();


            if (monster==null)
            {
                throw new InvalidCommandException("Бить некого");
            }
            else
            {
                int newMonsterHP = monster.getHp() - ctx.getPlayer().getAttack();
                if (newMonsterHP<=0)
                {
                    System.out.println(monster.getName() + " мёртв");
                    ctx.getCurrent().setMonster(null);
                    ctx.getCurrent().getItems().add(monster.getLoot());
                    ctx.addScore(monster.getLevel());
                }
                else {
                    System.out.println("Вы бьёте " + monster.getName() + " на " + ctx.getPlayer().getAttack() + ". HP монстра: " + newMonsterHP);
                    monster.setHp(newMonsterHP);


                    int monsterAttackValue = monster.getAttackValue();
                    int newPlayerHP = ctx.getPlayer().getHp() - monsterAttackValue;
                    if (newPlayerHP <= 0) {
                        System.out.println("Game over");

                    } else {
                        System.out.println("Монстр отвечает на " + monsterAttackValue + ". Ваше HP: " + newPlayerHP);
                        ctx.getPlayer().setHp(newPlayerHP);
                    }
                }
            }

        });
        commands.put("save", (ctx, a) -> SaveLoad.save(ctx));
        commands.put("load", (ctx, a) -> SaveLoad.load(ctx));
        commands.put("scores", (ctx, a) -> SaveLoad.printScores());
        commands.put("exit", (ctx, a) -> {
            System.out.println("Пока!");
            System.exit(0);
        });
    }

    private void bootstrapWorld() {
        Player hero = new Player("Герой", 20, 5);
        state.setPlayer(hero);

        Room square = new Room("Площадь", "Каменная площадь с фонтаном.");
        Room forest = new Room("Лес", "Шелест листвы и птичий щебет.");
        Room cave = new Room("Пещера", "Темно и сыро.");
        square.getNeighbors().put("north", forest);
        forest.getNeighbors().put("south", square);
        forest.getNeighbors().put("east", cave);
        cave.getNeighbors().put("west", forest);
        cave.setNeedKey(true);

        cave.setMonster(new Monster("Большой волк", 3, 12));

        forest.getItems().add(new Potion("Малое зелье", 5));
        forest.getItems().add(new Key("Ключ"));
        forest.setMonster(new Monster("Волк", 1, 8));

        state.setCurrent(square);
    }

    public void run() {
        System.out.println("DungeonMini (TEMPLATE). 'help' — команды.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("> ");
                String line = in.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;
                List<String> parts = Arrays.asList(line.split("\s+"));
                String cmd = parts.getFirst().toLowerCase(Locale.ROOT);
                List<String> args = parts.subList(1, parts.size());
                Command c = commands.get(cmd);
                try {
                    if (c == null) throw new InvalidCommandException("Неизвестная команда: " + cmd);
                    c.execute(state, args);
                    state.addScore(1);
                } catch (InvalidCommandException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Непредвиденная ошибка: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }
}
