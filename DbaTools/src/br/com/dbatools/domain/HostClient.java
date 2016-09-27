package br.com.dbatools.domain;

import java.util.Date;

public class HostClient {

	private Long idHost;
	private String host;
	private int port;
	private Date dtLastConnect;
	private String username;
	public Long getIdHost() {
		return idHost;
	}
	public void setIdHost(Long idHost) {
		this.idHost = idHost;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Date getDtLastConnect() {
		return dtLastConnect;
	}
	public void setDtLastConnect(Date dtLastConnect) {
		this.dtLastConnect = dtLastConnect;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
