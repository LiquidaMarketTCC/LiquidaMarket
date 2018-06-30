package org.market.servlets.avaliarEstabalecimento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.AvaliarEstabelecimento;
import org.market.negocios.AvaliarEstabelecimentoNegocios;

@WebServlet("/RecuperarTodasAvaliacoesConsumidorServlet")
public class RecuperarTodasAvaliacoesConsumidorServlet extends HttpServlet {

	private static final long serialVersionUID = 5886876703421065984L;
	AvaliarEstabelecimentoNegocios avaliarEstabelecimentoNegocios = new AvaliarEstabelecimentoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			long idConsumidor = Long.parseLong(req.getParameter("idConsumidor"));

			String json = recuperarTodasAvaliacoesConsumidor(idConsumidor);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String recuperarTodasAvaliacoesConsumidor(long idConsumidor) throws JsonProcessingException {

		List<AvaliarEstabelecimento> avaliacoes = avaliarEstabelecimentoNegocios
				.recuperarTodasAvaliacoesConsumidor(idConsumidor);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(avaliacoes);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
