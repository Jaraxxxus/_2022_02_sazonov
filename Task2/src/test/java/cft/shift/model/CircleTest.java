package cft.shift.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;





class CircleTest {

    @Test
    @DisplayName("Корректный перимитр круга")
    void testCalculateRadius(){
        double a = Math.abs(Math.random())+1;
        Circle circle = new Circle(a);
        assertEquals(circle.getPerimeter(),2*Math.PI*a);
    }

    @Test
    @DisplayName("Корректная площадь круга")
    void testCalculateArea(){
        double a = Math.abs(Math.random())+1;
        Circle circle = new Circle(a);
        assertEquals(circle.getPerimeter(),2*Math.PI*a);
    }

    @Test
    @DisplayName("корректность параметров круга")
    void testOtherParams(){
        double a = Math.abs(Math.random())+1;
        Circle circle = new Circle(a);
        assertThat(circle.getUniqueParams()).containsExactly(a, 2*a);

    }

}