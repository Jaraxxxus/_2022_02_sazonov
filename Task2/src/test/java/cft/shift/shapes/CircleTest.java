package cft.shift.shapes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;





class CircleTest {

    @Test
    @DisplayName("���������� �������� �����")
    void testCalculateRadius(){
        double a = Math.abs(Math.random())+1;
        Circle circle = new Circle(a);
        assertEquals(circle.calculatePerimeter(),2*Math.PI*a);
    }

    @Test
    @DisplayName("���������� ������� �����")
    void testCalculateArea(){
        double a = Math.abs(Math.random())+1;
        Circle circle = new Circle(a);
        assertEquals(circle.calculatePerimeter(),2*Math.PI*a);
    }

    @Test
    @DisplayName("������������ ���������� �����")
    void testOtherParams(){
        double a = Math.abs(Math.random())+1;
        Circle circle = new Circle(a);
        assertThat(circle.getUniqueParams()).containsExactly(a, 2*a);

    }

}