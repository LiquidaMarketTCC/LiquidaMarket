package org.market.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.Usuario;
import org.market.negocios.UsuarioNegocios;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = -7186601017063206506L;
	UsuarioNegocios usuarioNeg = new UsuarioNegocios();
	Usuario usuario;

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			LOGGER.info("Request /LoginServlet");
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");

			String json = realizarLogin(email, senha);
			LOGGER.info("Request /LoginServlet /Json :" + json);

			// Cria sessão
			HttpSession sessao = req.getSession();
			sessao.putValue("idUsuario", usuario.getIdUsuario());
			LOGGER.info("Session IdUsuario :" + sessao.getAttribute("idUsuario"));
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String realizarLogin(String email, String senha) throws JsonProcessingException {

		usuario = usuarioNeg.recuperarUsuario(email, senha);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(usuario);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
