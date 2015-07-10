package br.com.casadocodigo.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Product;

@Repository
public class ProdutoDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void adiciona(Product produto) { 
		entityManager.persist(produto);
	}
	
}
