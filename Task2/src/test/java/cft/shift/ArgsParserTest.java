package cft.shift;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ArgsParserTest {


    @Test
    @DisplayName("Имя капсом")
    void testRectangleCaps() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(5.);
        arr.add(9.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("RECTANGLE", arr);
        } catch (Exception ignored) {
        } finally {
            assertThat(args).containsExactly(5.0, 9.0);
        }
    }

    @Test
    @DisplayName("Имя маленькими буквами")
    void testCircleSmall() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(9.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("circle", arr);
        } catch (Exception ignored) {
        } finally {
            assertThat(args).containsExactly( 9.0);
        }
    }

    @Test
    @DisplayName("Круг с 2мя переменными")
    void testCircleWith2Args() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());


    }

    @Test
    @DisplayName("Круг без переменных")
    void testCircleWith0Args() {
        ArrayList<Double> arr = new ArrayList<>();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());
    }

    @Test
    @DisplayName("Прямоугольник с 3мя переменными")
    void testRectangleWith3Args() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        arr.add(2.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());


    }

    @Test
    @DisplayName("Прямоугольник без переменных")
    void testRectangleWith0Args() {
        ArrayList<Double> arr = new ArrayList<>();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());
    }

    @Test
    @DisplayName("Прямоугольник нормальный")
    void testRectangleNormal() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("rectangle", arr);
        } catch (Exception ignored) {
        } finally {
            assertThat(args).containsExactly(1.0, 2.0);
        }
    }

    @Test
    @DisplayName("Круг нормальный")
    void testCircleNormal() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("circle", arr);
        } catch (Exception ignored) {
        } finally {
            assertThat(args).containsExactly(1.0);
        }
    }

    @Test
    @DisplayName("Круг с нулем")
    void testCircleZero() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(0.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Круг с отрицательным радиусом")
    void testCircleMinus() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-2.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Прямоугольник с отрицательным параметром")
    void testRectangleWithMinusArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-1.);
        arr.add(3.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Прямоугольник с отрицательными параметрами")
    void testRectangleWithMinusArgs() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-1.);
        arr.add(-3.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Прямоугольник с нулевыми параметрами")
    void testRectangleWithZeroArgs() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(0.);
        arr.add(0.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Прямоугольник с нулевым параметром")
    void testRectangleWithZeroArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(3.);
        arr.add(0.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Прямоугольник с нулевым и  отрицательным параметром")
    void testRectangleWithMinusAndZeroArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-3.);
        arr.add(0.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }



    @Test
    @DisplayName("Опечатка в типе")
    void testRandomType() {
        ArrayList<Double> arr = new ArrayList<>();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("angle", arr);
        });
        Assertions.assertEquals("Type of figure is undetermined", thrown.getMessage());
    }

    @Test
    @DisplayName("Треугольник нормальный")
    void testTriangleNormal() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(5.);
        arr.add(3.);
        arr.add(4.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("triangle", arr);
        } catch (Exception ignored) {
        } finally {
            assertThat(args).containsExactly(5.0,3.,4.);
        }
    }

    @Test
    @DisplayName("Треугольник с нулевыми параметрами")
    void testTriangleWithZeroArgs() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(0.);
        arr.add(0.);
        arr.add(5.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("triangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Треугольник с нулевым и  отрицательным параметром")
    void testTriangleWithMinusAndZeroArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-3.);
        arr.add(0.);
        arr.add(3.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("triangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("Треугольник с 3мя переменными")
    void testTriangleWith4Args() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        arr.add(2.);
        arr.add(5.);
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            ArgsParser.parse("triangle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());
    }

}