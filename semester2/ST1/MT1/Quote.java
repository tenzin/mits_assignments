import java.lang.Math;
/**
 * Write a description of class Quote here.
 *
 * @author RC
 * @version 12/9/2017
 */
public class Quote
{
    
    private String sCode; // service code 
    private String sOrbit; // orbit (LEO, GTO)
    private int    sNum; // number of services eg 2=2 satelites
    private double sPayloadVal; // value of payload (for insurance purposes)
    private boolean sNitrogen; // true = nitrogen flush false = no nitrogen flush
    private boolean sInsurance; // true = insurance false = no insurance
    private boolean sManned; // true if its manned spaceflight
     
    private double sCost; // service base cost
    private double sCostByNum; // cost*num
    private String sDescription; // one line description of service
    private double sDiscount; // discount
    private double sCostNitrogen; // cost of nitrogen flush
    private double sCostGross; // gross cost intermediate value
    private double sTax; // 3% tax
    private double sCostNett; // Nett cost amount - Gross + Tax
    private int    sNearestMillion; // nearest million value rounded up for insurance calculation
    private double sCostInsurance; // the cost of insurance
    private double sCostNesa; // Extra for GTO tracking
    private double sCostInvoice; // Nett invoice amount (total)
    
    // Declare some constancts for Tax Rate and NESA Tracking
    public static final double DISCOUNT_PERCENTAGE = 0.02;
    public static final double TAX_RATE = 0.03;
    public static final double NESA_FEE_RATE = 0.004;
    public static final double NITROGEN_FLUSH_RATE = 180000;

    /**
     * Constructor for objects of class Quote
     */
    public Quote()
    {
    }
    
    public String getCode() {return sCode;} // service code 
    public String getOrbit() {return sOrbit;} // orbit (LEO, GTO)
    public int    getNum() {return sNum;} // number of services eg 2=2 satelites
    public double getPayloadVal() {return sPayloadVal;} // value of payload (for insurance purposes)
    public boolean getNitrogen() {return sNitrogen;} // true = nitrogen flush false = no nitrogen flush
    public boolean getInsurance() {return sInsurance;}
    public boolean getManned() {return sManned;} // true if its manned spaceflight
     
    public double getCost() {return sCost;} // service base cost
    public double getDiscount() {return sDiscount;} // discount
    public double getCostNitrogen() {return sCostNitrogen;} // cost of nitrogen flush
    public double getCostGross() {return sCostGross;} // gross cost intermediate value
    public double getTax() {return sTax;} // 3% tax
    public int    getNearestMillion() {return  sNearestMillion;} // nearest million value rounded up for insurance calculation
    public double getCostInsurance() {return sCostInsurance;} // the cost of insurance
    public double getCostNesa() {return sCostNesa;} // Extra for GTO tracking
    public double getCostNett() {return sCostNett; } // Nett invoice amount (total)
    public double getCostInvoice() {return sCostInvoice; } // Nett invoice amount (total)
    public double getCostByNum() {return sCostByNum; } // Nett invoice amount (total)
    public String getDescription() {return sDescription; } // Nett invoice amount (total)
    
    
    
    
    public void setCode(String code) {sCode=code;} // service code 
    public void setOrbit(String orbit) {sOrbit=orbit;} // orbit (LEO, GTO)
    public void setNum(int num) {sNum=num;} // number of services eg 2=2 satelites
    public void setPayloadVal(double payloadVal) {sPayloadVal=payloadVal;} // value of payload (for insurance purposes)
    public void setNitrogen(boolean nitrogen) {sNitrogen=nitrogen;} // true = nitrogen flush false = no nitrogen flush
    public void setInsurance(boolean insurance) {sInsurance=insurance;}
    public void setManned(boolean manned) {sManned=manned;} // true if its manned spaceflight
    //public method to set the cost for MOON2 from CEO
    public void setCost(double cost) {sCost = cost;}
     
    public void computeValues(ServiceList sl)
    {
        // ---------- students ----------- Put your code here 
        //    
        //final cost should be stored in sCostInvoice 
        //
        
        //Calculate base cost or base price. If it is MOON, its already calculated
        if(!sCode.equals("MOON2"))
            sCost = sl.getPrice(sCode);
        
        //Calcualte Cost by number of launch
        sCostByNum = sCost * sNum;
        
        //Set description
        sDescription = sl.getDescription(sCode);
        
        //Calculate Discount
        if(!sManned && (sNum >= 4)) { //
            sDiscount = DISCOUNT_PERCENTAGE * sCostByNum;
        }
        else {
            sDiscount = 0;
        }
        
        //Calculate nitrogen cost if applicable
        if(sNitrogen) {
            sCostNitrogen = NITROGEN_FLUSH_RATE * sNum;
        }
        else {
            sCostNitrogen = 0;
        }
        
        //Calculate Gross cost
        sCostGross = sCostByNum + sCostNitrogen - sDiscount;
        
        //Calculate 3% tax only on unmanned
        if(!sManned)
            sTax = TAX_RATE * sCostGross;
        else
            sTax = 0;
        
        //Nett launch cost
        sCostNett = sCostGross + sTax;
        
        //Calculate NESA tracking cost
        if((sOrbit != null) && sOrbit.equals("GTO"))
            sCostNesa = NESA_FEE_RATE * sCostGross;
        else
            sCostNesa = 0;
        
        //Calculate insurance
        if(sInsurance) {
            sNearestMillion = (int) Math.round(sPayloadVal / 1000000d);
            //System.out.println("****insurance near million = " + sNearestMillion);
            sCostInsurance = sl.getPrice("ILLOYDS") * sNearestMillion; //We can use sl.getCost("ILLOYDS") to get the insurance price
        }
        else {
            sCostInsurance = 0;
        }
        
        //Finally Calculate the final invoice cost
        sCostInvoice = sCostNett + sCostNesa + sCostInsurance;
    
     }

}
