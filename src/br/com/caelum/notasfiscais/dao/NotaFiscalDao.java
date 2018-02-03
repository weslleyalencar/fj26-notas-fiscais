package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.tx.Transactional;

public class NotaFiscalDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Transactional
	public void adiciona(NotaFiscal nota) {
		manager.merge(nota);
	}
	
	public List<NotaFiscal> listaTodos() {
		CriteriaQuery<NotaFiscal> query = manager.getCriteriaBuilder().createQuery(NotaFiscal.class);
		query.select(query.from(NotaFiscal.class));
		List<NotaFiscal> lista = manager.createQuery(query).getResultList();

		return lista; 
	}
	
	public int contaTodos() {
		long result = (Long) manager.createQuery("select count(n) from NotaFiscal n").getSingleResult();
		return (int) result;
	}

	public List<NotaFiscal> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<NotaFiscal> query = manager.getCriteriaBuilder().createQuery(NotaFiscal.class);
		query.select(query.from(NotaFiscal.class));

		List<NotaFiscal> lista = manager.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		return lista;
	}
	
	
	public List<NotaFiscal> listarTodasComItens(){
		
		return manager.createQuery("select n from NotaFiscal n left join fetch n.itens", NotaFiscal.class).getResultList();
		
	}
	

}
