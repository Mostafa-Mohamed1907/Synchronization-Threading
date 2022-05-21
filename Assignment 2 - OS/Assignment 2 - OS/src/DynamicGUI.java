//package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class DynamicGUI extends JFrame{
    Vector<JTextArea> myPump;
    DynamicGUI(int numberOfPumps){
        setTitle("** My Pumps **");
        setSize(600,800);
        getContentPane().setLayout(new FlowLayout());
        myPump = new Vector<>(numberOfPumps);
        for(int i = 0;i<numberOfPumps;i++){
            ImageIcon ii=new ImageIcon("Pump.jpg");
            JLabel label=new JLabel(ii);
            myPump.add(new JTextArea(10,10));
            getContentPane().add(label);
            myPump.elementAt(i).setBackground(Color.yellow);
            getContentPane().add(myPump.elementAt(i));
        }
        getContentPane().setBackground(Color.white);
    }
}
