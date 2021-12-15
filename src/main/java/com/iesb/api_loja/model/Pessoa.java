package com.iesb.api_loja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1, initialValue = 1)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	private Long id;

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nome;

	private String cpf;

	private String telefoneComercial;

	private String telefoneCelular;

	private String telefoneResidencial;

	private String email;
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL)
	private List<Endereco> enderecosUsuario = new ArrayList<Endereco>();

	public Pessoa() {

	}

	private Pessoa(String nome, String cpf, String telefoneComercial, String telefoneCelular,
			String telefoneResidencial, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefoneComercial = telefoneComercial;
		this.telefoneCelular = telefoneCelular;
		this.telefoneResidencial = telefoneResidencial;
		this.email = email;
	}

	public static class PessoaBuilder {

		private String nome;

		private String cpf;

		private String telefoneComercial;

		private String telefoneCelular;

		private String telefoneResidencial;

		private String email;

		public PessoaBuilder() {

		}

		public PessoaBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public PessoaBuilder cpf(String cpf) {
			this.cpf = cpf;
			return this;
		}

		public PessoaBuilder telefoneComercial(String telefoneComercial) {
			this.telefoneComercial = telefoneComercial;
			return this;
		}

		public PessoaBuilder telefoneCelular(String telefoneCelular) {
			this.telefoneCelular = telefoneCelular;
			return this;
		}

		public PessoaBuilder telefoneResidencial(String telefoneResidencial) {
			this.telefoneResidencial = telefoneResidencial;
			return this;
		}

		public PessoaBuilder email(String email) {
			this.email = email;
			return this;
		}

		public Pessoa criarPessoa() {
			return new Pessoa(nome, cpf, telefoneComercial, telefoneResidencial, telefoneCelular, email);
		}

	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Endereco> getEnderecosUsuario() {
		return enderecosUsuario;
	}

	public void setEnderecosUsuario(List<Endereco> enderecosUsuario) {
		this.enderecosUsuario = enderecosUsuario;
	}
	

}
