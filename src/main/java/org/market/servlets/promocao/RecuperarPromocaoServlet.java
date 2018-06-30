package org.market.servlets.promocao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.Promocao;
import org.market.negocios.PromocaoNegocios;

@WebServlet("/RecuperarPromocaoServlet")
public class RecuperarPromocaoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5885419885895699163L;
	PromocaoNegocios promocaoNegocios = new PromocaoNegocios();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		try {			
			
			long idPromocao = Long.parseLong(req.getParameter("idPromocao"));			
			
			String json = recuperarPromocao(idPromocao);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String recuperarPromocao(long idPromocao) throws JsonProcessingException {
		
		Promocao promocao = promocaoNegocios.recuperarPromocao(idPromocao);
		
		try {
			ObjectMapper om = new ObjectMapper();
			om.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
			String json = null;
			json = ow.writeValueAsString(promocao);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
