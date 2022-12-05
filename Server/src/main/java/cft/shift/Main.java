package cft.shift;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser();
            Server handler = new Server(parser);
            handler.startServer();
        } catch (IOException e) {
            System.out.println("error");
        } catch (NumberFormatException e) {
            System.out.println("errpr1");
        }
    }
}