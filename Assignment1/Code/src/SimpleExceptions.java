import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleExceptions {
	/*
	 * Singleston Instance
	 */
	private static SimpleExceptions excp;

	/*
	 * Get instance of SimpleExceptions
	 */
	public static SimpleExceptions getExceptionsInstance() {
		if (excp == null) {
			excp = new SimpleExceptions();
		}
		return excp;
	}
/*
 * Exception logger
 */
	private Logger excpLog;

	/*
	 * Private constructor
	 */
	private SimpleExceptions() {
	}

	/*
	 * Get exception logger
	 */
	public void createExcpLogger() {
		excpLog = Logger.getLogger("CSVExceptions");
	}

	/*
	 * Initialise Exception Logger
	 */
	public void initExcpLogger() {
		try {
			FileHandler excpFileHandler = new FileHandler("./csvExceptions.log", true);
			this.excpLog.addHandler(excpFileHandler);
			excpFileHandler.setLevel(Level.SEVERE);
			this.excpLog.setLevel(Level.SEVERE);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			excpFileHandler.setFormatter(simpleFormatter);
			this.excpLog.config("Configuration done.");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
			System.out.println("CSV Exception logger creation is failed");
		}

	}
	/*
	 * Logg exceptions
	 */
	public void logException(Level severe, String message) {
		this.excpLog.log(severe, message);
	}

	/*
	 * Clear Resource
	 */
	public void clearResource() {
		this.excpLog.getHandlers()[0].close();
		this.excpLog.removeHandler(this.excpLog.getHandlers()[0]);
	}
}
