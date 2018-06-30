package org.market.servlets.consumidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.Consumidor;
import org.market.negocios.ConsumidorNegocios;

@WebServlet("/SalvarConsumidorServlet")
public class SalvarConsumidorServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SalvarConsumidorServlet.class);
	private static final long serialVersionUID = -7186601017063206506L;
	ConsumidorNegocios consumidorNegocios = new ConsumidorNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			LOGGER.info("Request /SalvarConsumidorServlet");
			String cpf = req.getParameter("cpf");
			String nome = req.getParameter("nome");
			boolean sexo = Boolean.parseBoolean(req.getParameter("sexo"));

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date dataNasc = formato.parse(req.getParameter("dataNasc"));

			String endereco = req.getParameter("endereco");
			String telefone = req.getParameter("telefone");
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");

			String json = salvarConsumidor(cpf, nome, sexo, dataNasc, endereco, telefone, email, senha);
			LOGGER.info("Request /SalvarConsumidorServlet /Json :" + json);
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String salvarConsumidor(String cpf, String nome, boolean sexo, Date dataNasc, String endereco,
			String telefone, String email, String senha) throws JsonProcessingException {

		Consumidor consumidor = consumidorNegocios.salvarConsumidor(cpf, nome, sexo, dataNasc, endereco, telefone,
				email, senha);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(consumidor);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
