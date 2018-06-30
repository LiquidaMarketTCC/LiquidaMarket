package org.market.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);
	private static final long serialVersionUID = -6648223870207313739L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("Request /AdicionarItemPromocaoServlet");
		resp.setContentType("application/json");
		try {
			HttpSession sessao = req.getSession();
			sessao.invalidate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
