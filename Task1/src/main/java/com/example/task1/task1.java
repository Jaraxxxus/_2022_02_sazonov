package com.example.task1;
import java.util.Scanner;


public class task1 {

    int mult = 1;
    int tableSize = 0;
    int multLength = 0;
    int fieldLength = 0;


    String delimiter;
    StringBuilder curBuf = new StringBuilder();
    task1()
    {

        run();
    }




    private void run() {
        System.out.println("enter the field size");
        Scanner in = new Scanner(System.in);
        if (!in.hasNextInt())
        {
            System.out.println("That's not a number!");
            in.close();
            return;

        }
        tableSize = in.nextInt();
        in.close();
        if (tableSize<1)
        {
            System.out.println("negative number/zero detected");
            return;
        }
        multLength = findLength(tableSize);
        fieldLength = findLength(tableSize*tableSize);

        printTable();





    }





    private void createDelimiter()
    {
        StringBuilder buf = new StringBuilder();
        buf.append("-".repeat(Math.max(0, multLength)));


        for (int i = 0 ;i < tableSize; ++i)
        {
            buf.append("+").append("-".repeat(Math.max(0, fieldLength)));
        }
        delimiter = buf.toString();

    }

    private int findLength(int num)
    {
        int res = 0;
        while (num!=0)
        {
            num = num/10;
            ++res;
        }
        return res;
    }


    private void editField(int i)
    {
        int spaceLength = multLength - findLength(i) ;
        curBuf.append(" ".repeat(Math.max(0, spaceLength))).append(i);
    }
    private void editField()
    {
        curBuf.append(" ".repeat(Math.max(0, multLength)));
    }
    private void fillBuf(int curNum)
    {
        for (int i = 0 ;i < tableSize; ++i)
        {
            int spaceLength = fieldLength - findLength(curNum);
            curBuf.append("|").append(" ".repeat(Math.max(0, spaceLength))).append(curNum);
            curNum+=mult;
        }

    }
    private void printTable()
    {

        editField();
        fillBuf(1);
        System.out.println(curBuf);
        createDelimiter();
        System.out.println(delimiter);


        while(mult<=tableSize)
        {

            curBuf.setLength(0);
            editField(mult);
            fillBuf(mult);
            mult++;
            System.out.println(curBuf);
            System.out.println(delimiter);
        }
    }


}