package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final Storage storage;
    private final int consumerTime;
    private static volatile int curID = 0;
    private final int id = this.getID();

    private synchronized int getID() {
        return curID++;
    }

    Consumer(Storage storage, int consumerTime){
        this.consumerTime = consumerTime;
        this.storage = storage;
    }

    @Override
    public void run() {
        while(true) {
            Resource res = storage.get();
            System.out.println("Consumer " + id + " get product "+ res.getId());
            try {
                Thread.sleep(consumerTime);
            } catch (InterruptedException e) {
                  log.error(e.getMessage());
            }
             log.info("Cons Thread " + Thread.currentThread() + " looped ");

        }
    }

}
