package org.market.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.market.dao.CategoriaDAO;

public class DAOFactory {

	private DAOFactory() {
	}

	///////////////////////////////////////////////////////////////////
	// ENTITY MANAGER FACTORY
	///////////////////////////////////////////////////////////////////

	private static final String PERSISTENCE_UNIT_NAME = "marketPersistenceUnit";

	private static EntityManagerFactory entityManagerFactoryInstance;

	public static EntityManagerFactory entityManagerFactorInstance() {
		if (entityManagerFactoryInstance == null) {
			entityManagerFactoryInstance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}

		return entityManagerFactoryInstance;
	}

	///////////////////////////////////////////////////////////////////
	// Rua
	///////////////////////////////////////////////////////////////////

	private static CategoriaDAO categoriaDaoInstance;

	public static CategoriaDAO categoriaInstance() {
		if (categoriaDaoInstance == null) {
			categoriaDaoInstance = new CategoriaDAO();
		}

		return categoriaDaoInstance;
	}

}
