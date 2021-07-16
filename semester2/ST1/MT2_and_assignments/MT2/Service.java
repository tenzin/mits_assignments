
/**
 * Service Class for ST1 Marked tutorial 2
 * It is a model class
 * @author tenzin
 * @version 0.1
 */
public class Service
{
    // instance variables - replace the example below with your own
    private String sCode;
    private String sCodeDesc;
    private double sPrice;
    private String[] sOrbit;
    private String sManned;
    private int sNumOrbit;

    /**
     * Constructor for objects of class Service
     */
    public Service(String code, String codeDesc, double price, String[] orbit, String manned)
    {
        this.sCode = code;
        this.sCodeDesc = codeDesc;
        this.sPrice = price;
        
        //Copy value of String array orbit
        this.sOrbit = new String[orbit.length];
        for (int i = 0; i < orbit.length; i++)
            sOrbit[i] = orbit[i].trim();
        
        this.sManned = manned;
        this.sNumOrbit = sOrbit.length;
    }

    /**
     * Getter Methods
     */
    public String getCode() {return sCode;}
    public String getCodeDesc() {return sCodeDesc;};
    public double getPrice() {return sPrice;}
    public String[] getOrbit() {return sOrbit;}
    public String getManned() {return sManned;}
    public int getNumOrbit() {return sNumOrbit;}
}
