package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int port;
        try {
            port = ConfigParser.getPortNumber();

        } catch (IOException ex) {
            log.info(ex.getMessage());
            System.out.println("Порт не найден , продолжить работу не возможно");
            return;
        }
        Server server = new Server(port);
        server.startServer();
    }

}