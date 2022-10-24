package cft.shift;

import cft.shift.exception.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class InputFileReader {
    static BufferedReader reader;

    public static ArrayList<Double> read(String file) throws IOException, InvalidArgumentException {
        FileReader fr;
        fr = new FileReader(file);
        reader = new BufferedReader(fr);

        ArrayList<Double> val = new ArrayList<Double>();
        String type = reader.readLine();
        String curString = reader.readLine();
        fr.close();
        if(curString != null){
            String[] arr = curString.split(" ");
            for (String word : arr) {
                val.add(Double.valueOf(word.toString()));
            }
        }
        return ArgsParser.parse(type, val);
    }

    public static FigureType getType(){
        return ArgsParser.getType();
    }

}




