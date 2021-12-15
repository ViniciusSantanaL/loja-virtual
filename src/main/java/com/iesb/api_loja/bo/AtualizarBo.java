package com.iesb.api_loja.bo;

import com.iesb.api_loja.form.ClienteForm;
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
	
	
	public Cliente atualizarCliente(ClienteForm cliente) {
		ValidaDados.INSTANCE.validaDadosCadastroCliente(cliente);
		Cliente clienteAtualizar = null;
		Pessoa pessoaAtualizar = null;
		if(ValidaDados.INSTANCE.messages.size() < 0 || ValidaDados.INSTANCE.messages.size() == 0) {
			pessoaAtualizar = new Pessoa.PessoaBuilder()
				.nome(cliente.getNome())
				.cpf(cliente.getCpf())
				.email(cliente.getEmail())
				.telefoneCelular(cliente.getTelefoneCelular())
				.telefoneComercial(cliente.getTelefoneComercial())
				.telefoneResidencial(cliente.getTelefoneResidencial())
				.criarPessoa();
				
			clienteAtualizar = new Cliente.ClienteBuilder()
				.dadosPessoa(pessoaAtualizar)
				.login(cliente.getLogin().trim())
				.password(cliente.getPassword().trim())
				.quantidadeCompra(0)
				.criaCliente();
				
		}
		return clienteAtualizar;
	}
	
	
}
