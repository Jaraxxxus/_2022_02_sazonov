package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            Parser parser = new Parser();
            Server server = new Server(parser);
            server.startServer();
        } catch (NumberFormatException | IOException e) {
            log.error(e.getMessage());
        }
    }

}