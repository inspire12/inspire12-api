package com.inspire12.practice.api.lab.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.net.http.WebSocket;

@SpringBootApplication
public class SocketClient {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    public static void main(String[] args) {
        SpringApplication.run(SocketClient.class, args);
    }
}
