package ru.cft.javaLessons.miner.app;

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

    void setNewRecord(GameType gameType, String winnerName, int timeValue);

    void start(GameType type);

}
