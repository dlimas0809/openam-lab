package br.gov.serpro.openam.singlesingonclient.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.serpro.openam.singlesingonclient.security.OpenAMAuthenticator;

@ViewController
public class LoginMB {

	@Inject
	private OpenAMAuthenticator authenticator;
	
	public String login() {
		
		if (authenticator.authenticate()) {
			
			return "welcome";
			
		} else {
			
			// TODO Utilizar mecanismo de mensagens do demoiselle... a mensagem não está aparecendo!
			FacesContext.getCurrentInstance().addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));			Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));Beans.getReference(FacesContext.class).addMessage("login", new FacesMessage("Usuário/Senha inválidos."));
			Beans.getReference(MessageContext.class).add(new DefaultMessage("bookmark.security.label.login", null));
			Faces.addMessage(new DefaultMessage("bookmark.security.label.login", null));
			
			return null;
		}
		
	}

}
