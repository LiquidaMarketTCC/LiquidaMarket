package org.market.servlets.promocao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.Estabelecimento;
import org.market.entidades.Promocao;
import org.market.negocios.EstabelecimentoNegocios;
import org.market.negocios.PromocaoNegocios;
import org.market.servlets.estabelecimento.SalvarEstabelecimentoServlet;

@WebServlet("/SalvarPromocaoServlet")
public class SalvarPromocaoServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SalvarEstabelecimentoServlet.class);
	private static final long serialVersionUID = -1321121815253342181L;
	PromocaoNegocios promocaoNegocios = new PromocaoNegocios();
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {
			LOGGER.info("Request /SalvarPromocaoServlet");

			String nome = req.getParameter("nome");
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicial = formato.parse(req.getParameter("dataInicial"));
			Date dataTermino = formato.parse(req.getParameter("dataTermino"));
			boolean situacao = Boolean.parseBoolean(req.getParameter("situacao"));
			String str_IdPromocao = req.getParameter("idPromocao");
			long idPromocao = 0;
			try {
				idPromocao = Long.parseLong(str_IdPromocao);
			} catch (Exception e) {
				idPromocao = 0;
			}

			// Estabelecimeento
			HttpSession sessao = req.getSession();
			LOGGER.info("Session idEstabelecimento :" + sessao.getAttribute("idUsuario"));
			long idUsuario = (long) sessao.getAttribute("idUsuario");

			String json = salvarPromocao(nome, dataInicial, dataTermino, situacao,
					estabelecimentoNegocios.recuperarEstabelecimentoPorIdUsuario(idUsuario), idPromocao);
			LOGGER.info("Request /SalvarPromocaoServlet /Json :" + json);
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String salvarPromocao(String nome, Date dataInicial, Date dataTermino, boolean situacao,
			Estabelecimento estabelecimento, long idPromocao) throws JsonProcessingException {

		Promocao promocao = promocaoNegocios.salvarPromocao(nome, dataInicial, dataTermino, situacao, estabelecimento,
				idPromocao);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(promocao);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
