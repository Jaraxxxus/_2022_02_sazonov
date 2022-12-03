package cft.shift;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalculateFunction {
    public static double calculate(int num) throws ExecutionException, InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        double sum = 0;

        System.out.println(processors + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");
        ExecutorService pool = Executors.newFixedThreadPool(processors);
        Set<Future<Double>> set = new HashSet<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < processors; i++) {
            TaskDescribe task = new TaskDescribe(i + 1, num, processors);
            Future<Double> future = pool.submit(task);
            set.add(future);
        }
        for (Future<Double> future : set) {
            sum += future.get();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Calculation of the sum of  1/(N(N+1)) in " + (endTime - startTime) +
                " milliseconds.");

        return sum;
    }
}
