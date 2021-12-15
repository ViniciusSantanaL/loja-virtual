package com.iesb.api_loja.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1, initialValue = 1)
public class Endereco  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
	private Long id;
	
	private String nomeEndereco;
	
	private int numCep;
		
	private int tipoEndereco;
	
	@ManyToOne
    @JoinColumn(name="endereco_pessoa")
    private Pessoa pessoa;
	
	public Endereco() {
		
	}
	

	private Endereco(String nomeEndereco, int numCep, int tipoEndereco, Pessoa pessoa) {
		super();
		this.nomeEndereco = nomeEndereco;
		this.numCep = numCep;
		this.tipoEndereco = tipoEndereco;
		this.pessoa = pessoa;
	}
	
	public static class EnderecoBuilder{
	
		private String nomeEndereco;
		
		private int numCep;
			
		private int tipoEndereco;
	
	    private Pessoa pessoa;
			
			public EnderecoBuilder numCep(int numCep) {
				this.numCep = numCep;
				return this;
			}
			
			public EnderecoBuilder tipoEndereco(int tipoEndereco) {
				this.tipoEndereco = tipoEndereco;
				return this;
			}
			
			public EnderecoBuilder pessoa(Pessoa pessoa) {
				this.pessoa = pessoa;
				return this;
			}
			
			public EnderecoBuilder nomeEndereco(String nomeEndereco) {
				this.nomeEndereco = nomeEndereco;
				return this;
			}
			
			
			public Endereco criaEndereco() {
				return new Endereco(nomeEndereco,numCep,tipoEndereco,pessoa);
			}
			
		}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}

	public int getNumCep() {
		return numCep;
	}

	public void setNumCep(int numCep) {
		this.numCep = numCep;
	}

	public int getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(int tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
