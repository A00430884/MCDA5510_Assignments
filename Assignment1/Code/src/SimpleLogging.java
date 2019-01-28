import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleLogging {
/*
 * File Hanndler reference
 */
	private Handler fileHandler;
	private Logger logger = null;
	private Handler exceptionFileHandler;
	/*
	 * Single instance
	 */
	private static  SimpleLogging logging;
	/*
	 * Get singleton instance
	 */
	public static SimpleLogging getLogging() {
		if(logging == null) {
			logging = new SimpleLogging();
			return logging;
		}
		return logging;
		
	}
	/*
	 * Get the logger
	 */
	public Logger getLogger() {
		logger = Logger.getLogger("CSV Logger");
		return logger;
	}
	private SimpleLogging() {}
	/*
	 * Initialize Handlers
	 */
	public void init() {
		Handler consoleHandler = new ConsoleHandler();
		 fileHandler = null;
		try {
			fileHandler = new FileHandler("./csvLogfile.log",true);
		} catch (SecurityException | IOException e) {
			SimpleExceptions exceptionsInstance = SimpleExceptions.getExceptionsInstance();
			exceptionsInstance.createExcpLogger();
			exceptionsInstance.initExcpLogger();
			exceptionsInstance.logException(Level.SEVERE, e.getMessage());
		} 
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
		// Setting levels to handlers and LOGGER
		consoleHandler.setLevel(Level.ALL);
		fileHandler.setLevel(Level.FINE);
		logger.setLevel(Level.ALL);
		
		SimpleFormatter simpleFormatter = new SimpleFormatter();
		
		// Setting formatter to the handler
		fileHandler.setFormatter(simpleFormatter);
		logger.config("Configuration done.");
	}
	/*
	 * Logg messages
	 */
	public void logMsg(String message) {
		this.logger.log(Level.INFO, message);
		
	}
/*
 * Logg Fine Messages
 */
	public void logFineMsg(String message) {
		this.logger.log(Level.FINE, message);
	}
/*
 * Clear Resources
 */
	public void clearResource() {
		for (Handler handler : this.logger.getHandlers()) {
			this.logger.removeHandler(handler);
		}
		this.fileHandler.close();
	}

}
