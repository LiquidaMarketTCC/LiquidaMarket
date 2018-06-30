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
import org.market.negocios.ProdutoComercializadoNegocios;

@WebServlet("/ExcluirProdutoComercializadoServlet")
public class ExcluirProdutoComercializadoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8374955113835132392L;
	ProdutoComercializadoNegocios produtoComercializadoNegocios = new ProdutoComercializadoNegocios();	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idProdutoComercializado = Long.parseLong(req.getParameter("idProdutoComercializado"));			
			
			String json = excluirProdutoComercializado(idProdutoComercializado);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String excluirProdutoComercializado(long idProdutoComercializado) throws JsonProcessingException {
		
		boolean resposta = produtoComercializadoNegocios.excluirProdutoComercializado(idProdutoComercializado);
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
