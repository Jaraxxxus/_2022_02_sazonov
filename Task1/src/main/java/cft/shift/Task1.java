package cft.shift;

public class Task1 {

    static final int EXPECTED_BUFFER_CAPACITY = 170;
    public static void printTable(int tableSize)
    {

        int factorLength = findLength(tableSize);
        int fieldLength = findLength(tableSize*tableSize);
        int factor = 1;

        String delimiter = createDelimiter(factorLength,tableSize,fieldLength);

        System.out.print(editField(factorLength));
        System.out.println( fillRow(factor, tableSize, fieldLength));
        System.out.println(delimiter);

        while(factor<=tableSize)
        {

            System.out.print(editField(factor, factorLength));
            System.out.println( fillRow(factor, tableSize, fieldLength));
            System.out.println(delimiter);
            ++factor;

        }
    }



    private static String createDelimiter(int factorLength, int tableSize,int fieldLength)
    {
        StringBuilder buf = new StringBuilder(EXPECTED_BUFFER_CAPACITY);
        buf.append("-".repeat(Math.max(0, factorLength)));

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


    private static String editField(int curFactor, int factorLength)
    {
        int spaceLength = factorLength - findLength(curFactor) ;
        StringBuilder buf = new StringBuilder(EXPECTED_BUFFER_CAPACITY);
        buf.append(" ".repeat(Math.max(0, spaceLength))).append(curFactor);
        return buf.toString();

    }
    private static String editField( int factorLength)
    {
        StringBuilder curBuf = new StringBuilder();
        curBuf.append(" ".repeat(Math.max(0, factorLength)));
        return curBuf.toString();
    }
    private static String fillRow(int curProduct, int tableSize, int fieldLength)
    {
        StringBuilder curBuf = new StringBuilder(EXPECTED_BUFFER_CAPACITY);
        int curFactor = curProduct;
        for (int i = 0 ;i < tableSize; ++i)
        {
            int spaceLength = fieldLength - findLength(curProduct);
            curBuf.append("|").append(" ".repeat(Math.max(0, spaceLength))).append(curProduct);
            curProduct+=curFactor;
        }
        return curBuf.toString();

    }
}