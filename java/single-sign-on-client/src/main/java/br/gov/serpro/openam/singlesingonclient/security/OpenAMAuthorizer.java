package br.gov.serpro.openam.singlesingonclient.security;

import br.gov.frameworkdemoiselle.security.Authorizer;

/**
 * Autorizador que utiliza o serviço de SSO do OpenAM como mecanismo para verificação
 * de usuários.
 * 
 * @author Eudes Andrade
 *
 */
public class OpenAMAuthorizer implements Authorizer {

	@Override
	public boolean hasRole(String role) {
		
		return true;
		
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		
		return true;
		
	}

}
