package model;

public class Car {
    private String nomer;
    private String model;
    private String color;
    private int probeg;
    private int cost;

    public String getNomer() {
        return nomer;
    }

    public String getColor() {
        return color;
    }

    public int getProbeg() {
        return probeg;
    }

    public Car(String nomer, String model, String color, int probeg, int cost) {
        this.nomer = nomer;
        this.model = model;
        this.color = color;
        this.probeg = probeg;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Car{" +
                nomer + ' ' +
                model + ' ' +
                color + ' ' +
                probeg +' ' +
                cost +
                '}';
    }

    public Car(String str) {
        String[] words = str.split("\\|");
        this.nomer = words[0];
        this.model = words[1];
        this.color = words[2];
        this.probeg = Integer.parseInt(words[3]);
        this.cost = Integer.parseInt(words[4]);
    }
}
