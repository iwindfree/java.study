package io.windfree.jetty.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.util.ajax.JSON;

import com.google.gson.Gson;

import io.windfree.jetty.sample.DeviceInfoData.DeviceInfo;
import io.windfree.jetty.sample.ServerCommand.ServerCmd;


@SuppressWarnings("serial")
public class EdgeNodeServlet extends HttpServlet {
	
	private Gson gson = new Gson();
	private Map<String, ServerCmd> serverCommandInfoMap;
	private Map<String, DeviceInfo> deviceInfoMap;
	
	public EdgeNodeServlet(Map<String, ServerCmd> srvMap, Map<String, DeviceInfo> dmap) {
		this.serverCommandInfoMap = srvMap;
		this.deviceInfoMap = dmap;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(200);
		res.getWriter().write("Hello!");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String requestString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		FromServerRequest fromServerReq = gson.fromJson(requestString, FromServerRequest.class);
		
		switch(req.getPathInfo()) {
		case "/fromServer":
			List<String> result = new ArrayList<>();
			for(String device : fromServerReq.targetDevice) {
				DeviceInfo deviceInfo = deviceInfoMap.get(device);
				ToDeviceRequest deviceReq = new ToDeviceRequest(serverCommandInfoMap.get(fromServerReq.command).forwardCommand, fromServerReq.param);
				String jsonParam = gson.toJson(deviceReq);
				try {
					String response = sendRequest(deviceInfo, jsonParam);
					FromDeviceResponse fromDeviceResponse = gson.fromJson(response, FromDeviceResponse.class);
					result.addAll(fromDeviceResponse.result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ToServerResponse toServerResponse = new ToServerResponse();
			toServerResponse.result = (ArrayList<String>) result;
			String responseJson = gson.toJson(toServerResponse);
			res.setStatus(200);
			res.getWriter().write(responseJson);
			break;
		}
		
	}
	
	private String sendRequest(DeviceInfo deviceInfo, String param) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		try {
			String url = String.format("http://%s:%d/fromEdge", deviceInfo.hostname, deviceInfo.port);
			org.eclipse.jetty.client.api.Request request = httpClient.POST(url);
			request.header(HttpHeader.CONTENT_TYPE, "application/json");
			request.content(new StringContentProvider(param, "utf-8"));
			ContentResponse response = request.send();
			return new String(response.getContent());
		} catch (ExecutionException executionException) {
			executionException.printStackTrace();
		} finally {
			httpClient.stop();
		}
		return null;
	}
}
