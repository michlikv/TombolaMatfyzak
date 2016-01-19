package Tickets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import GUI.WindowManager;


public class TicketTools {
	
	private static Tickets tickets = Tickets.getInstance();
	
	public static String VALID_TICKETS_FILE = "valid_tickets.txt";
	public static String DRAWN_PRICES_FILE = "drawn_prices.csv";
	public static String PRICES_FILE = "prices.csv";
	public static String GIVE_PRICES_FILE = "log_give_prices.csv";
	
	public static String PATH;
	
	static {
		File f = new File(WindowManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PATH = f.getParent();
	}
	
	public static void readValidCodes() {
		List<String> lines = null;
		try {
			File file = new File(PATH, VALID_TICKETS_FILE);
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (lines != null) {
			for (String line : lines) {
				try {
					tickets.addValidTicket(Long.parseLong(line));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void readDrawnPrices() {
		List<String> lines = null;
		try {
			File file = new File(PATH, DRAWN_PRICES_FILE);
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (lines != null) {
			for (String line : lines) {
				try {
					String[] values = line.split(",");
					tickets.putWinningTicket(Long.parseLong(values[0]), Integer.parseInt(values[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void readPrices() {
		List<String> lines = null;
		try {
			File file = new File(PATH, PRICES_FILE);
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (lines != null) {
			for (String line : lines) {
				try {
					String[] values = line.split(",");
					if (values.length == 2) {
						tickets.putPrice(Integer.parseInt(values[0]), values[1]);
					}
					else {
						tickets.putPrice(Integer.parseInt(values[0]), null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void saveCode(Long code) {
		File file = new File(PATH, VALID_TICKETS_FILE);
		try {
			FileUtils.writeStringToFile(file, code+"\n", "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void makeFileEmpty(String filename) {
		File file = new File(TicketTools.PATH, filename);
		try {
			FileUtils.writeStringToFile(file, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveDraw(Long code, Integer price) {
		File file = new File(PATH, DRAWN_PRICES_FILE);
		try {
			FileUtils.writeStringToFile(file, code+","+price+"\n", "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logGivePrice(Long code, Integer price) {
		File file = new File(PATH, GIVE_PRICES_FILE);
		try {
			FileUtils.writeStringToFile(file, code+","+price+"\n", "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
