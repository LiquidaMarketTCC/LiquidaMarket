package org.market.negocios;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.market.dao.EstabelecimentoDAO;
import org.market.entidades.Estabelecimento;
import org.market.entidades.Usuario;

public class EstabelecimentoNegocios {

	private static final Logger LOGGER = Logger.getLogger(ConsumidorNegocios.class);
	private EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
	private UsuarioNegocios usuarioNeg = new UsuarioNegocios();

	public Estabelecimento salvarEstabelecimento(String cnpj, String razaoSocial, String nomeFantasia, String endereco,
			String telefone, String email, String horaInicial, String horaFinal, ArrayList<String> formasPagamento,
			String senha) {

		LOGGER.info("Iniciando negócios: EstabelecimentoNegocios");
		try {

			Estabelecimento consultaEstabelecimento;

			try {
				consultaEstabelecimento = estabelecimentoDAO.recuperarEstabelecimento(cnpj);
			} catch (Exception e) {
				consultaEstabelecimento = null;
			}
			Estabelecimento estabelecimento;

			if (consultaEstabelecimento == null) {
				estabelecimento = new Estabelecimento();
			} else {
				estabelecimento = consultaEstabelecimento;
			}

			estabelecimento.setCnpj(cnpj);
			estabelecimento.setRazaoSocial(razaoSocial);
			estabelecimento.setNomeFantasia(nomeFantasia);
			estabelecimento.setEndereco(endereco);
			estabelecimento.setTelefone(telefone);

			if (consultaEstabelecimento == null) {
				Usuario usuario = usuarioNeg.salvarUsuario(email, senha);

				estabelecimento.setUsuario(usuario);
			}
			estabelecimento.setHoraFuncionamentoInicial(horaInicial);
			estabelecimento.setHoraFuncionamentoFinal(horaFinal);
			String pagamento = "";
			for (String str : formasPagamento) {
				pagamento += str + ",";
			}
			if (pagamento.length() > 1) {
				pagamento = pagamento.substring(0, pagamento.length() - 1);
			}
			estabelecimento.setFormasPagamento(pagamento);
			estabelecimento = estabelecimentoDAO.salvarEstabelecimento(estabelecimento);
			return estabelecimento;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Estabelecimento recuperarEstabelecimentoPorId(long idEstabelecimento) {
		LOGGER.info("Iniciando negócios: EstabelecimentoNegocios");
		try {
			return estabelecimentoDAO.recuperarEstabelecimentoPorId(idEstabelecimento);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Estabelecimento recuperarEstabelecimentoPorIdUsuario(long idUsuario) {
		LOGGER.info("Iniciando negócios: EstabelecimentoNegocios");
		try {
			return estabelecimentoDAO.recuperarEstabelecimentoPorIdUsuario(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Estabelecimento recuperarEstabelecimento(String cnpj) {
		LOGGER.info("Iniciando negócios: EstabelecimentoNegocios");
		try {
			return estabelecimentoDAO.recuperarEstabelecimento(cnpj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Estabelecimento> recuperarTodosEstabelecimentos() {
		LOGGER.info("Iniciando negócios: EstabelecimentoNegocios");
		try {
			return estabelecimentoDAO.recuperarTodosEstabelecimentos();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean excluirEstabelecimento(long idEstabelecimento) {
		LOGGER.info("Iniciando negócios: EstabelecimentoNegocios");
		return estabelecimentoDAO.excluirEstabelecimento(idEstabelecimento);
	}
}
