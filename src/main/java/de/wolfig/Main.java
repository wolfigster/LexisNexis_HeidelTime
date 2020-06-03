package de.wolfig;

import de.wolfig.files.Configuration;
import de.wolfig.fx.Window;
import javafx.application.Application;
import org.apache.logging.log4j.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static Worker worker = null;

    public static void main(String[] args) {
        init();

        executeCommand(args);

        System.exit(0);
    }

    public static void init() {
        Configuration.loadConfiguration();

        worker = new Worker();
    }

    public static void executeCommand(String[] args) {
        // method for later console input to manage the parameter
        if(args.length == 0) {
            Application.launch(Window.class, args);
            worker.initializeList(0);
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("-ht")) {
                worker.updateHeidelTimeConfig();
                worker.executeHeidelTime();
            } else if(args[0].equalsIgnoreCase("-convert")) {
                worker.convertXMLtoTXT();
            } else if(args[0].equalsIgnoreCase("-csv")) {
                worker.createOutputCSV();
            }
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("-d")) {

            } else if(args[0].equalsIgnoreCase("-i")) {
                worker.initializeList(Integer.parseInt(args[1]));
                System.exit(0);
            }
        } else if(args.length == 3) {

        }
        worker.stop();
    }
}
