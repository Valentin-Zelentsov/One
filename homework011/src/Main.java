import model.Car;

import java.util.ArrayList;
import java.util.HashSet;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {
        ArrayList<Car> allCars = new ArrayList<>();
        allCars.add(new Car("a123me|Mercedes|White|0|8300000"));
        allCars.add(new Car("b873of|Volga|Black|0|673000"));
        allCars.add(new Car("w487mn|Lexus|Grey|76000|900000"));
        allCars.add(new Car("p987hj|Volga|Red|610|704340"));
        allCars.add(new Car("c987ss|Toyota|White|254000|761000"));
        allCars.add(new Car("o983op|Toyota|Black|698000|740000"));
        allCars.add(new Car("p146op|BMW|White|271000|850000"));
        allCars.add(new Car("u893ii|Toyota|Purple|210900|440000"));
        allCars.add(new Car("l097df|Toyota|Black|108000|780000"));
        allCars.add(new Car("y876wd|Toyota|Black|160000|1000000"));

        HashSet<Car> res = colorToFind(allCars,"Black");
        res.addAll(millageToFind(allCars,0));
        System.out.println ("Номера автомобилей по цвету или пробегу: ");
        for (Car s : res)
        {
            System.out.print(s.getNomer() + " ");
        }
        System.out.println();

        System.out.println("Цвет автомобиля с минимальной стоимостью: " + getCheapestColor(allCars));

        }
    public static HashSet<Car> colorToFind(ArrayList<Car> allCars, String color )
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

    public static HashSet<Car> millageToFind(ArrayList<Car> allCars, int maxProbeg )
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
    public static String getCheapestColor(ArrayList<Car> allCars) {
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