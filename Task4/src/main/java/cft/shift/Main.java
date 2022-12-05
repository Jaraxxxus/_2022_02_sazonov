package cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input number of calculation: ");
        int num = in.nextInt();
        double sum = 0;
        log.info("calculation started");

        try {
            sum = CalculateFunction.calculate(num);
        } catch (Exception e) {
            log.error("smth went wrong" + e.getMessage());
        }

        log.info(" sum  " + sum);
        System.out.println("result is " + sum + ", mathematical expectation = 1");
        System.exit(0);
    }

}
