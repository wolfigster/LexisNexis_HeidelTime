package de.wolfig;

import de.wolfig.files.Configuration;

public class Main {

    private static Worker worker = null;

    public static void main(String[] args) {
        init();

        executeCommand(args);
        worker.initializeList();
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
            // do something
        } else if(args.length == 1) {
            // do something
        }
    }
}
