package Tickets;

import java.util.HashMap;
import java.util.HashSet;

public class Tickets {

	private static Tickets instance = null;
	
	protected Tickets() {
		// Exists only to defeat instantiation.
	}
	public static Tickets getInstance() {
		if (instance == null) {
			instance = new Tickets();
		}
		return instance;
   }
	
	private HashSet<Long> validTickets;
	private HashMap<Long,Integer> winningTickets;
	private HashMap<Integer,String> prices;

	public boolean isTicketValid(Integer ticket) {
		return validTickets == null ? false : validTickets.contains(ticket);
	}
	
	public void addValidTicket(Long validTicket) {
		if (validTickets == null) {
			validTickets = new HashSet<>();
		}
		validTickets.add(validTicket);
	}
	
	public HashMap<Long,Integer> getWinningTickets() {
		return winningTickets;
	}

	public void setWinningTickets(HashMap<Long,Integer> winningTickets) {
		this.winningTickets = winningTickets;
	}
	
	public void putWinningTicket(Long ticketNumber, Integer price) {
		if (winningTickets == null) {
			winningTickets = new HashMap<>();
		}
		this.winningTickets.put(ticketNumber, price);
	}
	
	public HashSet<Long> getValidTickets() {
		return validTickets;
	}

	public void setValidTickets(HashSet<Long> validTickets) {
		this.validTickets = validTickets;
	}
	
	public HashMap<Integer,String> getPrices() {
		return prices;
	}

	public void setPrices(HashMap<Integer,String> prices) {
		this.prices = prices;
	}
	
	public void putPrice(Integer priceNumber, String description) {
		if (prices == null) {
			prices = new HashMap<>();
		}
		prices.put(priceNumber, description);
	}
}
