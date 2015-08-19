package br.com.casadocodigo.loja.dao;

import java.util.List;

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
	
	public List<Product> list() { 
		return entityManager
					.createQuery("select distinct(p) from Product p join fetch p.prices", Product.class)
//					.createQuery("from Product p", Product.class)
					.getResultList();
	}

	public Product find(Integer id) {
		return entityManager
				.createQuery("select distinct(p) from Product p join fetch p.prices where p.id = :pId", Product.class)
				.setParameter("pId", id)
//				.createQuery("from Product p", Product.class)
				.getSingleResult();
	}
	
}
