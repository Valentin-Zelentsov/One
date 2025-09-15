package repository;

import model.Car;
import java.util.HashSet;


public interface CarsRepository {

   HashSet<Car> colorToFind(String color );
   String getCheapestColor();
   HashSet<Car> millageToFind(int maxProbeg );

}
