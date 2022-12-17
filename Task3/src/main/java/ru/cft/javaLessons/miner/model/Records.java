package ru.cft.javaLessons.miner.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.view.GameType;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class Records {

    private static final Logger log = LoggerFactory.getLogger(Records.class);
    private final Hashtable<String, String> rec = new Hashtable<>();
    private final File file = new File("records.txt");


    Records() {
        if (file.exists() && file.canRead()) {
            try (FileReader reader = new FileReader(file)) {
                Scanner scan = new Scanner(reader);
                String str;
                while (scan.hasNextLine()) {
                    str = scan.nextLine();
                    String[] arrOfStr = str.split(":", 2);
                    rec.put(arrOfStr[0], arrOfStr[1]);
                }

            } catch (IOException e) {
                log.error("Не удалось открыть файл" + file.getName(), e);
            }
            if (rec.size() == 0) {
                rec.put("NOVICE", "Unknown-999");
                rec.put("MEDIUM", "Unknown-999");
                rec.put("EXPERT", "Unknown-999");
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("Не удалось создать файл" + file.getName(), e);
            }
            log.info("Файл " + file.getName() + " создан");
        }
    }

    public String getNameRecord(GameType gameType) {
        String str = null;
        switch (gameType) {
            case NOVICE -> str = rec.get("NOVICE").split("-")[0];
            case MEDIUM -> str = rec.get("MEDIUM").split("-")[0];
            case EXPERT -> str = rec.get("EXPERT").split("-")[0];
        }

        return str;
    }


    public Integer getTimeRecord(GameType gameType) {
        Integer time = null;
        switch (gameType) {
            case NOVICE -> time = Integer.parseInt(rec.get("NOVICE").split("-")[1]);
            case MEDIUM -> time = Integer.parseInt(rec.get("MEDIUM").split("-")[1]);
            case EXPERT -> time = Integer.parseInt(rec.get("EXPERT").split("-")[1]);
        }

        return time;
    }


    public void setRecord(GameType gameType, String name, int time) {
        String str = name + "-" + time;
        switch (gameType) {
            case NOVICE -> rec.put("NOVICE", str);
            case MEDIUM -> rec.put("MEDIUM", str);
            case EXPERT -> rec.put("EXPERT", str);
        }
    }


    public void writeRecord() {

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("Не удалось создать файл" + file.getName(), e);
            }
        }
        try (FileWriter writer = new FileWriter(file)) {
            String str;
            str = "NOVICE:" + rec.get("NOVICE");
            writer.write(str + "\n");
            str = "MEDIUM:" + rec.get("MEDIUM");
            writer.write(str + "\n");
            str = "EXPERT:" + rec.get("EXPERT");
            writer.write(str + "\n");
            log.info("Рекорд записан в файл " + file.getName());
        } catch (IOException e) {

            log.error("Не удалось записать рекорд в файл " + file.getName(), e);
        }
    }
}
