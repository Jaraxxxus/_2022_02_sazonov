package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class Producer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private static volatile int curID = 0;
    private final int id = this.getID();
    private Resource res;
    private final LinkedList<Resource> storageList;
    private final int producerTime, storageSize;


    private synchronized int getID() {
        return curID++;
    }

    Producer(LinkedList<Resource> storageList, int producerTime, int storageSize) {

        this.storageList = storageList;
        this.producerTime = producerTime;
        this.storageSize = storageSize;
    }

    @Override
    public void run() {
        while (true) {
            res = new Resource();
            putResource();
            try {
                Thread.sleep(producerTime);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            log.info("Producer Thread # " + Thread.currentThread() + "started");
        }
    }

    private void putResource() {
        synchronized (storageList) {
            while (storageList.size() == storageSize) {
                try {
                    log.info("Storage is full");
                    storageList.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            storageList.add(res);
            log.info("Producer " + id + " put product " + res.getId());
            storageList.notifyAll();
        }
    }
}
