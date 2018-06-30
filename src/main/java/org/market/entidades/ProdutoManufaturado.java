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
@Table(name = "tbl_ProdutoManufaturado")
public class ProdutoManufaturado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProdutoManufaturado", unique = true, nullable = false)
	private Long idProdutoManufaturado;

	@Column(name = "codigoBarras", unique = true, nullable = false)
	private long codigoBarras;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@Column(name = "descricao", nullable = true, length = 60)
	private String descricao;

	@OneToOne
	@JoinColumn(name = "idMarca", nullable = false)
	private Marca marca;

	@OneToOne
	@JoinColumn(name = "idCategoria", nullable = false)
	private Categoria categoria;

	public Long getIdProdutoManufaturado() {
		return idProdutoManufaturado;
	}

	public void setIdProdutoManufaturado(Long idProdutoManufaturado) {
		this.idProdutoManufaturado = idProdutoManufaturado;
	}

	public long getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}