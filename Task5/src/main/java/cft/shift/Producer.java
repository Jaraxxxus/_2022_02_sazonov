package cft.shift;

import java.util.LinkedList;

public class Producer implements Runnable {

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
                e.printStackTrace();
            }
            System.out.println("Producer Thread # " + Thread.currentThread());
        }
    }

    private void putResource() {
        synchronized (storageList) {
            while (storageList.size() == storageSize) {
                try {
                    System.out.println("Storage is full");
                    storageList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storageList.add(res);
            System.out.println("Producer " + id + " put product "+ res.getId());
            storageList.notifyAll();
        }
    }
}
