package br.com.dbatools.domain;

public class Empresa {

	private Long cod_empresa;
    private String nom_empresa;
    private String cnpj;


public Long getCod_empresa() {
	return cod_empresa;
}
public void setCod_empresa(Long cod_empresa) {
	this.cod_empresa = cod_empresa;
}
public String getNom_empresa() {
	return nom_empresa;
}
public void setNom_empresa(String nom_empresa) {
	this.nom_empresa = nom_empresa;
}
public String getCnpj() {
	return cnpj;
}
public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
}
 
}