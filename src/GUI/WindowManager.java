package GUI;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.swixml.SwingEngine;

import main.Logger;

public class WindowManager {

	/** Main frame of the application. */
    private Frame VDframe;
    /** Panel in main frame into which all the designs are being included. */
    private JPanel VDincludeHere;    
    
    public static JFrame mainFrame;
    public static window mainPanel;
    
    public Frame getFrame() {
        return VDframe;
    } 
    
    /**
     * Main window
     */
    public void openWindow()
    {
    	try {                         
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                 JFrame.setDefaultLookAndFeelDecorated(true);
            }                
            CreateFrame();
            mainPanel = new windowIntro();
            Container include = mainPanel.openWindow();         
            
            if(VDframe != null && VDincludeHere!= null && include != null)
            {                     
                includeIntoFrame(include);  
                VDframe.setVisible(true);           
            }
            else
            {
                ShowProgramLoadErrorWindow();
            }           
            
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }    
      
    private void includeIntoFrame(Container c)
    {
        VDincludeHere.removeAll();
        VDincludeHere.add(c);
        repaint();
    }  
    
    /** Shows error with program loading. */
    public static void ShowProgramLoadErrorWindow()
    {
        JOptionPane.showMessageDialog(null, Logger.errWindowCouldNotLoad0.getAsFormattedText(""), "Error", JOptionPane.ERROR_MESSAGE);
    }                 
	
    /**
     * Renders frame from "windowDesign-frame.xml" in resources.
     * Adds a window closing action to the frame.
     */
    private void CreateFrame() {
        try {
            InputStream resourceAsStream = WindowManager.class.getResourceAsStream("resources/windowDesign-frame.xml");
            mainFrame = (JFrame) new SwingEngine(this).render(resourceAsStream);
            
            VDframe.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent we){
                	//closeAll(); zavrit file handly atp.
                }
                
                @Override
                public void windowLostFocus(WindowEvent e) {                   
                }
            });             
        } catch (Exception ex) {
            Logger.windowErrWinNotFound1.PrintAsFormattedText(ex);
        }    
    }
    
    /**
     * Invalidates, validates and repaints frame.
     */
    public static void repaint()
    {
    	mainFrame.invalidate();
    	mainFrame.validate();
    	mainFrame.repaint();
    }
    
    public void closeAll()
    {
        System.exit(0);
    }
    
}
