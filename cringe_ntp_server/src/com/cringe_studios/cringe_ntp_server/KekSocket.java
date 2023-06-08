package com.cringe_studios.cringe_ntp_server;

import me.mrletsplay.simplehttpserver.http.websocket.WebSocketEndpoint;

public class KekSocket extends WebSocketEndpoint{	
	public void sendUpdates(long unixTimestamp) {
		super.getConnections().forEach(e -> e.sendText("Text"));
	}
}