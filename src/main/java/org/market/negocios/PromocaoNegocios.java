package org.market.negocios;

import java.util.Date;
import java.util.List;

import org.market.dao.ItensPromocaoDAO;
import org.market.dao.PromocaoDAO;
import org.market.entidades.Estabelecimento;
import org.market.entidades.ItensPromocao;
import org.market.entidades.ProdutoComercializado;
import org.market.entidades.Promocao;

public class PromocaoNegocios {

	private PromocaoDAO promocaoDAO = new PromocaoDAO();
	private ItensPromocaoDAO itensPromocaoDao = new ItensPromocaoDAO();

	public Promocao salvarPromocao(String nome, Date dataInicial, Date dataTermino, boolean situacao,
			Estabelecimento estabelecimento, long idPromocao) {

		Promocao promocaoAtual = null;

		try {
			promocaoAtual = recuperarPromocao(idPromocao);
		} catch (Exception e) {
			promocaoAtual = null;
		}

		try {
			Promocao promocao = null;
			if (promocaoAtual == null) {
				promocao = new Promocao();
			} else {
				promocao = promocaoAtual;
			}

			promocao.setNome(nome);
			promocao.setDataInicial(dataInicial);
			promocao.setDataTermino(dataTermino);
			promocao.setSituacao(situacao);
			promocao.setEstabelecimento(estabelecimento);
			promocao = promocaoDAO.salvarPromocao(promocao);
			return promocao;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public void excluirItensPromocao(long idPromocao) {
		try {
			itensPromocaoDao.excluirTodosItensPromocao(idPromocao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ItensPromocao adicionarItemPromocao(Promocao promocao, ProdutoComercializado produtoComercializado,
			double valorPromocionalProduto) {
		try {
			ItensPromocao item = new ItensPromocao();
			item.setPromocao(promocao);
			item.setProdutoComercializado(produtoComercializado);
			item.setValorPromocionalProduto(valorPromocionalProduto);
			item = itensPromocaoDao.adicionarItemPromocao(item);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Promocao recuperarPromocao(long idPromocao) {
		try {
			return promocaoDAO.recuperarPromocao(idPromocao);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Promocao> recuperarTodasPromocoes() {
		try {
			return promocaoDAO.recuperarTodasPromocoes();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Promocao> recuperarTodasPromocoesEstabelecimento(long idEstabelecimento) {
		try {
			return promocaoDAO.recuperarTodasPromocoesEstabelecimento(idEstabelecimento);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ItensPromocao> recuperarTodosItensPromocao(long idPromocao) {
		try {
			return itensPromocaoDao.recuperarTodosItensPromocao(idPromocao);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean alterarSituacaoPromocao(long idPromocao, boolean situacao) {
		return promocaoDAO.alterarSituacaoPromocao(idPromocao, situacao);
	}

	public boolean excluirPromocao(long idPromocao) {
		return promocaoDAO.excluirPromocao(idPromocao);
	}

	public boolean excluirTodasPromocoesEstabelecimento(long idEstabelecimento) {
		return promocaoDAO.excluirTodasPromocoesEstabelecimento(idEstabelecimento);
	}

	public boolean excluirTodosItensPromocao(long idPromocao) {
		return itensPromocaoDao.excluirTodosItensPromocao(idPromocao);
	}

	public boolean excluirItemPromocao(long idItensPromocao) {
		return itensPromocaoDao.excluirItemPromocao(idItensPromocao);
	}

}
