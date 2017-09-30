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
    
    public Quote getQuote(ServiceList sl)
    {
        System.out.print("\u000c");
        System.out.print("       SpaceY Quotation System\n");
        System.out.print("       =======================\n\n");
        
        q=new Quote();
        
        // first enter service code
        System.out.print("Enter Service Code:");
        s = in.nextLine();
        while(!sl.validateServiceCode(s.trim())) {
            System.out.println("Error: Invalid Code");
            System.out.print("Enter Service Code:");
            s = in.nextLine();
        }
        q.setCode(s.trim());
        
        //Check if the service code is MOON2 and ask the CEO for the Base cost
        if(q.getCode().equals("MOON2")) {
            System.out.print("Hi CEO, Kindly Enter Base Cost for MOON2:");
            s = in.nextLine();
            while(!isPositiveInteger(s)) {
                System.out.println("Invalid Base Cost Value.");
                System.out.print("CEO, Enter Base Cost for MOON2:");
                s = in.nextLine();
            }
            q.setCost(Double.parseDouble(s));
        }
        
        // Checked whether service is manned or not
        q.setManned(sl.isManned(q.getCode()));
        
        // now num of launches
        // Input needs to be a positive integer between 1 and 15
        boolean correctNumberOfLaunches = false;
        System.out.print("Enter Number of Launches (1-15):");
        s = in.nextLine();
        while(!correctNumberOfLaunches) {
            if(!isPositiveInteger(s)) {
                System.out.println("Error: Invalid Number of Launches");
                System.out.print("Enter Number of Launches (1-15):");
                s = in.nextLine();
            }
            else {
                if(Integer.parseInt(s) > 15 || Integer.parseInt(s) == 0) {
                    System.out.println("Error: Invalid Number of Launches");
                    System.out.print("Enter Number of Launches (1-15):");
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
            System.out.print("Enter Choice of Orbit LEO/GTO:");
            s = in.nextLine();
            s = s.toUpperCase().trim();
            // Check if the input string is valid choice of orbit or not
            while(!(s.equals("LEO") || s.equals("GTO"))) {
                System.out.println("Error in Choice of Orbit.");
                System.out.print("Enter Choice of Orbit LEO/GTO:");
                s = in.nextLine();
                s = s.toUpperCase().trim();
            }
            q.setOrbit(s);
        }
        else q.setOrbit(null); // set orbit to null if there is no choice of orbit
        
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
        // enter insurance question - Assignment document says rules for insurance are same as notrogen flush
        //This means if Nitrogen flush is a choice, so is choice for insurance.
        if(sl.choiceNitrogen(q.getCode())) {
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
        
        // enter value of satelite
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

}
