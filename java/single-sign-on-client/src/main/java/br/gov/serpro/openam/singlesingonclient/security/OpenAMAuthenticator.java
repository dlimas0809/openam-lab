package br.gov.serpro.openam.singlesingonclient.security;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;
import br.gov.frameworkdemoiselle.util.Beans;

/**
 * Autenticador que utiliza o serviço de SSO do OpenAM como mecanismo para verificação de usuários.
 * 
 * @author Eudes Andrade
 */
@SessionScoped
public class OpenAMAuthenticator implements Authenticator {

	private User user;

	@Inject
	private Credential credential;

	@Inject
	private OpenAMAuthenticatorClient amAuthenticatorClient;

	@Override
	public boolean authenticate() {

		try {
			
//			boolean b = SenhaRedeTibcoClient.authenticate("SISCOMEX", "USEREX", "senha123");
//			System.out.println(b);

			return amAuthenticatorClient.login(credential.getLogin(), credential.getPassword());

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void unAuthenticate() {

	}

	@Override
	public User getUser() {

		if (amAuthenticatorClient.checkLogin(Beans.getReference(HttpServletRequest.class))) {
			this.user = this.createUser();
		} else {
			this.user = null;
		}

		return this.user;

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
