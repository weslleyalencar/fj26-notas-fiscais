package br.com.caelum.notasfiscais.dao;

import javax.persistence.EntityManager;

import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.util.JPAUtil;

public class Testes {
	
	public static void main(String[] args) {
		
		EntityManager manager =  new JPAUtil().getEntityManager();
		
		Produto p = new Produto();
		
		p.setId(8L);
		p.setNome("Coca-cola");
		p.setDescricao("Refrigerante");
		p.setPreco(10D);
		
		manager.getTransaction().begin();
		manager.merge(p);
		manager.getTransaction().commit();
		
		manager.close();
	}
}
