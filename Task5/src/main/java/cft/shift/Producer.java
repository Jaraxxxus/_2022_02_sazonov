package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private static volatile int curID = 0;
    private final int id = this.getID();
    private final Storage storage;
    private final int producerTime;


    private synchronized int getID() {
        return curID++;
    }

    Producer(Storage storage, int producerTime) {

        this.producerTime = producerTime;
        this.storage = storage;
    }


    @Override
    public void run() {
        while (true) {
            Resource res = new Resource();
            storage.put(res);
            System.out.println("Producer " + id + " put product " + res.getId());
            try {
                Thread.sleep(producerTime);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            log.info("Producer Thread # " + Thread.currentThread() + "looped");
        }
    }
}
