package it.jobinformatica;

import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        logger.info("Inizio programma");
        new Esecuzione().start("Venezia");
    }
}