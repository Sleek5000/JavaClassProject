////////////////////////////////////////////////////////////////////////////////
//  Course:   CSC 151 Summer 2023
//  Section:  0001
//
//  Project:  Final Project
//  File:     CarLot.java
//
//  Name:     Owen Cawlfield
//  Email:    odcawlfield@my.waketech.edu
////////////////////////////////////////////////////////////////////////////////

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/**
 * A CarLot represents a collection of distinct Cars that serve as the car lot's inventory
 * and associated operations on the car lot.
 *
 * @author Owen Cawlfield
 */
public class CarLot
{
    private ArrayList<Car> inventory;
    private final String CARLOT_INVENTORY_LOCATION = "carlot.txt";

    /**
     * Default constructor. Initializes the inventory
     */
    public CarLot()
    {
        inventory = new ArrayList<>();
    }


    public ArrayList<Car> getInventory()
    {
        return inventory;
    }

    public void setInventory(ArrayList<Car> inventory)
    {
        this.inventory = inventory;
    }

    /**
     * Return the Car in inventory that matches the identifier
     *
     * @param identifier Car identifier to search for
     * @return Car with match identifier or null if no match
     */
    public Car findCarByIdentifier(String identifier)
    {
        for (Car car : inventory)
        {
            if (car.getCarID().equals(identifier))
                return car;
        }
        return null;

    }

    /**
     * Return a new list containing all of the Cars in inventory,
     * in the order that they were added to the inventory
     *
     * @return new list of Cars
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Car> getCarsInOrderOfEntry()
    {
        return (ArrayList<Car>) inventory.clone();

    }

    /**
     * Return a new list containing all of the Cars in inventory, sorted by highest to lowest MPG
     *
     * @return new, sorted list
     */
    public ArrayList<Car> getCarsSortedByMPG()
    {
        ArrayList<Car> clone = getCarsInOrderOfEntry();
        clone.sort(Car::compareMPG);
        Collections.reverse(clone);
        return clone;
    }

    /**
     * Return the single Car in the inventory that has the highest MPG
     *
     * @return Car with highest MPG
     */
    public Car getCarWithBestMPG()
    {
        ArrayList<Car> clone = getCarsSortedByMPG();
        return clone.get(0);
    }

    /**
     * Return the single Car in the inventory that has the highest mileage
     *
     * @return Car with the highest mileage
     */
    public Car getCarWithHighestMileage()
    {
        ArrayList<Car> clone = getCarsInOrderOfEntry();
        clone.sort(Car::compareMileage);
        return clone.get(clone.size() - 1);
    }

    /**
     * Return the average MPG of all cars in inventory
     *
     * @return average MPG of all cars
     */
    public double getAverageMpg()
    {
        double mpg = 0;
        for (Car c : inventory)
        {
            mpg += c.getMpg();
        }
        return mpg / inventory.size();
    }

    /**
     * Return the total profit of all sold cars in inventory
     *
     * @return total profit of all sold cars
     */
    public double getTotalProfit()
    {
        double profit = 0;
        for (Car c : inventory)
        {
            if (c.isSold())
                profit += c.getProfit();
        }
        return profit;
    }

    /**
     * Add a new car to this CarLot's inventory
     *
     * @param id         Identifier of the new Car
     * @param mileage    Current mileage of the new Car
     * @param mpg        Car's miles per gallon
     * @param cost       Amount paid to acquire this Car
     * @param salesPrice Asking price for the Car
     */
    public void addCar(String id, int mileage, int mpg, double cost, double salesPrice)
    {
        boolean search = inventory.contains(findCarByIdentifier(id));
        if (!search)
        {
            inventory.add(new Car(id, mileage, mpg, cost, salesPrice));
        } else
        {
            System.out.println("\n***************************\t" +
                    "Car " + id + " already in inventory"
                    + "\t****************************\n");
        }
    }

    /**
     * Sell the car identified by identifier for the priceSold.
     *
     * @param identifier Identifier for the Car being sold
     * @param priceSold  Price the Car is selling for
     * @throws IllegalArgumentException Thrown if there is no car with identifier in the inventory
     */
    public void sellCar(String identifier, double priceSold) throws IllegalArgumentException
    {

        try
        {
            Car car = findCarByIdentifier(identifier);
            car.setPriceSold(priceSold);
            car.setSold(true);
            car.setProfit(priceSold - car.getCost());
            System.out.printf("\n Car %1$s sold for $%2$.2f \n\n", identifier, priceSold);
        } catch (Exception ex)
        {
            System.out.println("\n************************\t" +
                    "Car " + identifier + " not found in inventory" +
                    "\t*******************************");
        }
    }

    /**
     * Save the cars in this carlot to the file specified in CARLOT_INVENTORY_LOCATION
     *
     * @throws java.io.FileNotFoundException is thrown if the file cannot be opened
     */
    public void saveToDisk() throws FileNotFoundException
    {
        ArrayList<Car> carList = getCarsInOrderOfEntry();
        File file = new File(CARLOT_INVENTORY_LOCATION);

        try (

                PrintWriter output = new PrintWriter(file)
        )
        {
            for (int i = 0; i < inventory.size(); i++)
            {
                output.print(carList.get(i).getCarID() + " ");
                output.print(carList.get(i).getMileage() + " ");
                output.print(carList.get(i).getMpg() + " ");
                output.print(carList.get(i).getCost() + " ");
                output.print(carList.get(i).getSalePrice() + " ");
                output.print(carList.get(i).getPriceSold() + " ");
                output.print(carList.get(i).getProfit() + " ");
                output.print(carList.get(i).isSold() + " \n");
            }
        }

    }


    /**
     * Load the cars stores in CARLOT_INVENTORY_LOCATION
     *
     * @throws java.io.FileNotFoundException is thrown if the file cannot be found
     */
    public void loadFromDisk() throws FileNotFoundException
    {
        File file = new File(CARLOT_INVENTORY_LOCATION);
        Scanner input = new Scanner(file);

        ArrayList<Car> cars = new ArrayList<>();
        while (input.hasNext())
        {
            Car car = new Car();
            car.setCarID(input.next());
            car.setMileage(input.nextInt());
            car.setMpg(input.nextInt());
            car.setCost(input.nextDouble());
            car.setSalePrice(input.nextDouble());
            car.setPriceSold(input.nextDouble());
            car.setProfit(input.nextDouble());
            car.setSold(input.nextBoolean());
            cars.add(car);
        }
        input.close();
        setInventory(cars);

    }

}
