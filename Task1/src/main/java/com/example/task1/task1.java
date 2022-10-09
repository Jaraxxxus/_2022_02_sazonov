package com.example.task1;
import java.io.IOException;
import java.util.Scanner;


public class task1 {
    int curNum = 1;
    int mult = 1;
    int tableSize = 0;
    int multLength = 0;
    int fieldLength = 0;


    String delimitr;
    StringBuilder curBuf = new StringBuilder();
    task1()
    {

        run();
    }




    private void run() {
        System.out.println("Введите размер поля || vvedi");
        Scanner in = new Scanner(System.in);
        tableSize = in.nextInt();
        in.close();
        multLength = findLength(tableSize);
        fieldLength = findLength(tableSize*tableSize);
        createDelimiter();
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
        delimitr = buf.toString();

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
    private void fillBuf()
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
        fillBuf();
        System.out.println(curBuf);
        System.out.println(delimitr);


        while(mult<=tableSize)
        {
            curNum = mult;
            curBuf.setLength(0);
            editField(mult);
            fillBuf();
            mult++;
            System.out.println(curBuf);
            System.out.println(delimitr);
        }
    }


}