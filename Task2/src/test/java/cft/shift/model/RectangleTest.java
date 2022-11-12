package cft.shift.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {
    @Test
    @DisplayName("Проверка периметра прямоугольника")
    void testCalculatePerimeterMethod()
    {
        Rectangle rectangle = new Rectangle(4,5);
        assertEquals((4+5)*2, rectangle.getPerimeter());
    }

    @Test
    @DisplayName("Проверка площади прямоугольника")
    void testCalculateAreaMethod()
    {
        Rectangle rectangle = new Rectangle(4,5);
        assertEquals(4*5, rectangle.getArea());
    }

    @Test
    @DisplayName("Проверка диагонали прямоугольника")
    void testCalculateDiagonalMethod()
    {
        Rectangle rectangle = new Rectangle(4,3);
        assertEquals(5, rectangle.calculateDiagonal());
    }

    @Test
    @DisplayName("Корректность параметров прямоугольника")
    void testOtherParams(){
        double a = Math.abs(Math.random())+1;
        double b = Math.abs(Math.random())+1;
        Rectangle rectangle = new Rectangle(a, b);
        assertThat(rectangle.getUniqueParams()).containsExactly(Math.max(a,b),Math.min(a,b),rectangle.calculateDiagonal());

    }




}