import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.awt.Font;
import java.awt.Color;
import java.text.*;
import java.util.*;


public class displayQuote
{
    // instance variables - replace the example below with your own
    //private int x;

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
        
        NumberFormat numberFormat = new DecimalFormat("#,###"); //To format the output numbers with commas
        
        String temp;
        int lineCounter;
        //Initialize UCCanvas to do output on canpus
        UCCanvas can = new UCCanvas("SpaceY Quote", 500, 650);
        can.setForegroundColor(new Color(2, 89, 82));
        can.fillRectangle(0,0,500,600);
        can.setForegroundColor(Color.WHITE);
        can.fillRectangle(20,20,500-40,600-40);
        Font font = new Font("Courier New", Font.BOLD, 14);
        can.setFont(font);
        can.setForegroundColor(new Color(40, 89, 168));
        can.drawString("SPACEY QUOTE", 180, 50);
        can.drawString("-------------", 180, 60);
        lineCounter = 100;
     
        System.out.println("-------------------------------------------");
        System.out.println("SpaceY Quotation System");
        System.out.println("-------------------------------------------");
        
        temp = String.format("%-24s%s%s%s%s", "Client", ":", q.getCustId(), " - ", q.getCustname());
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        temp = String.format("%-24s%s%s", "Service code", ":", q.getCode());
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        temp = String.format("%-24s%s%s", "Number of Launches", ":", q.getNum());
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        if(q.getOrbit() != null) { //Check if we need to display orbit or not
            temp = String.format("%-24s%s%s", "Orbit Type", ":", q.getOrbit());
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
        }
     
        if(!q.getManned()) {
            temp = String.format("%-24s%s%s", "Nitrogen Flush", ":", yn(q.getNitrogen()));
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
            
            temp = String.format("%-24s%s%s", "Insurance", ":", yn(q.getInsurance()));
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
            
            if(q.getInsurance()) {
                temp = String.format("%-24s%s%12s", "Payload Value", ":$", numberFormat.format(q.getPayloadVal()));
                System.out.println(temp);
                can.drawString(temp, 75, lineCounter);
                lineCounter += 20;
            }
        }
        
        //Credit check text display
        Date dd=new Date();
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        temp = String.format("%-24s%s%s", "Credit Checked", ":", df.format(dd));
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        if(q.getCredit() != null && !q.getCredit().isEmpty()) {
            temp = String.format("%-24s%s%s", "****************", ":", q.getCredit());
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
        }
        
        System.out.println("-------------------------------------------");
        can.drawString("---------------------------", 150, lineCounter);
        lineCounter += 20;
        
        temp = String.format("%-24s%s%12s", "Base Price", ":$", numberFormat.format(q.getCost()));
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        temp = String.format("%-24s%s%12s", "Base Price * Launches",":$", numberFormat.format(q.getCostByNum()));
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        if(q.getNitrogen()) {
            temp = String.format("%-24s%s%12s", "Nitrogen * Launches", ":$", numberFormat.format(q.getCostNitrogen()));
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
        }
        
        temp = String.format("%-24s%s%12s", "Gross Launch Cost", ":$", numberFormat.format(q.getCostGross()));
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        if(q.getTax() > 0) {
            temp = String.format("%-24s%s%12s", "Tax @ 3%", ":$", numberFormat.format(q.getTax()));
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
        }
        
        temp = String.format("%-24s%s%12s", "Nett Launch Cost", ":$", numberFormat.format(q.getCostNett()));
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        if(q.getCostNesa() > 0) {
            temp = String.format("%-24s%s%12s", "NESA Tracking", ":$", numberFormat.format(q.getCostNesa()));
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
        }
        
        if(!q.getManned() && q.getInsurance()) {
            temp = String.format("%-24s%s%12s", "Payload Value", ":$", numberFormat.format(q.getPayloadVal()));
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
        }
        
        if(q.getInsurance()) {
            temp = String.format("%-24s%s%12s", "Insurance", ":$", numberFormat.format(q.getCostInsurance()));
            System.out.println(temp);
            can.drawString(temp, 75, lineCounter);
            lineCounter += 20;
        }
        
        System.out.println("-------------------------------------------");
        can.drawString("---------------------------", 150, lineCounter);
        lineCounter += 20;
        
        temp = String.format("%-24s%s%12s", "Total Amount Due", ":$", numberFormat.format(q.getCostInvoice()));
        System.out.println(temp);
        can.drawString(temp, 75, lineCounter);
        lineCounter += 20;
        
        System.out.println("-------------------------------------------");
     
    }

}
