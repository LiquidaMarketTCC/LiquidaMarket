package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.market.entidades.Estabelecimento;

public class EstabelecimentoDAO extends GenericDAO<Estabelecimento, Long> {

	private static final Logger LOGGER = Logger.getLogger(ConsumidorDAO.class);
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;

	public Estabelecimento salvarEstabelecimento(Estabelecimento estabelecimento) {
		save(estabelecimento);
		LOGGER.info("Estabelecimento cadastrado! ID:" + estabelecimento.getIdEstabelecimento());
		return estabelecimento;
	}

	public Estabelecimento recuperarEstabelecimento(String cnpj) {
		@SuppressWarnings("unchecked")
		List<Estabelecimento> lista = (List<Estabelecimento>) executeQuery(
				"SELECT e FROM Estabelecimento e WHERE e.cnpj = ?", cnpj);
		Estabelecimento estabelecimento = (Estabelecimento) lista.get(0);

		return estabelecimento;
	}

	public Estabelecimento recuperarEstabelecimentoPorId(long idEstabelecimento) {
		return findById(idEstabelecimento);
	}

	@SuppressWarnings("unchecked")
	public Estabelecimento recuperarEstabelecimentoPorIdUsuario(long idUsuario) {
		List<Estabelecimento> lista = (List<Estabelecimento>) executeQuery(
				"Select c FROM Estabelecimento c WHERE c.usuario.idUsuario = ?", idUsuario);
		Estabelecimento estabelecimento = lista.get(0);
		return estabelecimento;
	}

	public List<Estabelecimento> recuperarTodosEstabelecimentos() {
		return findAll();
	}

	public boolean excluirEstabelecimento(long idEstabelecimento) {

		try {
			delete(findById(idEstabelecimento));
			return true;

		} catch (Exception e) {
			return false;
		}

	}

}
