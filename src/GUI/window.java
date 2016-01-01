package GUI;


import java.awt.Container;
import java.util.LinkedList;
import java.util.Queue;


public abstract class window {
    
    public abstract Container openWindow();
    
    private Queue<String> lastNReadCodes = new LinkedList<>();
    private static int n = 5;
    
    public String getTextAreaString(){
    	String s = "";
    	for (String i : lastNReadCodes) {
    		s += i + "\n";
    	}
    	for (int i=lastNReadCodes.size(); i<5; i++) {
    		s += "\n";
    	}
    	return s;
    }
    
    public void appendCode(String text) {
    	lastNReadCodes.add(text);
    	if (lastNReadCodes.size() > n) {
    		lastNReadCodes.poll();
    	}
    }
    
    public abstract void refreshMainPanel();
}
