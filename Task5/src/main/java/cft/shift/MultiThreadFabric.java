package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MultiThreadFabric {
    private static final Logger log = LoggerFactory.getLogger(MultiThreadFabric.class);
    private static int producerCount, consumerCount, producerTime, consumerTime, storageSize;


    MultiThreadFabric() {
        try {
            init();
        } catch (IOException e) {
            return;
        }
        Storage storage = new Storage(storageSize);
        for (int i = 0; i < producerCount; i++) {
            new Thread(new Producer(storage, producerTime)).start();
        }

        for (int i = 0; i < consumerCount; i++) {
            new Thread(new Consumer(storage, consumerTime)).start();
        }

    }

    private void init() throws IOException {

        Properties properties = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);


        try {
            properties.load(inputStream);
            producerCount = Integer.parseInt(properties.getProperty("producerCount"));
            consumerCount = Integer.parseInt(properties.getProperty("consumerCount"));
            producerTime = Integer.parseInt(properties.getProperty("producerTime"));
            consumerTime = Integer.parseInt(properties.getProperty("consumerTime"));
            storageSize = Integer.parseInt(properties.getProperty("storageSize"));
        } catch (IOException e) {
            log.error(e.getMessage());

            log.error("further work is not possible");
            throw e;

        }
        log.info("will be created : " + producerCount + " producers, " + consumerCount + "consumers, storageSize = " + storageSize);
        log.info("cooldown : " + producerTime + " ms for producer, " + consumerTime + " ms for consumers");

    }

}
