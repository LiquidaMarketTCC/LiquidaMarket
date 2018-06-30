package org.market.negocios;

import java.util.List;

import org.market.dao.AvaliarEstabelecimentoDAO;
import org.market.entidades.AvaliarEstabelecimento;
import org.market.entidades.Consumidor;
import org.market.entidades.Estabelecimento;

public class AvaliarEstabelecimentoNegocios {

	private AvaliarEstabelecimentoDAO avaliarEstabelecimentoDAO = new AvaliarEstabelecimentoDAO();

	public AvaliarEstabelecimento salvarAvaliacao(int pontuacao, String comentario, Estabelecimento estabelecimento,
			Consumidor consumidor) {

		try {
			AvaliarEstabelecimento avaliacao = new AvaliarEstabelecimento();
			avaliacao.setPontuacao(pontuacao);
			avaliacao.setComentario(comentario);
			avaliacao.setEstabelecimento(estabelecimento);
			avaliacao.setConsumidor(consumidor);
			avaliacao = avaliarEstabelecimentoDAO.salvarAvaliacao(avaliacao);
			return avaliacao;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<AvaliarEstabelecimento> recuperarTodasAvaliacoesEstabelecimento(long idEstabelecimento) {
		try {
			return avaliarEstabelecimentoDAO.recuperarTodasAvaliacoesEstabelecimento(idEstabelecimento);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<AvaliarEstabelecimento> recuperarTodasAvaliacoesConsumidor(long idConsumidor) {
		try {
			return avaliarEstabelecimentoDAO.recuperarTodasAvaliacoesConsumidor(idConsumidor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public double mediaAvaliacaoEstabelecimento(long idEstabelecimento) {

		return avaliarEstabelecimentoDAO.mediaAvaliacaoEstabelecimento(idEstabelecimento);
	}

}
