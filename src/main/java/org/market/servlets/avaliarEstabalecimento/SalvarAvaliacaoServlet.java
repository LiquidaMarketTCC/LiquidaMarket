package org.market.servlets.avaliarEstabalecimento;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.AvaliarEstabelecimento;
import org.market.entidades.Consumidor;
import org.market.entidades.Estabelecimento;
import org.market.negocios.AvaliarEstabelecimentoNegocios;
import org.market.negocios.ConsumidorNegocios;
import org.market.negocios.EstabelecimentoNegocios;

@WebServlet("/SalvarAvaliacaoServlet")
public class SalvarAvaliacaoServlet extends HttpServlet {

	private static final long serialVersionUID = 4048390046264043210L;
	AvaliarEstabelecimentoNegocios avaliarEstabelecimentoNegocios = new AvaliarEstabelecimentoNegocios();
	ConsumidorNegocios consumidorNegocios = new ConsumidorNegocios();
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			int pontuacao = Integer.parseInt(req.getParameter("pontuacao"));
			String comentario = req.getParameter("comentario");
			long idEstabelecimento = Long.parseLong(req.getParameter("estabelecimento"));
			long idConsumidor = Long.parseLong(req.getParameter("consumidor"));

			Estabelecimento estabelecimento = estabelecimentoNegocios.recuperarEstabelecimentoPorId(idEstabelecimento);
			Consumidor consumidor = consumidorNegocios.recuperarConsumidorPorId(idConsumidor);

			String json = salvarAvaliacao(pontuacao, comentario, estabelecimento, consumidor);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String salvarAvaliacao(int pontuacao, String comentario, Estabelecimento estabelecimento,
			Consumidor consumidor) throws JsonProcessingException {

		AvaliarEstabelecimento avialiacao = avaliarEstabelecimentoNegocios.salvarAvaliacao(pontuacao, comentario,
				estabelecimento, consumidor);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(avialiacao);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
