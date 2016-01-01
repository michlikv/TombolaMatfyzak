package GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.*;

import main.Logger;
import org.swixml.SwingEngine;

import Tickets.TicketTools;
import Tickets.Tickets;

public class windowDrawPrices extends window
{       
	private JFrame VDDrawPricesframe;
	
	private static JTextArea NumberLabel;
	private static JTextField TicketNumber;
    private static JButton ConfirmButton;
    private static JTextArea ReadCodes;
    
    private Iterator<Integer> iterator;
    private Tickets tickets = Tickets.getInstance();
    
    public void resetWindow() {
    	VDDrawPricesframe.invalidate();
    	VDDrawPricesframe.validate();
    	VDDrawPricesframe.repaint();
    }

    @Override
    public Container openWindow()
    {        
        try {
            new SwingEngine(this).render( windowDrawPrices.class.getResourceAsStream("resources/windowDesign-drawPrices.xml") );
            VDDrawPricesframe.setVisible(true);
            VDDrawPricesframe.setAlwaysOnTop(true);
            VDDrawPricesframe.setEnabled(true);
            
            ReadCodes.setText(getTextAreaString());
            
            VDDrawPricesframe.getRootPane().setDefaultButton(ConfirmButton);
            
            if (tickets.getPrices() != null) {
            	iterator = tickets.getPrices().keySet().iterator();
            }
            setNumberLabel();
            
            TicketTools.makeFileEmpty(TicketTools.DRAWN_PRICES_FILE);
            tickets.setWinningTickets(null);
        } catch (Exception ex) {
            Logger.windowErrWinNotFound1.PrintAsFormattedText(ex);
            return null;
        }             
        
        return VDDrawPricesframe;                          
    }
    
    private void setNumberLabel() {
    	if (iterator.hasNext()) {
    		NumberLabel.setText(iterator.next().toString());
    	}
    	else {
    		NumberLabel.setText("E");
    	}
    }
    
    
    public Action Confirm = new AbstractAction() {
        @Override
        public void actionPerformed( ActionEvent e ) {
        	try {
        		Long code = Long.parseLong(TicketNumber.getText());
        		
        		if (tickets.getWinningTickets() != null && tickets.getWinningTickets().containsKey(code)) {
        			appendCode(code + " DUPLICITNÍ");
        		}
        		else {
        			Integer price = Integer.parseInt(NumberLabel.getText());
        			TicketTools.saveDraw(code, price);
        			
        			String message = code + " -> " + NumberLabel.getText(); 
        			if (tickets.getValidTickets() == null || ! tickets.getValidTickets().contains(code)) {
        				message += " NEPLATNÝ KÓD";
        			}
            		appendCode(message);
            		
            		setNumberLabel();
            		tickets.putWinningTicket(code, price);
        		}        		
        	} catch (Exception ex) {
        		JOptionPane.showMessageDialog(null, Logger.FormatError2.getAsFormattedText(TicketNumber.getText(), NumberLabel.getText()), "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	ReadCodes.setText(getTextAreaString());
        	TicketNumber.setText("");
        	resetWindow();
        }
    };

	@Override
	public void refreshMainPanel() {
		// TODO Auto-generated method stub
		
	}
    
}