////////////////////////////////////////////////////////////////////////////////
//  Course:   CSC 151 Summer 2023
//  Section:  0001
//
//  Project:  Final Project
//  File:     CarLotTester.java
//
//  Name:     Owen Cawlfield
//  Email:    odcawlfield@my.waketech.edu
////////////////////////////////////////////////////////////////////////////////

import java.io.IOException;
import java.util.Scanner;


/**
 * This class tests the CarLot class to make sure everything works as intended
 * @author Owen Cawlfield
 */
public class CarLotTester
{
    /**
     * @param args arguments not used
     * @throws IOException Throws exception if file not found
     */
    public static void main(String[] args) throws IOException
    {
        Scanner userInput = new Scanner(System.in);
        CarLot cars = new CarLot();
        int selection;
        String identifier;
        int mileage;
        int mpg;
        double cost;
        double salesPrice;
        cars.addCar("test1", 1000, 30, 12500, 17500);
        cars.addCar("test2", 36075, 34, 23000, 28583.58);
        cars.addCar("test3", 48790, 28, 43500, 56000);


        System.out.println("Starting inventory:\n" + cars.getCarsInOrderOfEntry());
        System.out.println("The car \"test2\" is: " + cars.findCarByIdentifier("test2"));
        System.out.printf("The fleet average MPG is: %.2f\n", cars.getAverageMpg());
        System.out.println("The car with the best MPG is: " + cars.getCarWithBestMPG().getCarID());
        System.out.println("The car with highest mileage is: " + cars.getCarWithHighestMileage().getCarID());
        System.out.println("Inventory sorted by MPG: \n" + cars.getCarsSortedByMPG());

        String menu = """
                [0] exit
                [1] add car
                [2] sell car
                [3] display inventory
                [4] load inventory from disk
                [5] save inventory to disk
                [6] display profits from sold cars
                Enter a number 0 to 6:\s""";
        System.out.print(menu);
        selection = userInput.nextInt();
        while (selection > 6 || selection < 0)
        {
            System.out.println("Number entered must be 0 to 6");
            System.out.print(menu);
            selection = userInput.nextInt();
        }

        while (selection != 0)
        {
            switch (selection)
            {
                case 1 ->
                {
                    System.out.print("Enter car Identifier: ");
                    identifier = userInput.next();


                    System.out.printf("Enter mileage for %s: ", identifier);
                    // input validation for mileage
                    while (!userInput.hasNextInt())
                    {
                        System.out.println("Mileage entered must be an integer");
                        userInput.next();
                        System.out.printf("Enter mileage for %s: ", identifier);
                    }
                    mileage = userInput.nextInt();

                    System.out.printf("Enter MPG of %s: ", identifier);
                    // input validation for MPG
                    while (!userInput.hasNextInt())
                    {
                        System.out.println("MPG entered must be an integer");
                        userInput.next();
                        System.out.printf("Enter MPG of %s: ", identifier);
                    }
                    mpg = userInput.nextInt();

                    System.out.printf("Enter cost of %s: ", identifier);
                    // input validation for dealer cost
                    while (!userInput.hasNextDouble())
                    {
                        System.out.println("Cost entered must be a number");
                        userInput.next();
                        System.out.printf("Enter cost of %s: ", identifier);
                    }
                    cost = userInput.nextDouble();

                    System.out.printf("Enter dealer asking price for %s: ", identifier);
                    // input validation for dealer asking price
                    while (!userInput.hasNextDouble())
                    {
                        System.out.println("Dealer asking price must be a number");
                        userInput.next();
                        System.out.printf("Enter dealer asking price for %s: ", identifier);
                    }
                    salesPrice = userInput.nextDouble();
                    // add car to inventory
                    Car car = new Car(identifier, mileage, mpg, cost, salesPrice);
                    cars.addCar(identifier, mileage, mpg, cost, salesPrice);
                    System.out.println(car + " added to inventory");
                }
                case 2 ->
                {
                    System.out.print("What car would you like to sell? ");
                    userInput.nextLine();
                    String carID = userInput.nextLine();
                    System.out.print("Enter selling price of " + carID + " : ");
                    salesPrice = userInput.nextDouble();
                    cars.sellCar(carID, salesPrice);
                }
                case 3 -> System.out.print(cars.getCarsInOrderOfEntry());
                case 4 ->
                {
                    cars.loadFromDisk();
                    System.out.println("Inventory loaded");
                }
                case 5 ->
                {
                    cars.saveToDisk();
                    System.out.println("Saved to carlot.txt");
                }

                case 6 ->
                {
                    System.out.println("The total profit of car lot is: " + cars.getTotalProfit());
                    System.out.println(cars.getInventory());
                }
            }
            System.out.print(menu);
            selection = userInput.nextInt();
        }


    }

}
