package org.market.servlets.estabelecimento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.market.entidades.Estabelecimento;
import org.market.negocios.EstabelecimentoNegocios;

@WebServlet("/SalvarEstabelecimentoServlet")
public class SalvarEstabelecimentoServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SalvarEstabelecimentoServlet.class);
	private static final long serialVersionUID = 604954234026087566L;
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			LOGGER.info("Request /SalvarEstabelecimentoServlet");
			String cnpj = req.getParameter("cnpj");
			String razaoSocial = req.getParameter("razaoSocial");
			String nomeFantasia = req.getParameter("nomeFantasia");
			String endereco = req.getParameter("endereco");
			String telefone = req.getParameter("telefone");
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");

			String horaInicial = req.getParameter("horaInicial");
			String horaFinal = req.getParameter("horaFinal");
		
			String formasPgto[] = req.getParameter("formasPgto").split(",");

			ArrayList<String> formasPagamento = new ArrayList<>();
			for (String item : formasPgto) {
				formasPagamento.add(item);
			}

			String json = salvarEstabelecimento(cnpj, razaoSocial, nomeFantasia, endereco, telefone, email, horaInicial,
					horaFinal, formasPagamento,senha);
			LOGGER.info("Request /SalvarEstabelecimentoServlet /Json :" + json);

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String salvarEstabelecimento(String cnpj, String razaoSocial, String nomeFantasia, String endereco,
			String telefone, String email, String horaInicial, String horaFinal, ArrayList<String> formasPagamento,
			String senha) throws JsonProcessingException {

		Estabelecimento estabelecimento = estabelecimentoNegocios.salvarEstabelecimento(cnpj, razaoSocial, nomeFantasia,
				endereco, telefone, email, horaInicial, horaFinal, formasPagamento, senha);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(estabelecimento);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

}
