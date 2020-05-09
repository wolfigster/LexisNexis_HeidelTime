package de.wolfig;

import de.wolfig.files.Configuration;

public class Main {

    private static Worker worker = null;

    public static void main(String[] args) {
        init();
        worker.initializeList();
        worker.stop();
    }

    public static void init() {
        Configuration.loadConfiguration();

        worker = new Worker();
    }
}
