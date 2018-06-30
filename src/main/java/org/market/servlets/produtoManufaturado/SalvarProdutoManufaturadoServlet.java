package org.market.servlets.produtoManufaturado;

import java.io.IOException;
import java.io.PrintWriter;

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
import org.market.entidades.Categoria;
import org.market.entidades.Estabelecimento;
import org.market.entidades.Marca;
import org.market.entidades.ProdutoComercializado;
import org.market.entidades.ProdutoManufaturado;
import org.market.negocios.CategoriaNegocios;
import org.market.negocios.EstabelecimentoNegocios;
import org.market.negocios.MarcaNegocios;
import org.market.negocios.ProdutoComercializadoNegocios;
import org.market.negocios.ProdutoManufaturadoNegocios;
import org.market.servlets.estabelecimento.SalvarEstabelecimentoServlet;

@WebServlet("/SalvarProdutoManufaturadoServlet")
public class SalvarProdutoManufaturadoServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SalvarEstabelecimentoServlet.class);

	private static final long serialVersionUID = -3979798861965473067L;
	ProdutoManufaturadoNegocios produtoManufaturadoNegocios = new ProdutoManufaturadoNegocios();
	MarcaNegocios marcaNegocios = new MarcaNegocios();
	CategoriaNegocios categoriaNegocios = new CategoriaNegocios();
	ProdutoComercializadoNegocios produtoComercializadoNegocios = new ProdutoComercializadoNegocios();
	ProdutoManufaturado produtoManufaturado = null;
	EstabelecimentoNegocios estabelecimentoNegocios = new EstabelecimentoNegocios();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {

			LOGGER.info("Request /SalvarProdutoManufaturadoServlet");

			// Produto Manufaturado
			String strCodigoBarras = req.getParameter("codigoBarras");
			long codigoBarras = Long.parseLong(req.getParameter("codigoBarras"));
			String nome = req.getParameter("nome");
			String descricao = req.getParameter("descricao");
			String strMarca = req.getParameter("marca");
			String strCategoria = req.getParameter("categoria");
			double valor = Double.parseDouble(req.getParameter("valor").replace(',', '.'));
			boolean situacao = Integer.parseInt(req.getParameter("situacao")) == 1 ? true : false;

			Marca marca = tratarMarca(strCodigoBarras, strMarca);
			Categoria categoria = categoriaNegocios.recuperarCategoria(strCategoria);

			String json = salvarProdutoManufaturado(codigoBarras, nome, descricao, marca, categoria);
			LOGGER.info("Request /SalvarProdutoManufaturadoServlet /Json :" + json);

			// Produto Comercializado
			HttpSession sessao = req.getSession();
			LOGGER.info("Session idEstabelecimento :" + sessao.getAttribute("idUsuario"));
			long idUsuario = (long) sessao.getAttribute("idUsuario");

			json = salvarProdutoComercializado(valor, situacao, produtoManufaturado,
					estabelecimentoNegocios.recuperarEstabelecimentoPorIdUsuario(idUsuario));

			PrintWriter out = resp.getWriter();
			out.print(json);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String salvarProdutoComercializado(double valor, boolean situacao, ProdutoManufaturado produtoManufaturado,
			Estabelecimento estabelecimento) throws JsonProcessingException {

		ProdutoComercializado produtoComercializado = produtoComercializadoNegocios.salvarProdutoComercializado(valor,
				situacao, produtoManufaturado, estabelecimento);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(produtoComercializado);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

	private String salvarProdutoManufaturado(long codigoBarras, String nome, String descricao, Marca marca,
			Categoria categoria) throws JsonProcessingException {

		produtoManufaturado = produtoManufaturadoNegocios.recuperarProdutoManufaturadoPorCod(codigoBarras);

		if (produtoManufaturado == null) {
			produtoManufaturado = produtoManufaturadoNegocios.salvarProdutoManufaturado(codigoBarras, nome, descricao,
					marca, categoria);
		}

		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(produtoManufaturado);
			return json;
		} catch (Exception e) {
			return null;
		}

	}

	private Marca tratarMarca(String codBarra, String nomeMarca) {
		String strIdMarca = codBarra.substring(3, 8);
		long idMarca = Long.parseLong(strIdMarca);
		Marca marca = null;

		marca = marcaNegocios.recuperarMarcaPorId(idMarca);
		if (marca != null) {
			return marca;
		}

		marca = marcaNegocios.salvarMarca(idMarca, nomeMarca);

		return marca;
	}

}
