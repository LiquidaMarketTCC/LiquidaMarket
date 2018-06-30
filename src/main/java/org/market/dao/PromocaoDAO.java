package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.market.entidades.Promocao;

public class PromocaoDAO extends GenericDAO<Promocao,Long>{
	
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;
		
	public Promocao salvarPromocao(Promocao promocao) {
		save(promocao);
		return promocao;
	}
	
	public Promocao recuperarPromocao(long idPromocao) {
		return findById(idPromocao);
	}	
	
	public List<Promocao> recuperarTodasPromocoes() {
		return findAll();
	}
	
	public List<Promocao> recuperarTodasPromocoesEstabelecimento(long idEstabelecimento) {
		@SuppressWarnings("unchecked")
		List<Promocao> lista = (List<Promocao>) executeQuery("SELECT p FROM Promocao p WHERE p.idEstabelecimento = ?", idEstabelecimento);
		return lista;
	}
	
	public boolean alterarSituacaoPromocao(long idPromocao, boolean situacao) {

		try {		
			executeQuery("UPDATE Promocao p SET p.situacao =? WHERE p.idPromocao = ?", situacao, idPromocao);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean excluirPromocao(long idPromocao) {
		try {
			delete(findById(idPromocao));	
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
	
	public boolean excluirTodasPromocoesEstabelecimento(long idEstabelecimento) {
		try {
			executeQuery("DELETE FROM Promocao p WHERE p.idEstabelecimento = = ?", idEstabelecimento);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
