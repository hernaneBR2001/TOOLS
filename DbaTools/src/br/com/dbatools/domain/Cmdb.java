package br.com.dbatools.domain;

public class Cmdb {
private Long cod_planilha;
private Empresa empresa = new Empresa();
private String servidor;
private String ip;
private String versao_so;
private String database;
private String versao_database;
private String tipo_ambiente;
private String sgdb;
private String referencia;
private Usuario contato1 = new Usuario();
private Usuario contato2 = new Usuario();
private Usuario contato3 = new Usuario();


public Long getCod_planilha() {
	return cod_planilha;
}
public void setCod_planilha(Long cod_planilha) {
	this.cod_planilha = cod_planilha;
}
public Empresa getEmpresa() {
	return empresa;
}
public void setEmpresa(Empresa empresa) {
	this.empresa = empresa;
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
public String getVersao_so() {
	return versao_so;
}
public void setVersao_so(String versao_so) {
	this.versao_so = versao_so;
}
public String getDatabase() {
	return database;
}
public void setDatabase(String database) {
	this.database = database;
}
public String getVersao_database() {
	return versao_database;
}
public void setVersao_database(String versao_database) {
	this.versao_database = versao_database;
}
public String getTipo_ambiente() {
	return tipo_ambiente;
}
public void setTipo_ambiente(String tipo_ambiente) {
	this.tipo_ambiente = tipo_ambiente;
}
public String getSgdb() {
	return sgdb;
}
public void setSgdb(String sgdb) {
	this.sgdb = sgdb;
}
public String getReferencia() {
	return referencia;
}
public void setReferencia(String referencia) {
	this.referencia = referencia;
}
public Usuario getContato1() {
	return contato1;
}
public void setContato1(Usuario contato1) {
	this.contato1 = contato1;
}
public Usuario getContato2() {
	return contato2;
}
public void setContato2(Usuario contato2) {
	this.contato2 = contato2;
}
public Usuario getContato3() {
	return contato3;
}
public void setContato3(Usuario contato3) {
	this.contato3 = contato3;
}



}
