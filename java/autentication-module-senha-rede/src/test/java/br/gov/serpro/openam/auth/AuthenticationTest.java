package br.gov.serpro.openam.auth;

import junit.framework.Assert;

import org.junit.Test;

import br.gov.serpro.openam.auth.tibco.SenhaRedeTibcoClient;

public class AuthenticationTest {

	private static final String SISTEMA = "SISCOMEX";
	private static final String USERNAME = "USEREX";
	private static final String PASSWORD = "senha123";
	
	@Test
	public void testAuthentication() {
		
		Assert.assertTrue(SenhaRedeTibcoClient.authenticate(SISTEMA, USERNAME, PASSWORD));

	}
	
}
