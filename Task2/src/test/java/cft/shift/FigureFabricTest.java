package cft.shift;

import cft.shift.exception.BadTriangleParams;
import cft.shift.shapes.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FigureFabricTest {
    @Test
    @DisplayName("�������� ������������")
    void testCreateFigureTriangle() throws BadTriangleParams {
            assertThat(FigureFabric.createFigure(1.,2.,3.).type).isEqualTo(FigureType.TRIANGLE);
            assertThat(FigureFabric.createFigure(1.,2.,3.).calculatePerimeter()).isEqualTo(6.);
    }


    @Test
    @DisplayName("�������� ��������������")
    void testCreateFigureRectangle(){
            assertThat(FigureFabric.createFigure(1.,2.).type).isEqualTo(FigureType.RECTANGLE);
            assertThat(FigureFabric.createFigure(1.,2.).calculatePerimeter()).isEqualTo(6.);
    }

    @Test
    @DisplayName("�������� �����")
    void testCreateFigureCircle(){
        assertThat(FigureFabric.createFigure(1.).type).isEqualTo(FigureType.CIRCLE);
        assertThat(FigureFabric.createFigure(1.).calculatePerimeter()).isEqualTo(Math.PI*2*1);
    }


    @Test
    @DisplayName("�������� ������������ ����� ����� �������")
    void testCreateFigureTriangleGeneric() throws BadTriangleParams {
        ArrayList arr = new ArrayList<Double>();
        arr.add(1.);
        arr.add(2.);
        arr.add(2.);
        Shape cur = FigureFabric.createFigure(FigureType.TRIANGLE,arr);
        assertThat(cur.type).isEqualTo(FigureType.TRIANGLE);
        assertThat(cur.calculatePerimeter()).isEqualTo(5.);
    }


    @Test
    @DisplayName("�������� �������������� ����� ����� �������")
    void testCreateFigureRectangleGeneric() throws BadTriangleParams {
        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(1.);
        arr.add(2.);
        Shape cur = FigureFabric.createFigure(FigureType.RECTANGLE,arr);
        assertThat(cur.type).isEqualTo(FigureType.RECTANGLE);
        assertThat(FigureFabric.createFigure(1.,2.).calculatePerimeter()).isEqualTo(6.);
    }

    @Test
    @DisplayName("�������� ����� ����� ����� �������")
    void testCreateFigureCircleGeneric() throws BadTriangleParams {
        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(1.);
        Shape cur = FigureFabric.createFigure(FigureType.CIRCLE,arr);
        assertThat(cur.type).isEqualTo(FigureType.CIRCLE);
        assertThat(cur.calculatePerimeter()).isEqualTo(Math.PI*2*1);
    }

}