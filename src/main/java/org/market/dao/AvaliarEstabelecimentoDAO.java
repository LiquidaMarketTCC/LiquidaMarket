package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.market.entidades.AvaliarEstabelecimento;

public class AvaliarEstabelecimentoDAO extends GenericDAO<AvaliarEstabelecimento,Long>{
	
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;
		
	public AvaliarEstabelecimento salvarAvaliacao(AvaliarEstabelecimento avaliacao) {
		save(avaliacao);
		return avaliacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<AvaliarEstabelecimento> recuperarTodasAvaliacoesEstabelecimento(long idEstabelecimento) {
		List<AvaliarEstabelecimento> lista = (List<AvaliarEstabelecimento>) executeQuery("SELECT a FROM AvaliarEstabelecimento a WHERE a.idEstabelecimento =?", idEstabelecimento);
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AvaliarEstabelecimento> recuperarTodasAvaliacoesConsumidor(long idConsumidor) {
		List<AvaliarEstabelecimento> lista = (List<AvaliarEstabelecimento>) executeQuery("SELECT a FROM AvaliarEstabelecimento a WHERE a.idConsumidor =?", idConsumidor);
		return lista;
	}
	
	public double mediaAvaliacaoEstabelecimento(long idEstabelecimento) {
		double mediaEstabelecimento =  (double) executeQuery("SELECT AVG(pontuacao) FROM AvaliarEstabelecimento a WHERE a.idEstabelecimento =?", idEstabelecimento);
		return mediaEstabelecimento;
	}
	
	
}
