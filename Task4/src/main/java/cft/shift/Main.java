package cft.shift;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner in = new Scanner(System.in);
        System.out.print("Input number of calculation: ");
        int num = in.nextInt();
        double sum ;
        sum = CalculateFunction.calculate(num);

        System.out.println(" sum  " + sum);
        System.exit(0);
    }

}
