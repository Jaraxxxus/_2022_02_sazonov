package cft.shift;

public class Task1 {


    public static void printTable(int tableSize)
    {

        int multLength = findLength(tableSize);
        int fieldLength = findLength(tableSize*tableSize);
        int mult = 1;

        String delimiter = createDelimiter(multLength,tableSize,fieldLength);

        System.out.print(editField(multLength));
        System.out.println( fillRow(mult, tableSize, fieldLength));
        System.out.println(delimiter);

        while(mult<=tableSize)
        {

            System.out.print(editField(mult, multLength));
            System.out.println( fillRow(mult, tableSize, fieldLength));



            mult++;
            System.out.println(delimiter);
        }
    }



    private static String createDelimiter(int multLength, int tableSize,int fieldLength)
    {
        StringBuilder buf = new StringBuilder();
        buf.append("-".repeat(Math.max(0, multLength)));

        for (int i = 0 ;i < tableSize; ++i)
        {
            buf.append("+").append("-".repeat(Math.max(0, fieldLength)));
        }
        return buf.toString();

    }

    private static int findLength(int num)
    {
        String str =Integer.toString(num);
        return str.length();
    }


    private static String editField(int curMultiplayer, int multLength)
    {
        int spaceLength = multLength - findLength(curMultiplayer) ;
        StringBuilder buf = new StringBuilder();
        buf.append(" ".repeat(Math.max(0, spaceLength))).append(curMultiplayer);
        return buf.toString();

    }
    private static String editField( int multLength)
    {
        StringBuilder curBuf = new StringBuilder();
        curBuf.append(" ".repeat(Math.max(0, multLength)));
        return curBuf.toString();
    }
    private static String fillRow(int curNum, int tableSize, int fieldLength)
    {
        StringBuilder curBuf = new StringBuilder();
        int curMult = curNum;
        for (int i = 0 ;i < tableSize; ++i)
        {
            int spaceLength = fieldLength - findLength(curNum);
            curBuf.append("|").append(" ".repeat(Math.max(0, spaceLength))).append(curNum);
            curNum+=curMult;
        }
        return curBuf.toString();

    }


}