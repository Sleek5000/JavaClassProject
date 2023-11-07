////////////////////////////////////////////////////////////////////////////////
//  Course:   CSC 151 Summer 2023
//  Section:  0001
//
//  Project:  Final Project
//  File:     CarTester.java
//
//  Name:     Owen Cawlfield
//  Email:    odcawlfield@my.waketech.edu
////////////////////////////////////////////////////////////////////////////////

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

/**
 * This class is to test the Car object
 * @author Owen Cawlfield
 */
public class CarTester
{
    /**
     * Starting point for testing Car class
     * @param args not used
     */
    public static void main(String[] args)
    {

        // array to contain car inventory
        ArrayList<Car> cars = new ArrayList<>();

        // 3 test car objects including one default object
        Car car1 = new Car("test1", 10000, 30, 12500, 17500);
        Car car2 = new Car("Test2", 36075, 34, 23000, 28583.58);
        Car car3 = new Car();

        // adding the 3 cars to the array
        Collections.addAll(cars, car1, car2, car3);

        // printing out the list of cars
        for (Car value : cars)
        {
            System.out.println(value);
        }
        // printing out the results of comparing MPG, Miles, and price
        System.out.println("Selling test1 for $25658");
        cars.get(0).sellCar(25658);
        System.out.println(cars.get(0).getCarID());
        cars.sort(Comparator.comparing(Car::getMpg));
        for (Car car : cars)
        {
            System.out.println(car);
        }
    }
}
