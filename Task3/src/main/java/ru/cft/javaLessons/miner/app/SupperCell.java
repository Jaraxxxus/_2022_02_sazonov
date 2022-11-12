package ru.cft.javaLessons.miner.app;

public class SupperCell {
    int row, col;
    int count = 0;
    boolean mine = false;

    CellType status;
    public SupperCell(int col, int row){
        this.row = row;
        this.col = col;
        this.mine = false;
        this.status = CellType.CLOSED;
        this.count = 0;
    }
    void open(){
        if (this.status != CellType.FLAGGED) {
            this.status = CellType.OPENED;
        }
    }
}
