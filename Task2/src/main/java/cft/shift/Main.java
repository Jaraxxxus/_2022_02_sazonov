package cft.shift;

import cft.shift.model.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Старт программы");

        if (args.length < 1) {
            log.error("Недостаточно входных аргументов");
            return;
        }

        try {
            log.info("Чтение входных данных");
            ArrayList<Double> data = InputFileReader.read(args[0]);
            log.info("Создание фигуры");
            Shape figure = FigureFabric.createFigure(InputFileReader.getType(), data);

            if (args.length == 2) {

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
                    writer.write(ShapeFormatter.PrintShape(figure));
                }

            } else {
                log.info("Вывод в консоль");
                System.out.println(ShapeFormatter.PrintShape(figure));
            }
        } catch (FileNotFoundException ex) {
            log.error("Нет такого выходного файла " + args[0]);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
