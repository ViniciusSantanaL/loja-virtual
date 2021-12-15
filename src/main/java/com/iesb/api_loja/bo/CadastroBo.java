package com.iesb.api_loja.bo;

import com.iesb.api_loja.form.CartaoForm;
import com.iesb.api_loja.form.ClienteForm;
import com.iesb.api_loja.form.EnderecoForm;
import com.iesb.api_loja.model.Carrinho;
import com.iesb.api_loja.model.Cartao;
import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.model.Endereco;
import com.iesb.api_loja.model.Pessoa;
import com.iesb.api_loja.model.Produto;

public class CadastroBo {
	
	public static final CadastroBo INSTANCE = new CadastroBo();

	private CadastroBo() {

	}
	
	public Produto cadastroProduto(Produto prod) {
		
		ValidaDados.INSTANCE.validaDadosCadastroProduto(prod);
		
		Produto prodCadastrar = null;
		
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0)
			prodCadastrar = new Produto.ProdutoBuilder().codigoProduto(prod.getCodigoProduto().trim())
			.nome(prod.getNome())
			.preco(prod.getPreco())
			.quantidadeEstoque(prod.getQuantidadeEstoque())
			.criarProduto();
		
		return prodCadastrar;
	}
	
	public Cliente cadastroCliente(ClienteForm cliente) {
		ValidaDados.INSTANCE.validaDadosCadastroCliente(cliente);
		Cliente clienteCadastrar = null;
		Pessoa pessoaCadastrar = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) {
			
			pessoaCadastrar = new Pessoa.PessoaBuilder()
			.nome(cliente.getNome())
			.cpf(cliente.getCpf())
			.email(cliente.getEmail())
			.telefoneCelular(cliente.getTelefoneCelular())
			.telefoneComercial(cliente.getTelefoneComercial())
			.telefoneResidencial(cliente.getTelefoneResidencial())
			.criarPessoa();
			
			clienteCadastrar = new Cliente.ClienteBuilder()
			.dadosPessoa(pessoaCadastrar)
			.login(cliente.getLogin().trim())
			.password(cliente.getPassword().trim())
			.quantidadeCompra(0)
			.criaCliente();
			
		}
			
		
		return clienteCadastrar;
	}
	
	public Endereco cadastrarEnderecoEntrega(EnderecoForm endereco, Pessoa pessoa) {
		
		ValidaDados.INSTANCE.validaDadosEndereco(endereco);
		Endereco enderecoCadastrar = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) 
		enderecoCadastrar = new Endereco.EnderecoBuilder()
		.numCep(endereco.getNumCep())
		.pessoa(pessoa)
		.tipoEndereco(1)
		.nomeEndereco(endereco.getNomeEndereco())
		.criaEndereco();
		
		return enderecoCadastrar;
	}
	
	public Endereco cadastrarEnderecoCobranca(EnderecoForm endereco, Pessoa pessoa) {
		
		ValidaDados.INSTANCE.validaDadosEndereco(endereco);
		Endereco enderecoCadastrar = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) 
		enderecoCadastrar = new Endereco.EnderecoBuilder()
		.numCep(endereco.getNumCep())
		.pessoa(pessoa)
		.tipoEndereco(2)
		.nomeEndereco(endereco.getNomeEndereco())
		.criaEndereco();
		
		return enderecoCadastrar;
	}
		
	public Cartao cadastrarCartao(CartaoForm cartao) {
		ValidaDados.INSTANCE.validaDadosCartao(cartao);
		Cartao card = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) 
			card = new Cartao.CartaoBuilder()
			.nomeCartao(cartao.getNomeCartao())
			.numCartao(cartao.getNumCartao())
			.CVV(cartao.getCVV())
			.dataVencimento(cartao.getDataVencimento())
			.criaCartao();
		
		return card;
	}
	public Carrinho cadastraCarrinho(int cupom,Cliente cli, Endereco endCobraca, Endereco endEntrega) {
		Carrinho carrinho = null;
		
		carrinho = new Carrinho.CarrinhoBuilder()
		.cliente(cli)
		.cupomDesconto(cupom)
		.enderecoEntrega(endEntrega)
		.enderecoCobranca(endCobraca)
		.qtdProdCarrinho(0)
		.estadoCarrinho(0)
		.precoTotalCarrinho(0)
		.tipoPagamento(1)
		.criaCarrinho();
		
		
		
		return carrinho;
	
	}
}
