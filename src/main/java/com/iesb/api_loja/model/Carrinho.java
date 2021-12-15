package com.iesb.api_loja.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_carrinho", sequenceName = "seq_carrinho", allocationSize = 1, initialValue = 1)
public class Carrinho  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carrinho")
	private Long id;
	
	private int cupomDesconto;
	
	private int qtdProdCarrinho;
	
	private float precoTotalCarrinho;
	
	private int tipoPagamento;
	
	private int estadoCarrinho;
	
	private Endereco enderecoEntrega;
	
	private Endereco enderecoCobranca;
	
	
	 @ElementCollection
	    @MapKeyColumn(name="cod_prod")
	    @Column(name="produto_carrinho")
	    @CollectionTable(name="produtos_carrinho", joinColumns=@JoinColumn(name="codigo_produto"))
	private Map<Produto,Integer> produtoCarrinho;
	 
	private Cliente cliente;
		 
	private Carrinho(int cupomDesconto, int qtdProdCarrinho, float precoTotalCarrinho, int tipoPagamento,
			int estadoCarrinho, Endereco enderecoEntrega, Endereco enderecoCobranca,
			Map<Produto, Integer> produtoCarrinho, Cliente cliente) {
		if(cupomDesconto != 0) {
			cupomDesconto = cupomDesconto/100;
		}
		this.cupomDesconto = cupomDesconto;
		this.qtdProdCarrinho = qtdProdCarrinho;
		this.precoTotalCarrinho = precoTotalCarrinho;
		this.tipoPagamento = tipoPagamento;
		this.estadoCarrinho = estadoCarrinho;
		this.enderecoEntrega = enderecoEntrega;
		this.enderecoCobranca = enderecoCobranca;
		this.produtoCarrinho = produtoCarrinho;
		this.cliente = cliente;
	}
	
	public static class CarrinhoBuilder{
		
		private int cupomDesconto;
		
		private int qtdProdCarrinho;
		
		private float precoTotalCarrinho;
		
		private int tipoPagamento;
		
		private int estadoCarrinho;
		
		private Endereco enderecoEntrega;
		
		private Endereco enderecoCobranca;

		private Map<Produto,Integer> produtoCarrinho;
		 
		private Cliente cliente;
			
			public CarrinhoBuilder qtdProdCarrinho(int qtdProdCarrinho) {
				this.qtdProdCarrinho = qtdProdCarrinho;
				return this;
			}
			
			public CarrinhoBuilder estadoCarrinho(int estadoCarrinho) {
				this.estadoCarrinho = estadoCarrinho;
				return this;
			}
			public CarrinhoBuilder tipoPagamento(int tipoPagamento) {
				this.tipoPagamento = tipoPagamento;
				return this;
			}
			public CarrinhoBuilder precoTotalCarrinho(float precoTotalCarrinho) {
				this.precoTotalCarrinho = precoTotalCarrinho;
				return this;
			}
			
			public CarrinhoBuilder enderecoEntrega(Endereco enderecoEntrega) {
				this.enderecoEntrega = enderecoEntrega;
				return this;
			}
			public CarrinhoBuilder enderecoCobranca(Endereco enderecoCobranca) {
				this.enderecoCobranca = enderecoCobranca;
				return this;
			}
			public CarrinhoBuilder cupomDesconto(int cupomDesconto) {
				this.cupomDesconto = cupomDesconto;
				return this;
			}
			
			public CarrinhoBuilder produtoCarrinho(Map<Produto,Integer>  produtoCarrinho) {
				this.produtoCarrinho = produtoCarrinho;
				return this;
			}
			
			public CarrinhoBuilder cliente(Cliente cliente) {
				this.cliente = cliente;
				return this;
			}
			
			
			public Carrinho criaCarrinho() {
				return new Carrinho(cupomDesconto,qtdProdCarrinho,precoTotalCarrinho,tipoPagamento,estadoCarrinho,enderecoEntrega,
						 enderecoCobranca, produtoCarrinho, cliente);
			}
			
		}

	public Carrinho() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCupomDesconto() {
		return cupomDesconto;
	}

	public void setCupomDesconto(int cupomDesconto) {
		this.cupomDesconto = cupomDesconto;
	}

	public int getQtdProdCarrinho() {
		return qtdProdCarrinho;
	}

	public void setQtdProdCarrinho(int qtdProdCarrinho) {
		this.qtdProdCarrinho = qtdProdCarrinho;
	}

	public float getPrecoTotalCarrinho() {
		return precoTotalCarrinho;
	}

	public void setPrecoTotalCarrinho(float precoTotalCarrinho) {
		if(cupomDesconto != 0) {
			precoTotalCarrinho = precoTotalCarrinho - cupomDesconto;
		}
		this.precoTotalCarrinho = precoTotalCarrinho;
	}

	public int getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(int tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public int getEstadoCarrinho() {
		return estadoCarrinho;
	}

	public void setEstadoCarrinho(int estadoCarrinho) {
		this.estadoCarrinho = estadoCarrinho;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public Map<Produto, Integer> getProdutoCarrinho() {
		return produtoCarrinho;
	}

	public void setProdutoCarrinho(Map<Produto, Integer> produtoCarrinho) {
		this.produtoCarrinho = produtoCarrinho;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
