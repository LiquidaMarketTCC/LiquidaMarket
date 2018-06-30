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
import org.market.entidades.Consumidor;
import org.market.negocios.ConsumidorNegocios;

@WebServlet("/RecuperarConsumidorServlet")
public class RecuperarConsumidorServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5323187450187059998L;
	ConsumidorNegocios consumidorNegocios = new ConsumidorNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			String cpf = req.getParameter("cpf");			
			
			String json = recuperarConsumidor(cpf);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarConsumidor(String cpf) throws JsonProcessingException {
		
		Consumidor consumidor = consumidorNegocios.recuperarConsumidor(cpf);
		
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = null;
			json = ow.writeValueAsString(consumidor);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
