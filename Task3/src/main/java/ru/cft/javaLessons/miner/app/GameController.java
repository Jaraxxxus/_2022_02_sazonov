package ru.cft.javaLessons.miner.app;

import ru.cft.javaLessons.miner.model.SupperModel;
import ru.cft.javaLessons.miner.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener, GameTypeListener, CellEventListener, RecordNameListener {
    private final SupperModel model;

    public GameController(SupperModel model) {
        this.model = model;
    }


    public void startNewGame() {
        model.startGame();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        startNewGame();
    }


    @Override
    public void onGameTypeChanged(GameType gameType) {
        model.changeGameType(gameType);
    }


    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        if (buttonType == ButtonType.LEFT_BUTTON) {
            model.openCell(x, y);
        } else if (buttonType == ButtonType.RIGHT_BUTTON) {
            model.changeStatus(x, y);
        } else if (buttonType == ButtonType.MIDDLE_BUTTON) {
            model.openAround(x, y);
        }
    }


    @Override
    public void onRecordNameEntered(String name) {
        model.setName(name);
    }
}
