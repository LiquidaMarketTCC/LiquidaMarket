package org.market.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.market.utils.DAOFactory;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<T, PK> {

	private final EntityManager entityManager;

	private final EntityManagerFactory factory;

	private Class<?> clazz;

	///////////////////////////////////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////////////////////////////////

	public GenericDAO() {
		this(DAOFactory.entityManagerFactorInstance());
	}

	public GenericDAO(EntityManagerFactory factory) {
		this.factory = factory;
		this.entityManager = factory.createEntityManager();
		this.clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	///////////////////////////////////////////////////////////////////
	// CRUD Methods
	///////////////////////////////////////////////////////////////////

	public Object executeQuery(String query, Object... params) {
		Query createdQuery = this.entityManager.createQuery(query);
		System.out.println(query);
		for (int i = 1; i <= params.length; i++) {
			System.out.println(params[i-1]);
			createdQuery.setParameter(i, params[i-1]);
		}

		return createdQuery.getResultList();
	}

	public List<T> findAll() {
		return this.entityManager.createQuery(("FROM " + this.clazz.getName())).getResultList();
	}

	public T findById(PK pk) {
		return (T) this.entityManager.find(this.clazz, pk);
	}

	public void save(T entity) {
		try {
			this.beginTransaction();
			this.entityManager.persist(entity);
			this.commit();
		} catch (Exception e) {
			this.rollBack();
			throw e;
		}
	}

	public void update(T entity) {
		try {
			this.beginTransaction();
			this.entityManager.merge(entity);
			this.commit();
		} catch (Exception e) {
			this.rollBack();
			throw e;
		}
	}

	public void delete(T entity) {
		try {
			this.beginTransaction();
			this.entityManager.remove(entity);
			this.commit();
		} catch (Exception e) {
			this.rollBack();
			throw e;
		}
	}

	///////////////////////////////////////////////////////////////////
	// Transaction Methods
	///////////////////////////////////////////////////////////////////

	public void beginTransaction() {
		this.entityManager.getTransaction().begin();
	}

	public void commit() {
		this.entityManager.getTransaction().commit();
	}

	public void close() {
		this.entityManager.close();
		this.factory.close();
	}

	public void rollBack() {
		this.entityManager.getTransaction().rollback();
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

}