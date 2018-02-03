package br.com.caelum.notasfiscais.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.datamodel.DataModelNostasFiscais;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@Named
@ViewScoped
public class ListaNotasFiscaisBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DataModelNostasFiscais dataModelNostasFiscais;
	
	private NotaFiscal notaFiscal;
	
	
	public String editar() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("nf", notaFiscal);
		return "notafiscal?faces-redirect=true";
	}
	
	public DataModelNostasFiscais getDataModelNostasFiscais() {
		return dataModelNostasFiscais;
	}
	
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	

}
