package cft.shift;

import java.util.LinkedList;

public class Consumer implements Runnable{
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
                e.printStackTrace();
            }
            System.out.println("Cons Thread loop " + Thread.currentThread());
        }
    }

    private void getResource() {

        synchronized (storageList) {
            while (storageList.size()==0){
                System.out.println("storage is empty");
                try {
                    storageList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Resource res = storageList.removeFirst();
            System.out.println("Consumer " + id + " get product "+ res.getId());
            storageList.notifyAll();
        }
    }

}
