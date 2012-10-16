package br.gov.serpro.openam.singlesingonclient.security;

import java.io.Serializable;

import javax.inject.Inject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.util.Beans;

import com.iplanet.sso.SSOToken;
import com.iplanet.sso.SSOTokenManager;
import com.sun.identity.authentication.AuthContext;
import com.sun.identity.authentication.spi.AuthLoginException;

/**
 * Utilitário que encapsula o acesso à API do Openam para a rotina de autenticação.
 * 
 * @author Eudes Andrade
 */
public class OpenAMAuthenticatorClient implements Serializable {

	private static final String LOGIN_INDEX_NAME = "DataStore";

	private static final String ORG_NAME = "/";

	private static final String LOCALE = "en_US";

	@Inject
	private Logger logger;
	
	private String name;

	private String password;

	public boolean login(String name, String password) {
		
		boolean succeed = false;

		try {
			
			logger.debug("Iniciando login");
			
			this.name = name;
			this.password = password;
			
			AuthContext authContext = this.obterAuthenticationContext();
			
			this.realizarLogin(authContext);
			
			if (authContext.getStatus() == AuthContext.Status.SUCCESS) {
				
				logger.info("Login realizado com sucesso");
			
				this.gerarCookie(authContext);				
				succeed = true;
				
			} else if (authContext.getStatus() == AuthContext.Status.FAILED) {
				logger.info("Login falhou");
			} else {
				logger.info("Login retornou um status desconhecido: [" + authContext.getStatus() + "]");
			}
			
		} catch (Exception e) {
			succeed = false;
		}		

		logger.debug("Login concluído");
		
		return succeed;
		
	}

	private void gerarCookie(AuthContext authContext) throws Exception {
		
		logger.debug("Iniciando geração de cookie");
		
		// TODO Rever parametrização para geração de cookie
		Cookie cookie = new Cookie("iPlanetDirectoryPro", authContext.getSSOToken().getTokenID().toString());
		cookie.setDomain(".serpro.org");
		cookie.setPath("/");
		
		((HttpServletResponse) Beans.getReference(HttpServletResponse.class)).addCookie(cookie);
		
		logger.debug("Cookie gerado com sucesso");
	}

	private void realizarLogin(AuthContext authContext) throws UnsupportedCallbackException {
		
		while (authContext.hasMoreRequirements()) {
			
			Callback[] callbacks = authContext.getRequirements();
			
			if (callbacks != null) {				
				tratarCallbacks(callbacks);
				authContext.submitRequirements(callbacks);				
			}
			
		}
		
	}

	// TODO Rever se o logout pode recriar o authenticationContext ou deve reutilizar o do login 
	public void logout() throws AuthLoginException {
		
		logger.debug("Iniciando logout");
		
		obterAuthenticationContext().logout();
		
		logger.info("Logout realizado com sucesso");
		logger.debug("Logout concluído");
		
	}

	public boolean checkLogin(HttpServletRequest request) {
		
		boolean validated = true;
		
		try {
			
			logger.debug("Iniciando checagem de Login");
			
 			SSOTokenManager manager = SSOTokenManager.getInstance();
			SSOToken token = manager.createSSOToken(request);
			
			if (manager.isValidToken(token)) {				
				logger.info("Login Válido");
				validated = true;				
			} else {
				logger.info("Login inválido");
				validated = false;
			}
			
		} catch (Exception e) {
			logger.info("Erro ao validar Login");
			validated = true;
		}		

		logger.debug("Checagem de Login concluída");
		return validated;
		
	}
	
	private AuthContext obterAuthenticationContext() throws AuthLoginException {
		
		logger.debug("Iniciando obtenção de Login Context");
		
		AuthContext lc = new AuthContext(ORG_NAME);
		AuthContext.IndexType indexType = AuthContext.IndexType.MODULE_INSTANCE;
		
		lc.login(indexType, LOGIN_INDEX_NAME, LOCALE);
		
		logger.debug("Login Context obtido com sucesso");
		
		return lc;
		
	}

	private void tratarCallbacks(Callback[] callbacks) throws UnsupportedCallbackException {

		logger.debug("Iniciando tratamento de callbacks");
		
		int i = 0;
		
		for (i = 0; i < callbacks.length; i++) {

			if (callbacks[i] instanceof TextOutputCallback) {
				tratarMensagemTextoCallback((TextOutputCallback) callbacks[i]);
			} else if (callbacks[i] instanceof NameCallback) {
				((NameCallback) callbacks[i]).setName(this.name);
			} else if (callbacks[i] instanceof PasswordCallback) {
				((PasswordCallback) callbacks[i]).setPassword(this.password.toCharArray());
			} else {
				throw new UnsupportedCallbackException(callbacks[i]);
			}

		}

		logger.debug("Tratamento de callbacks concluído");
		
	}

	private void tratarMensagemTextoCallback(TextOutputCallback toc) {

		switch (toc.getMessageType()) {

			case TextOutputCallback.INFORMATION:
				logger.info(toc.getMessage());
				break;
			case TextOutputCallback.ERROR:
				logger.error(toc.getMessage());
				break;
			case TextOutputCallback.WARNING:
				logger.warn(toc.getMessage());
				break;
			default:
				logger.debug(toc.getMessage());

		}

	}

}
