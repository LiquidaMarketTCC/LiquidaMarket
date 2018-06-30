package org.market.servlets.promocao;

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
import org.market.negocios.PromocaoNegocios;

@WebServlet("/ExcluirPromocaoServlet")
public class ExcluirPromocaoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3621840466109720056L;
	PromocaoNegocios promocaoNegocios = new PromocaoNegocios();	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idPromocao = Long.parseLong(req.getParameter("idPromocao"));			
			
			String json = excluirPromocao(idPromocao);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String excluirPromocao(long idPromocao) throws JsonProcessingException {
		
		boolean resposta = promocaoNegocios.excluirPromocao(idPromocao);
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
