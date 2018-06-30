package org.market.negocios;

import java.util.Date;

import org.apache.log4j.Logger;
import org.market.dao.ConsumidorDAO;
import org.market.entidades.Consumidor;
import org.market.entidades.Usuario;

public class ConsumidorNegocios {

	private static final Logger LOGGER = Logger.getLogger(ConsumidorNegocios.class);
	private ConsumidorDAO consumidorDAO = new ConsumidorDAO();
	private UsuarioNegocios usuarioNeg = new UsuarioNegocios();

	public Consumidor salvarConsumidor(String cpf, String nome, boolean sexo, Date dataNasc, String endereco,
			String telefone, String email, String senha) {

		LOGGER.info("Iniciando negócios: ConsumidorNegocios");
		try {
			Consumidor consultaConsumidor;

			try {
				consultaConsumidor = consumidorDAO.recuperarConsumidor(cpf);
			} catch (Exception e) {
				consultaConsumidor = null;
			}

			Consumidor consumidor;

			if (consultaConsumidor == null) {
				consumidor = new Consumidor();
			} else {
				consumidor = consultaConsumidor;
			}
			consumidor.setCpf(cpf);
			consumidor.setNome(nome);
			consumidor.setSexo(sexo);
			consumidor.setDataNasc(dataNasc);
			consumidor.setEndereco(endereco);
			consumidor.setTelefone(telefone);

			if (consultaConsumidor == null) {
				Usuario usuario = usuarioNeg.salvarUsuario(email, senha);

				consumidor.setUsuario(usuario);
			}
			consumidor = consumidorDAO.salvarConsumidor(consumidor);
			return consumidor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Consumidor recuperarConsumidor(String cpf) {
		try {
			return consumidorDAO.recuperarConsumidor(cpf);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Consumidor recuperarConsumidorPorId(long idConsumidor) {
		try {
			return consumidorDAO.recuperarConsumidorPorId(idConsumidor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Consumidor recuperarConsumidorPorIdUsuario(long idUsuario) {
		LOGGER.info("Iniciando negócios: ConsumidorNegocios");
		try {
			return consumidorDAO.recuperarConsumidorPorIdUsuario(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean excluirConsumidor(long idConsumidor) {
		return consumidorDAO.excluirConsumidor(idConsumidor);
	}

}
