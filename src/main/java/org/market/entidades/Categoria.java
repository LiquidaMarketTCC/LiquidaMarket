package org.market.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.market.utils.EntityObject;

@Entity
@Table(name = "tbl_Categoria")
public class Categoria extends EntityObject {

	private static final long serialVersionUID = -960877200056326513L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCategoria", unique = true, nullable = false)
	private Long idCategoria;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}