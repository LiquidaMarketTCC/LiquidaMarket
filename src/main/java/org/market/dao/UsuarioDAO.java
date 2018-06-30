package org.market.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.market.entidades.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario,Long>{
	
	private static final Logger LOGGER = Logger.getLogger(ConsumidorDAO.class);
	@PersistenceContext(unitName = "liquidaMarket")
	private EntityManager manager;
		
	public Usuario salvarUsuario(Usuario usuario) {
		save(usuario);
		LOGGER.info("Usuario cadastrado! ID:" + usuario.getIdUsuario());
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public Usuario recuperarUsuario(String login, String senha) {

		List<Usuario> lista = (List<Usuario>) executeQuery("Select u FROM Usuario u WHERE u.login = ? AND u.senha = ?", login,senha);
		Usuario usuario = lista.get(0);
		LOGGER.info("Login efetuado! ID:" + usuario.getIdUsuario());
		return usuario;
	}	

	
}
