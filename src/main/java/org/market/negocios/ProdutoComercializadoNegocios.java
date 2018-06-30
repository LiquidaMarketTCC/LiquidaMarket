package org.market.negocios;

import java.util.ArrayList;
import java.util.List;

import org.market.dao.ProdutoComercializadoDAO;
import org.market.entidades.Estabelecimento;
import org.market.entidades.ItensPromocao;
import org.market.entidades.ProdutoComercializado;
import org.market.entidades.ProdutoManufaturado;
import org.market.entidades.view.Produto_Promocao;

public class ProdutoComercializadoNegocios {

	private ProdutoComercializadoDAO produtoComercializadoDAO = new ProdutoComercializadoDAO();
	private PromocaoNegocios promocaoNegocios = new PromocaoNegocios();

	public ProdutoComercializado salvarProdutoComercializado(double valor, boolean situacao,
			ProdutoManufaturado produtoManufaturado, Estabelecimento estabelecimento) {

		try {
			ProdutoComercializado produtoComercializado = new ProdutoComercializado();

			try {
				produtoComercializado = produtoComercializadoDAO.recuperarProdutoComercializadoCodBarrasEstabelecimento(
						produtoManufaturado.getCodigoBarras(), estabelecimento.getIdEstabelecimento());
			} catch (Exception e) {
				produtoComercializado = new ProdutoComercializado();
			}

			produtoComercializado.setValor(valor);
			produtoComercializado.setSituacao(situacao);
			produtoComercializado.setProdutoManufaturado(produtoManufaturado);
			produtoComercializado.setEstabelecimento(estabelecimento);
			produtoComercializado = produtoComercializadoDAO.salvarProdutoComercializado(produtoComercializado);
			return produtoComercializado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProdutoComercializado recuperarProdutoComercializado(long idProdutoComercializado) {
		try {
			return produtoComercializadoDAO.recuperarProdutoComercializado(idProdutoComercializado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProdutoComercializado> recuperarTodosProdutosComercializado() {
		try {
			return produtoComercializadoDAO.recuperarTodosProdutosComercializado();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProdutoComercializado> recuperarTodosProdutosComercializado(long idEstabelecimento) {
		try {
			return produtoComercializadoDAO.recuperarTodosProdutosComercializado(idEstabelecimento);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProdutoComercializado recuperarTodosProdutosComercializadoCodBarras(long codBarra) {
		try {
			return produtoComercializadoDAO.recuperarProdutoComercializadoCodBarras(codBarra);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Produto_Promocao> recuperarTodosProdutosComercializadoESuaPromocao(long idEstabelecimento,
			long idPromocao) {
		try {
			List<Produto_Promocao> produtoPromocao = new ArrayList<>();
			List<ProdutoComercializado> listProdutoComercializado = new ArrayList<>();
			List<ItensPromocao> listItenPromocao = new ArrayList<>();
			listProdutoComercializado = produtoComercializadoDAO
					.recuperarTodosProdutosComercializado(idEstabelecimento);
			listItenPromocao = promocaoNegocios.recuperarTodosItensPromocao(idPromocao);

			for (ProdutoComercializado pC : listProdutoComercializado) {
				Produto_Promocao pP = new Produto_Promocao();
				pP.setProduto(pC);

				for (ItensPromocao ip : listItenPromocao) {
					if (pC.getIdProdutoComercializado() == ip.getIdItensPromocao()) {
						pP.setPromocao(ip);
					}
				}

				produtoPromocao.add(pP);
			}

			return produtoPromocao;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProdutoComercializado> recuperarTodosProdutosComercializadoCategoria(long idCategoria) {
		try {
			return produtoComercializadoDAO.buscaProdutosPorCategoria(idCategoria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean alterarSituacao(long idProdutoComercializado, boolean situacao) {
		return produtoComercializadoDAO.alterarSituacao(idProdutoComercializado, situacao);
	}

	public boolean excluirProdutoComercializado(long idProdutoComercializado) {
		return produtoComercializadoDAO.excluirProdutoComercializado(idProdutoComercializado);
	}
}
