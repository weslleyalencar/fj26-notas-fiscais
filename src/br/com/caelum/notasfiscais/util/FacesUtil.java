package br.com.caelum.notasfiscais.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static void add(FacesMessage.Severity severity, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, mensagem, mensagem));
	}

	public static void info(String mensagem) {
		add(FacesMessage.SEVERITY_INFO, mensagem);
	}
	
	public static void warn(String mensagem) {
		add(FacesMessage.SEVERITY_WARN, mensagem);
	}
	
	public static void error(String mensagem) {
		add(FacesMessage.SEVERITY_ERROR, mensagem);
	}

}
