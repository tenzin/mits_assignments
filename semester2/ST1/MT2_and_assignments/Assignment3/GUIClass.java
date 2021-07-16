
/**
 * Class to implement Assignment 3 for ST1
 *
 * @author tenzin
 * @version 1
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUIClass extends JFrame
{
    final static int ADD_OPTION = 1;
    final static int SUBTRACT_OPTION = 2;
    final static int INTERLEAVE_OPTION = 3;
    
    int option;
    
    JTextArea textArea;
    JPanel panel;
    JButton button;
    JList list;
    JScrollPane scrollPane1, scrollPane2;
    JComboBox comboBox;
    ButtonGroup buttonGroup;
    JRadioButton radioButtonAdd, radioButtonSubtract, radioButtonInterLeave;
    
    String[] listData = {"10", "20", "30", "50","100","200","300","500","BRAD"};  
    String[] cdata = {"10", "20", "30", "50","100","200","300","500","JANET"};
    

    /**
     * Constructor for objects of class GUIClass
     */
    public GUIClass()
    {
        setSize(460, 380);
        setTitle("ST1 Assignment 3");
        getContentPane().setLayout(null);
        
        option = 0;
        
        DefaultListModel listModel = new DefaultListModel();
        for(int i = 0; i < listData.length; i++)
        {
            listModel.addElement(listData[i]);
        }
        
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(15, 30, 150, 90);
        scrollPane1.setViewportView(list);
        getContentPane().add(scrollPane1);
        
        comboBox = new JComboBox(cdata);
        comboBox.setBounds(10, 140, 160, 30);
        getContentPane().add(comboBox);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane2 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(180, 30, 250, 230);
        scrollPane2.setViewportView(textArea);
        textArea.insert("Answers", textArea.getCaretPosition());
        textArea.setCaretPosition(7);
        getContentPane().add(scrollPane2);
        
        panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.RAISED));
        panel.setBounds(15, 180, 150, 100);
        panel.setLayout(null);
        getContentPane().add(panel);
        
        buttonGroup = new ButtonGroup();
        
        radioButtonAdd = new JRadioButton("Add");
        radioButtonAdd.setBounds(5, 10, 100, 20);
        Add add = new Add();
        radioButtonAdd.addActionListener(add);
        buttonGroup.add(radioButtonAdd);
        panel.add(radioButtonAdd);
        
        radioButtonSubtract = new JRadioButton("Subtract");
        radioButtonSubtract.setBounds(5, 40, 100, 20);
        Subtract subtract = new Subtract();
        radioButtonSubtract.addActionListener(subtract);
        buttonGroup.add(radioButtonSubtract);
        panel.add(radioButtonSubtract);
        
        radioButtonInterLeave = new JRadioButton("Interleave");
        radioButtonInterLeave.setBounds(5, 70, 100, 20);
        InterLeave interLeave = new InterLeave();
        radioButtonInterLeave.addActionListener(interLeave);
        buttonGroup.add(radioButtonInterLeave);
        panel.add(radioButtonInterLeave);
        
        button = new JButton("Do It");
        button.setBounds(10, 290, 150, 40);
        DoIt doit = new DoIt();
        button.addActionListener(doit);
        getContentPane().add(button);
        
        setVisible(true);
    }
    
    //Class to implement Do It button action
    public class DoIt implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(option == ADD_OPTION)
            {
                try
                {
                    float i = Float.parseFloat((String)list.getSelectedValue());
                    double j = Float.parseFloat((String)comboBox.getSelectedItem());
                    String answer = String.format("\n%.2f + %.2f = %.2f", i, j, i + j);
                    textArea.insert(answer, textArea.getCaretPosition());
                }
                catch (Exception exception)
                {
                    textArea.insert("\nInvalid Selection", textArea.getCaretPosition());
                }
            }
            else if(option == SUBTRACT_OPTION)
            {
                try
                {
                    float i = Float.parseFloat((String)list.getSelectedValue());
                    double j = Float.parseFloat((String)comboBox.getSelectedItem());
                    String answer = String.format("\n%.2f - %.2f = %.2f", i, j, i - j);
                    textArea.insert(answer, textArea.getCaretPosition());
                }
                catch (Exception exception)
                {
                    textArea.insert("\nInvalid Selection", textArea.getCaretPosition());
                }
            }
            else if(option == INTERLEAVE_OPTION)
            {
                String s1 = list.getSelectedValue().toString();
                String s2 = comboBox.getSelectedItem().toString();
                int char_length = s1.length() + s2.length();
                //System.out.println("Strings " + s1 + " " + s2 + " char length " + char_length);
                //Construct interleaved array of characters.
                char[] interleave = new char[char_length];
                int current_char = 0;
                for(int i = 0; i < s1.length(); i++)
                {
                    //System.out.println("current char index " + current_char);
                    interleave[current_char] = s1.charAt(i);
                    if(i >= s2.length())
                    {
                        current_char++;
                    }
                    else
                    {
                        current_char += 2;
                    }
                }
                
                current_char = 1;
                for(int i = 0; i < s2.length(); i++)
                {
                    //System.out.println("current char index " + current_char);
                    interleave[current_char] = s2.charAt(i);
                    if(i + 1 >= s1.length())
                    {
                        current_char++;
                    }
                    else
                    {
                        current_char += 2;
                    }
                }
                
                textArea.insert(String.format("\nInterleave = %s", new String(interleave)), textArea.getCaretPosition());
                
            }
            
            
        }
        
    }
    
    //ActionListener for Add RadioButton
    public class Add implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            option = ADD_OPTION;
        }
        
    }
    
    //ActionListener for Subtract RadioButton
    public class Subtract implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            option = SUBTRACT_OPTION;
        }
        
    }
    
    //ActionListener for InterLeave RadioButton
    public class InterLeave implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            option = INTERLEAVE_OPTION;
        }
        
    }
}
