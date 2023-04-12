package cs1302.omega;

import javafx.application.Application;

/**
 * Driver for the {@code OmegaApp} class.
 */
public class OmegaDriver {

    /**
     * Main entry-point into the application.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        try {
            Application.launch(OmegaApp.class, args);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            System.err.println();
            System.err.println(e);
            System.err.println("If this is a DISPLAY problem, then your X server connection");
            System.err.println("has likely timed out. This can generally be fixed by logging");
            System.err.println("out and logging back in.");
            System.exit(1);
        } catch (RuntimeException re) {
            re.printStackTrace();
            System.err.println();
            System.err.println(re);
            System.err.println("A runtime exception has occurred somewhere in the application,");
            System.err.println("and it propagated all the way up to the main method. Please");
            System.err.println("inspect the backtrace above for more information.");
            System.exit(1);
        } // try
    } // main

} // OmegaDriver
