package br.com.dbatools.domain;

public class DbaTools {
private Long cod_dbatools;
private String descricao;
private String diretorio;
private String cod_tipo;
private String comando2;



public String getCod_tipo() {
	return cod_tipo;
}
public void setCod_tipo(String cod_tipo) {
	this.cod_tipo = cod_tipo;
}
public String getComando2() {
	return comando2;
}
public void setComando2(String comando2) {
	this.comando2 = comando2;
}
public Long getCod_dbatools() {
	return cod_dbatools;
}
public void setCod_dbatools(Long cod_dbatools) {
	this.cod_dbatools = cod_dbatools;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public String getDiretorio() {
	return diretorio;
}
public void setDiretorio(String diretorio) {
	this.diretorio = diretorio;
}


}
