package cft.shift;

import cft.shift.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ArgsParserTest {


    @Test
    @DisplayName("��� ������")
    void testRectangleCaps() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(5.);
        arr.add(9.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("RECTANGLE", arr);
        } catch (InvalidArgumentException ignored) {
        } finally {
            assertThat(args).containsExactly(5.0, 9.0);
        }
    }

    @Test
    @DisplayName("��� ���������� �������")
    void testCircleSmall() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(9.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("circle", arr);
        } catch (InvalidArgumentException ignored) {
        } finally {
            assertThat(args).containsExactly( 9.0);
        }
    }

    @Test
    @DisplayName("���� � 2�� �����������")
    void testCircleWith2Args() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());


    }

    @Test
    @DisplayName("���� ��� ����������")
    void testCircleWith0Args() {
        ArrayList<Double> arr = new ArrayList<>();
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());
    }

    @Test
    @DisplayName("������������� � 3�� �����������")
    void testRectangleWith3Args() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        arr.add(2.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());


    }

    @Test
    @DisplayName("������������� ��� ����������")
    void testRectangleWith0Args() {
        ArrayList<Double> arr = new ArrayList<>();
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());
    }

    @Test
    @DisplayName("������������� ����������")
    void testRectangleNormal() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("rectangle", arr);
        } catch (InvalidArgumentException ignored) {
        } finally {
            assertThat(args).containsExactly(1.0, 2.0);
        }
    }

    @Test
    @DisplayName("���� ����������")
    void testCircleNormal() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("circle", arr);
        } catch (InvalidArgumentException ignored) {
        } finally {
            assertThat(args).containsExactly(1.0);
        }
    }

    @Test
    @DisplayName("���� � �����")
    void testCircleZero() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(0.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("���� � ������������� ��������")
    void testCircleMinus() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-2.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("circle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("������������� � ������������� ����������")
    void testRectangleWithMinusArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-1.);
        arr.add(3.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("������������� � �������������� �����������")
    void testRectangleWithMinusArgs() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-1.);
        arr.add(-3.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("������������� � �������� �����������")
    void testRectangleWithZeroArgs() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(0.);
        arr.add(0.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("������������� � ������� ����������")
    void testRectangleWithZeroArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(3.);
        arr.add(0.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("������������� � ������� �  ������������� ����������")
    void testRectangleWithMinusAndZeroArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-3.);
        arr.add(0.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("rectangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }



    @Test
    @DisplayName("�������� � ����")
    void testRandomType() {
        ArrayList<Double> arr = new ArrayList<>();
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("angle", arr);
        });
        Assertions.assertEquals("Type of figure is undetermined", thrown.getMessage());
    }

    @Test
    @DisplayName("����������� ����������")
    void testTriangleNormal() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(5.);
        arr.add(3.);
        arr.add(4.);
        ArrayList<Double> args = null;
        try {
            args = ArgsParser.parse("triangle", arr);
        } catch (InvalidArgumentException ignored) {
        } finally {
            assertThat(args).containsExactly(5.0,3.,4.);
        }
    }

    @Test
    @DisplayName("����������� � �������� �����������")
    void testTriangleWithZeroArgs() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(0.);
        arr.add(0.);
        arr.add(5.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("triangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("����������� � ������� �  ������������� ����������")
    void testTriangleWithMinusAndZeroArg() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(-3.);
        arr.add(0.);
        arr.add(3.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("triangle", arr);
        });
        Assertions.assertEquals("Value must be greater than zero", thrown.getMessage());
    }

    @Test
    @DisplayName("����������� � 3�� �����������")
    void testTriangleWith4Args() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.);
        arr.add(2.);
        arr.add(2.);
        arr.add(5.);
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            ArgsParser.parse("triangle", arr);
        });
        Assertions.assertEquals("Unexpected number of params", thrown.getMessage());


    }

}