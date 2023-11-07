////////////////////////////////////////////////////////////////////////////////
//  Course:   CSC 151 Summer 2023
//  Section:  0001
//
//  Project:  Final Project
//  File:     CarLotMain.java
//
//  Name:     Owen Cawlfield
//  Email:    odcawlfield@my.waketech.edu
////////////////////////////////////////////////////////////////////////////////

import java.io.IOException;
import java.util.Scanner;

/**
 * CarLotMain is the main user interface class for the CarLot application.
 * It reads input and performs output to the Console for all features in the application by using an instance of CarLot
 *
 * @author Owen Cawlfield
 */
public class CarLotMain
{
    /**
     * Starting point for the CarLot application
     *
     * @param args Not used
     */
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        CarLot cars = new CarLot();

        String identifier;
        int mileage;
        int mpg;
        double cost;
        double salesPrice;
        String lineBreak = "\t--------------------\t";

        int selection;

        String menu = """
                *** Main Menu ***
                [0] Exit
                [1] Add a car to inventory
                [2] Sell a car from inventory
                [3] List inventory by order of acquisition
                [4] List inventory from best MPG to worst MPG
                [5] Display car with best MPG
                [6] Display car with the highest mileage
                [7] Display overall MPG for the entire inventory
                [8] Display profit for all sold cars
                [9] Save to disk
                [10] Load from disk
                Enter a number from 0 to 10:\s""";

        System.out.print(menu);
        selection = input.nextInt();
        while (selection > 10 || selection < 0)
        {
            System.out.println("Number entered must be 0 to 10");
            System.out.print(menu);
            selection = input.nextInt();
        }
        while (selection != 0)
        {

            switch (selection)
            {
                case 1 ->
                {
                    System.out.print("Enter car Identifier: ");
                    identifier = input.next();


                    System.out.printf("Enter mileage for %s: ", identifier);
                    // input validation for mileage
                    while (!input.hasNextInt())
                    {
                        System.out.println("Mileage entered must be an integer");
                        input.next();
                        System.out.printf("Enter mileage for %s: ", identifier);
                    }
                    mileage = input.nextInt();

                    System.out.printf("Enter MPG of %s: ", identifier);
                    // input validation for MPG
                    while (!input.hasNextInt())
                    {
                        System.out.println("MPG entered must be an integer");
                        input.next();
                        System.out.printf("Enter MPG of %s: ", identifier);
                    }
                    mpg = input.nextInt();

                    System.out.printf("Enter cost of %s: ", identifier);
                    // input validation for dealer cost
                    while (!input.hasNextDouble())
                    {
                        System.out.println("Cost entered must be a number");
                        input.next();
                        System.out.printf("Enter cost of %s: ", identifier);
                    }
                    cost = input.nextDouble();

                    System.out.printf("Enter dealer asking price for %s: ", identifier);
                    // input validation for dealer asking price
                    while (!input.hasNextDouble())
                    {
                        System.out.println("Dealer asking price must be a number");
                        input.next();
                        System.out.printf("Enter dealer asking price for %s: ", identifier);
                    }
                    salesPrice = input.nextDouble();
                    // add car to inventory
                    Car car = new Car(identifier, mileage, mpg, cost, salesPrice);
                    cars.addCar(identifier, mileage, mpg, cost, salesPrice);
                    System.out.println(car + " added to inventory");
                }
                case 2 ->
                {
                    System.out.print("What car would you like to sell? ");
                    input.nextLine();
                    String carID = input.nextLine();
                    System.out.print("Enter selling price of " + carID + " : ");
                    double salePrice = input.nextDouble();
                    cars.sellCar(carID, salePrice);
                }
                case 3 -> System.out.println(lineBreak + "Current inventory" + lineBreak + "\n"
                        + cars.getInventory() +
                        "-------------------------------------------------------------------");

                case 4 -> System.out.println(lineBreak + "Inventory from best MPG to worst" +
                        lineBreak + "\n" + cars.getCarsSortedByMPG() +
                        "-------------------------------------------------------------------");

                case 5 -> System.out.println(lineBreak + "The car with the best MPG is: " +
                        cars.getCarWithBestMPG().getCarID() + lineBreak);

                case 6 -> System.out.println(lineBreak + "The car with highest mileage is: " +
                        cars.getCarWithHighestMileage().getCarID() + lineBreak);

                case 7 -> System.out.printf(lineBreak + "The average MPG of inventory is: %.2f"
                        + lineBreak + "\n", cars.getAverageMpg());

                case 8 -> System.out.printf(lineBreak + "The profit of cars sold is: $%1$.2f" +
                        lineBreak + "\n", cars.getTotalProfit());

                case 9 ->
                {
                    cars.saveToDisk();
                    System.out.println(lineBreak + "Saved to carlot.txt" + lineBreak);
                }

                case 10 ->
                {
                    cars.loadFromDisk();
                    System.out.println(lineBreak + "Inventory loaded" + lineBreak);
                }
            }
            System.out.print(menu);
            selection = input.nextInt();
        }
        System.out.println("Have a nice day");

    }
}
