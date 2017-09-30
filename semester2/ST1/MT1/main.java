
/**
 * Write a description of class main here.
 *
 * @author R.Cox
 * @version 12/9/2017
 */
import java.util.*;
public class main
{

    ServiceList sl = new ServiceList();
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
        
            qu = iq.getQuote(sl);
            qu.computeValues(sl);
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
