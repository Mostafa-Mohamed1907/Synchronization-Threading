//package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Client extends Thread{
	private String name;
	Pump []Pumps;
	SemaphoreClass s;
	JTextArea guiConsole;
	DynamicGUI guiPumps;
	Random rand = new Random();
	int random = rand.nextInt(3000) + 1000 ;
	
	Client(Pump []P , String name ,SemaphoreClass s ,JTextArea guiConsole , DynamicGUI guiPumps){
		this.Pumps = P;
		this.name = name;
		this.s= s;
		this.guiConsole = guiConsole;
		this.guiPumps = guiPumps;
	}
	
	public void Arrived() {
        guiConsole.append(name + " has arrived \n");
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	public void Occupy(int i) {
		guiPumps.myPump.elementAt(i).setBackground(Color.red);
        guiPumps.myPump.elementAt(i).setText("Pump " + Integer.toString(i + 1) +"\n"+ name+ "\n"+" on work \n");
        guiConsole.append(Pumps[i].getName() + name + " is being occupied  \n");
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	public void getServed(int i) {
		guiPumps.myPump.elementAt(i).setBackground(Color.green);
		guiConsole.append(Pumps[i].getName() + name + " is getting served  \n");
        guiPumps.myPump.elementAt(i).setText("Pump " + Integer.toString(i + 1) +"\n"+ name+ "\n"+" is getting served \n");
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
	public void Pay(int i) {
		guiPumps.myPump.elementAt(i).setBackground(Color.cyan);
		guiConsole.append(Pumps[i].getName() + name + " is Paying  \n");
        guiPumps.myPump.elementAt(i).setText("Table " + Integer.toString(i + 1) +"\n"+ name+ "\n"+" is Paying\n");
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
	public synchronized void Leave(int i) {
        guiConsole.append(Pumps[i].getName() + name + " left the Station \n");
        guiPumps.myPump.elementAt(i).setText("Table " + Integer.toString(i + 1) +"\n"+ name+ "\n"+" left the Station \n");
        guiPumps.myPump.elementAt(i).setBackground(Color.yellow);
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (s){
            s.V();
        }

    }
	public void run() {

        synchronized (s){
            Arrived();
            s.P(name,guiConsole);
        }
        int pos=-1;
        for (int i=0; i<Pumps.length;i++){
            if(Pumps[i].getFlag()==0){
                Pumps[i].setFlag(1);
                pos=i;
                //empty place
                break;
            }
        }
        Occupy(pos);
        getServed(pos);
        Pay(pos);
        Leave(pos);
        
        Pumps[pos].setFlag(0);


    }
}

