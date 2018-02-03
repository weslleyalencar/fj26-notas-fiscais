package br.com.caelum.notasfiscais.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@SessionScoped
public class UsuarioLogadoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public void logar(Usuario usuario) {
		this.usuario = usuario;
	}

	public String deslogar() {
		this.usuario = null;
		return "login?faces-redirect=true";
	}

	public Boolean isLogado() {
		return usuario != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
