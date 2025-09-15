package repository;

import model.Car;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class CarsRepositoryImpl implements CarsRepository {

private ArrayList<Car> allCars = new ArrayList<>();

    public CarsRepositoryImpl(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                allCars.add(new Car(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<Car> colorToFind(String color )
    {
        HashSet<Car> result = new HashSet<>();
        for (Car c : allCars)
        {
            if (c.getColor().equals(color)) {
                result.add(c);
            }
        }
        return result;
    }

    public HashSet<Car> millageToFind(int maxProbeg )
    {
        HashSet<Car> result = new HashSet<>();
        for (Car c : allCars)
        {
            if (c.getProbeg()<=maxProbeg) {
                result.add(c);
            }
        }
        return result;
    }
    public String getCheapestColor() {
        String color = "";
        int costMin = Integer.MAX_VALUE;
        for (Car c : allCars)
        {
            if (c.getCost()<=costMin) {
                costMin = c.getCost();
                color = c.getColor();
            }
        }
        return color;}

}

