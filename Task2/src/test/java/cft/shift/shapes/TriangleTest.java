package cft.shift.shapes;



import cft.shift.exception.BadTriangleParams;
import cft.shift.exception.InvalidArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    @DisplayName("Треугольник с нулевым углом")
    void testZeroAngle()
    {
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            Triangle triangle = new Triangle(1, 1,5);
        });

        Assertions.assertEquals("Сторона треугольника 5.0 больше суммы двух других", thrown.getMessage());
    }

    @Test
    @DisplayName("Проверка периметра треугольника")
    void testCalculatePerimeterMethod()
    {
        Triangle triangle = null;
        try {
            triangle = new Triangle(3, 4,5);
        }
        catch (BadTriangleParams ignored) {
        }
        assertEquals(3+4+5, triangle.calculatePerimeter());
    }

    @Test
    @DisplayName("Проверка площади треугольника")
    void testCalculateAreaMethod()
    {
        Triangle triangle = null;
        try {
            triangle = new Triangle(3, 4,5);
        }
        catch (BadTriangleParams ignored)
        {

        }
        assertEquals((double)3*4/2,triangle.calculateArea());
    }

    @Test
    @DisplayName("поиск угла треугольника")
    void testFindAngleMethod()
    {
        Triangle triangle = null;
        try {
            triangle = new Triangle(3, 4,5);
        }
        catch (BadTriangleParams ignored)
        {

        }
        assertEquals(90.0,triangle.findAngle(5,4,3));
    }

    @Test
    @DisplayName("корректность параметров круга")
    void testOtherParams(){
        Triangle triangle = null;
        try {
            triangle = new Triangle(3, 4,5);
        }
        catch (BadTriangleParams ignored)
        {

        }
        assertThat(triangle.getUniqueParams()).containsExactly(3.0,triangle.findAngle(3,4,5),
                4.0, triangle.findAngle(4,3,5),
                5.0, triangle.findAngle(5,4,3));

    }



}