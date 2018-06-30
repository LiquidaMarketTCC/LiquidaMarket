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
@Table(name = "tbl_Estabelecimento")
public class Estabelecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstabelecimento", unique = true, nullable = false)
	private Long idEstabelecimento;

	@Column(name = "cnpj", nullable = false, length = 14)
	private String cnpj;

	@Column(name = "razaoSocial", nullable = false, length = 70)
	private String razaoSocial;

	@Column(name = "nomeFantasia", nullable = false, length = 50)
	private String nomeFantasia;

	@Column(name = "endereco", nullable = false, length = 150)
	private String endereco;

	@Column(name = "telefone", nullable = true, length = 15)
	private String telefone;

	@Column(name = "horaFuncInicial", nullable = true)
	private String horaFuncionamentoInicial;

	@Column(name = "horaFuncFinal", nullable = true)
	private String horaFuncionamentoFinal;

	@Column(name = "formasPgto", nullable = false)
	private String formasPagamento;

	@OneToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
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

	public String getHoraFuncionamentoInicial() {
		return horaFuncionamentoInicial;
	}

	public void setHoraFuncionamentoInicial(String horaFuncionamentoInicial) {
		this.horaFuncionamentoInicial = horaFuncionamentoInicial;
	}

	public String getHoraFuncionamentoFinal() {
		return horaFuncionamentoFinal;
	}

	public void setHoraFuncionamentoFinal(String horaFuncionamentoFinal) {
		this.horaFuncionamentoFinal = horaFuncionamentoFinal;
	}

	public String getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(String formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}