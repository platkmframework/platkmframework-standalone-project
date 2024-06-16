package com.example.websocket.server;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

import org.platkmframework.content.json.JsonUtil;
import org.platkmframework.util.JsonException;

import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
 

@ServerEndpoint(value="/message", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class}) 
public class WebSocketServerMessage {
	
    private static Session session;
    
    /** only for test porpuse */
    private TestWebSocket testWebSocket; 

    @OnOpen
    public void onOpen(Session session) throws IOException {
    	WebSocketServerMessage.session = session;
    	WebSocketServerMessage.session.setMaxIdleTimeout(-1);
    	
    	/** only for test porpuse */
    	testWebSocket = new TestWebSocket(3000);
    	testWebSocket.start();
    }

    @OnMessage
    public void onMessage(Session session, Message message) 
      throws IOException {
    	try {
			System.out.println("[onMessage] Client send message : " + JsonUtil.objectToJson(message));
		
    	} catch (JsonException e) {
			e.printStackTrace();
		}
    }

    @OnClose
    public void onClose(Session session) throws IOException {
    	System.out.println("onClose");
    	testWebSocket.stop();
    }

    @OnError
    public void onError(Session session, Throwable e) {
    	e.printStackTrace();
    }

    public static void broadcastMessage(Message message) {
    	try {
    		if(WebSocketServerMessage.session.isOpen())
    			WebSocketServerMessage.session.getBasicRemote().sendObject(message);
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
    }
    
    
    /** only for test porpuse */
    class TestWebSocket implements Runnable{
    	
    	private Thread worker;
        private final AtomicBoolean running = new AtomicBoolean(false);
        private int interval;

        public TestWebSocket(int sleepInterval) {
            interval = sleepInterval;
        }
     
        public void start() {
            worker = new Thread(this);
            worker.start();
        }
     
        public void stop() {
            running.set(false);
        }

        public void run(){
            running.set(true);
            while (running.get()) {
                try {
                	
                    Thread.sleep(interval); 
                    WebSocketServerMessage.broadcastMessage(new Message("Message from server -> " +  LocalDateTime.now().getSecond()));
                    
                } catch (InterruptedException e){ 
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted, Failed to complete operation");
                }
           } 
        } 
    }
}
