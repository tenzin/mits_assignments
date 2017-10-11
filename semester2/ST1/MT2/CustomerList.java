
/**
 * CustomerList class for ST1 Marked tutorial 2
 * It is a builder class to build an array of Customer objects.
 * Input is read from file.
 *
 * @author tenzin
 * @version 0.1
 */
import java.io.*;

public class CustomerList
{
    // instance variables - replace the example below with your own
    private Customer[] customers;
    private int numCustomers;

    /**
     * Constructor for objects of class CustomerList. Customer list is constucted in the constructor
     */
    public CustomerList()
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Customers.txt"));
            customers = new Customer[100];
            numCustomers = 0;
            String sLine;
            while((sLine = in.readLine()) != null) {
                String[] temp = sLine.split(",");
                customers[numCustomers] = new Customer(temp[0].trim(), temp[1].trim(), temp[2].trim(), Double.parseDouble(temp[3].trim()));
                numCustomers++; //When accessing Customer array, highest index will be numCustomers - 1, since its incremented here one more
            }
        }
        catch (IOException e) {
            System.out.println ("Error reading Customer File");
            System.exit(0);
        }
    }
    
    /**
     * Method to get index of customer with the given Customer Code
     */
    
    public int findIndex(String customerCode) {
        for (int i = 0; i < numCustomers; i++) {
            if (customers[i].getCode().equals(customerCode)) return i;
        }
        return -1;
    }
    
    /**
     * Method to check if customer code is valid or not
     */
    
    public boolean validateCustomerCode(String customerCode) {
        if (findIndex(customerCode) != -1) return true;
        else return false;
    }
    
    /** 
     * Method to get customer name
     */
    public String getName(String customerCode) {
        int index = findIndex(customerCode);
        if (index!=-1) {
            return (customers[index].getName());
        }
        return "";
    }
    
    
    /**
     * Method to get customer's credit
     */
    public double getCredit(String customerCode) {
        int index = findIndex(customerCode);
        if (index!=-1) {
            return (customers[index].getCredit());
        }
        return 0;
    }
    
    /**
     * Method to check customer's credit
     */
    public boolean checkCredit(String customerCode, double cost) {
        if (customers[findIndex(customerCode)].getCredit() >= cost) return true;
        else return false;
    }

    /**
     * Test method to print the customer list
     */
    public void displayCustomers() {
        // put your code here
        for (int i = 0; i < numCustomers; i++) {
            System.out.println(customers[i].getCode() + ":" + customers[i].getName() + ":" + 
            customers[i].getCompanyName() + ":" + customers[i].getCredit());
        }
    }
    
    public void test() {
        System.out.println("Index of DOG is: " + findIndex("DOG")); //return 13
        System.out.println("Customer code MATTY is " + (validateCustomerCode("MATTY") ? "valid" : "invalid"));
        System.out.println("Customer code TENZIN is " + (validateCustomerCode("TENZIN") ? "valid" : "invalid"));
        System.out.println("Customer BILLY's credit for 2500000005 is " + (checkCredit("BILLY", 250000005) ? "OK" : "NOT OK"));
    }
}
