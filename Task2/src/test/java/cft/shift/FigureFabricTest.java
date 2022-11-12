package cft.shift;


import cft.shift.model.FigureType;
import cft.shift.model.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FigureFabricTest {
    @Test
    @DisplayName("Создание треугольника")
    void testCreateFigureTriangle() throws Exception {
            assertThat(FigureFabric.createFigure(1.,2.,3.).type).isEqualTo(FigureType.TRIANGLE);
            assertThat(FigureFabric.createFigure(1.,2.,3.).getPerimeter()).isEqualTo(6.);
    }


    @Test
    @DisplayName("Создание Прямоугольника")
    void testCreateFigureRectangle(){
            assertThat(FigureFabric.createFigure(1.,2.).type).isEqualTo(FigureType.RECTANGLE);
            assertThat(FigureFabric.createFigure(1.,2.).getPerimeter()).isEqualTo(6.);
    }

    @Test
    @DisplayName("Создание Круга")
    void testCreateFigureCircle(){
        assertThat(FigureFabric.createFigure(1.).type).isEqualTo(FigureType.CIRCLE);
        assertThat(FigureFabric.createFigure(1.).getPerimeter()).isEqualTo(Math.PI*2*1);
    }


    @Test
    @DisplayName("Создание треугольника через общую функцию")
    void testCreateFigureTriangleGeneric() throws Exception {
        ArrayList arr = new ArrayList<Double>();
        arr.add(1.);
        arr.add(2.);
        arr.add(2.);
        Shape cur = FigureFabric.createFigure(FigureType.TRIANGLE,arr);
        assertThat(cur.type).isEqualTo(FigureType.TRIANGLE);
        assertThat(cur.getPerimeter()).isEqualTo(5.);
    }


    @Test
    @DisplayName("Создание Прямоугольника через общую функцию")
    void testCreateFigureRectangleGeneric() throws Exception {
        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(1.);
        arr.add(2.);
        Shape cur = FigureFabric.createFigure(FigureType.RECTANGLE,arr);
        assertThat(cur.type).isEqualTo(FigureType.RECTANGLE);
        assertThat(FigureFabric.createFigure(1.,2.).getPerimeter()).isEqualTo(6.);
    }

    @Test
    @DisplayName("Создание Круга через общую функцию")
    void testCreateFigureCircleGeneric() throws Exception {
        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(1.);
        Shape cur = FigureFabric.createFigure(FigureType.CIRCLE,arr);
        assertThat(cur.type).isEqualTo(FigureType.CIRCLE);
        assertThat(cur.getPerimeter()).isEqualTo(Math.PI*2*1);
    }

}