package org.market.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_Promocao")
public class Promocao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPromocao", unique = true, nullable = false)
	private Long idPromocao;
	
	@Column(name = "nome", nullable = false, length = 30)
	private String nome;
	
	@Column(name = "dataInicial", nullable = false)
	private Date dataInicial;
	
	@Column(name = "dataTermino", nullable = false)
	private Date dataTermino;
	
	@Column(name = "situacao", nullable = false)
	private boolean situacao;
	
	@OneToOne
	@JoinColumn(name = "idEstabelecimento", nullable = false)
	private Estabelecimento estabelecimento;
	
	public long getIdPromocao() {
		return idPromocao;
	}
	public void setIdPromocao(long idPromocao) {
		this.idPromocao = idPromocao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	public boolean isSituacao() {
		return situacao;
	}
	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
}