package org.market.servlets.produtoComercializado;

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
import org.market.entidades.ProdutoComercializado;
import org.market.negocios.ProdutoComercializadoNegocios;

@WebServlet("/RecuperarProdutoComercializadoServlet")
public class RecuperarProdutoComercializadoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7757295518178639826L;
	ProdutoComercializadoNegocios produtoComercializadoNegocios = new ProdutoComercializadoNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idProdutoComercializado = Long.parseLong(req.getParameter("idProdutoComercializado"));			
			
			String json = recuperarProdutoComercializado(idProdutoComercializado);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarProdutoComercializado(long idProdutoComercializado) throws JsonProcessingException {
		
		ProdutoComercializado produtoComercializado = produtoComercializadoNegocios.recuperarProdutoComercializado(idProdutoComercializado);
		
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = null;
			json = ow.writeValueAsString(produtoComercializado);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
