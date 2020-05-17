package de.wolfig;

import de.wolfig.files.Configuration;
import org.apache.logging.log4j.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static Worker worker = null;

    public static void main(String[] args) {
        init();

        executeCommand(args);
        worker.convertXMLtoTXT();
        worker.stop();

        System.exit(0);
    }

    public static void init() {
        Configuration.loadConfiguration();

        worker = new Worker();
    }

    public static void executeCommand(String[] args) {
        // method for later console input to manage the parameter
        if(args.length == 0) {
            worker.initializeList(0);
        } else if(args.length == 1) {
            // do something
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("-d")) {

            } else if(args[0].equalsIgnoreCase("-i")) {
                worker.initializeList(Integer.parseInt(args[1]));
                System.exit(0);
            }
        } else if(args.length == 3) {

        }
    }
}
