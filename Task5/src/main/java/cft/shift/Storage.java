package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class Storage {
    private static final Logger log = LoggerFactory.getLogger(Storage.class);
    private final int storageSize;
    private static final Queue<Resource> storageQueue = new LinkedList<>();

    public Storage(int StorageSize) {
        this.storageSize = StorageSize;
    }

    public void put(Resource res) {
        synchronized (storageQueue) {
            while (storageQueue.size() == storageSize) {
                try {
                    log.info("Storage is full");
                    storageQueue.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            storageQueue.add(res);
            storageQueue.notifyAll();
        }
    }


    public Resource get() {
        Resource res;
        synchronized (storageQueue) {
            while (storageQueue.size() == 0) {
                log.info("storage is empty");
                try {
                    storageQueue.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            res = storageQueue.remove();
            storageQueue.notifyAll();
        }

        return res;
    }
}