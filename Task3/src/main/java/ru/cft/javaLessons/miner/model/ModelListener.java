package ru.cft.javaLessons.miner.model;

import ru.cft.javaLessons.miner.view.GameImage;
import ru.cft.javaLessons.miner.view.GameType;

public interface ModelListener {

    void onTimerChange(int value);

    void setGameLose();

    void setGameWin();

    void onFieldSizeChanged(GameType type);

    void onsetCellImage(int col, int row, GameImage gameImage);

    void onMineCountChanged(int mineCount);

    void onRecordChanged();

    void setRecord(GameType gameType, String winnerName, int timeValue);

    void start(GameType type);

}
