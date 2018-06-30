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
@Table(name = "tbl_ItensPromocao")
public class ItensPromocao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idItensPromocao", unique = true, nullable = false)
	private long idItensPromocao;

	@Column(name = "valorPromocional", nullable = false)
	private double valorPromocionalProduto;

	@OneToOne
	@JoinColumn(name = "idPromocao", nullable = false)
	private Promocao promocao;

	@OneToOne
	@JoinColumn(name = "idProdutoComercializado", nullable = false)
	private ProdutoComercializado produtoComercializado;

	public long getIdItensPromocao() {
		return idItensPromocao;
	}

	public void setIdItensPromocao(long idItensPromocao) {
		this.idItensPromocao = idItensPromocao;
	}

	public double getValorPromocionalProduto() {
		return valorPromocionalProduto;
	}

	public void setValorPromocionalProduto(double valorPromocionalProduto) {
		this.valorPromocionalProduto = valorPromocionalProduto;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public ProdutoComercializado getProdutoComercializado() {
		return produtoComercializado;
	}

	public void setProdutoComercializado(ProdutoComercializado produtoComercializado) {
		this.produtoComercializado = produtoComercializado;
	}

}