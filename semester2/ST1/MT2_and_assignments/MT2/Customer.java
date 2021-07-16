
/**
 * Customer Class for ST1 Marked tutorial 2
 * It is a model class
 *
 * @author tenzin
 * @version 0.1
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private String cCode;
    private String cName;
    private String cCompanyName;
    private double cCredit;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String code, String name, String companyName, double credit)
    {
        this.cCode = code;
        this.cName = name;
        this.cCompanyName = companyName;
        this.cCredit = credit;
    }

    /**
     * Getter methods
     */
    public String getCode() {return cCode;}
    public String getName() {return cName;}
    public String getCompanyName() {return cCompanyName;}
    public double getCredit() {return cCredit;}
    
    /**
     * Setter methods
     */
    //public void setCode(String code) {cCode = code;}
    //public void setName(String name) {cName = name;}
    //public void setCompanyName(String companyName) {cCompanyName = companyName;}
    //public void setCredit(double credit) {cCredit = credit;}
}
