package ru.cft.javaLessons.miner.model;

public class SupperCell {

    public int row, col;
    public int count;
    public boolean mine = false;

    CellType status;

    public SupperCell(int col, int row) {
        this.row = row;
        this.col = col;
        this.mine = false;
        this.status = CellType.CLOSED;
        this.count = 0;
    }

    void open() {
        if (this.status != CellType.FLAGGED) {
            this.status = CellType.OPENED;
        }
    }
}
