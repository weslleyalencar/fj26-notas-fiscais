package br.com.caelum.notasfiscais.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.util.FacesUtil;

@Named
@RequestScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDao produtoDao;
	
	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	private Double valorTotalDeProdutosVendidos;
	private Integer quantidadeTotalDeProdutosVendidos;
	
	@PostConstruct
	public void novoProduto() {
		produtos = produtoDao.listaTodosOrdenadoPorNome();
		produto = new Produto();
		//valorTotalDeProdutosVendidos = produtos.stream().mapToDouble(Produto::getPreco).sum();
		quantidadeTotalDeProdutosVendidos = produtos.size();
	}
	
	public void salvar() {
		produtoDao.adiciona(produto);
		novoProduto();
		produto = new Produto();
		FacesUtil.info("Produto salvo com sucesso!");
	}
	
	public void remover(Produto produtoParaRemover) {
		System.out.println(produtoParaRemover);
		produtoDao.remove(produtoParaRemover);
		novoProduto();
		FacesUtil.info("Produto removido com sucesso!");
	}
	
	public void cancelarEdicao() {
	}
	
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		if(produtos == null) {
			produtos = produtoDao.listaTodos();
		}
		return produtos;
	}
	
	public Double getTotalDeProdutosVendidos() {
		return valorTotalDeProdutosVendidos;
	}
	
	public Integer getQuantidadeTotalDeProdutosVendidos() {
		return quantidadeTotalDeProdutosVendidos;
	}

	public void comecarComMaiscula(FacesContext context, UIComponent ui, Object obj) throws ValidatorException {
		if(!obj.toString().matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Deveria começar com maiúsculo", "Deveria começar com maiúsculo"));
		}
	}
	
	
}
