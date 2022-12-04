package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class Consumer implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final LinkedList<Resource> storageList;
    private final int consumerTime;
    private static volatile int curID = 0;
    private final int id = this.getID();

    private synchronized int getID() {
        return curID++;
    }

    Consumer(LinkedList<Resource> storageList, int consumerTime){
        this.consumerTime = consumerTime;
        this.storageList = storageList;
    }

    @Override
    public void run() {
        while(true) {
            getResource();
            try {
                Thread.sleep(consumerTime);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
           log.info("Cons Thread loop " + Thread.currentThread());
        }
    }

    private void getResource() {

        synchronized (storageList) {
            while (storageList.size()==0){
                log.info("storage is empty");
                try {
                    storageList.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            Resource res = storageList.removeFirst();
            log.info("Consumer " + id + " get product "+ res.getId());
            storageList.notifyAll();
        }
    }

}
