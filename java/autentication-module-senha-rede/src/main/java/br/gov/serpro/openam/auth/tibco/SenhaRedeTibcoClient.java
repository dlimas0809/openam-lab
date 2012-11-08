package br.gov.serpro.openam.auth.tibco;

import java.net.URL;

import serpro.servicoscorporativos.senharede.authentication.v1.AuthenticateData;
import serpro.servicoscorporativos.senharede.authentication.v1.UserCredential;
import serpro.servicoscorporativos.senharede.authentication.v1.UserInfo;
import serpro.servicoscorporativos.senharede.ws.authenticationservice.authenticationserviceendpoint.v1.AuthenticationPortType;
import serpro.servicoscorporativos.senharede.ws.authenticationservice.authenticationserviceendpoint.v1.AuthenticationServiceHTTPServiceagentLocator;

public class SenhaRedeTibcoClient {

	private static final String IP = "127.0.01";
	private static final String PATH_CLIENT_JKS = "/tmp/clientCertificate.jks";
	private static final String PATH_TRUST_JKS = "/tmp/truststore.jks";
	
	public static boolean authenticate(String sistema, String userName, String password) {

		boolean retorno = false;
		
		try {
			
			loadProperties();
			
			AuthenticateData authenticateData = new AuthenticateData();
			authenticateData.setSystem(sistema);
			authenticateData.setGetProfiles(false);
			authenticateData.setUserInfo(loadUserInfo(userName, password));
			
			AuthenticationPortType authentication = loadAuthenticator();
			
			UserCredential credencial = authentication.authenticate(authenticateData);
			
			if (credencial != null) {
				retorno = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}

	private static UserInfo loadUserInfo(String userName, String password) {
		
		UserInfo userInfo = new UserInfo(userName, password, null, IP);
		
		return userInfo;
		
	}

	private static void loadProperties() {

		String[][] props = {
				{ "javax.net.ssl.trustStore", PATH_TRUST_JKS },
				{ "javax.net.ssl.trustStoreType", "JKS" }, { "javax.net.ssl.trustStorePassword", "changeit" },
				{ "javax.net.ssl.keyStore", PATH_CLIENT_JKS },
				{ "javax.net.ssl.keyStorePassword", "123456" },
				{ "javax.net.ssl.keyStoreType", "JKS" }
		};

		for (int i = 0; i < props.length; i++) {
			System.getProperties().setProperty(props[i][0], props[i][1]);
		}

	}

	private static AuthenticationPortType loadAuthenticator() {
		
		AuthenticationPortType retorno = null;
		
		try {
			
			URL url = new URL("https://10.31.0.11:8082/AuthenticationServiceEndpointHTTP/v1.0");			
			AuthenticationPortType authentication = new AuthenticationServiceHTTPServiceagentLocator().getAuthenticationPortTypeEndpointHTTP(url);
			
			retorno = authentication;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}

}
