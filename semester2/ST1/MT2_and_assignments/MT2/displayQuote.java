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
    String[] quoteDisplayText;
    int displayTextCounter;
    DisplayHTML writeHtml;

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
        quoteDisplayText = new String[50];
        displayTextCounter = 0;
        
        /**
         * Get all the strings to be printed in a string array which can then be used to print on terminal,
         * UCcanvas and HTML file.
         */
      
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s%s%s", "Client", ":", q.getCustId(), " - ", q.getCustname());
        displayTextCounter++;
        
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s", "Service code", ":", q.getCode());
        displayTextCounter++;
        
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s", "Number of Launches", ":", q.getNum());
        displayTextCounter++;
        
        if(q.getOrbit() != null) { //Check if we need to display orbit or not
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s", "Orbit Type", ":", q.getOrbit());
            displayTextCounter++;
        }
     
        if(!q.getManned()) {
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s", "Nitrogen Flush", ":", yn(q.getNitrogen()));
            displayTextCounter++;
            
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s", "Insurance", ":", yn(q.getInsurance()));
            displayTextCounter++;
            
            if(q.getInsurance()) {
                quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Payload Value", ":$", numberFormat.format(q.getPayloadVal()));
                displayTextCounter++;
            }
        }
        
        //Credit check text display
        Date dd=new Date();
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s", "Credit Checked", ":", df.format(dd));
        displayTextCounter++;

        if(q.getCredit() != null && !q.getCredit().isEmpty()) {
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%s", "**WARNING**", ":", q.getCredit());
            displayTextCounter++;
        }
        
        quoteDisplayText[displayTextCounter] = "-------------------------------------";
        displayTextCounter++;
        
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Base Price", ":$", numberFormat.format(q.getCost()));
        displayTextCounter++;
        
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Base Price * Launches",":$", numberFormat.format(q.getCostByNum()));
        displayTextCounter++;
        
        if(q.getNitrogen()) {
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Nitrogen * Launches", ":$", numberFormat.format(q.getCostNitrogen()));
            displayTextCounter++;
        }
        
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Gross Launch Cost", ":$", numberFormat.format(q.getCostGross()));
        displayTextCounter++;
        
        if(q.getTax() > 0) {
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Tax @ 3%", ":$", numberFormat.format(q.getTax()));
            displayTextCounter++;
        }
        
        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Nett Launch Cost", ":$", numberFormat.format(q.getCostNett()));
        displayTextCounter++;
        
        if(q.getCostNesa() > 0) {
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "NESA Tracking", ":$", numberFormat.format(q.getCostNesa()));
            displayTextCounter++;
        }
        
        if(!q.getManned() && q.getInsurance()) {
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Payload Value", ":$", numberFormat.format(q.getPayloadVal()));
            displayTextCounter++;
        }
        
        if(q.getInsurance()) {
            quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Insurance", ":$", numberFormat.format(q.getCostInsurance()));
            displayTextCounter++;
        }
        
        quoteDisplayText[displayTextCounter] = "-------------------------------------";
        displayTextCounter++;

        quoteDisplayText[displayTextCounter] = String.format("%-24s%s%12s", "Total Amount Due", ":$", numberFormat.format(q.getCostInvoice()));
        displayTextCounter++;
        
        //Display output on Terminal
        displayTerminal();
        
        //Display output to canvas
        displayCanvas();
        
        //Ask if user wants to output to HTML file
        Scanner in = new Scanner(System.in);
        String s;
        System.out.println("Do you wish to create the HTML Quote? Y/N:");
        s = in.nextLine();
        if(s.equals("Y") || s.equals("y")) {
            writeHtml = new DisplayHTML(quoteDisplayText, displayTextCounter);
            writeHtml.display();
        }
     
    }
    
    /**
     * Function to display on Terminal
     */
    public void displayTerminal() {
        System.out.println("-------------------------------------------");
        System.out.println("SpaceY Quotation System Version 2");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < displayTextCounter; i++) {
            System.out.println(quoteDisplayText[i]);
        }
        System.out.println("-------------------------------------------");
    }
    
    /**
     * Function to display on UCCanvas
     */
    public void displayCanvas() {
        int lineCounter;
        UCCanvas can = new UCCanvas("SpaceY Quote", 500, 650);
        can.setForegroundColor(new Color(2, 89, 82));
        can.fillRectangle(0,0,500,600);
        can.setForegroundColor(Color.WHITE);
        can.fillRectangle(20,20,500-40,600-40);
        Font font = new Font("Courier New", Font.BOLD, 14);
        can.setFont(font);
        can.setForegroundColor(new Color(40, 89, 168));
        can.drawString("SPACEY QUOTATION SYSTEM VERSION 2", 130, 50);
        can.drawString("-------------------------------", 130, 60);
        lineCounter = 100;
        for (int i = 0; i < displayTextCounter; i++) {
            can.drawString(quoteDisplayText[i], 75, lineCounter);
            lineCounter += 20;
        }
    }

}
