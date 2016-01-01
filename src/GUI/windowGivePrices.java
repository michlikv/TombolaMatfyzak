package GUI;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.*;
import main.Logger;
import org.swixml.SwingEngine;
import Tickets.TicketTools;
import Tickets.Tickets;

public class windowGivePrices extends window
{       
	private JFrame VDGivePricesframe;
	
	private static JTextField TicketNumber;
    private static JButton ConfirmButton;
    private static JTextArea PrizeLabel;
    
    private Iterator<Integer> iterator;
    private Tickets tickets = Tickets.getInstance();
    
    public void resetWindow() {
    	VDGivePricesframe.invalidate();
    	VDGivePricesframe.validate();
    	VDGivePricesframe.repaint();
    }

    @Override
    public Container openWindow()
    {        
        try {
            new SwingEngine(this).render( windowGivePrices.class.getResourceAsStream("resources/windowDesign-givePrices.xml") );
            VDGivePricesframe.setVisible(true);
            VDGivePricesframe.setAlwaysOnTop(true);
            VDGivePricesframe.setEnabled(true);
            
            VDGivePricesframe.getRootPane().setDefaultButton(ConfirmButton);
            
            TicketTools.makeFileEmpty(TicketTools.GIVE_PRICES_FILE);
        } catch (Exception ex) {
            Logger.windowErrWinNotFound1.PrintAsFormattedText(ex);
            return null;
        }             
        
        return VDGivePricesframe;                          
    }
    
    
    public Action Confirm = new AbstractAction() {
        @Override
        public void actionPerformed( ActionEvent e ) {
        	try {
        		Long code = Long.parseLong(TicketNumber.getText());
        		
        		if (tickets.getWinningTickets() != null && tickets.getWinningTickets().containsKey(code)) {
        			Integer i = tickets.getWinningTickets().get(code);
        			String s = i.toString();
        			if (tickets.getPrices().get(i) != null) {
        				s += "\n" + tickets.getPrices().get(i);
        			}
        			if (tickets.getValidTickets() == null || ! tickets.getValidTickets().contains(code)) {
        				s += "\nNEPLATNÝ KÓD";
        			}
        			PrizeLabel.setText(s);
        			TicketTools.logGivePrice(code, i);
        		}
        		else {
        			PrizeLabel.setText("Nevýherní");
        			TicketTools.logGivePrice(code, null);
        		}
        	} catch (Exception ex) {
        		JOptionPane.showMessageDialog(null, Logger.FormatError1.getAsFormattedText(TicketNumber.getText()), "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	TicketNumber.setText("");
        	resetWindow();
        }
    };
    
	@Override
	public void refreshMainPanel() {
		// TODO Auto-generated method stub
		
	}
}