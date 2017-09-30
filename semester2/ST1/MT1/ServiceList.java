
/**
 * Write a description of class ServiceList here.
 *
 * @author Rob Cox
 * @version 13/9/2017
 */
public class ServiceList
{

    /* 
     * For interested students this structure of storing information in 
     * several arrays with a common index is called using paralell arrays
     */
    String[] sCode = {"ORB1","ORBH","ISS5","ILLOYDS","DLAB","LEOM7","MOON2"};
    
    String[] sCodeDesc = {
        "ORB1 - One Satelite To Orbit",
        "ORBH - One large Satelite to Orbit",
        "ISS5 - 5 Tonne ISS Supply Run",
        "ILLOYDS - Insurance",
        "DLAB - Drako lab LEO",
        "LEOM7 - Drako tourist experience",
        "MOON2 - Ultimate Holiday"
        };
        
    double[] sCodeCost = {62000000,90000000,76400000,97100,112000000,140000000,-1};
    
    
    /**
     * Constructor for objects of class ServiceList
     */
    public ServiceList()
    {
   
    }

    /**
     * Returns true if serviceCode is a valid service code
     *
     */
    private int findIndex(String serviceCode)
    {
        int index=-1;
        for (int i=0;i<sCode.length;i++)
        {
            if (serviceCode.compareTo(sCode[i])==0){index=i;break;}
        }
        return index;
    }  
    
    /**
     * Returns true if serviceCode is a valid service code
     *
     */
    public boolean validateServiceCode(String serviceCode)
    {
        if (findIndex(serviceCode)!=-1) return true;
        return false;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     */
    public double getPrice(String serviceCode)
    {
        if (findIndex(serviceCode)!=-1) 
        {
            return sCodeCost[findIndex(serviceCode)];
        }
        return 0;
    }
    
     /**
     * Returns true if the service is a manned spaceflight
     *
     */
    public boolean isManned(String serviceCode)
    {
 
         if (serviceCode.compareTo("DLAB")==0) return true; 
         if (serviceCode.compareTo("LEOM7")==0) return true; 
         if (serviceCode.compareTo("MOON2")==0) return true; 
        return false;
    }
    
    /**
     * returns true if the service code offers a choice of orbits
     */
    public boolean choiceOfOrbit(String serviceCode)
    {
        if (serviceCode.compareTo("ISS5")==0) return false; 
        if (serviceCode.compareTo("LEOM7")==0) return false; 
        return true;
    }
    
    /**
     * returns true if the service code offers a choice of nitrogen flush
     */
    public boolean choiceNitrogen(String serviceCode)
    {
        if (serviceCode.compareTo("ORB1")==0) return true; 
        if (serviceCode.compareTo("ORBH")==0) return true; 
        if (serviceCode.compareTo("ISS5")==0) return true; 
        return false;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     */
    public String getDescription(String serviceCode)
    {
        int index = findIndex(serviceCode);
        if (index != -1) 
        {
            return sCodeDesc[index];
        }
        return "";
    }
    
    
    /*
     * A standard to string for use as a test routine
     */
    public String toString()
    {
        String retv="";
        for (int i=0;i<sCode.length;i++)
        {
            String temp = String.format( "%,.2f", sCodeCost[i] );
            retv = retv + sCode[i] +" "+ temp +" "+sCodeDesc[i] +"\n";
        }
        return retv;
    }
}
