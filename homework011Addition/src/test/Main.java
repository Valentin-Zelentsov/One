package test;

import model.Car;
import repository.CarsRepositoryImpl;

import java.util.ArrayList;
import java.util.HashSet;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        ArrayList<Car> allCars = new ArrayList<>();
        CarsRepositoryImpl rep = new CarsRepositoryImpl("src\\data\\cars.txt");

        HashSet<Car> res = rep.colorToFind("Black");
        res.addAll(rep.millageToFind(0));

        System.out.println ("Номера автомобилей по цвету или пробегу: ");
        for (Car s : res)
        {
            System.out.print(s.getNomer() + " ");
        }
        System.out.println();

        System.out.println("Цвет автомобиля с минимальной стоимостью: " + rep.getCheapestColor());

                }
}