package src.cft.shift;
import java.io.IOException;


public class task1 {
    int mult = 1;
    int tableSize = 0;
    int multLength = 0;
    int fieldLength = 0;
    String delimitr;
    int curNum = 1;
    StringBuilder curBuf;
    task1()
    {
        run();
    }



int findLength(int num)
{
    int res = 0;
    while (num!=0)
    {
        num = num/10;
        ++res;
    }
    return res;
}
    void run() {
        System.out.println("Введите размер поля");
        try {
            tableSize=System.in.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        multLength = findLength(tableSize);
        fieldLength = findLength(tableSize*tableSize);
        createDelimiter();
        //curBuf.append();




    }
    void createDelimiter()
    {
        StringBuilder buf = new StringBuilder();
        buf.append("-".repeat(Math.max(0, multLength)));


        for (int i = 0 ;i < tableSize; ++i)
        {
            buf.append("+").append("-".repeat(Math.max(0, fieldLength)));
        }
        delimitr = buf.toString();

    }


    void editField(int i)
    {
        int spaceLength = multLength - findLength(i) ;
        curBuf.append(" ".repeat(Math.max(0, spaceLength))).append(curNum);
    }


    void fillTable()
    {
        for (int i = 0 ;i < tableSize; ++i)
        {
            int spaceLength = fieldLength - findLength(curNum);
            curBuf.append("|").append(" ".repeat(Math.max(0, spaceLength))).append(curNum);
            curNum+=mult;
        }

    }


}
