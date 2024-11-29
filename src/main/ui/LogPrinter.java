package ui;
import model.*;
import model.exceptions.LogException;

/**
 * Defines a log printer that prints the events logged.
 */
public class LogPrinter {
	/**
	 * Prints the log
	 * @param el  the event log to be printed
	 * @throws LogException when printing fails for any reason
	 */
    static void printLog(EventLog el) throws LogException {
        for (Event event : el) {
            System.out.println(event.toString());
            System.out.println();
        }
    }
}
