package ru.cft.javaLessons.miner.app;

import ru.cft.javaLessons.miner.view.GameType;
import ru.cft.javaLessons.miner.view.View;

public class Application {
    public static void main(String[] args) {
        SupperModel mod = new SupperModel();
        //MainWindow mainWindow = new MainWindow();

        //SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        //HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
        GameControl gameControl = new GameControl(mod);
        View view = new View(gameControl);
        mod.setModelListener(view);
        mod.startGame(GameType.NOVICE);
        //view.start(GameType.NOVICE);
        //mainWindow.setNewGameMenuAction(e -> gameControl.startNewGame()); //{ /* TODO */ });
        //mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        //mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        //mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        //mainWindow.setCellListener((x, y, buttonType) -> gameControl.onClick(x, y, buttonType)); //  });/* TODO */
        //mainWindow.setCellListener(gameControl);



        //mainWindow.createGameField(9, 9);
        //mainWindow.setVisible(true);
        //settingsWindow.setGameTypeListener(gameControl);

        // TODO: There is a sample code below, remove it after try
       // gameControl.run();

    /*    public boolean handleEvent(Event e) {
            switch (e.id) {

                case Event.MOUSE_DOWN:
                    // mouse button pressed
                    System.out.println("HELLO");
                    break;
                case Event.MOUSE_UP:
                    // mouse button released
                    gameControl.onClick(e.x, e.y, buttonType);
                    break;
                case Event.MOUSE_MOVE:
                    // mouse is being moved
                    break;
                case Event.MOUSE_DRAG:
                    // mouse is being dragged (button pressed)
                    break;
            }
            return false;
        }*/


        //mainWindow.setTimerValue(145);
        //mainWindow.setBombsCount(10);

      /*  mainWindow.setCellImage(0, 0, GameImage.EMPTY);
        mainWindow.setCellImage(0, 1, GameImage.CLOSED);
        mainWindow.setCellImage(0, 2, GameImage.MARKED);
        mainWindow.setCellImage(0, 3, GameImage.BOMB);
        mainWindow.setCellImage(1, 0, GameImage.NUM_1);
        mainWindow.setCellImage(1, 1, GameImage.NUM_2);
        mainWindow.setCellImage(1, 2, GameImage.NUM_3);
        mainWindow.setCellImage(1, 3, GameImage.NUM_4);
        mainWindow.setCellImage(1, 4, GameImage.NUM_5);
        mainWindow.setCellImage(1, 5, GameImage.NUM_6);
        mainWindow.setCellImage(1, 6, GameImage.NUM_7);
        mainWindow.setCellImage(1, 7, GameImage.NUM_8);*/
    }
}
