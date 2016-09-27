package br.com.dbatools.domain;

public class InstalacaoConfig {
private Long cod_instalacao;
private String nom_instalacao;
private String caminho;
private String comando;
public Long getCod_instalacao() {
	return cod_instalacao;
}
public void setCod_instalacao(Long cod_instalacao) {
	this.cod_instalacao = cod_instalacao;
}
public String getNom_instalacao() {
	return nom_instalacao;
}
public void setNom_instalacao(String nom_instalacao) {
	this.nom_instalacao = nom_instalacao;
}
public String getCaminho() {
	return caminho;
}
public void setCaminho(String caminho) {
	this.caminho = caminho;
}
public String getComando() {
	return comando;
}
public void setComando(String comando) {
	this.comando = comando;
}
}
