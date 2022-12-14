package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input number of calculation: ");
        int num = in.nextInt();
        double sum;
        log.info("calculation started");

        try {
            sum = CalculateFunction.calculate(num);
        } catch (ExecutionException e) {
            log.error("smth went wrong " + e.getMessage());
            System.out.println("further correct work is not possible");
            return;
        }

        log.info(" sum  = " + sum);
        System.out.println("result is " + sum + ", mathematical expectation = 1");
    }

}
