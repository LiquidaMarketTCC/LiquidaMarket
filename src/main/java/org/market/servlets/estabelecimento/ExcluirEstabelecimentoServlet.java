package org.market.servlets.estabelecimento;

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
import org.market.negocios.EstabelecimentoNegocios;

@WebServlet("/ExcluirEstabelecimentoServlet")
public class ExcluirEstabelecimentoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6259126015269603053L;
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idEstabelecimento = Long.parseLong(req.getParameter("idEstabelecimento"));			
			
			String json = excluirEstabelecimento(idEstabelecimento);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String excluirEstabelecimento(long idEstabelecimento) throws JsonProcessingException {
		
		boolean resposta = estabelecimentoNegocios.excluirEstabelecimento(idEstabelecimento);
		try {
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(resposta);
			return json;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
