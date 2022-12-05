package cft.shift;


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

    Server(Parser parser) {
        this.threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()/2);
        this.portNumber = parser.getPortNumber();
        connectionsMap = new ConcurrentHashMap<String, ClientHandler>();
    }

    void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            log.info("Сервер запущен");
            while (!Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();
                log.info("Сервер принял входящее соединение с адреса {}", clientSocket.getRemoteSocketAddress());
                threadPool.submit(new ClientHandler(clientSocket, this));
            }
        } catch (IOException e) {
            log.error("Ошибка при работе сервера : ", e.getMessage());
        }
        shutdown();

    }

    void sendBroadcastMessage(Message message) {
        for (ClientHandler handler : connectionsMap.values()) {
            try {
                handler.sendMessage(message);
                log.info("Пользователю {} отправлено сообщение.", handler.getAuthorizedUserName());
            } catch (IOException e) {
                log.error("Ошибка при отправке сообщения пользователю " + handler.getAuthorizedUserName() + " :", e);
            }
        }
    }

    boolean isNameAvailable(String userName) {
        return !connectionsMap.containsKey(userName);
    }

    void newUser(String userName, ClientHandler handler) {
        connectionsMap.put(userName, handler);
    }

    void deleteUser(String newName) {
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