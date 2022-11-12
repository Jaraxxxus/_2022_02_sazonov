package ru.cft.javaLessons.miner.view;

import ru.cft.javaLessons.miner.app.GameControl;
import ru.cft.javaLessons.miner.app.ModelListener;

public class View implements ModelListener {
    private final MainWindow mainWindow;
    private final WinWindow winWindow;
    private final GameControl control;
    private final LoseWindow loseWindow;
    private final HighScoresWindow highScoresWindow;
    private final SettingsWindow settingsWindow;

    public View(GameControl control) {
        this.control = control;
        mainWindow = new MainWindow();
        settingsWindow = new SettingsWindow(mainWindow);
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        highScoresWindow = new HighScoresWindow(mainWindow);
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        mainWindow.setNewGameMenuAction(e ->control.startNewGame());
        winWindow = new WinWindow(mainWindow);
        loseWindow = new LoseWindow(mainWindow);
        loseWindow.setExitListener(e -> mainWindow.dispose());
        winWindow.setExitListener(e -> mainWindow.dispose());
        start(GameType.NOVICE);
    }

    @Override
    public void onTimerChange(int value) {

    }

    @Override
    public void setGameLose() {
        //stop watch

        loseWindow.setVisible(true);

    }

    @Override
    public void setGameWin() {
        //stop watch
        winWindow.setVisible(true);
        winWindow.setNewGameListener(control);


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
        System.out.println(" smth change \n");

    }

    @Override
    public void setNewRecord(GameType gameType, String winnerName, int timeValue) {
        System.out.println("record new winner \n");

    }

    @Override
    public void start(GameType type) {

        int col = 0, row = 0, bombCount = 0;

        settingsWindow.setGameTypeListener(control);
        loseWindow.setNewGameListener(control);
        winWindow.setNewGameListener(control);
        mainWindow.setCellListener(control);
        mainWindow.setVisible(true);

        switch (type) {
            case NOVICE:  {
                col = 9;
                row = 9;
                bombCount = 10;
                break;

            }
            case MEDIUM: {
                col = 16;
                row = 16;
                bombCount = 40;
                break;
            }
            case EXPERT: {
                col = 16;
                row = 30;
                bombCount = 99;
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        mainWindow.createGameField(col, row);
        for(int i = 0; i< col;++i) {
            for(int j = 0; j < row; ++j){
                mainWindow.setCellImage(j, i, GameImage.CLOSED);
            }
        }
        mainWindow.setBombsCount(bombCount);
        mainWindow.setVisible(true);

    }


 /*   private void onStartNewGame(GameType type) {
        mainWindow.dispose();
        start(type);
    }*/
}
