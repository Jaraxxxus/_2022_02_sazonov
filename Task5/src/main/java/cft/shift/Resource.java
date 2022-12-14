package cft.shift;

public class Resource {
    static private volatile int curID = 0;
    private final int id;

    private synchronized int setID() {
        return curID++;
    }

    Resource() {
        this.id = setID();
    }

    public int getId() {
        return this.id;
    }

}
