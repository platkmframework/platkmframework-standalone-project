package com.example.websocket.server;


import org.platkmframework.content.json.JsonUtil;
import org.platkmframework.util.JsonException;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class MessageDecoder  implements Decoder.Text<Message> {


	    @Override
	    public Message decode(String s) throws DecodeException {
	        try {
				return JsonUtil.jsonToObject(s, Message.class);
			} catch (JsonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	    }

	    @Override
	    public boolean willDecode(String s) {
	        return (s != null);
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
