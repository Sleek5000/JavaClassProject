////////////////////////////////////////////////////////////////////////////////
//  Course:   CSC 151 Summer 2023
//  Section:  0001
//
//  Project:  Final Project
//  File:     Car.java
//
//  Name:     Owen Cawlfield
//  Email:    odcawlfield@my.waketech.edu
////////////////////////////////////////////////////////////////////////////////

/**
 * A Car represents a single vehicle for sale by a CarLot Cars are modeled with the following values:
 * 1. ID - used to uniquely represent a Car. IDs should not contain spaces
 * 2. MPG - Miles per gallon
 * 3. soldCarID - used to uniquely represent a Car after being sold. Will usually be the same as the ID
 * 4. cost - Amount the Car Lot to acquire this Car
 * 5. salesPrice - the Amount the Car Lot is asking for this Car
 * 6. sold - is the Car sold or available?
 * 7. priceSold - the amount the Car actually sold for. May be different than the sales price
 * 8. profit - The amount of profit made by selling this car. Calculated by subtracting the cost from the priceSold
 *
 * @author Owen Cawlfield
 */
public class Car
{
    private String carID;
    private int mileage;
    private int mpg;
    private double cost;
    private boolean sold;
    private double salePrice;
    private double priceSold;
    private double profit;

    /**
     * Default constructor. Sets all fields of the car to default values
     */
    public Car()
    {
        this.carID = "";
        this.mileage = 0;
        this.mpg = 0;
        this.cost = 0;
        this.sold = false;
        this.priceSold = 0;
        this.profit = 0;
    }

    /**
     * Constructor used when creating a new Car that will be added to a CarLot
     *
     * @param carID     String representation to identify this car. should not contain spaces
     * @param mileage   Mileage of the vehicle when added to inventory
     * @param mpg       Miles Per Gallon
     * @param cost      Price paid to acquire this car
     * @param salePrice Price that the CarLot wants to sell this car for.
     *                  Note that when a vehicle is sold,
     *                  the price that it sells for may be different than the sales price
     */
    public Car(String carID, int mileage, int mpg, double cost, double salePrice)
    {
        this.carID = carID;
        this.mileage = mileage;
        this.mpg = mpg;
        this.cost = cost;
        this.salePrice = salePrice;
    }

    /**
     * @return double price sold minus dealer cost
     */
    public double getProfit()
    {
        return priceSold - cost;
    }


    public String getCarID()
    {
        return carID;
    }


    public void setCarID(String carID)
    {
        this.carID = carID;
    }


    public int getMileage()
    {
        return mileage;
    }


    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }


    public int getMpg()
    {
        return mpg;
    }


    public void setMpg(int mpg)
    {
        this.mpg = mpg;
    }


    public double getCost()
    {
        return cost;
    }


    public void setCost(double cost)
    {
        this.cost = cost;
    }


    public boolean isSold()
    {
        return sold;
    }


    public void setSold(boolean sold)
    {
        this.sold = sold;
    }


    public double getSalePrice()
    {
        return salePrice;
    }


    public void setSalePrice(double salePrice)
    {
        this.salePrice = salePrice;
    }


    public void setProfit(double profit)
    {
        this.profit = profit;
    }


    public double getPriceSold()
    {
        return priceSold;
    }


    public void setPriceSold(double priceSold)
    {
        this.priceSold = priceSold;
    }

    /**
     * Compare the MPG of this Car compared to the otherCar.
     * 1. Returns a negative number if this car's MPG is lower than otherCar's MPG.
     * 2. Returns 0 if the MPG of both cars is the same.
     * 3. Returns a positive number if this car's MPG is higher than otherCar's MPG.
     *
     * @param otherCar The Car compared to this Car
     * @return evaluation result described above
     */
    public int compareMPG(Car otherCar)
    {
        return mpg - otherCar.getMpg();
    }

    /**
     * Compare the mileage of this Car compared to the otherCar.
     * 1. Returns a negative number if this car's mileage is lower than otherCar's MPG.
     * 2. Returns 0 if the mileage of both cars is the same.
     * 3. Returns a positive number if this car's mileage is higher than otherCar's MPG.
     *
     * @param otherCar The Car compared to this Car
     * @return evaluation result described above
     */
    public int compareMileage(Car otherCar)
    {
        return mileage - otherCar.getMileage();
    }

    /**
     * Compare the sales price of this Car compared to the otherCar.
     * 1. Returns a negative number if this car's sales price is lower than otherCar's MPG.
     * 2. Returns 0 if the sales price of both cars is the same.
     * 3. Returns a positive number if this car's sales price is higher than otherCar's MPG.
     *
     * @param otherCar The Car compared to this Car
     * @return evaluation result described above
     */
    public double compareSalesPrice(Car otherCar)
    {
        return salePrice - otherCar.getSalePrice();
    }

    /**
     * Mark this Car as sold and calculate the profit.
     * Note that the profit is equal to the selling price
     * minus the cost the car lot paid for the vehicle.
     *
     * @param priceSold The price that the car sold for.
     */
    public void sellCar(double priceSold)
    {
        this.sold = true;
        setPriceSold(priceSold);
        this.profit = priceSold - this.cost;
    }

    /**
     * @return Return a human-consumable and well-formatted representation of this Car instance.
     */
    @Override
    public String toString()
    {
        return String.format("Car: %1$s, Mileage: %2$d, MPG: %3$d, Sold: %4$b," +
                        " Cost: $%5$.2f, Selling Price: $%6$.2f, Sold for: $%7$.2f, Profit: $%8$.2f\n"
                , carID, mileage, mpg, sold, cost, salePrice, priceSold, profit);
    }

}
