
/**
 * Class to write HTML output to a file for MT2, ST1
 *
 * @author tenzin
 * @version 1.0
 */
import java.io.*;

public class DisplayHTML
{
    private String[] htmlBody;
    int bodyLineCount;
    private String htmlHeader;
    private String htmlFooter;

    /**
     * Constructor for objects of class DisplayHTML
     */
    public DisplayHTML(String[] input, int counter)
    {
        bodyLineCount = counter;
        this.htmlBody = new String[bodyLineCount];
        for (int i = 0; i < bodyLineCount; i++) {
            htmlBody[i] = input[i];
        }
        
        htmlHeader = "<DOCTYPE=html>\n<html>\n<body>\n<h1>Space Y Quote V2</h2>\n<h1>-------------------------------------</h1>\n<font face=\"Courier New\">";
        htmlFooter = "</font>\n</body>\n</html>";
    }
    

    /**
     * Method to Write HTML file
     */
    public void display()
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("spacey_quotation.html"));
            out.write(htmlHeader);
            for (int i = 0; i < bodyLineCount; i++) {
                out.write("<p>"+htmlBody[i]+"</p>"+"\n");
            }
            out.write(htmlFooter);
            out.close();
            System.out.println("Output Written to **spaceY_quotation.html**\n");
        }
        catch (IOException e) {
            System.out.println("Error Writing HTML File\n");
            System.exit(0);
        }
    }
}
