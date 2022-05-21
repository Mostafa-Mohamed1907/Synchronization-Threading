//package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    JTextField Pumps=new JTextField(50);
    JTextField Clients=new JTextField(50);
    JTextField NamesOfClientsText=new JTextField(50);
    JTextArea Output=new JTextArea(40,50);
    JLabel NumOfPumps=new JLabel("Enter number of pumps : ");
    JLabel NumOfClients=new JLabel("Enter number of clients : ");
    JLabel NamesOfClientsLabel=new JLabel("Enter name of clients : ");
    JButton Input=new JButton("Enter");
    JScrollPane scroll=new JScrollPane(Output,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public GUI(){
        setTitle("PetrolStation");
        setSize(600, 1000);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(NumOfPumps);
        getContentPane().add(Pumps);
        getContentPane().add(NumOfClients);
        getContentPane().add(Clients);
        getContentPane().add(NamesOfClientsLabel);
        getContentPane().add(NamesOfClientsText);
        getContentPane().add(Input);
        getContentPane().add(scroll);
        Input.addActionListener(new action());
    }

    private class action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            Object buttonPressed=e.getSource();
            if(buttonPressed.equals(Input)){
                int numOfPumps=Integer.parseInt(Pumps.getText());
                int NumOfClients=Integer.parseInt(Clients.getText());
                String NameOfCustomers;
                Client []ArrayOfClients=new Client[NumOfClients];

                DynamicGUI guiPumps;
                guiPumps=new DynamicGUI(numOfPumps);
                guiPumps.setVisible(true);
                Pump[] myPumps;


                myPumps=new Pump[numOfPumps];
                Font Size=new Font("Verdana",Font.BOLD,20);
                for (int i = 0; i < myPumps.length; i++) {
                    myPumps[i]=new Pump("Pump " + Integer.toString(i + 1) + ": ",0);
                    guiPumps.myPump.elementAt(i).setFont(Size);
                }



                SemaphoreClass s=new SemaphoreClass(numOfPumps);

                for (int i=0;i < NumOfClients;i++){
                    NameOfCustomers =NamesOfClientsText.getText();
                    String []Names=NameOfCustomers.split("\\s+");
                    ArrayOfClients[i] = new Client(myPumps,Names[i],s,Output,guiPumps);
                }
                for (int i=0;i < NumOfClients;i++){
                   ArrayOfClients[i].start();
                }
            }
        }
    }
}