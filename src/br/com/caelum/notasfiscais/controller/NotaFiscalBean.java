package br.com.caelum.notasfiscais.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private NotaFiscalDao notaFiscalDao;

	@Inject
	private ProdutoDao produtoDao;
	
//	@Inject
//	private Conversation conversionScoped;

	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	private Long idProduto;
	private Double total;
	
	
	@PostConstruct
	public void init() {
		NotaFiscal nf =(NotaFiscal) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("nf");
		notaFiscal = nf;
	}

	public void salvar() {
		System.out.println("Nota Fiscal" + notaFiscal.getItens());
		notaFiscalDao.adiciona(notaFiscal);
		notaFiscal = new NotaFiscal();
	}

	public void adicionarItem() {
		Produto produto = produtoDao.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());

		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);

		item = new Item();
		idProduto = null;
	}
	
	public void removerItem() {
		item.setNotaFiscal(null);
		notaFiscal.getItens().remove(item);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscalBean other = (NotaFiscalBean) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		System.out.println("-------------->>>> SETANDO NF" + notaFiscal);
		this.notaFiscal = notaFiscal;
	}

}
