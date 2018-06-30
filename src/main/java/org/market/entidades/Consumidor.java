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
@Table(name = "tbl_Consumidor")
public class Consumidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idConsumidor", unique = true, nullable = false)
	private long idConsumidor;

	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "sexo", nullable = false)
	private boolean sexo;

	@Column(name = "dataNasc", nullable = false)
	private Date dataNasc;

	@Column(name = "endereco", nullable = false, length = 150)
	private String endereco;

	@Column(name = "telefone", nullable = false, length = 15)
	private String telefone;

	@OneToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	public long getIdConsumidor() {
		return idConsumidor;
	}

	public void setIdConsumidor(long idConsumidor) {
		this.idConsumidor = idConsumidor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}