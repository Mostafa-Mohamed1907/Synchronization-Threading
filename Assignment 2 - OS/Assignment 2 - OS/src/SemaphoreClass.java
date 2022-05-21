//package Assignment2;

import javax.swing.*;

public class SemaphoreClass {
    protected int value = 0;


    protected SemaphoreClass(int initial) {
        value = initial;
    }

    public synchronized void P(String n, JTextArea guiConsole) {//wait 
        value--;
        if (value < 0){
            try {
                guiConsole.append(n+" is waiting \n");
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void V() {//
        value++;
        if(value<=0) {
        	notify();
        }
    }


}
