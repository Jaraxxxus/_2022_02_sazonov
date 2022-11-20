package ru.cft.javaLessons.miner.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.view.GameImage;
import ru.cft.javaLessons.miner.view.GameType;

import java.util.Hashtable;
import java.util.stream.IntStream;

import static ru.cft.javaLessons.miner.view.GameType.*;

public class SupperModel {

    private static final Logger log = LoggerFactory.getLogger(SupperModel.class);
    private final Hashtable<Integer, GameImage> cellImage;
    private GameType type;
    private ModelListener listener;
    private int rowCount, colCount, mineCount;
    private boolean firstClick;

    private String curRecordName;
    private SupperCell[][] cellsTable;
    private Timer timer;
    private final Records records;
    private int size, openedCount = 0, flagsCount = 0;

    public SupperModel() {
        records = new Records();
        firstClick = false;
        cellImage = new Hashtable<>();
        cellImage.put(0, GameImage.EMPTY);
        cellImage.put(1, GameImage.NUM_1);
        cellImage.put(2, GameImage.NUM_2);
        cellImage.put(3, GameImage.NUM_3);
        cellImage.put(4, GameImage.NUM_4);
        cellImage.put(5, GameImage.NUM_5);
        cellImage.put(6, GameImage.NUM_6);
        cellImage.put(7, GameImage.NUM_7);
        cellImage.put(8, GameImage.NUM_8);
    }

    public void setModelListener(ModelListener listener) {
        this.listener = listener;
        timer = new Timer(listener);
        listener.setRecord(NOVICE, records.getNameRecord(NOVICE), records.getTimeRecord(NOVICE));
        listener.setRecord(MEDIUM, records.getNameRecord(MEDIUM), records.getTimeRecord(MEDIUM));
        listener.setRecord(EXPERT, records.getNameRecord(EXPERT), records.getTimeRecord(EXPERT));
    }


    public void changeGameType(GameType type) {
        cellsTable = null;
        listener.onFieldSizeChanged(type);
        startGame(type);
        log.info("Сложность изменена на " + type);
    }

    public void startGame() {
        startGame(type);
    }
    public void startGame(GameType gameType) {
        type = gameType;
        flagsCount = 0;
        openedCount = 0;
        switch (gameType) {
            case NOVICE -> {
                rowCount = 9;
                colCount = 9;
                mineCount = 10;
            }
            case MEDIUM -> {
                rowCount = 16;
                colCount = 16;
                mineCount = 40;
            }
            case EXPERT -> {
                rowCount = 16;
                colCount = 30;
                mineCount = 99;
            }
        }
        size = rowCount * colCount;
        firstClick = false;

        cellsTable = new SupperCell[colCount][rowCount];
        for (int j = 0; j < colCount; j++) {
            for (int i = 0; i < rowCount; i++) {
                cellsTable[j][i] = new SupperCell(j, i);
            }
        }

        listener.start(type);
        timer.stopTime();
    }


    public void openCell(int col, int row) {
        if (getCell(col, row).status.equals(CellType.CLOSED))
            innerOpenCell(col, row);
    }


    public void changeStatus(int col, int row) {
        SupperCell cell = getCell(col, row);
        if (cell.status == CellType.OPENED) return;
        if (cell.status == CellType.FLAGGED) {
            cell.status = CellType.CLOSED;
            listener.onsetCellImage(col, row, GameImage.CLOSED);
            flagsCount--;
        } else if (flagsCount < mineCount) {
            cell.status = CellType.FLAGGED;
            listener.onsetCellImage(col, row, GameImage.MARKED);
            flagsCount++;
        }
        listener.onMineCountChanged(mineCount - flagsCount);
    }


    public void openAround(int col, int row) {
        SupperCell cell = getCell(col, row);
        if (!cell.status.equals(CellType.OPENED)) return;
        if (cell.count < 1) return;
        int[] colArray = IntStream.range(col - 1, col + 2).toArray();
        int[] rowArray = IntStream.range(row - 1, row + 2).toArray();
        int countFlags = 0;

        for (int i : colArray) {
            for (int j : rowArray) {
                if (i > -1 && j > -1 && i < colCount && j < rowCount &&
                        cellsTable[i][j] != null && cellsTable[i][j].status == CellType.FLAGGED)
                    countFlags++;
            }
        }

        if (countFlags == cell.count) {
            for (int i : colArray) {
                for (int j : rowArray) {
                    if (i > -1 && j > -1 && i < colCount && j < rowCount &&
                            cellsTable[i][j] != null && cellsTable[i][j].status == CellType.CLOSED)
                        innerOpenCell(i, j);
                }
            }
        }
    }


