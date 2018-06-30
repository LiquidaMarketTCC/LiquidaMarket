package org.market.servlets.estabelecimento;

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
import org.market.entidades.Estabelecimento;
import org.market.negocios.EstabelecimentoNegocios;

@WebServlet("/RecuperarTodosEstabelecimentosServlet")
public class RecuperarTodosEstabelecimentosServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1215957034793507590L;
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			String json = recuperarTodosEstabelecimentos();
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarTodosEstabelecimentos() throws JsonProcessingException {
		
		List<Estabelecimento> estabelecimentos = estabelecimentoNegocios.recuperarTodosEstabelecimentos();
		try {
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(estabelecimentos);
			return json;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
