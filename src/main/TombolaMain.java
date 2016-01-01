package main;

import GUI.WindowManager;

public class TombolaMain {

    public static WindowManager wdm;
    
    public static void main(String[] args) { 
    	try {
        wdm = new WindowManager();
        wdm.openWindow(); 
        }
    	finally {
    		// zavrit soubory
    	}
    }              
}
