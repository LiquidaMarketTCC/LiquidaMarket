package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.market.entidades.Categoria;

public class CategoriaDAO extends GenericDAO<Categoria,Long>{
	
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;
		
	public Categoria salvarCategoria(Categoria categoria) {
		save(categoria);
		return categoria;
	}
	
	@SuppressWarnings("unchecked")
	public Categoria recuperarCategoria(String nome) {

		List<Categoria> lista = (List<Categoria>) executeQuery("Select c FROM Categoria c WHERE c.nome = ?", nome);
		Categoria categoria = lista.get(0);
		return categoria;
	}	
	
	public List<Categoria> recuperarTodasCategorias() {
		return findAll();
	}	
	
}
