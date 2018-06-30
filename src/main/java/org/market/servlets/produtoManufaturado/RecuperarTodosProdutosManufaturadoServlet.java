package org.market.servlets.produtoManufaturado;

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
import org.market.entidades.ProdutoManufaturado;
import org.market.negocios.ProdutoManufaturadoNegocios;

@WebServlet("/RecuperarTodosProdutosManufaturadoServlet")
public class RecuperarTodosProdutosManufaturadoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -186665475770790296L;
	ProdutoManufaturadoNegocios produtoManufaturadoNegocios = new ProdutoManufaturadoNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			String json = recuperarTodosProdutosManufaturado();
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarTodosProdutosManufaturado() throws JsonProcessingException {
		
		List<ProdutoManufaturado> produtosManufaturado = produtoManufaturadoNegocios.recuperarTodosProdutosManufaturado();
		try {
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(produtosManufaturado);
			return json;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
