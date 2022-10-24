package cft.shift;

import cft.shift.exception.InvalidArgumentException;
import cft.shift.shapes.Shape;
import org.slf4j.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        log.info("Старт программы");


        if (args.length<1) {
            log.error("недостаточно входных аргументов");
            return;
        }

        try {
            log.info("чтение входных данных");
            ArrayList<Double> data = InputFileReader.read(args[0]);
            log.info("создание фигуры");
            Shape figure = FigureFabric.createFigure(InputFileReader.getType() , data);

            if (args.length == 2) {

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
                    writer.write(ShapeFormatter.PrintShape(figure));
                }

            }else {
                log.info("Вывод в консоль");
                System.out.println(ShapeFormatter.PrintShape(figure));
            }
        } catch (FileNotFoundException ex) {
            log.error("Нет такого файла " + args[0]);
        }
        catch (InvalidArgumentException ex)
        {
            log.error(ex.getMessage());

        }

        catch (IOException ex)
        {
            log.error(ex.getMessage());
        }
    }
}
