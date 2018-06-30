package org.market.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

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
import org.market.entidades.Consumidor;
import org.market.entidades.Estabelecimento;
import org.market.negocios.ConsumidorNegocios;
import org.market.negocios.EstabelecimentoNegocios;

@WebServlet("/VerificarLoginServlet")
public class VerificarLoginServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(VerificarLoginServlet.class);
	private static final long serialVersionUID = -7186601017063206506L;

	ConsumidorNegocios consumidorNegocios = new ConsumidorNegocios();
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			LOGGER.info("Request /VerificarLoginServlet");

			// Cria sessão
			HttpSession sessao = req.getSession();
			LOGGER.info("Session IdUsuario :" + sessao.getAttribute("idUsuario"));

			long idUsuario = (long) sessao.getAttribute("idUsuario");
			String json = verificarLogin(idUsuario);
			LOGGER.info("Request /VerificarLoginServlet Json :" + json);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
		}
	}

	private String verificarLogin(long idUsuario) throws JsonProcessingException {

		Consumidor consumidor = null;
		Estabelecimento estabelecimento = null;

		try {
			consumidor = consumidorNegocios.recuperarConsumidorPorIdUsuario(idUsuario);
		} catch (Exception e) {
		}

		try {
			estabelecimento = estabelecimentoNegocios.recuperarEstabelecimentoPorIdUsuario(idUsuario);
		} catch (Exception e) {

		}
		try {
			ObjectMapper om = new ObjectMapper();
			om.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();

			String json = "";
			if (consumidor != null) {
				json = ow.writeValueAsString(consumidor);
			} else {
				json = ow.writeValueAsString(estabelecimento);
			}
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
