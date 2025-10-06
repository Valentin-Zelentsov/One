package com.example.dungeon.model;

import java.util.Random;

public class Monster extends Entity {
    private int level;

    public Monster(String name, int level, int hp) {
        super(name, hp);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttackValue() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        return level+randomNumber;
    }
    public Item getLoot() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);

        Item item;

        if (randomNumber == 0) {
            item = new Weapon("Меч рандома", random.nextInt(5)+1);
        }
        else {
            item = new Potion("Малое зелье", 5);
        }
        return item;
    }
}
