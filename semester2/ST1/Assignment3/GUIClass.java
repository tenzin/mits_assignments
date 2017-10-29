
/**
 * Class to implement Assignment 3 for ST1
 *
 * @author tenzin
 * @version 1
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIClass extends JFrame
{
    JLabel jLabelFirstNum;
    JLabel jLabelSecondNum;
    JLabel jLabelLine;
    
    JButton jButtonDoIt;
    
    JList list;
    JScrollPane scrollPane;
    JComboBox combo;
    
    String[] listData = {"10", "20", "30", "50","100","200","300","500","BRAD"};  
    

    /**
     * Constructor for objects of class GUIClass
     */
    public GUIClass()
    {
        setSize(450, 200);
        setTitle("ST1 Assignment 3");
        getContentPane().setLayout(null);
        
        DefaultListModel listModel = new DefaultListModel();
        for(int i = 0; i < listData.length; i++)
        {
            listModel.addElement(listData[i]);
        }
        
        list = new JList(listModel);
        list.setBounds(5, 5, 20, 40);
        
        getContentPane().add(list);
        
        setVisible(true);
    }
    
    //Class to implement Addition
    public class AddEvent implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
        
    }
}
