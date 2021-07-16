
/**
 * Order Class for ST1 Assignment 2.
 *
 * @author tenzin - u3149399
 * @version 1
 * 
 * Oder class models an order.
 * Each line in the input file is an order. The information in each line is used to create an Order object.
 * This Order class defines the order object, its attributes and methods.
 * 
 */
public class Order
{
    private int day;
    private int month;
    private int year;
    private String clientID;
    private String serviceCode;
    private String launchID;
    private String orbit;
    private boolean nitrogen;
    private boolean insurance;
    private double value;
    private String comment;
    
    private String launchVehicle;
    private double lox;
    private double rp1;
    private double methane;

    /**
     * Constructor for objects of class Order
     */
    public Order(int day, int month, int year, String clientID, String serviceCode, String launchID, 
    String orbit, boolean nitrogen, boolean insurance, double value, String comment)
    {
        this.day = day;
        this.month = month;
        this.year = year;
        this.clientID = clientID;
        this.serviceCode = serviceCode;
        this.launchID = launchID;
        this.orbit = orbit;
        this.nitrogen = nitrogen;
        this.insurance = insurance;
        this.value = value;
        this.comment = comment;
        
        this.launchVehicle = null;
        this.lox = 0;
        this.rp1 = 0;
        this.methane = 0;
    }

    /**
     * Getter Methods
     */
    public int getDay() { return day;}
    public int getMonth() {return month;}
    public int getYear() {return year;}
    public String getDateFormatted() {return day + "/" + month + "/" + year;}
    public String getClientID() {return clientID;}
    public String getServiceCode() {return serviceCode;}
    public String getLaunchID() {return launchID;}
    public String getOrbit() {return orbit;}
    public boolean getNitrogen() {return nitrogen;}
    public boolean getInsurance() {return insurance;}
    public double getValue() {return value;}
    public String getComment() {return comment;}
    public String getLaunchVehicle() {return launchVehicle;}
    public double getLox() {return lox;}
    public double getRP1() {return rp1;}
    public double getMethane() {return methane;}
    
    /**
     * Setter Methods
     */
    public void setLox(double quantityLox) {lox = quantityLox;}
    public void setRP1(double quantityRP1) {rp1 = quantityRP1;}
    public void setMethane(double quantityMethane) {methane = quantityMethane;}
    public void setLaunchVehicle(String vehicle) {launchVehicle = vehicle;}
    
    /**
     * Method to check if the order requires Nesa tracking
     */
    public boolean isNesa()
    {
        if(orbit.toUpperCase().equals("CSO") || orbit.toUpperCase().equals("GTO")) 
            return true;
        else
            return false;
    }
    
    /**
     * Method to check if the order required a Drako spacecraft or not
     */
    public boolean isDrako()
    {
        if(serviceCode.toUpperCase().equals("ISS5") || serviceCode.toUpperCase().equals("MOON2")) 
            return true;
        else
            return false;
    }
    /**
     * Test method to display Order details
     * Used to test if the Order object is created correctly or not.
     */
    public void displayOrder()
    {
        System.out.println("Date: " + getDateFormatted());
        System.out.println("ClientID: " + getClientID());
        System.out.println("Service Code: " + getServiceCode());
        System.out.println("LaunchID: " + getLaunchID());
        System.out.println("Orbit: " + getOrbit());
        System.out.println("Nitrogen: " + (getNitrogen()? "Y" : "N"));
        System.out.println("Insurance: " + (getInsurance()? "Y" : "N"));
        System.out.println("Value: " + getValue());
        System.out.println("Comment: " + getComment());
        System.out.println("Launch Vehicle: " + getLaunchVehicle());
        System.out.println("--------------------");
    }
}
