package org.market.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.ItensPromocao;
import org.market.entidades.ProdutoComercializado;
import org.market.entidades.Promocao;
import org.market.negocios.EstabelecimentoNegocios;
import org.market.negocios.ProdutoComercializadoNegocios;
import org.market.negocios.PromocaoNegocios;

@WebServlet("/AdicionarItemPromocaoServlet")
public class AdicionarItemPromocaoServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(AdicionarItemPromocaoServlet.class);
	private static final long serialVersionUID = -6648223870207313739L;
	PromocaoNegocios promocaoNegocios = new PromocaoNegocios();
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();
	ProdutoComercializadoNegocios produtoComercializadoNegocios = new ProdutoComercializadoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("Request /AdicionarItemPromocaoServlet");
		resp.setContentType("application/json");
		try {

			long idPromocao = Long.parseLong(req.getParameter("idPromocao"));
			String listaProdutos[] = req.getParameter("itens").split(";");
			Promocao promocao = promocaoNegocios.recuperarPromocao(idPromocao);
			String json = null;

			promocaoNegocios.excluirTodosItensPromocao(idPromocao);

			for (String item : listaProdutos) {

				String prod[] = item.split(",");
				long idProdutoComercializado = Long.parseLong(prod[0]);
				double valorPromocional = Double.parseDouble(prod[1]);
				ProdutoComercializado produtoComercializado = produtoComercializadoNegocios
						.recuperarProdutoComercializado(idProdutoComercializado);
				json = adicionarItemPromocao(promocao, produtoComercializado, valorPromocional);
			}
			LOGGER.info("Request /AdicionarItemPromocaoServlet /Json :" + json);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@SuppressWarnings("unused")
	private String adicionarItemPromocao(Promocao promocao, ProdutoComercializado produtoComercializado,
			double valorPromocionalProduto) throws JsonProcessingException {

		ItensPromocao item = promocaoNegocios.adicionarItemPromocao(promocao, produtoComercializado,
				valorPromocionalProduto);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(promocao);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
