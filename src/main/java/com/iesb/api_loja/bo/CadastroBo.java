package com.iesb.api_loja.bo;

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
	
	public Cliente cadastroCliente(Cliente cliente) {
		ValidaDados.INSTANCE.validaDadosCadastroCliente(cliente);
		Cliente clienteCadastrar = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) {
			clienteCadastrar = new Cliente.ClienteBuilder()
			.dadosPessoa(cliente.getDadosPessoa())
			.login(cliente.getLogin().trim())
			.password(cliente.getPassword())
			.quantidadeCompra(0)
			.criaCliente();
			Pessoa pessoa = clienteCadastrar.getDadosPessoa();
			clienteCadastrar.setDadosPessoa(pessoa);
			pessoa.setCliente(cliente);
		}
			
		
		return clienteCadastrar;
	}
	
	public Endereco cadastrarEnderecoEntrega(Endereco endereco) {
		
		ValidaDados.INSTANCE.validaDadosEndereco(endereco);
		Endereco enderecoCadastrar = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) 
		enderecoCadastrar = new Endereco.EnderecoBuilder()
		.numCep(endereco.getNumCep())
		.pessoa(endereco.getPessoa())
		.tipoEndereco(1)
		.nomeEndereco(endereco.getNomeEndereco())
		.criaEndereco();
		
		return enderecoCadastrar;
	}
	
	public Endereco cadastrarEnderecoCobranca(Endereco endereco) {
		
		ValidaDados.INSTANCE.validaDadosEndereco(endereco);
		Endereco enderecoCadastrar = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) 
		enderecoCadastrar = new Endereco.EnderecoBuilder()
		.numCep(endereco.getNumCep())
		.pessoa(endereco.getPessoa())
		.tipoEndereco(2)
		.nomeEndereco(endereco.getNomeEndereco())
		.criaEndereco();
		
		return enderecoCadastrar;
	}
		
	public Cartao cadastrarCartao(Cartao cartao) {
		ValidaDados.INSTANCE.validaDadosCartao(cartao);
		Cartao card = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) 
			card = new Cartao.CartaoBuilder()
			.nomeCartao(cartao.getNomeCartao())
			.numCartao(cartao.getNumCartao())
			.CVV(cartao.getCVV())
			.dataVencimento(cartao.getDataVencimento())
			.cliente(cartao.getCliente())
			.criaCartao();
		
		return card;
	}
	public Carrinho cadastraCarrinho(Carrinho car,Cliente cli) {
		Carrinho carrinho = null;
		
		carrinho = new Carrinho.CarrinhoBuilder()
		.cliente(cli)
		.cupomDesconto(car.getCupomDesconto())
		.enderecoEntrega(car.getEnderecoEntrega())
		.enderecoCobranca(car.getEnderecoCobranca())
		.qtdProdCarrinho(car.getQtdProdCarrinho())
		.estadoCarrinho(0)
		.precoTotalCarrinho(0)
		.tipoPagamento(1)
		.criaCarrinho();
		
		
		
		return carrinho;
	
	}
}
