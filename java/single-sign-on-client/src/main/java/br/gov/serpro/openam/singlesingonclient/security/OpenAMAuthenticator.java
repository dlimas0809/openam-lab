package br.gov.serpro.openam.singlesingonclient.security;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

/**
 * Autenticador que utiliza o serviço de SSO do OpenAM como mecanismo para verificação
 * de usuários.
 * 
 * @author Eudes Andrade
 *
 */
@SessionScoped
public class OpenAMAuthenticator implements Authenticator {

	private User user;
	
	@Inject
	private Credential credential;
	
	@Override
	public boolean authenticate() {
		
		if (credential.getLogin().equalsIgnoreCase("eudes") && credential.getPassword().equalsIgnoreCase("1234")) {
			
			this.user = this.createUser();
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

	@Override
	public void unAuthenticate() {
		
		
	}

	@Override
	public User getUser() {
		
		return user;
		
	}

	private User createUser() {
		
		return new User() {
			
			@Override
			public void setAttribute(Object key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getAttribute(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
	}
	
}

