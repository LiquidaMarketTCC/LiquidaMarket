package org.market.negocios;

import org.apache.log4j.Logger;
import org.market.dao.UsuarioDAO;
import org.market.entidades.Usuario;

public class UsuarioNegocios {

	private static final Logger LOGGER = Logger.getLogger(UsuarioNegocios.class);
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario salvarUsuario(String email, String senha) {
		LOGGER.info("Iniciando negócios: UsuarioNegocios [ email :" + email + " ; senha : " + senha);
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(email);
			usuario.setSenha(senha);
			return usuarioDAO.salvarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario recuperarUsuario(String email, String senha) {
		LOGGER.info("Iniciando negócios: UsuarioNegocios [ email :" + email + " ; senha : " + senha);
		try {
			return usuarioDAO.recuperarUsuario(email, senha);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
