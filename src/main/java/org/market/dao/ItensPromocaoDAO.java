package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.market.entidades.ItensPromocao;

public class ItensPromocaoDAO extends GenericDAO<ItensPromocao, Long> {

	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;

	public ItensPromocao adicionarItemPromocao(ItensPromocao item) {
		save(item);
		return item;
	}

	public List<ItensPromocao> recuperarTodosItensPromocao(long idPromocao) {
		@SuppressWarnings("unchecked")
		List<ItensPromocao> lista = (List<ItensPromocao>) executeQuery(
				"Select c FROM ItensPromocao c WHERE c.promocao.idPromocao = ?", idPromocao);
		return lista;
	}

	public boolean excluirItemPromocao(long idItensPromocao) {

		try {
			delete(findById(idItensPromocao));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean excluirTodosItensPromocao(long idPromocao) {
		try {
			List<ItensPromocao> lista = recuperarTodosItensPromocao(idPromocao);
			for (ItensPromocao i : lista) {
				delete(i);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
