import java.text.NumberFormat;
import java.text.DecimalFormat;

public class displayQuote
{
    // instance variables - replace the example below with your own
    //private int x;
    NumberFormat numberFormat = new DecimalFormat("#,###");

    /**
     * Constructor for objects of class displayQuote
     */
    public displayQuote()
    {

    }
    
    String yn(boolean b)
    {
        if (b) return "Y"; else return "N";
    }
    
    public void display(Quote q)
    {
        //
        // ---------- students ----------- Put your code here 
        //
        //   System.out.printf("------------------------------------------\n");
     
        System.out.println("-------------------------------------------");
        System.out.println("SpaceY Quotation System");
        System.out.println("-------------------------------------------");
     
        System.out.printf("%-24s%s%s\n", "Service code", ": ", q.getCode());
        System.out.printf("%-24s%s%s\n", "Number of Launches", ": ", q.getNum());
        if(q.getOrbit() != null) //Check if we need to display orbit or not
            System.out.printf("%-24s%s%s\n", "Orbit Type", ": ", q.getOrbit());
     
        if(!q.getManned()) {
            System.out.printf("%-24s%s%s\n", "Nitrogen Flush", ": ", yn(q.getNitrogen()));
            System.out.printf("%-24s%s%s\n", "Insurance", ": ", yn(q.getInsurance()));
            if(q.getInsurance())
                System.out.printf("%-24s%s%13s\n", "Payload Value", ":$", numberFormat.format(q.getPayloadVal()));
        }
        
        System.out.println("-------------------------------------------");
        System.out.printf("%-24s%s%13s\n", "Base Price", ":$", numberFormat.format(q.getCost()));
        System.out.printf("%-24s%s%13s\n", "Base Price * Launches",":$", numberFormat.format(q.getCostByNum()));
        
        if(q.getDiscount() > 0) {
            System.out.printf("%-24s%s%13s\n", "Discount 2%", ":$", numberFormat.format(q.getDiscount()));
        }
        
        if(q.getNitrogen()) {
            System.out.printf("%-24s%s%13s\n", "Nitrogen * Launches", ":$", numberFormat.format(q.getCostNitrogen()));
        }
        
        System.out.printf("%-24s%s%13s\n", "Gross Launch Cost", ":$", numberFormat.format(q.getCostGross()));
        
        if(q.getTax() > 0) {
            System.out.printf("%-24s%s%13s\n", "Tax @ 3%", ":$", numberFormat.format(q.getTax()));
        }
        
        System.out.printf("%-24s%s%13s\n", "Nett Launch Cost", ":$", numberFormat.format(q.getCostNett()));
        
        if(q.getCostNesa() > 0)
            System.out.printf("%-24s%s%13s\n", "NESA Tracking", ":$", numberFormat.format(q.getCostNesa()));
        
        if(!q.getManned())
            System.out.printf("%-24s%s%13s\n", "Payload Value", ":$", numberFormat.format(q.getPayloadVal()));
        
        if(q.getInsurance()) {
            System.out.printf("%-24s%s%13s\n", "Insurance", ":$", numberFormat.format(q.getCostInsurance()));
        }
        
        System.out.println("-------------------------------------------");
        System.out.printf("%-24s%s%13s\n", "Total Amount Due", ":$", numberFormat.format(q.getCostInvoice()));
        System.out.println("-------------------------------------------");
     
    }

}
