package com.inspire12.practice.api.lab.socket;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint(value = "/websocket")
public class Socket {
    private Session session;
    public static Set<Socket> listeners = new CopyOnWriteArraySet<>();
    private static int onlineCount = 0;

    @OnOpen
    public void onOpen(Session session) {
        onlineCount++;
        this.session = session;
        listeners.add(this);
        log.info("onOpen called, userCount: {}", onlineCount);
    }

    @OnClose
    public void onClose(Session session) {
        onlineCount--;
        listeners.remove(this);
        log.info("onClose called, userCount: {}", onlineCount);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("onMessage called, message: {}", message);
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.warn("onClose called, error: {}", throwable.getMessage());
        listeners.remove(this);
        onlineCount--;
    }

    public static void broadcast(String message) {
        for (Socket listener: listeners) {
            listener.sendMessage(message);
        }
    }

    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.warn("Caught exception while sending message to Session {}", this.session.getId() + "error:" + e.getMessage());
        }
    }
}
