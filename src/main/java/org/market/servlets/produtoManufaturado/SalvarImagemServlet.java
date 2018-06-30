package org.market.servlets.produtoManufaturado;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

@MultipartConfig
@WebServlet("/SalvarImagemServlet")
public class SalvarImagemServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SalvarImagemServlet.class);
	private static final long serialVersionUID = -3979798861965473067L;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		try {

			LOGGER.info("Request /SalvarImagemServlet");

			Part filePart = req.getPart("imagem");
			Part strCodigoBarras = req.getPart("codigoBarras");
			
			System.out.println(filePart);
			System.out.println(strCodigoBarras);
			PrintWriter out = resp.getWriter();
			out.print(1);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
