package cft.shift;

import cft.shift.exception.InvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ArgsParser {

    private static final Logger log = LoggerFactory.getLogger(ArgsParser.class);
    static FigureType type;
    public static ArrayList<Double> parse(String typeName, ArrayList<Double> arr) throws InvalidArgumentException {
        int size = arr.size();
        typeName = typeName.toUpperCase();
        if(typeName != null && (typeName.equals("CIRCLE")  || typeName.equals("RECTANGLE") ||
                typeName.equals("TRIANGLE") )) {
            for (Double val : arr) {
                if (val <= 0){
                    log.error("Аргумент "+val +" меньше/равен нулю" );
                    throw  new InvalidArgumentException("Value must be greater than zero");
                }
            }


            if (typeName.equals("CIRCLE") && size == 1){
                type =FigureType.CIRCLE;

            } else if (typeName.equals("RECTANGLE") && size == 2){
                type = FigureType.RECTANGLE;

            } else if (typeName.equals("TRIANGLE") && size == 3){
                type = FigureType.TRIANGLE;

            }
            else {
                log.error("Тип фигуры несоответствует кол-ву параметров" );
                throw new InvalidArgumentException("Unexpected number of params");
            }
            return arr;
        }else {
            log.error("Неизвестный тип фигуры" );
            throw new InvalidArgumentException("Type of figure is undetermined");
        }
    }

    public static FigureType getType()
    {
        return type;
    }
}
