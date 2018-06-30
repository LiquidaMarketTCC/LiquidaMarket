package org.market.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_Marca")
public class Marca {

	@Id
	@Column(name = "idMarca", unique = true, nullable = false)
	private Long idMarca;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}