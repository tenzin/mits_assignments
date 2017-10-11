
/**
 * ServiceList class for ST1 Marked tutorial 2
 * ServiceList is a builder class to build an array of Service objects from a file
 * Input is read from file.
 * @author Rob Cox, tenzin
 * @version 13/9/2017
 */
import java.io.*;

public class ServiceList
{
    private Service[] services;
    private int numServices;
    /**
     * Constructor for objects of class ServiceList. Servicelist is constucted in the constructor
     */
    public ServiceList()
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader("ServiceCodes.txt"));
            services = new Service[100];
            numServices = 0;
            String sLine;
            while((sLine = in.readLine()) != null) {
                String[] temp = sLine.split(",");
                services[numServices] = new Service(temp[0].trim(), temp[1].trim(), 
                    Double.parseDouble(temp[2].trim()), temp[3].split("\\Q|\\E"), temp[4].trim());
                numServices++; //When accessing Customer array, highest index will be numCustomers - 1, since its incremented here one more
            }
        }
        catch (IOException e) {
            System.out.println ("Error reading Customer File");
            System.exit(0);
        }
    }

    /**
     * Returns true if serviceCode is a valid service code
     *
     */
    private int findIndex(String serviceCode)
    {
        for (int i = 0; i < numServices; i++) {
            if (services[i].getCode().equals(serviceCode))
                return i;
        }
        return -1;
    }  
    
    /**
     * Returns true if serviceCode is a valid service code
     *
     */
    public boolean validateServiceCode(String serviceCode)
    {
        if (findIndex(serviceCode)!=-1) return true;
        return false;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     */
    public double getPrice(String serviceCode)
    {
        if (findIndex(serviceCode)!=-1) 
        {
            return services[findIndex(serviceCode)].getPrice();
        }
        return 0;
    }
    
     /**
     * Returns true if the service is a manned spaceflight
     *
     */
    public boolean isManned(String serviceCode)
    {
        if (findIndex(serviceCode)!=-1) 
        {
            return (services[findIndex(serviceCode)].getManned().equals("M") ? true : false);
        }
        return false;
    }
    
    /**
     * returns true if the service code offers a choice of orbits
     */
    public boolean choiceOfOrbit(String serviceCode)
    {
        if (findIndex(serviceCode)!=-1) 
        {
            if (services[findIndex(serviceCode)].getNumOrbit() > 1)
                return true;
            else
                return false;
        }
        return false;
    }
    
    /**
     * Method to return the list of orbit choice
     */
    public String[] getOrbitList(String serviceCode) {
        int index = findIndex(serviceCode);
        if (index != -1) {
            return (services[index].getOrbit());
        }
        return null;
    }
    
    /**
     * returns true if the service code offers a choice of nitrogen flush
     * Nitrogen flus is available only for Unmanned (U)
     */
    public boolean choiceNitrogen(String serviceCode) {
        if (findIndex(serviceCode)!=-1) 
        {
            return (services[findIndex(serviceCode)].getManned().equals("U") ? true : false);
        }
        return false;
    }
    
    /**
     * Method to return base value of nitrogen flush
     */
    public double getCostPerNitrogenFlush() {
        int index = findIndex("ONF");
        if (index != -1) {
            return(services[index].getPrice());
        }
        else
            return 0;
    }
    
    /**
     * Method to return base value of insurance
     */
    public double getCostOfInsurance() {
        int index = findIndex("LLOYDS");
        if (index != -1) {
            return(services[index].getPrice());
        }
        else
            return 0;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     */
    public String getDescription(String serviceCode)
    {
        int index = findIndex(serviceCode);
        if (index != -1) 
        {
            return services[index].getCodeDesc();
        }
        return "";
    }
    
    public void test() {
        /**Display content services
        for (int i = 0; i < numServices; i++) {
            System.out.println("Code: " + services[i].getCode());
            System.out.println("Description: " + services[i].getCodeDesc());
            System.out.println("Price: " + services[i].getPrice());
            System.out.println("Number of orbit choice: " + services[i].getNumOrbit());
            System.out.println("Orbit choices are:");
            for (int j = 0; j < services[i].getNumOrbit(); j++)
                System.out.println(services[i].getOrbit()[j]);
            System.out.println("Manned?: " + services[i].getManned());
        }**/
        
        System.out.println(findIndex("ORB1"));
        System.out.println(findIndex("ORBHH"));
        System.out.println(findIndex("ONF"));
        if (choiceOfOrbit("ISS5")) System.out.println("Yes Choice");
        else System.out.println("No Choice");
    }
    /*
     * A standard to string for use as a test routine
     */
    /**public String toString()
    {
        String retv="";
        for (int i=0;i<sCode.length;i++)
        {
            String temp = String.format( "%,.2f", sCodeCost[i] );
            retv = retv + sCode[i] +" "+ temp +" "+sCodeDesc[i] +"\n";
        }
        return retv;
    }**/
}
