
/**
 * class OrderList for ST1 Assignment 2.
 *
 * @author tenzin
 * @version 1
 */
import java.io.*;
import java.time.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.*;

public class OrderList
{
    
    String[] sCode = {"ORB1","ORBH", "ORBVH", "ISS5", "MOON2", "MARS1", "MARS2"};
    String[] lVehicle = {"Hawk-9", "Hawk Heavy", "BFR", "Hawk-9", "Hawk Heavy", "BFR", "BFR"};
    double[] LOX = {275000, 810000, 4280000};
    double[] RP1 = {120000, 353450, 0};
    double[] Methane = {0, 0, 1870000};
    
    private Order[] orderList;
    private int numberOfOrders;
    

    /**
     * Constructor for objects of class OrderList
     */
    public OrderList(String fileName)
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            orderList = new Order[45];
            numberOfOrders = 0;
            String sLine;
            while((sLine = in.readLine()) != null) {
                String[] temp = sLine.split(",");
                String[] dateString = temp[0].split("/");
                
                int day = Integer.parseInt(dateString[0].trim());
                int month = Integer.parseInt(dateString[1].trim());
                int year = Integer.parseInt(dateString[2].trim());
                
                int indexOfServiceCode = indexOf(sCode, temp[2].trim());
                
                boolean nitrogen = temp[5].trim().toUpperCase().equals("Y")? true : false;
                boolean insurance = temp[6].trim().toUpperCase().equals("Y")? true : false;
                
                String comment;
                if (temp.length == 9)
                {
                    //No comment in Order
                    comment = temp[8];
                }
                else
                {
                    comment = null;
                }
                //Build the order object
                orderList[numberOfOrders] = new Order(day, month, year, temp[1].trim(), temp[2].trim(),
                    temp[3].trim(), temp[4].trim(), nitrogen, insurance, 
                    Double.parseDouble(temp[7].trim()), comment);
                //Set additional object information;
                String launchVehicle = lVehicle[indexOfServiceCode];
                orderList[numberOfOrders].setLaunchVehicle(launchVehicle);
                int indexOfLaunchVehicle = indexOf(lVehicle, launchVehicle);
                orderList[numberOfOrders].setLox(LOX[indexOfLaunchVehicle]);
                orderList[numberOfOrders].setRP1(RP1[indexOfLaunchVehicle]);
                orderList[numberOfOrders].setMethane(Methane[indexOfLaunchVehicle]);
                
                numberOfOrders++; //When accessing the array, highest index will be numberOfOrder - 1, since its incremented here one more
            }
            in.close();
        }
        catch (IOException e) {
            System.out.println("Error reading Input File " + fileName);
            System.exit(0);
        }
    }
    
    /**
     * Method to generate order plan
     */
    public void createOrderPlan()
    {
        System.out.println("\nOrder Plan for " + Month.of(orderList[0].getMonth()).name() + 
            " 20" + orderList[0].getYear());
        
        int nitrogenCylinder = 0;
        double quantityLOX = 0;
        double quantityRP1 = 0;
        double quantityMethane = 0;
        int drako = 0;
        int hawk9 = 0;
        int hawkHeavy = 0;
        int bfr = 0;
        NumberFormat numberFormat = new DecimalFormat("#,###");
        
        for(int i = 0; i < numberOfOrders; i++)
        {
            //Check Insurance
            if(orderList[i].getInsurance())
            {
                int sNearestMillion = (int) Math.ceil(orderList[i].getValue() / 1000000d);
                System.out.printf("Arrange %d million insurance for launch %s on %s\n",
                    sNearestMillion, orderList[i].getLaunchID(), orderList[i].getDateFormatted());
            }
            
            //Check Nesa
            if(orderList[i].isNesa())
            {
                System.out.printf("Arrange for NESA tracking for %s on %s\n", 
                    orderList[i].getLaunchID(), orderList[i].getDateFormatted());
            }
            
            //Check nitrogen flush and increment cylinder count if required
            if(orderList[i].getNitrogen())
            {
                nitrogenCylinder += 3;
            }
            
            //Get required launch vehicle and calculate quantity
            if(orderList[i].getLaunchVehicle().equals("Hawk-9")) {hawk9++;}
            if(orderList[i].getLaunchVehicle().equals("Hawk Heavy")) {hawkHeavy++;}
            if(orderList[i].getLaunchVehicle().equals("BFR")) {bfr++;}
            
            //Calculate fuel requirement
            quantityLOX += orderList[i].getLox();
            quantityRP1 += orderList[i].getRP1();
            quantityMethane += orderList[i].getMethane();
            
            //Calcuate Drako requirement
            if(orderList[i].isDrako())
            {
                drako++;
            }
        }
        
        //Print out Launch Vehicle requirement
        if(hawk9 > 0) 
        {
            System.out.printf("Order %d Hawk-9 launch vehicles\n", hawk9);
        }
        if(hawkHeavy > 0) 
        {
            System.out.printf("Order %d Hawk Heavy launch vehicles\n", hawkHeavy);
        }
        if(bfr > 0) 
        {
            System.out.printf("Order %d BFR launch Vehicles\n", bfr);
        }
        
        //Print out Nitrogen requirement
        if(nitrogenCylinder > 0)
        {
            System.out.printf("Order %d cylinders of Nitrogen\n", nitrogenCylinder);
        }
        
        //Print out Fuel requirement
        if(quantityLOX > 0)
        {
            System.out.printf("Order %s kg of LOX\n", numberFormat.format(quantityLOX));
        }
        if(quantityRP1 > 0)
        {
            System.out.printf("Order %s kg of RP1\n", numberFormat.format(quantityRP1));
        }
        if(quantityMethane > 0)
        {
            System.out.printf("Order %s kg of Methane\n", numberFormat.format(quantityMethane));
        }
        
        //Printout Drako requirement
        if(drako > 0) 
        {
            System.out.printf("Order %d Drako Spacecrafts\n", drako);
        }
    }
    
    /**
     * Method to generate action plan.
     * It accepts two arguments. If output to file is required, writefile is true
     * and out is the BufferedWriter object to write to.
     * Else, writeFile is false and out is null
     */
    public void createActionPlan(boolean writeFile, BufferedWriter out)
    {
        boolean nextLaunchSameDay = false; //Keeps track to see if the next launch is on the same day or not
        String nextLaunchPad = "";
        
        for(int i = 0; i < numberOfOrders; i++)
        {
            if (!nextLaunchSameDay)
            {
                //This is morning launch
                //Determine Launchpad to use. For early launch both launch pads are available.
                if(orderList[i].getLaunchVehicle().equals("BFR"))
                {
                    //If launchvehicle is BFR, use launchpad KSC
                    String[] stringArray = earlyLaunch(orderList[i], "KSC");
                    
                    //Print output to terminal
                    for(int j = 0; j < stringArray.length; j++)
                    {
                        System.out.println(stringArray[j]);
                    }
                    
                    //If option 3, write to file
                    if(writeFile)
                    {
                        for(int j = 0; j < stringArray.length; j++)
                        {
                            try
                            {
                                out.write(stringArray[j] + "\n");
                            }
                            catch (IOException e)
                            {
                                System.out.println("Error Writing to file");
                            }
                        }
                    }
                    //set the other launchpad if there is another launch late in the day
                    nextLaunchPad = "LC40";
                }
                else
                {
                    //Else, use launchpad LC40
                    String[] stringArray = earlyLaunch(orderList[i], "LC40");
                    //Print output to terminal
                    for(int j = 0; j < stringArray.length; j++)
                    {
                        System.out.println(stringArray[j]);
                    }
                    
                    //If option is 3, write it to file also
                    if(writeFile)
                    {
                        for(int j = 0; j < stringArray.length; j++)
                        {
                            try
                            {
                                out.write(stringArray[j] + "\n");
                            }
                            catch (IOException e)
                            {
                                System.out.println("Error Writing to file");
                            }
                        }
                    }
                    
                    //set the other launchpad if there is another launch late in the day
                    nextLaunchPad = "KSC";
                }
                
                //Check if next launch is on the same day or not
                if(i < numberOfOrders - 1) //This is not the last order
                {
                    if(orderList[i].getDateFormatted().equals(orderList[i+1].getDateFormatted()))
                    {
                        //The next launch is on the same day
                        nextLaunchSameDay = true;
                    }
                }
                //The else is reached if i is the last order. No need to do anything.
            }
            else
            {
                //This is afternoon launch.
                String[] stringArray = lateLaunch(orderList[i], nextLaunchPad);
                //Print output to terminal
                for(int j = 0; j < stringArray.length; j++)
                {
                    System.out.println(stringArray[j]);
                }
                
                //If option 3, write to output file
                if(writeFile)
                {
                    for(int j = 0; j < stringArray.length; j++)
                    {
                        try
                        {
                            out.write(stringArray[j] + "\n");
                        }
                        catch (IOException e)
                        {
                            System.out.println("Error Writing to file");
                        }
                    }
                }
                //Next launch cannot be on the same day now
                nextLaunchSameDay = false;
            }
        }
    }
    
    /**
     * Method to prepare AM/Early Launch Plan
     * Takes in an order object and launchpad string
     * Returns String Array of plan statements
     */
    private String[] earlyLaunch(Order order, String launchPad)
    {
        ArrayList<String> arrayList = new ArrayList();
        String date = order.getDateFormatted();
        String launchVehicle = order.getLaunchVehicle();
        String launchID = order.getLaunchID();
        String clientID = order.getClientID();
        String comment = order.getComment();
        
        arrayList.add(String.format("\n%s 6:00 Start vehicle rollout of %s on pad %s for %s", 
            date, launchVehicle, launchPad, launchID));
        if(order.getLox() > 0)
        {
            arrayList.add(String.format("%s 7:00 Fuel rocket with LOX for %s", date, launchID));
        }
        
        if(order.getRP1() > 0)
        {
            arrayList.add(String.format("%s 8:00 Fuel rocket with RP1 for %s", date, launchID));
        }
        
        if(order.getMethane() > 0)
        {
            arrayList.add(String.format("%s 8:00 Fuel rocket with Methane for %s", date, launchID));
        }
        
        if(order.getNitrogen())
        {
            arrayList.add(String.format("%s 10:00 Nitrogen flush %s", date, launchID));
        }
        
        arrayList.add(String.format("%s 10:50 GO/NOGO for launch %s", date, launchID));
        
        if(comment != null)
        {
            arrayList.add(String.format("%s 11:00 Launch %s for %s %s", date, launchID, clientID, comment));
        }
        else
        {
            arrayList.add(String.format("%s 11:00 Launch %s for %s", date, launchID, clientID));
        }
        
        Object[] objArray = arrayList.toArray();
        return Arrays.copyOf(objArray, objArray.length, String[].class);
    }
    
    /**
     * Method to prepare PM/Late Launch Plan
     * Takes in Order object and launchpad string
     * Returns String Array of Plan statements.
     */
    private String[] lateLaunch(Order order, String launchPad)
    {
        ArrayList<String> arrayList = new ArrayList();
        String date = order.getDateFormatted();
        String launchVehicle = order.getLaunchVehicle();
        String launchID = order.getLaunchID();
        String clientID = order.getClientID();
        String comment = order.getComment();
        
        arrayList.add(String.format("\n%s 11:30 Start vehicle rollout of %s on pad %s for %s", 
            date, launchVehicle, launchPad, launchID));
        if(order.getLox() > 0)
        {
            arrayList.add(String.format("%s 12:30 Fuel rocket with LOX for %s", date, launchID));
        }
        
        if(order.getRP1() > 0)
        {
            arrayList.add(String.format("%s 14:00 Fuel rocket with RP1 for %s", date, launchID));
        }
        
        if(order.getMethane() > 0)
        {
            arrayList.add(String.format("%s 14:00 Fuel rocket with Methane for %s", date, launchID));
        }
        
        if(order.getNitrogen())
        {
            arrayList.add(String.format("%s 15:00 Nitrogen flush %s", date, launchID));
        }
        
        arrayList.add(String.format("%s 15:30 GO/NOGO for launch %s", date, launchID));
        
        if(comment != null)
        {
            arrayList.add(String.format("%s 15:40 Launch %s for %s %s", date, launchID, clientID, comment));
        }
        else
        {
            arrayList.add(String.format("%s 15:40 Launch %s for %s", date, launchID, clientID));
        }
        
        Object[] objArray = arrayList.toArray();
        return Arrays.copyOf(objArray, objArray.length, String[].class);
    }
    
    
    /**
     * Other Useful methods
     */
    public int indexOf(String[] array, String element)
    {
        int index = -1;
        for(int i = 0; i < array.length; i++)
        {
            if(array[i].equals(element))
            {
                index = i;
                break;
            }
        }
        return index;
    }
    /**
     * Test method to display all the Order Objects
     */
    public void displayOrderList()
    {
        for(int i = 0; i < numberOfOrders; i++)
        {
            orderList[i].displayOrder();
        }
    }
}
