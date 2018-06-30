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
@Table(name = "tbl_ProdutoComercializado")
public class ProdutoComercializado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProdutoComercializado", unique = true, nullable = false)
	private Long idProdutoComercializado;

	@Column(name = "valor", nullable = false)
	private double valor;

	@Column(name = "situacao", nullable = false)
	private boolean situacao;

	@OneToOne
	@JoinColumn(name = "idProdutoManufaturado", nullable = false)
	private ProdutoManufaturado produtoManufaturado;

	@OneToOne
	@JoinColumn(name = "idEstabelecimento", nullable = false)
	private Estabelecimento estabelecimento;

	public Long getIdProdutoComercializado() {
		return idProdutoComercializado;
	}

	public void setIdProdutoComercializado(Long idProdutoComercializado) {
		this.idProdutoComercializado = idProdutoComercializado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public ProdutoManufaturado getProdutoManufaturado() {
		return produtoManufaturado;
	}

	public void setProdutoManufaturado(ProdutoManufaturado produtoManufaturado) {
		this.produtoManufaturado = produtoManufaturado;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
}
