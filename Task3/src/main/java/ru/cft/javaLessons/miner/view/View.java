package ru.cft.javaLessons.miner.view;

import ru.cft.javaLessons.miner.app.GameController;
import ru.cft.javaLessons.miner.model.ModelListener;

public class View implements ModelListener {

    private final MainWindow mainWindow;
    private final WinWindow winWindow;
    private final GameController control;
    private final LoseWindow loseWindow;
    private final HighScoresWindow highScoresWindow;
    private final SettingsWindow settingsWindow;

    private final RecordsWindow recordWindow;

    public View(GameController control) {
        this.control = control;
        mainWindow = new MainWindow();
        settingsWindow = new SettingsWindow(mainWindow);
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        highScoresWindow = new HighScoresWindow(mainWindow);
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        mainWindow.setNewGameMenuAction(e -> control.startNewGame());
        winWindow = new WinWindow(mainWindow);
        loseWindow = new LoseWindow(mainWindow);
        loseWindow.setExitListener(e -> mainWindow.dispose());
        winWindow.setExitListener(e -> mainWindow.dispose());
        winWindow.setNewGameListener(control);

        recordWindow = new RecordsWindow(mainWindow);
        recordWindow.setNameListener(control);
        start(GameType.NOVICE);
    }


    @Override
    public void onTimerChange(int value) {
        mainWindow.setTimerValue(value);
    }

    @Override
    public void setGameLose() {
        loseWindow.setVisible(true);
    }

    @Override
    public void setGameWin() {
        winWindow.setVisible(true);
    }

    @Override
    public void onFieldSizeChanged(GameType type) {
        mainWindow.dispose();
        start(type);
    }

    @Override
    public void onsetCellImage(int col, int row, GameImage gameImage) {
        mainWindow.setCellImage(col, row, gameImage);
    }

    @Override
    public void onMineCountChanged(int bombsCount) {
        mainWindow.setBombsCount(bombsCount);
    }

    @Override
    public void onRecordChanged() {
        recordWindow.setVisible(true);
    }

    @Override
    public void setRecord(GameType gameType, String winnerName, int timeValue) {
        switch (gameType) {
            case NOVICE -> highScoresWindow.setNoviceRecord(winnerName, timeValue);
            case MEDIUM -> highScoresWindow.setMediumRecord(winnerName, timeValue);
            case EXPERT -> highScoresWindow.setExpertRecord(winnerName, timeValue);
            default -> throw new IllegalStateException("Unexpected value: " + gameType);
        }
    }

    @Override
    public void start(GameType type) {
        int col, row, bombCount;

        settingsWindow.setGameTypeListener(control);
        loseWindow.setNewGameListener(control);
        winWindow.setNewGameListener(control);
        mainWindow.setCellListener(control);
        mainWindow.setVisible(true);

        switch (type) {
            case NOVICE -> {
                col = 9;
                row = 9;
                bombCount = 10;
            }
            case MEDIUM -> {
                col = 16;
                row = 16;
                bombCount = 40;
            }
            case EXPERT -> {
                col = 16;
                row = 30;
                bombCount = 99;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        mainWindow.createGameField(col, row);
        for (int i = 0; i < col; ++i) {
            for (int j = 0; j < row; ++j) {
                mainWindow.setCellImage(j, i, GameImage.CLOSED);
            }
        }
        mainWindow.setBombsCount(bombCount);
        mainWindow.setVisible(true);
    }
}
