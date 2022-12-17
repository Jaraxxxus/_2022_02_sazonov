package ru.cft.javaLessons.miner.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.model.SupperModel;
import ru.cft.javaLessons.miner.view.GameType;
import ru.cft.javaLessons.miner.view.View;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SupperModel model = new SupperModel();
        GameController gameControl = new GameController(model);
        View view = new View();
        view.setListener(gameControl);
        model.setModelListener(view);
        model.startGame(GameType.NOVICE);
        log.info("Игра запущена");
    }

}
