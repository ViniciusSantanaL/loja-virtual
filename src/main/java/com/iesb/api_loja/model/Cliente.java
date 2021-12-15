package com.iesb.api_loja.model; 

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1, initialValue = 1)
public class Cliente  extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	private Pessoa pessoa;
	
	private String login;
	
	private String password;
	
	private int quantidadeCompra;
	
	public Cliente() {
		
	}
	
	private Cliente(Pessoa dadosPessoa, String login, String password, int quantidadeCompra) {
		
		this.pessoa = dadosPessoa;
		this.login = login;
		this.password = password;
		this.quantidadeCompra = quantidadeCompra;
	}

	public static class ClienteBuilder{
		
		private Pessoa dadosPessoa;
		
		private String login;
		
		private String password;
		
		private int quantidadeCompra;
		
		public ClienteBuilder login(String login) {
			this.login = login;
			return this;
		}
		
		public ClienteBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public ClienteBuilder quantidadeCompra(int quantidadeCompra) {
			this.quantidadeCompra = quantidadeCompra;
			return this;
		}
		
		public ClienteBuilder dadosPessoa(Pessoa dadosPessoa) {
			this.dadosPessoa = dadosPessoa;
			return this;
		}
		
		
		public Cliente criaCliente() {
			return new Cliente(dadosPessoa,login,password,quantidadeCompra);
		}
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getDadosPessoa() {
		return pessoa;
	}

	public void setDadosPessoa(Pessoa dadosPessoa) {
		this.pessoa = dadosPessoa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getQuantidadeCompra() {
		return quantidadeCompra;
	}

	public void setQuantidadeCompra(int quantidadeCompra) {
		this.quantidadeCompra = quantidadeCompra;
	}
}