    private void innerOpenCell(int col, int row) {
        SupperCell cell = getCell(col, row);

        if (cell != null) {
            if (cell.status.equals(CellType.OPENED)) return;
            cell.open();

            if (!firstClick) {
                firstClick = true;

                timer.restartTime();
                prepareField(col, row);
            }

            if (cell.mine) {
                log.info("Клетка " + col + " " + row + " содержит бомбу, конец игры");
                innerOpenCell(col, row);
                gameOver();
                return;
            }

            if (cell.status == CellType.FLAGGED) {
                flagsCount--;
                listener.onMineCountChanged(mineCount - flagsCount);
            }

            cell.status = CellType.OPENED;
            openedCount++;
            log.info("Клетка " + col + " " + row + " открыта ");
            listener.onsetCellImage(col, row, cellImage.get(cell.count));
            if (cellsTable[col][row].count == 0) {
                int[] colArray = IntStream.range(col - 1, col + 2).toArray();
                int[] rowArray = IntStream.range(row - 1, row + 2).toArray();
                for (int i : rowArray) {
                    for (int j : colArray) {
                        innerOpenCell(j, i);
                    }
                }
            }
            if (isWin()) {
                gameWin();
            }
        }
    }


    private boolean isWin() {
        return size - mineCount - openedCount == 0;
    }


    private void gameOver() {
        showAllMines();
        timer.stopTime();
        log.info("Игра проиграна");
        listener.setGameLose();
    }


    private void gameWin() {
        timer.stopTime();

        log.info("Все пустые клетки открыты, победа");
        int curTime = timer.getTime();
        int oldTime = records.getTimeRecord(type);

        if (curTime <= oldTime) {
            listener.onRecordChanged();
            log.info("Рекорд поставлен " + curRecordName + " за " + curTime + "секунд на сложности " + type);
            records.setRecord(type, curRecordName, curTime);
            listener.setRecord(type, records.getNameRecord(type), records.getTimeRecord(type));
            records.writeRecord();
        }
        listener.setGameWin();
    }


    private void showAllMines() {
        for (int i = 0; i < colCount; ++i) {
            for (int j = 0; j < rowCount; ++j) {
                if (cellsTable[i][j].mine)
                    listener.onsetCellImage(i, j, GameImage.BOMB);
            }
        }
    }


    private void prepareField(int colStart, int rowStart) {
        scatterMines(colStart, rowStart);
        for (int j = 0; j < colCount; j++) {
            for (int i = 0; i < rowCount; i++) {
                countMines(cellsTable[j][i]);
            }
        }
        log.info("Мины разбросаны ");
    }

    private void scatterMines(int colStart, int rowStart) {
        for (int i = 0; i < mineCount; i++) {

            while (true) {
                int row = (int) (Math.random() * rowCount);
                int col = (int) (Math.random() * colCount);
                SupperCell cell = getCell(col, row);
                if (!(Math.abs(row - rowStart) < 2 && Math.abs(col - colStart) < 2) && (cell.status != CellType.OPENED) && (!cell.mine)) {
                    cell.mine = true;
                    log.info("Бомба помещена в клетку " + col + " " + row);
                    break;
                }
            }
        }
    }


    private SupperCell getCell(int col, int row) {
        if (row < 0 || col < 0 || row > rowCount - 1 || col > colCount - 1) {
            return null;
        }

        return cellsTable[col][row];
    }


    private void countMines(SupperCell cell) {
        int x = cell.row;
        int y = cell.col;
        int[] colArray = IntStream.range(y - 1, y + 2).toArray();
        int[] rowArray = IntStream.range(x - 1, x + 2).toArray();
        for (int i : colArray) {
            for (int j : rowArray) {
                if (i > -1 && j > -1 && i < colCount && j < rowCount
                        && cellsTable[i][j] != null && cellsTable[i][j].mine)
                    cell.count++;
            }
        }
    }


    public void setName(String name) {
        curRecordName = name;
    }

}



