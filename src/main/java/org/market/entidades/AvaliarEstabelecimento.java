package org.market.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_AvaliarEstabelecimento")
public class AvaliarEstabelecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAvaliarEstabelecimento", unique = true, nullable = false)
	private Long idAvaliarEstabelecimento;

	@Column(name = "pontuacao", nullable = false)
	private int pontuacao;

	@Column(name = "comentario", nullable = false, length = 200)
	private String comentario;

	@OneToOne
	@JoinColumn(name = "idEstabelecimento", nullable = false)
	private Estabelecimento estabelecimento;

	@OneToOne
	@JoinColumn(name = "idConsumidor", nullable = false)
	private Consumidor consumidor;

	public Long getIdAvaliarEstabelecimento() {
		return idAvaliarEstabelecimento;
	}

	public void setIdAvaliarEstabelecimento(Long idAvaliarEstabelecimento) {
		this.idAvaliarEstabelecimento = idAvaliarEstabelecimento;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

}