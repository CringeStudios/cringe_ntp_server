package com.cringe_studios.cringe_ntp_server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import me.mrletsplay.mrcore.json.JSONObject;
import me.mrletsplay.mrcore.json.JSONParseException;
import me.mrletsplay.simplehttpserver.http.HttpRequestMethod;
import me.mrletsplay.simplehttpserver.http.server.HttpServer;
import me.mrletsplay.simplehttpserver.http.server.HttpServerConfiguration;

public class CringeNTP {
	private static KekSocket websockets;

	public static void main(String[] args) throws IOException {
		Path configPath = Paths.get("config.json");
		if(!Files.exists(configPath)) Files.createFile(configPath);

		String configStr = Files.readString(configPath);
		if(configStr.isEmpty()) {
			JSONObject config = new JSONObject();
			config.put("host", "0.0.0.0");
			config.put("port", 42069);
			Files.writeString(configPath, config.toFancyString(), StandardCharsets.UTF_8);
			System.out.println(Messages.CONFIG_CREATED);
			return;
		}

		JSONObject config;
		try {
			config = new JSONObject(configStr);
		}catch(JSONParseException | ClassCastException e) {
			throw new RuntimeException(Messages.CONFIG_CREATED_FAILED, e);
		}

		HttpServer server = new HttpServer(new HttpServerConfiguration.Builder()
			.host(config.getString("host"))
			.port(config.getInt("port"))
			.create());

		websockets = new KekSocket();
		server.getDocumentProvider().register(HttpRequestMethod.GET, "/api/websockets", websockets);

		server.start();
	}
	
	public static KekSocket getWebSockets() {
		return websockets;
	}

}