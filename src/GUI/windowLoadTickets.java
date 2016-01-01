package GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.*;

import main.Logger;
import org.swixml.SwingEngine;

import Tickets.TicketTools;
import Tickets.Tickets;

public class windowLoadTickets extends window
{       
	private JFrame VDReadTicketsframe;

	private static JTextField TicketNumber;
    private static JButton ConfirmButton;
    private static JTextArea ReadCodes;
    
    public void resetWindow() {
    	VDReadTicketsframe.invalidate();
    	VDReadTicketsframe.validate();
    	VDReadTicketsframe.repaint();
    }

    @Override
    public Container openWindow()
    {        
        try {
            new SwingEngine(this).render( windowLoadTickets.class.getResourceAsStream("resources/windowDesign-loadTickets.xml") );
            VDReadTicketsframe.setVisible(true);
            VDReadTicketsframe.setAlwaysOnTop(true);
            VDReadTicketsframe.setEnabled(true);
            
            ReadCodes.setText(getTextAreaString());
            
            VDReadTicketsframe.getRootPane().setDefaultButton(ConfirmButton);
            
            Tickets.getInstance().setValidTickets(null);
            TicketTools.makeFileEmpty(TicketTools.VALID_TICKETS_FILE);
        } catch (Exception ex) {
            Logger.windowErrWinNotFound1.PrintAsFormattedText(ex);
            return null;
        }             
        
        return VDReadTicketsframe;                          
    }
    
    public Action Confirm = new AbstractAction() {
        @Override
        public void actionPerformed( ActionEvent e ) {
        	try {
        		Long code = Long.parseLong(TicketNumber.getText());
        		appendCode(code.toString());
        		ReadCodes.setText(getTextAreaString());
        		TicketTools.saveCode(code);
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