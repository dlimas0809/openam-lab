package br.gov.serpro.openam.auth;

import java.io.Serializable;
import java.security.Principal;

public class SenhaRedePrincipal implements Principal,Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	public SenhaRedePrincipal(String nome){
		this.nome = nome;
	}

	public String getName() {
		return nome;
	}

}
