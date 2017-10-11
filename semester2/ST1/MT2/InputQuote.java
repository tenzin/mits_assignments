import java.util.*;
/**
 * Write a description of class InputQuote here.
 *
 * @author RC
 * @version 12/9/2017
 */
public class InputQuote
{

    /**
     * Constructor for objects of class InputQuote
     */
    String s;
    Quote q; 
    Scanner in;
    String code;
    
    public InputQuote()
    {
        in = new Scanner(System.in);
    }
    
    public Quote getQuote(ServiceList sl, CustomerList cl)
    {
        System.out.print("\u000c");
        System.out.print("       SpaceY Quotation System\n");
        System.out.print("       =======================\n\n");
        
        q=new Quote();
        
        //Get client ID
        System.out.print("Enter Client ID:");
        s = in.nextLine();
        s = s.trim();
        while(!cl.validateCustomerCode(s)) {
            System.out.println("Error: Invalid Customer ID");
            System.out.print("Enter Customer ID:");
            s = in.nextLine();
        }
        q.setCustId(s);
        q.setCustname(cl.getName(s));
        
        // Enter service code
        System.out.print("Enter Service Code:");
        s = in.nextLine();
        s = s.trim();
        while(!sl.validateServiceCode(s)) {
            System.out.println("Error: Invalid Code");
            System.out.print("Enter Service Code:");
            s = in.nextLine();
        }
        q.setCode(s);
        
        // Checked whether service is manned or not
        q.setManned(sl.isManned(q.getCode()));
        
        // now num of launches
        // Input needs to be a positive integer between 1 and 15
        boolean correctNumberOfLaunches = false;
        System.out.print("Enter Number of Launches (1-20):");
        s = in.nextLine();
        s = s.trim();
        while(!correctNumberOfLaunches) {
            if(!isPositiveInteger(s)) {
                System.out.println("Error: Invalid Number of Launches");
                System.out.print("Enter Number of Launches (1-20):");
                s = in.nextLine();
            }
            else {
                if(Integer.parseInt(s) > 20 || Integer.parseInt(s) == 0) {
                    System.out.println("Error: Invalid Number of Launches");
                    System.out.print("Enter Number of Launches (1-20):");
                    s = in.nextLine();
                }
                else { // Input is both positive integer and between 1 and 15
                    correctNumberOfLaunches = true;
                }
            }
        }
        q.setNum(Integer.parseInt(s));
        
        // Orbit type
        if(sl.choiceOfOrbit(q.getCode())) {
            String[] orbitList = sl.getOrbitList(q.getCode());
            String orbitListText = "";
            for (int i = 0; i < orbitList.length; i++)
                orbitListText = orbitListText + " " + orbitList[i];
            System.out.print("Enter Choice of Orbit. Valid choices are" + orbitListText+":");
            s = in.nextLine();
            s = s.toUpperCase().trim();
            // Check if the input string is valid choice of orbit or not
            while(!contains(orbitList, s)) {
                System.out.println("Error in Choice of Orbit.");
                System.out.print("Enter Choice of Orbit. Valid choices are: " + orbitListText);
                s = in.nextLine();
                s = s.toUpperCase().trim();
            }
            q.setOrbit(s);
        }
        else { //This means there is no choice of orbit, so we set it to its orbit type and need not get input from user
            String[] temp = sl.getOrbitList(q.getCode());
            q.setOrbit(temp[0]); //For services codes with just one orbit, its stored as the first member of Orbit array.
        }
        // enter nitrogen flush
        if(sl.choiceNitrogen(q.getCode())) {
            System.out.print("Nitrogen Flush Needed Y/N?:");
            s = in.nextLine();
            s = s.toUpperCase().trim();
            //Check if input is correct or not
            while(!(s.equals("Y") || s.equals("N"))) {
                System.out.println("Error in Choice of Nitrogen Flush.");
                System.out.print("Nitrogen Flush Needed Y/N?:");
                s = in.nextLine();
                s = s.toUpperCase().trim();
            }
            q.setNitrogen(s.equals("Y")? true : false);
        }
        else {
            q.setNitrogen(false);
        }
        // Ask for insurance if it is unmanned flight - given in assignment description
        if(!q.getManned()) {
            System.out.print("Insurance Needed Y/N?:");
            s = in.nextLine();
            s = s.toUpperCase().trim();
            //Check if input is correct or not
            while(!(s.equals("Y") || s.equals("N"))) {
                System.out.println("Error in Choice of Insurance.");
                System.out.print("Insurance Needed Y/N?:");
                s = in.nextLine();
                s = s.toUpperCase().trim();
            }
            q.setInsurance(s.equals("Y")? true : false);
        }
        else {
            q.setInsurance(false);
        }
        
        // enter payload value of satelite
        if(!q.getManned() && q.getInsurance()) {
            System.out.print("Enter Payload Value:");
            s = in.nextLine();
            while(!isPositiveInteger(s)) {
                System.out.println("Invalid payload Value.");
                System.out.print("Enter Payload Value:");
                s = in.nextLine();
            }
            q.setPayloadVal(Double.parseDouble(s));
        }
        else {
            q.setPayloadVal(0.00);
        }
        
        return q;
    }
    
    public boolean isPositiveInteger(String s)
    {
    if(s.isEmpty()) return false;
    for(int i = 0; i < s.length(); i++)
        {
        if(Character.digit(s.charAt(i),10) < 0) return false;
        }
    return true;
    }
    
    /**
     * Function to check whether a string equals an element in an array of string
     */
    public boolean contains(String[] sArray, String s) {
        int length = sArray.length;
        for (int i = 0; i < length; i++) {
            if(sArray[i].equals(s))
                return true;
        }
        return false;
    }

}
