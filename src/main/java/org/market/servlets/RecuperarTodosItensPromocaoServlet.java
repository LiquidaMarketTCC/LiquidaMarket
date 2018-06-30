package org.market.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.ItensPromocao;
import org.market.negocios.PromocaoNegocios;

@WebServlet("/RecuperarTodosItensPromocaoServlet")
public class RecuperarTodosItensPromocaoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 550163098799312430L;
	PromocaoNegocios promocaoNegocios = new PromocaoNegocios();	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idPromocao = Long.parseLong(req.getParameter("idPromocao"));
			String json = recuperarTodosItensPromocao(idPromocao);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarTodosItensPromocao(long idPromocao) throws JsonProcessingException {
		
		List<ItensPromocao> itens = promocaoNegocios.recuperarTodosItensPromocao(idPromocao);
		try {
			ObjectMapper om = new ObjectMapper();
			om.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			ObjectWriter ow = om.writer()
					.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(itens);
			return json;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
