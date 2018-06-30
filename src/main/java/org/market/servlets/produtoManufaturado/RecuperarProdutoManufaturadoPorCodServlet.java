package org.market.servlets.produtoManufaturado;

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
import org.market.entidades.ProdutoManufaturado;
import org.market.negocios.ProdutoManufaturadoNegocios;

@WebServlet("/RecuperarProdutoManufaturadoPorCodServlet")
public class RecuperarProdutoManufaturadoPorCodServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1882605190256144057L;
	ProdutoManufaturadoNegocios produtoManufaturadoNegocios = new ProdutoManufaturadoNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long codigoBarras = Long.parseLong(req.getParameter("codigoBarras"));			
			
			String json = recuperarProdutoManufaturadoPorCod(codigoBarras);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarProdutoManufaturadoPorCod(long codigoBarras) throws JsonProcessingException {
		
		ProdutoManufaturado produtoManufaturado = produtoManufaturadoNegocios.recuperarProdutoManufaturadoPorCod(codigoBarras);
		
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = null;
			json = ow.writeValueAsString(produtoManufaturado);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
