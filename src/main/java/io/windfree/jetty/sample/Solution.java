package io.windfree.jetty.sample;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.gson.Gson;

import io.windfree.jetty.sample.DeviceInfoData.DeviceInfo;
import io.windfree.jetty.sample.ServerCommand.ServerCmd;



public class Solution {

	private Gson gson = new Gson();
	private Map<String, ServerCmd> serverCommandInfoMap;
	private Map<String, DeviceInfo> deviceInfoMap;

	public void run() throws Exception {

		loadServerCommandInfo();
		loadDeviceInfo();

		Server fromServer = createServer();
		fromServer.start();
	}

	private void loadServerCommandInfo() throws Exception {
		serverCommandInfoMap = new HashMap<>();
		File file = new File("INFO/SERVER_COMMAND.JSON");
		
		String strServerCommand = new String(Util.readAll(file));
		ServerCommand serverCommand = gson.fromJson(strServerCommand, ServerCommand.class);
		for(ServerCmd cmd : serverCommand.serverCommandInfo) {
			serverCommandInfoMap.put(cmd.command, cmd);
		}
		System.out.println("hello");
		
		
	}

	private void loadDeviceInfo() throws Exception {
		deviceInfoMap = new HashMap<>();
		File file = new File("INFO/DEVICE.JSON");
		String strDevice = new String(Util.readAll(file));
		DeviceInfoData deviceInfoData = gson.fromJson(strDevice, DeviceInfoData.class);
		for (DeviceInfo d : deviceInfoData.deviceInfo) {
			deviceInfoMap.put(d.device, d);
		}
	}

	private Server createServer() {
		
		  Server server = new Server();
		  ServerConnector http = new ServerConnector(server);
		  http.setHost("127.0.0.1"); 
		  http.setPort(8010);
		  server.addConnector(http);
		  
		  ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		  context.setContextPath("/*"); context.addServlet(new ServletHolder(new EdgeNodeServlet(serverCommandInfoMap, deviceInfoMap)), "/*");
		  server.setHandler(context);
		  
		  return server;
		 
	}
}
