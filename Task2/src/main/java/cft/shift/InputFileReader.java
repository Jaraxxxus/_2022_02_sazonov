package cft.shift;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class InputFileReader {
    BufferedReader reader;
    InputFileReader(String fileName) throws FileNotFoundException, IOException {

        FileReader fr;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            throw e;
        }
        reader = new BufferedReader(fr);
    }



/*
    String cur = null;
        try {
        cur=reader.readLine();
    } catch (IOException e) {
        logger.log(Level.WARNING, "can't read from file"+ fileName);

    }
*/
}
