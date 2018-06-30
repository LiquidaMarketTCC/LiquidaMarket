package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.market.entidades.ProdutoComercializado;

public class ProdutoComercializadoDAO extends GenericDAO<ProdutoComercializado, Long> {

	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;

	public ProdutoComercializado salvarProdutoComercializado(ProdutoComercializado produtoComercializado) {
		save(produtoComercializado);
		return produtoComercializado;
	}

	public ProdutoComercializado recuperarProdutoComercializado(long idProdutoComercializado) {
		return findById(idProdutoComercializado);
	}

	public List<ProdutoComercializado> recuperarTodosProdutosComercializado() {
		return findAll();
	}

	@SuppressWarnings("unchecked")
	public List<ProdutoComercializado> recuperarTodosProdutosComercializado(long idEstabelecimento) {
		List<ProdutoComercializado> lista = (List<ProdutoComercializado>) executeQuery(
				"Select c FROM ProdutoComercializado c WHERE c.estabelecimento.idEstabelecimento = ?",
				idEstabelecimento);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public ProdutoComercializado recuperarProdutoComercializadoCodBarras(long codBarra) {
		List<ProdutoComercializado> lista = (List<ProdutoComercializado>) executeQuery(
				"Select c FROM ProdutoComercializado c WHERE c.produtoManufaturado.codigoBarras = ?", codBarra);
		return lista.get(0);
	}

	public ProdutoComercializado recuperarProdutoComercializadoCodBarrasEstabelecimento(long codBarra,
			long idEstabelecimento) {
		List<ProdutoComercializado> lista = (List<ProdutoComercializado>) executeQuery(
				"Select c FROM ProdutoComercializado c WHERE c.produtoManufaturado.codigoBarras = ? AND c.estabelecimento.idEstabelecimento = ?",
				codBarra, idEstabelecimento);
		return lista.get(0);
	}

	public boolean alterarSituacao(long idProdutoComercializado, boolean situacao) {
		try {
			executeQuery("UPDATE ProdutoComercializado p SET p.situacao = ? WHERE p.idProdutoComercializado = ?",
					situacao, idProdutoComercializado);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ProdutoComercializado> buscaProdutosPorCategoria(long idCategoria) {
		try {
			@SuppressWarnings("unchecked")
			List<ProdutoComercializado> lista = (List<ProdutoComercializado>) executeQuery(
					"SELECT pC FROM ProdutoComercializado pC, ProdutoManufaturado pM WHERE pM.categoria.idCategoria = ? AND pM.idProdutoManufaturado = pC.produtoManufaturado.idProdutoManufaturado",
					idCategoria);
			return lista;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean excluirProdutoComercializado(long idProdutoComercializado) {
		try {
			delete(findById(idProdutoComercializado));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean excluirTodosProdutosEstabelecimento(long idEstabelecimento) {
		try {
			executeQuery("DELETE FROM ProdutoComercializado p WHERE p.estabelecimento.idEstabelecimento = ?",
					idEstabelecimento);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
