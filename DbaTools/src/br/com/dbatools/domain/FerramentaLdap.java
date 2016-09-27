package br.com.dbatools.domain;

public class FerramentaLdap {
    private String tipo;
    private String nom_usuario;
	private String servidor;
	private String ip;
	private String database;
	private String usuario_bd_app;
	private String programa;
	private String comando2;
	private String tipo_ambiente;
	
	
	public String getNom_usuario() {
		return nom_usuario;
	}
	public void setNom_usuario(String nom_usuario) {
		this.nom_usuario = nom_usuario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getUsuario_bd_app() {
		return usuario_bd_app;
	}
	public void setUsuario_bd_app(String usuario_bd_app) {
		this.usuario_bd_app = usuario_bd_app;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getComando2() {
		return comando2;
	}
	public void setComando2(String comando2) {
		this.comando2 = comando2;
	}
	public String getTipo_ambiente() {
		return tipo_ambiente;
	}
	public void setTipo_ambiente(String tipo_ambiente) {
		this.tipo_ambiente = tipo_ambiente;
	}
		
}
