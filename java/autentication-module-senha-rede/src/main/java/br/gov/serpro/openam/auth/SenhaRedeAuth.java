package br.gov.serpro.openam.auth;

import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;

import com.sun.identity.authentication.spi.AMLoginModule;
import com.sun.identity.authentication.spi.AuthLoginException;
import com.sun.identity.authentication.spi.InvalidPasswordException;
import com.sun.identity.authentication.util.ISAuthConstants;
import com.sun.identity.shared.debug.Debug;

/**
 * 
 * Módulo de autenticação compatível com OpenAM e que autentica usuários na senha rede.
 * 
 * Tutorial de criação de módulos: <a href="https://wikis.forgerock.org/confluence/display/openam/Write+a+custom+authentication+module#Writeacustomauthenticationmodule-CreatingaCustomAuthenticationModule">clique aqui!</a>
 * 
 * @author serpro
 *
 */
public class SenhaRedeAuth extends AMLoginModule {
	
	private static final int ESTADO_LOGIN = 1;
	
	private static final String[] USUARIOS = new String[]{"danilo,wilson,eudes"}; //$NON-NLS-1$
	private static final String[] SENHAS = new String[]{"1234,5678,9012"}; //$NON-NLS-1$
	
	private Debug debug = Debug.getInstance(SenhaRedeAuth.class.getName());
	
	//Segundo a documentação, instâncias de SenhaRedeAuth não são reutilizadas, uma nova é criada para processar cada
	//login individual, por isso é permitido que este objeto guarde estados.
	private String usuarioLogado = null;

	@Override
	public Principal getPrincipal() {
		//Deve retornar uma instância da interface java.security.Principal, em nosso
		//caso criamos a classe SenhaRedePrincipal que contém o nome do usuário. Em nosso exemplo
		//o nome é igual ao login.
		if(debug.messageEnabled()){
			debug.message(Messages.getString("senha-rede.debug-user-principal")); //$NON-NLS-1$
		}
		return new SenhaRedePrincipal(usuarioLogado);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void init(Subject subject, Map sharedState, Map options) {
		//Chamado no início do processo de autenticação e serve para guardarmos 
		//opções passadas aqui.
	}

	@Override
	public int process(Callback[] callbacks, int state) throws LoginException {
		//Chamado a cada fase do processo de autenticação. Em nosso autenticador
		//de Senha Rede só existe uma fase, que é a de obter login e senha. Se o login
		//e senha providos não conferirem, um LoginException é lançado imediatamente.
		//Em um módulo com multiplas fases, cada fase deve escolher entre retornar
		//a constante ISAuthConstants.LOGIN_SUCCEED para reportar sucesso ou retornar
		//o índice da nova fase. Fases começam em 1 e são sempre positivas. Veja o arquivo
		//"SenhaRedeAuth.xml" para entender como uma fase é criada.
		
		switch(state){
		case ESTADO_LOGIN:
			//As classes XPTOCallback equivalem aos estados de callback definidos em nosso arquivo XML.
			//No caso atual temos um NameCallback que armazena o login do usuário e um PasswordCallback que
			//armazena a senha informada. Coletamos estas informações aqui.
			NameCallback nc = (NameCallback)callbacks[0];
			PasswordCallback pc = (PasswordCallback)callbacks[1];
			String username = nc.getName();
			String password = new String(pc.getPassword());
			
			for (int i=0; i<USUARIOS.length; i++){
				if (USUARIOS[i].equals(username) && SENHAS[i].equals(password) ){
					//A constante que informa o OpenAM que as informações fornecidas conferem.
					if(debug.messageEnabled()){
						debug.message(Messages.getString("senha-rede.debug-autenticacao-sucesso")+username); //$NON-NLS-1$
					}
					usuarioLogado = username;
					return ISAuthConstants.LOGIN_SUCCEED;
				}
			}
			
			//Se chegarmos aqui, o login informado não confere com nenhum login de teste.
			if(debug.messageEnabled()){
				debug.warning(Messages.getString("senha-rede.debug-autenticacao-falha")+username); //$NON-NLS-1$
			}
			throw new InvalidPasswordException(Messages.getString("senha-rede.password-invalido")); //$NON-NLS-1$
			
		default:
			//Só temos uma fase de callback, indexada com "1". Se chegar aqui então o módulo
			//passou uma fase inválida e devemos reportar o erro.
			if(debug.messageEnabled()){
				debug.error(Messages.getString("senha-rede.debug-estado-invalido")+state); //$NON-NLS-1$
			}
			throw new AuthLoginException(Messages.getString("senha-rede.estado-invalido")); //$NON-NLS-1$
		}
		
	}

}
