package com.iesb.api_loja.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_cartao", sequenceName = "seq_cartao", allocationSize = 1, initialValue = 1)
public class Cartao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cartao")
	private Long id;
	
	
	private String numCartao;
	
	private int CVV;
	
	private Date dataVencimento;
	
	private String nomeCartao;
	
	@ManyToOne
	private Cliente cliente;
	
	public Cartao() {
		
	}
	
	private Cartao(String numCartao, int cVV, Date dataVencimento, String nomeCartao, Cliente cliente) {
		super();
		this.numCartao = numCartao;
		this.CVV = cVV;
		this.dataVencimento = dataVencimento;
		this.nomeCartao = nomeCartao;
		this.cliente = cliente;
	}
	
public static class CartaoBuilder{
	
	private String numCartao;
	
	private int CVV;
	
	private Date dataVencimento;
	
	private String nomeCartao;
	
	private Cliente cliente;
		
		public CartaoBuilder numCartao(String numCartao) {
			this.numCartao = numCartao;
			return this;
		}
		
		public CartaoBuilder CVV(int CVV) {
			this.CVV = CVV;
			return this;
		}
		
		public CartaoBuilder dataVencimento(Date dataVencimento) {
			this.dataVencimento = dataVencimento;
			return this;
		}
		
		public CartaoBuilder cliente(Cliente cliente) {
			this.cliente = cliente;
			return this;
		}
		
		public CartaoBuilder nomeCartao(String nomeCartao) {
			this.nomeCartao = nomeCartao;
			return this;
		}
		
		
		public Cartao criaCartao() {
			return new Cartao(numCartao,CVV,dataVencimento, nomeCartao,cliente);
		}
		
	}

	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}

	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
