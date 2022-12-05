package cft.shift;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


public class Parser {

    private final int portNumber;

    public Parser() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (getClass().getResourceAsStream("/config.properties")))) {
            Properties properties = new Properties();
            properties.load(reader);
            portNumber = Integer.parseInt(properties.getProperty("port :"));
        }
    }
    public int getPortNumber(){
        return portNumber;
    }
}