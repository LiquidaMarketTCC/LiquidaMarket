package org.market.servlets.marca;

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
import org.market.entidades.Marca;
import org.market.negocios.MarcaNegocios;

@WebServlet("/RecuperarMarcaPorIdServlet")
public class RecuperarMarcaPorIdServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6879246090624581173L;
	MarcaNegocios marcaNegocios = new MarcaNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idMarca = Long.parseLong(req.getParameter("idMarca"));			
			
			String json = recuperarMarcaPorId(idMarca);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarMarcaPorId(long idMarca) throws JsonProcessingException {
		
		Marca marca = marcaNegocios.recuperarMarcaPorId(idMarca);
		try {
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(marca);
			return json;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
