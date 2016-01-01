package GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractMap;
import java.util.Collection;
import javax.swing.*;
import main.Logger;
import org.swixml.SwingEngine;

import Tickets.TicketTools;
import Tickets.Tickets;

public class windowIntro extends window
{       
	private static Container VDintro;
	
    private static JTextArea ValidTicketsFileStats;
    private static JTextArea DrawnPricesFileStats;
    private static JTextArea PricesFileStats;
    
    private static JButton LoadWithReaderButton;
    
    private static JTextField FieldFrom;
    private static JTextField FieldTo;
    
    private static JButton DrawPricesButton;
    
    private static JButton GiveAwayPricesButton;
    
    @Override
    public Container openWindow()
    {        
        try {
            new SwingEngine(this).render( windowIntro.class.getResourceAsStream("resources/windowDesign-intro.xml") ).setVisible(true);
        } catch (Exception ex) {
            Logger.windowErrWinNotFound1.PrintAsFormattedText(ex);
            return null;
        }            
        
        setStatsFromFiles();
                                             
        return VDintro;                          
    }

    @Override
    public void refreshMainPanel() {
    	setStatsFromFiles();
    	VDintro.invalidate();
    	VDintro.validate();
    	VDintro.repaint();
    }
    
    private void setStatsFromFiles() {
    	Tickets tickets = Tickets.getInstance();
        
        TicketTools.readValidCodes();
        ValidTicketsFileStats.setText(getFileStatsString(tickets.getValidTickets()));

        TicketTools.readDrawnPrices();        
        DrawnPricesFileStats.setText(getFileStatsString(tickets.getWinningTickets()));
        
        TicketTools.readPrices();        
        PricesFileStats.setText(getFileStatsString(tickets.getPrices()));
    }    
    
    private String getFileStatsString(Collection c) {
    	return c == null ? "není" : c.size() + " kódů";
    }
    
    private String getFileStatsString(AbstractMap c) {
    	return c == null ? "není" : c.size() + " kódů";
    }
    
    public Action loadWithReader = new AbstractAction() {
        @Override
        public void actionPerformed( ActionEvent e ) {
        	JFrame frame = (JFrame) new windowLoadTickets().openWindow();
        	addRefreshAction(frame);
        }
    };
    
    public Action drawPrices = new AbstractAction() {
        @Override
        public void actionPerformed( ActionEvent e ) {
        	JFrame frame = (JFrame) new windowDrawPrices().openWindow();
        	addRefreshAction(frame);
        }
    };
    
    public Action giveAwayPrices = new AbstractAction() {
        @Override
        public void actionPerformed( ActionEvent e ) {
        	JFrame frame = (JFrame) new windowGivePrices().openWindow();
        	addRefreshAction(frame);
        }
    };
    
    private void addRefreshAction(JFrame frame) {
    	frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
            	WindowManager.mainPanel.refreshMainPanel();
            	WindowManager.repaint();
            }
            
            @Override
            public void windowLostFocus(WindowEvent e) {                   
            }
        });
    }
}