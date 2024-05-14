package io.windfree.jetty.sample;

public class ToDeviceRequest {
	public String command;
	public String param;
	public ToDeviceRequest(String cmd, String param) {
		this.command = cmd;
		this.param = param;
	}
}
