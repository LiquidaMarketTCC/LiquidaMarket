package org.market.negocios;

import java.util.List;

import org.market.dao.ProdutoManufaturadoDAO;
import org.market.entidades.Categoria;
import org.market.entidades.Marca;
import org.market.entidades.ProdutoManufaturado;

public class ProdutoManufaturadoNegocios {

	private ProdutoManufaturadoDAO produtoManufaturadoDAO = new ProdutoManufaturadoDAO();

	public ProdutoManufaturado salvarProdutoManufaturado(long codigoBarras, String nome, String descricao, Marca marca,
			Categoria categoria) {

		try {
			ProdutoManufaturado produtoManufaturado = new ProdutoManufaturado();
			produtoManufaturado.setCodigoBarras(codigoBarras);
			produtoManufaturado.setNome(nome);
			produtoManufaturado.setDescricao(descricao);
			produtoManufaturado.setMarca(marca);
			produtoManufaturado.setCategoria(categoria);
			produtoManufaturado = produtoManufaturadoDAO.salvarProdutoManufaturado(produtoManufaturado);
			return produtoManufaturado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProdutoManufaturado recuperarProdutoManufaturado(long idProdutoManufaturado) {
		try {
			return produtoManufaturadoDAO.recuperarProdutoManufaturado(idProdutoManufaturado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProdutoManufaturado recuperarProdutoManufaturadoPorCod(long codigoBarras) {
		try {
			return produtoManufaturadoDAO.recuperarProdutoManufaturadoPorCod(codigoBarras);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProdutoManufaturado> recuperarTodosProdutosManufaturado() {
		try {
			return produtoManufaturadoDAO.recuperarTodosProdutosManufaturado();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean excluirProdutoManufaturado(long idProdutoManufaturado) {
		return produtoManufaturadoDAO.excluirProdutoManufaturado(idProdutoManufaturado);
	}
}
