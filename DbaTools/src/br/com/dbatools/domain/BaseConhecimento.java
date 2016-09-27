package br.com.dbatools.domain;

public class BaseConhecimento {
private Long cod_conhecimento;
private Empresa empresa = new Empresa();
private Cmdb servidor = new Cmdb();
private Cmdb database = new Cmdb();
private String titulo_doc;
private String link;
private String comando2;
private String comando3;
private String cod_tipo;
private String comando4;


public String getCod_tipo() {
	return cod_tipo;
}
public void setCod_tipo(String cod_tipo) {
	this.cod_tipo = cod_tipo;
}
public String getComando3() {
	return comando3;
}
public void setComando3(String comando3) {
	this.comando3 = comando3;
}
public String getComando2() {
	return comando2;
}
public void setComando2(String comando2) {
	this.comando2 = comando2;
}
public Long getCod_conhecimento() {
	return cod_conhecimento;
}
public void setCod_conhecimento(Long cod_conhecimento) {
	this.cod_conhecimento = cod_conhecimento;
}
public Empresa getEmpresa() {
	return empresa;
}
public void setEmpresa(Empresa empresa) {
	this.empresa = empresa;
}
public Cmdb getServidor() {
	return servidor;
}
public void setServidor(Cmdb servidor) {
	this.servidor = servidor;
}
public Cmdb getDatabase() {
	return database;
}
public void setDatabase(Cmdb database) {
	this.database = database;
}
public String getTitulo_doc() {
	return titulo_doc;
}
public void setTitulo_doc(String titulo_doc) {
	this.titulo_doc = titulo_doc;
}
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
public String getComando4() {
	return comando4;
}
public void setComando4(String comando4) {
	this.comando4 = comando4;
}

}
