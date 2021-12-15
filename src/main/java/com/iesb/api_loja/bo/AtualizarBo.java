package com.iesb.api_loja.bo;

import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.model.Pessoa;
import com.iesb.api_loja.model.Produto;

public class AtualizarBo {
	
	public static final AtualizarBo INSTANCE = new AtualizarBo();

	private AtualizarBo() {

	}
	
	public Produto atualizarProduto(Produto prod) {
		
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
	
	
	public Cliente atualizarCliente(Cliente cliente) {
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
	
	
}
