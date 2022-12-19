package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {
    private final int nStart;
    private final int nEnd;
    private final int nStep;
    private double nResult = 0;

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public Task(int start, int end, int step) {
        nStart = start;
        nEnd = end;
        nStep = step;
    }

    @Override
    public Double call() throws InterruptedException {
        for (int n = nStart; n <= nEnd; n = n + nStep) {
            long divider = (long) n * (n + 1);
            nResult += 1. / divider;
            log.info("Cur step = " + n + ", cur result =" + nResult);
            if (Thread.currentThread().isInterrupted())
            {
                log.info("I was interrupted, cur step is " + nStep);
                throw new InterruptedException();
            }
        }
        return nResult;
    }
}
