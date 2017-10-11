
/**
 * Marked tutotial 2 for ST1
 * Main controller class
 *
 * @author R.Cox, tenzin
 * @version 2
 */
import java.util.*;
public class main
{

    ServiceList sl = new ServiceList();
    CustomerList cl = new CustomerList();
    InputQuote iq;
    Quote qu;
    displayQuote dq;
    
    /**
     * Constructor for objects of class main
     */
    public main()
    {
        // uncomment lines below to test ServiceList
        System.out.print("\u000c");
        //System.out.println(sl.toString());
        Scanner in = new Scanner(System.in);
        String s;
        boolean keepGoing = true;
        do {
            iq = new InputQuote();
            dq = new displayQuote();
        
            qu = iq.getQuote(sl, cl);
            qu.computeValues(sl, cl);
            dq.display(qu);
            System.out.println("Do you want to create another Invoice? Y/N:");
            s = in.nextLine();
            if(s.equals("y") || s.equals("Y"))
                keepGoing = true;
            else
                keepGoing = false;
            
        } while(keepGoing);
    }

}
