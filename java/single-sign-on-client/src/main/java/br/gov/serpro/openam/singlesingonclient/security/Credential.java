package br.gov.serpro.openam.singlesingonclient.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Credenciais para rotinas de autenticacao.
 * 
 * @author Eudes Andrade
 *
 */
@SessionScoped
@Named
public class Credential implements Serializable {

	private String login;
	private String password;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
