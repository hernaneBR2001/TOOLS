package br.com.dbatools.domain;

import java.sql.Date;


public class Feria {
private Long cod_planilha_ferias;
private Usuario cod_usuario = new Usuario();
private QtdDia qtidade_dias = new QtdDia();
private String data_inicio;
private String data_fim;
private Usuario substituto1 = new Usuario();
private Usuario substituto2 = new Usuario();
private Usuario substituto3 = new Usuario();
public Long getCod_planilha_ferias() {
	return cod_planilha_ferias;
}
public void setCod_planilha_ferias(Long cod_planilha_ferias) {
	this.cod_planilha_ferias = cod_planilha_ferias;
}
public Usuario getCod_usuario() {
	return cod_usuario;
}
public void setCod_usuario(Usuario cod_usuario) {
	this.cod_usuario = cod_usuario;
}
public QtdDia getQtidade_dias() {
	return qtidade_dias;
}
public void setQtidade_dias(QtdDia qtidade_dias) {
	this.qtidade_dias = qtidade_dias;
}
public String getData_inicio() {
	return data_inicio;
}
public void setData_inicio(String data_inicio) {
	this.data_inicio = data_inicio;
}
public String getData_fim() {
	return data_fim;
}
public void setData_fim(String data_fim) {
	this.data_fim = data_fim;
}
public Usuario getSubstituto1() {
	return substituto1;
}
public void setSubstituto1(Usuario substituto1) {
	this.substituto1 = substituto1;
}
public Usuario getSubstituto2() {
	return substituto2;
}
public void setSubstituto2(Usuario substituto2) {
	this.substituto2 = substituto2;
}
public Usuario getSubstituto3() {
	return substituto3;
}
public void setSubstituto3(Usuario substituto3) {
	this.substituto3 = substituto3;
}


}
