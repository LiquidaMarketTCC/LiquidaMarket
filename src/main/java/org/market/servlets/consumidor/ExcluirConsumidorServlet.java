package org.market.servlets.consumidor;

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
import org.market.negocios.ConsumidorNegocios;

@WebServlet("/ExcluirConsumidorServlet")
public class ExcluirConsumidorServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1990527671889420249L;
	ConsumidorNegocios consumidorNegocios = new ConsumidorNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idConsumidor = Long.parseLong(req.getParameter("idConsumidor"));			
			
			String json = excluirConsumidor(idConsumidor);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String excluirConsumidor(long idConsumidor) throws JsonProcessingException {
		
		boolean resposta = consumidorNegocios.excluirConsumidor(idConsumidor);
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
