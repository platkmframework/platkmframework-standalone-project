package com.example.websocket.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import jakarta.websocket.ClientEndpointConfig;
import jakarta.websocket.CloseReason;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import org.eclipse.jetty.util.component.LifeCycle;
import org.platkmframework.content.json.JsonUtil;
import org.platkmframework.util.JsonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.websocket.server.Message;

public class MainWebSocketClientExample {

	public static void main(String[] args) throws IOException, InterruptedException, DeploymentException, URISyntaxException, JsonException {
	
	      URI uri = URI.create("ws://localhost:8082/ctx/message");

	        if (args.length == 1)
	            uri = new URI(args[0]);

	        WebSocketContainer client = ContainerProvider.getWebSocketContainer();

	        try
	        {
	        	MainWebSocketClientExample.performEcho(client, uri);
	        }
	        finally
	        {
	            LifeCycle.stop(client);
	        }
	}

	public static void performEcho(WebSocketContainer client, URI uri) throws IOException, InterruptedException, DeploymentException, JsonException
    {
        EchoClientEndpoint echoSocket = new EchoClientEndpoint();
        ClientEndpointConfig endpointConfig = ClientEndpointConfig.Builder.create().build();
        try (Session session = client.connectToServer(echoSocket, endpointConfig, uri))
        {
        	JsonUtil.init();
            session.getBasicRemote().sendText(JsonUtil.objectToJson(new Message("Hello from de client")));
            
            Thread.sleep(20000);
         
            session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Goodbye"));
            
            if (!echoSocket.closeLatch.await(5, TimeUnit.SECONDS))
                throw new IOException("Failed to receive WebSocket close");
        }
    }
	
	public static class EchoClientEndpoint extends Endpoint implements MessageHandler.Whole<String>
    {
        private static final Logger LOG = LoggerFactory.getLogger(EchoClientEndpoint.class);
        private final CountDownLatch closeLatch = new CountDownLatch(1);

        @Override
        public void onClose(Session session, CloseReason closeReason)
        {
            LOG.info("WebSocket Close: {}", closeReason);
            closeLatch.countDown();
        }

        @Override
        public void onError(Session session, Throwable cause)
        {
            LOG.warn("WebSocket Error", cause);
        }

        @Override
        public void onOpen(Session session, EndpointConfig config)
        {
            LOG.info("WebSocket Open: {}", session);
            session.addMessageHandler(this);
        }

        @Override
        public void onMessage(String message)
        {
            LOG.info("Text Message [{}]", message);
        }
    }
}
