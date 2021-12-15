package com.iesb.api_loja.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private Long id;
	
	private String codigoProduto;
	
	private String nome;
	
	private float preco;
	
	private int quantidadeEstoque;
	
	private Date dataCadastro;
	
	public Produto() {
		
	}
		
	private Produto(String codigoProduto, String nome, int quantidadeEstoque,float preco) {
		this.codigoProduto = codigoProduto;
		this.nome = nome;
		this.quantidadeEstoque = quantidadeEstoque;
		this.preco = preco;
		setDataCadastro(Calendar.getInstance().getTime());
	}


	public static class ProdutoBuilder{
	
		private String nome;
		
		private int quantidadeEstoque;
		
		private String codigoProduto;
		
		private float preco;
		
		
		public ProdutoBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public ProdutoBuilder quantidadeEstoque(int quantidadeEstoque) {
			this.quantidadeEstoque = quantidadeEstoque;
			return this;
		}
		
		public ProdutoBuilder codigoProduto(String codigoProduto) {
			this.codigoProduto = codigoProduto;
			return this;
		}
		
		public ProdutoBuilder preco(float preco) {
			this.preco = preco;
			return this;
		}
			
		public Produto criarProduto() {
			return new Produto(codigoProduto,nome,quantidadeEstoque,preco);
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

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataVenda) {
		this.dataCadastro = dataVenda;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
	
}
