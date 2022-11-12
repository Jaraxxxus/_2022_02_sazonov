package cft.shift.model;




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
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
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
        catch (Exception ignored) {
        }
        assertEquals(3+4+5, triangle.getPerimeter());
    }

    @Test
    @DisplayName("Проверка площади треугольника")
    void testCalculateAreaMethod()
    {
        Triangle triangle = null;
        try {
            triangle = new Triangle(3, 4,5);
        }
        catch (Exception ignored)
        {

        }
        assertEquals((double)3*4/2,triangle.getArea());
    }

    @Test
    @DisplayName("поиск угла треугольника")
    void testFindAngleMethod()
    {
        Triangle triangle = null;
        try {
            triangle = new Triangle(3, 4,5);
        }
        catch (Exception ignored)
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
        catch (Exception ignored)
        {

        }
        assertThat(triangle.getUniqueParams()).containsExactly(3.0,triangle.findAngle(3,4,5),
                4.0, triangle.findAngle(4,3,5),
                5.0, triangle.findAngle(5,4,3));

    }



}