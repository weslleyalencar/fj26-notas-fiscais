package br.com.caelum.notasfiscais.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Inject
	private Event<Usuario> eventoLogin;
	
	private Usuario usuario = new Usuario();
	
	
	public String efetuaLogin() {
		boolean loginValido = usuarioDao.existe(usuario);
		if(loginValido) {
			usuarioLogado.logar(usuario);
			eventoLogin.fire(usuario);
			return "notafiscal?faces-redirect=true";
		}else {
			usuarioLogado.deslogar();
			return "login";
		}
	}
	
	public boolean isLogado() {
		return usuarioLogado.getUsuario() != null;
	}
	
	public String deslogar() {
		usuarioLogado.setUsuario(null);
		return "login";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
