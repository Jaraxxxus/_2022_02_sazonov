package cft.shift;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        final int maxSize = 32;
        System.out.println("enter the field size");
        Scanner in = new Scanner(System.in);
        if (!in.hasNextInt())
        {
            System.out.println("That's not a number!");
            in.close();
            return;

        }
        int tableSize = in.nextInt();
        in.close();
        if (tableSize<1 )
        {
            System.out.println("negative number/zero detected");
            return;
        }

        if (tableSize>maxSize )
        {
            System.out.println("your number is too big");
            return;
        }
        Task1.printTable(tableSize);


    }


}
