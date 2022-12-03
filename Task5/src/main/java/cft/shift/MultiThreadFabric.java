package cft.shift;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;


public class MultiThreadFabric {

   private static int producerCount, consumerCount, producerTime, consumerTime, storageSize;

    {

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
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    MultiThreadFabric(){

        LinkedList<Resource> storageList = new LinkedList<>();
        for (int i = 0; i < producerCount; i++) {
            new Thread(new Producer(storageList,producerTime,storageSize)).start();
        }

        for(int i = 0; i < consumerCount;i++) {
            new Thread(new Consumer(storageList,consumerTime)).start();
        }

    }

}
