package cft.shift.shapes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {
    @Test
    @DisplayName("�������� ��������� ��������������")
    void testCalculatePerimeterMethod()
    {
        Rectangle rectangle = new Rectangle(4,5);
        assertEquals((4+5)*2, rectangle.calculatePerimeter());
    }

    @Test
    @DisplayName("�������� ������� ��������������")
    void testCalculateAreaMethod()
    {
        Rectangle rectangle = new Rectangle(4,5);
        assertEquals(4*5, rectangle.calculateArea());
    }

    @Test
    @DisplayName("�������� ��������� ��������������")
    void testCalculateDiagonalMethod()
    {
        Rectangle rectangle = new Rectangle(4,3);
        assertEquals(5, rectangle.calculateDiagonal());
    }

    @Test
    @DisplayName("������������ ���������� ��������������")
    void testOtherParams(){
        double a = Math.abs(Math.random())+1;
        double b = Math.abs(Math.random())+1;
        Rectangle rectangle = new Rectangle(a, b);
        assertThat(rectangle.getUniqueParams()).containsExactly(Math.max(a,b),Math.min(a,b),rectangle.calculateDiagonal());

    }




}