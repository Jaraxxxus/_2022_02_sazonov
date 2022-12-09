package cft.shift.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;


public class Parser {

    private final int portNumber;


    private static final Logger log = LoggerFactory.getLogger(Parser.class);
    public Parser() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().
                getResourceAsStream("/config.properties"))))) {
            Properties properties = new Properties();
            properties.load(reader);
            portNumber = Integer.parseInt(properties.getProperty("port"));

        }
    }






    public int getPortNumber(){
        return portNumber;
    }
}