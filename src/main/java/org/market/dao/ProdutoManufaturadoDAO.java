package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.market.entidades.ProdutoManufaturado;

public class ProdutoManufaturadoDAO extends GenericDAO<ProdutoManufaturado,Long>{
	
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;
		
	public ProdutoManufaturado salvarProdutoManufaturado(ProdutoManufaturado produtoManufaturado) {
		save(produtoManufaturado);
		return produtoManufaturado;
	}
	
	public ProdutoManufaturado recuperarProdutoManufaturado(long idProdutoManufaturado) {
		return findById(idProdutoManufaturado);
	}
	
	@SuppressWarnings("unchecked")
	public ProdutoManufaturado recuperarProdutoManufaturadoPorCod(long codigoBarras) {
		List<ProdutoManufaturado> lista = (List<ProdutoManufaturado>) executeQuery("Select c FROM ProdutoManufaturado c WHERE c.codigoBarras = ?", codigoBarras);
		ProdutoManufaturado produtoManufaturado = lista.get(0);
		return produtoManufaturado;
	}
	
	public List<ProdutoManufaturado> recuperarTodosProdutosManufaturado() {
		return findAll();
	}
	
	public boolean excluirProdutoManufaturado(long idProdutoManufaturado) {

		try {
			delete(findById(idProdutoManufaturado));
			return true;
		} catch (Exception e) {
			return false;
		}

	}	
	
}
