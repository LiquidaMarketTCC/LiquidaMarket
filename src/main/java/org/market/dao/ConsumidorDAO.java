package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.market.entidades.Consumidor;

public class ConsumidorDAO extends GenericDAO<Consumidor, Long> {

	private static final Logger LOGGER = Logger.getLogger(ConsumidorDAO.class);
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;

	public Consumidor salvarConsumidor(Consumidor consumidor) {
		save(consumidor);
		LOGGER.info("Consumidor cadastrado! ID:" + consumidor.getIdConsumidor());
		return consumidor;
	}

	@SuppressWarnings("unchecked")
	public Consumidor recuperarConsumidor(String cpf) {
		List<Consumidor> lista = (List<Consumidor>) executeQuery("Select c FROM Consumidor c WHERE c.cpf = ?", cpf);
		Consumidor consumidor = lista.get(0);
		return consumidor;
	}

	public Consumidor recuperarConsumidorPorId(long idConsumidor) {
		return findById(idConsumidor);
	}

	@SuppressWarnings("unchecked")
	public Consumidor recuperarConsumidorPorIdUsuario(long idUsuario) {
		List<Consumidor> lista = (List<Consumidor>) executeQuery("Select c FROM Consumidor c WHERE c.usuario.idUsuario = ?",
				idUsuario);
		Consumidor consumidor = lista.get(0);
		LOGGER.info("Usuario recuperado! ID:" + consumidor.getIdConsumidor());
		return consumidor;
	}

	public boolean excluirConsumidor(long idConsumidor) {

		try {
			delete(findById(idConsumidor));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
