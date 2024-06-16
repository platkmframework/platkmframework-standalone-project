package com.example.websocket.server;


import org.platkmframework.content.json.JsonUtil;
import org.platkmframework.util.JsonException;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message message) throws EncodeException {
        try {
			return JsonUtil.objectToJson(message);
		} catch (JsonException e) {
			e.printStackTrace();
			return "";
		}
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }

}