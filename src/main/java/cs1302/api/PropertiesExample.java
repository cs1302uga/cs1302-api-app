package cs1302.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Example driver that shows how to read the {@code "resources/config.properties"}
 * file using the {@link Properties} and {@link FileInputStream} classes.
 *
 * <p>
 * The examples assumes {@code "resources/config.properties"} looks like this:
 *
 * <pre>
 * dogapi.key=the-dogapi-key-value
 * catapi.key=the-catapi-key-value
 * </pre>
 *
 * <p>
 * To run this example on Odin, use the following commands:
 *
 * <pre>
 * $ mvn clean compile
 * $ mvn exec:java -Dexec.mainClass=cs1302.api.PropertiesExample
 * </pre>
 */
public class PropertiesExample {

    public static void main(String[] args) {

        String configPath = "resources/config.properties";

        // the following try-statement is called a try-with-resources statement
        // see https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try (FileInputStream configFileStream = new FileInputStream(configPath)) {
            Properties config = new Properties();
            config.load(configFileStream);
            config.list(System.out);                             // list all using standard out
            String dogApiKey = config.getProperty("dogapi.key"); // get dogapi.key
            String catApiKey = config.getProperty("catapi.key"); // get catapi.key
            System.out.printf("dogApiKey = \"%s\"\n", dogApiKey);
            System.out.printf("catApiKey = \"%s\"\n", catApiKey);
        } catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
        } // try

    } // main

} // PropertiesExample
