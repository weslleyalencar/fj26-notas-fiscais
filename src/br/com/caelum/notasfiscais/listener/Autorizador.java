package br.com.caelum.notasfiscais.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.controller.UsuarioLogadoBean;

public class Autorizador implements PhaseListener{
	private static final long serialVersionUID = 1L;
	
	@Inject
	UsuarioLogadoBean usuarioLogado;

	@Override
	public void afterPhase(PhaseEvent event) {
			
		FacesContext context = event.getFacesContext();
		
		if(context.getViewRoot().getViewId().endsWith("login.xhtml") && usuarioLogado.isLogado()) {
			context.getApplication().getNavigationHandler().handleNavigation(context, null, "notafiscal?faces-redirect=true");
		}
		
		if(context.getViewRoot().getViewId().endsWith("login.xhtml")) {
			return;
		}
		
		if(!usuarioLogado.isLogado()) {
			context.getApplication().getNavigationHandler().handleNavigation(context, null, "login?faces-redirect=true");
			context.renderResponse();
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
