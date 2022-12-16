package cft.shift;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;


public class ConfigParser {

    public static int getPortNumber() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ConfigParser.class.
                getResourceAsStream("/config.properties"))));
        Properties properties = new Properties();
        properties.load(reader);
        return Integer.parseInt(properties.getProperty("port"));

    }
}
