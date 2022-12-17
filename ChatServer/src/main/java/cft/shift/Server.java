package cft.shift;


import cft.shift.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Server {

    private static final Logger log = LoggerFactory.getLogger(Server.class);
    private final ExecutorService threadPool;
    private final int portNumber;
    private final Map<String, ClientHandler> connectionsMap;

    Server(int port) {
        this.threadPool = Executors.newCachedThreadPool();
        this.portNumber = port;
        connectionsMap = new ConcurrentHashMap<String, ClientHandler>();
    }

    void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("port = " + portNumber);
            log.info("Сервер запущен, порт = " + portNumber);
            while (!Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();
                log.info("Сервер принял входящее соединение с адреса {}", clientSocket.getRemoteSocketAddress());
                threadPool.submit(new ClientHandler(clientSocket, this));
            }
        } catch (IOException e) {
            log.error("Ошибка при работе сервера : " + e.getMessage());
        }
        shutdown();
    }

    void sendBroadcastMessage(Message message) {
        for (ClientHandler handler : connectionsMap.values()) {
            try {
                handler.sendMessage(message);
                log.info("Пользователем {} было отправлено сообщение.", handler.getUserName());
            } catch (IOException e) {
                log.error("Ошибка при отправке сообщения пользователя " + handler.getUserName() + " :", e);
            }
        }
    }

    boolean checkName(String userName) {
        log.info("connectionsMap.containsKey(userName) " + userName + " " + !connectionsMap.containsKey(userName));
        return !connectionsMap.containsKey(userName);
    }

    void newUser(String userName, ClientHandler handler) {
        connectionsMap.put(userName, handler);
    }

    void deleteUser(String newName) {
        log.info("connectionsMap.remove " + newName);
        connectionsMap.remove(newName);
    }

    Set<String> getAllUsers() {
        return connectionsMap.keySet();
    }

    private void shutdown() {
        threadPool.shutdown();
        log.info("Сервер остановлен.");
    }
}