
/**
 * Main controller class for assignment 2 ST1
 *
 * @author tenzin
 * @version 1
 */
import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        String choice;
        String keepgoing;
        String inputFile;
        String outputFile;
        boolean correctInput = true;
        Scanner in = new Scanner(System.in);
        OrderList orderList;
        
        do
        {
            System.out.print("\u000c");
            System.out.println("      SpaceY Monthly Plan      ");
            System.out.println("      ===================      ");
            System.out.println();
            System.out.println("1 - Produce an Order Plan");
            System.out.println("2 - Produce an Action Plan");
            System.out.println("3 - Produce an Action Plan to a File");
            System.out.println("4 - Exit");
            System.out.println("Enter your choice: ");
            //Ensure user inputs correct choice.
            //If choice is wrong, keep asking
            do
            {
                choice = in.nextLine().trim();
                if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4"))
                {
                    //Input is correct
                    correctInput = true;
                }
                else
                {
                    //input not correct
                    System.out.println("Incorrect Choice. Enter your choice: ");
                    correctInput = false;
                }
            } while (!correctInput);
            
            //Now that choice is correct, process:
            switch(choice)
            {
                case "1" :
                    System.out.println("Enter the month input filename: ");
                    inputFile = in.nextLine().trim();
                    orderList = new OrderList(inputFile);
                    orderList.createOrderPlan();
                    break;
                
                case "2" :
                    System.out.println("Enter the month input filename: ");
                    inputFile = in.nextLine().trim();
                    orderList = new OrderList(inputFile);
                    orderList.createActionPlan(false, null);
                    break;
                
                case "3" : 
                    System.out.println("Enter the month input filename: ");
                    inputFile = in.nextLine().trim();
                    orderList = new OrderList(inputFile);
                    System.out.println("Enter the month output action plan filename: ");
                    outputFile = in.nextLine().trim();
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
                        orderList.createActionPlan(true, out);
                        out.close();
                    }
                    catch (IOException e) {
                        System.out.println("Error Writing HTML File\n");
                        System.exit(0);
                    }
                    
                    break;
            }
            
            //Ask if user wants to process another order file
            System.out.println("\nDo you wish to process another Order File Y/N: ");
            keepgoing = in.nextLine().trim().toUpperCase();
        } while(keepgoing.equals("Y"));
    }
}
