package cft.shift;

import java.util.concurrent.Callable;

public class TaskDescribe implements Callable<Double> {
    private final int nStart;
    private final int nEnd;
    private final int nStep;
    private double nResult = 0;

    public TaskDescribe(int start, int end, int step) {
        nStart = start;
        nEnd = end;
        nStep = step;
    }

    @Override
    public Double call() {
        for (int n = nStart; n <= nEnd; n = n + nStep) {
            long del = (long) n * n + n;
            nResult += 1. / del;
        }
        return nResult;
    }
}
