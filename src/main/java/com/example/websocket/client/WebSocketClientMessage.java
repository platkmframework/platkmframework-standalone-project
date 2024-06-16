package com.example.websocket.client;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
 

@WebSocket
public class WebSocketClientMessage {

    protected WebSocketClient webSocketClient;
    URI serverURI;
    Session session;

    public WebSocketClientMessage() {
    	HttpClient httpClient = new HttpClient();
    	webSocketClient = new WebSocketClient(httpClient);
    }

    public void connect(String sServer) {
    	serverURI = URI.create(sServer);
    	try {
			CompletableFuture<Session> clientSessionPromise = webSocketClient.connect(this, serverURI);
			session = clientSessionPromise.get();
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    }

    public void sendMessage(String sMsg) throws IOException {
    	RemoteEndpoint remote = session.getRemote();
    	remote.sendString(sMsg);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    	System.out.print("onClose");
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("Message from server -> " +msg);
    }

    public void disconnect() throws IOException {
    	session.close();
    }
}
