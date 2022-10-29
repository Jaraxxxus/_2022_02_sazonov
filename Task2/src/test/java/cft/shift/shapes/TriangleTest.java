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
    @DisplayName("����������� � ������� �����")
    void testZeroAngle()
    {
        InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
            Triangle triangle = new Triangle(1, 1,5);
        });

        Assertions.assertEquals("������� ������������ 5.0 ������ ����� ���� ������", thrown.getMessage());
    }

    @Test
    @DisplayName("�������� ��������� ������������")
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
    @DisplayName("�������� ������� ������������")
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
    @DisplayName("����� ���� ������������")
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
    @DisplayName("������������ ���������� �����")
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