package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.market.entidades.Marca;

public class MarcaDAO extends GenericDAO<Marca,Long>{
	
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;
		
	public Marca salvarMarca(Marca marca) {
		save(marca);
		return marca;
	}
	
	@SuppressWarnings("unchecked")
	public Marca recuperarMarca(String nome) {
		List<Marca> lista = (List<Marca>) executeQuery("Select c FROM Marca c WHERE c.nome = ?", nome);
		Marca marca = lista.get(0);
		return marca;
	}
	
	public Marca recuperarMarcaPorId(long idMarca) {			
		return findById(idMarca);
	}	
	
	public List<Marca> recuperarTodasMarcas() {
		return findAll();
	}
			
}
