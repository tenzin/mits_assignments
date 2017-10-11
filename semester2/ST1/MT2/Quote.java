import java.lang.Math;
import java.text.*;
import java.util.*;

/**
 * Write a description of class Quote here.
 *
 * @author RC
 * @version 12/9/2017
 */
public class Quote
{
    
    private String sCode; // service code 
    private String sCustId; // customer Id 
    private String sCustname; // customername 
    private String sOrbit; // orbit (LEO, GTO, CSO)
    private String sCredit; // credit check text
    private int    sNum; // number of services eg 2=2 satelites
    private double sPayloadVal; // value of payload (for insurance purposes)
    private boolean sNitrogen; // true = nitrogen flush false = no nitrogen flush
    private boolean sManned; // true if its manned spaceflight
    private boolean sInsurance;
     
    private double sCost; // service base cost
    private double sCostByNum; // cost*num
    private String sDescription; // one line description of service
    private double sCostNitrogen; // cost of nitrogen flush
    private double sCostGross; // gross cost intermediate value
    private double sTax; // 3% tax
    private int    sNearestMillion; // nearest million value rounded up for insurance calculation
    private double sCostInsurance; // the cost of insurance
    private double sCostNesa; // Extra for GTO tracking
    private double sCostNett; // Nett invoice amount (total)
    private double sCostInvoice; // Nett invoice amount (total)
    
    // Declare some constancts for Tax Rate and NESA Tracking
    public static final double TAX_RATE = 0.03;
    public static final double NESA_FEE_GTO = 0.004;
    public static final double NESA_FEE_CSO = 0.006;
    
    

    /**
     * Constructor for objects of class Quote
     */
    public Quote()
    {
        //Calcualte 
    }
    
    public String getCode() {return sCode;} // service code 
    public String getOrbit() {return sOrbit;} // orbit (LEO, GTO)
    public int    getNum() {return sNum;} // number of services eg 2=2 satelites
    public double getPayloadVal() {return sPayloadVal;} // value of payload (for insurance purposes)
    public boolean getNitrogen() {return sNitrogen;} // true = nitrogen flush false = no nitrogen flush
    public boolean getManned() {return sManned;} // true if its manned spaceflight
    public boolean getInsurance() {return sInsurance;}
     
    public double getCost() {return sCost;} // service base cost
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
    public String getCustname(){return sCustname;} 
    public String getCustId(){return sCustId;} // customer Id 
    public String getCredit(){return sCredit;} // credit
    
    
    
    public void setCode(String code) {sCode=code;} // service code 
    public void setOrbit(String orbit) {sOrbit=orbit;} // orbit (LEO, GTO)
    public void setNum(int num) {sNum=num;} // number of services eg 2=2 satelites
    public void setPayloadVal(double payloadVal) {sPayloadVal=payloadVal;} // value of payload (for insurance purposes)
    public void setNitrogen(boolean nitrogen) {sNitrogen=nitrogen;} // true = nitrogen flush false = no nitrogen flush
    public void setManned(boolean manned) {sManned=manned;} // true if its manned spaceflight
    public void setCustname(String custName){sCustname=custName;}// 
    public void setCustId(String custId){sCustId=custId;} // customer Id 
    public void setCredit(String credit){sCredit=credit;} // credit text
    public void setInsurance(boolean insurance) {sInsurance = insurance;}
    
    
    public void computeValues(ServiceList sl, CustomerList cl) {
        //Calculate base cost;
        sCost = sl.getPrice(sCode);
        
        //Calculate Cost by num
        sCostByNum = sCost * sNum;
        
        //Calculate nitrogen cost if applicable
        if(getNitrogen()) {sCostNitrogen = sl.getCostPerNitrogenFlush() * sNum;}
        else {sCostNitrogen = 0;}
        
        //Calcualte sCostGross - Gross Cost
        sCostGross = sCostByNum + sCostNitrogen;
        
        //Calculate Tax - 3 % only on unmanned
        if(!sManned) {sTax = sCostGross * TAX_RATE;}
        else {sTax = 0;}
        
        //Calcualte sCostNett
        sCostNett = sCostGross + sTax;
        
        //Calculate NESA tracking cost
        if(sOrbit.equals("GTO")) {
            sCostNesa = NESA_FEE_GTO * sCostGross;
        }
        else {
            if(sOrbit.equals("CSO")) {
                sCostNesa = NESA_FEE_CSO * sCostGross;
            }
            else sCostNesa = 0;
        }
            
        //Calcualte insurance if applicable
        if(sInsurance) {
            sNearestMillion = (int) Math.ceil(sPayloadVal / 1000000d); //calculate to the upper million value
            //System.out.println("****insurance near million = " + sNearestMillion);
            sCostInsurance = sl.getCostOfInsurance() * sNearestMillion;
        }
        else {
            sCostInsurance = 0;
        }
        
        //Finally Calculate the final invoice cost
        sCostInvoice = sCostNett + sCostNesa + sCostInsurance;
        
        //Now do the credit check
        if(sCostInvoice > cl.getCredit(sCustId)) {sCredit = "WARNING CLIENT HAS EXCEEDED CREDIT LIMIT";}
        else {sCredit = null;}
        
    }

}
