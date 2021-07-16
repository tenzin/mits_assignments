
/**
 * Class to implement Assignment 1 for ST1
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
    JLabel jLabelResult;
    
    JTextField jTextFieldFirstNum;
    JTextField jTextFieldSecondNum;
    
    JButton jButtonAdd;
    JButton jButtonSubtract;
    JButton jButtonConcat;


    /**
     * Constructor for objects of class GUIClass
     */
    public GUIClass()
    {
        setSize(450, 200);
        setTitle("ST1 Assignment 1");
        
        getContentPane().setLayout(null);
        
        jLabelFirstNum = new JLabel("First Number");
        jLabelFirstNum.setBounds(40, 40, 100, 15);
        getContentPane().add(jLabelFirstNum);
        
        jLabelSecondNum = new JLabel("Second Number");
        jLabelSecondNum.setBounds(40, 80, 100, 15);
        getContentPane().add(jLabelSecondNum);
        
        jTextFieldFirstNum = new JTextField();
        jTextFieldFirstNum.setBounds(150, 30, 120, 30);
        getContentPane().add(jTextFieldFirstNum);
        
        jTextFieldSecondNum = new JTextField();
        jTextFieldSecondNum.setBounds(150, 70, 120, 30);
        getContentPane().add(jTextFieldSecondNum);
        
        jLabelLine = new JLabel("============");
        jLabelLine.setBounds(150, 110, 120, 10);
        getContentPane().add(jLabelLine);
        
        jLabelResult = new JLabel("0");
        jLabelResult.setBounds(160, 130, 120, 15);
        getContentPane().add(jLabelResult);
        
        jButtonAdd = new JButton("Add");
        jButtonAdd.setBounds(290, 30, 110, 30);
        getContentPane().add(jButtonAdd);
        AddEvent add = new AddEvent();
        jButtonAdd.addActionListener(add);
        
        jButtonSubtract = new JButton("Subtract");
        jButtonSubtract.setBounds(290, 70, 110, 30);
        getContentPane().add(jButtonSubtract);
        SubtractEvent subtract = new SubtractEvent();
        jButtonSubtract.addActionListener(subtract);
        
        jButtonConcat = new JButton("Concatenate");
        jButtonConcat.setBounds(290, 110, 110, 30);
        getContentPane().add(jButtonConcat);
        ConcatEvent concat = new ConcatEvent();
        jButtonConcat.addActionListener(concat);
        
        setVisible(true);
    }
    
    //Class to implement Addition
    public class AddEvent implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double i, j, sum;
            try
            {
                i = Double.parseDouble(jTextFieldFirstNum.getText().trim());
                j = Double.parseDouble(jTextFieldSecondNum.getText().trim());
                sum = i + j;
                jLabelResult.setText(" " + sum);
            }
            catch (Exception ex)
            {
                jLabelResult.setText("Incorrect Input");
            }
        }
        
    }
    
    //Class to implement subtraction
    public class SubtractEvent implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double i, j, diff;
            try
            {
                i = Double.parseDouble(jTextFieldFirstNum.getText().trim());
                j = Double.parseDouble(jTextFieldSecondNum.getText().trim());
                diff = i - j;
                jLabelResult.setText(" " + diff);
            }
            catch (Exception ex)
            {
                jLabelResult.setText("Incorrect Input");
            }
        }
        
    }
    
    //Class to implement concatenation
    public class ConcatEvent implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String s1 = jTextFieldFirstNum.getText().trim();
            String s2 = jTextFieldSecondNum.getText().trim();
            jLabelResult.setText(s1+s2);
        }
        
    }
}
