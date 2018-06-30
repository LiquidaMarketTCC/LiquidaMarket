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
import org.market.negocios.AvaliarEstabelecimentoNegocios;

@WebServlet("/MediaAvaliacaoEstabelecimentoServlet")
public class MediaAvaliacaoEstabelecimentoServlet extends HttpServlet {

	private static final long serialVersionUID = 4639895551561378259L;
	AvaliarEstabelecimentoNegocios avaliarEstabelecimentoNegocios = new AvaliarEstabelecimentoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			long idEstabelecimento = Long.parseLong(req.getParameter("idEstabelecimento"));

			String json = mediaAvaliacaoEstabelecimento(idEstabelecimento);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String mediaAvaliacaoEstabelecimento(long idEstabelecimento) throws JsonProcessingException {

		double media = avaliarEstabelecimentoNegocios.mediaAvaliacaoEstabelecimento(idEstabelecimento);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(media);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
