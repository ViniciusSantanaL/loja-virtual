package com.iesb.api_loja.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iesb.api_loja.bo.CadastroBo;
import com.iesb.api_loja.bo.ValidaDados;
import com.iesb.api_loja.bo.VerificaLogin;
import com.iesb.api_loja.model.Carrinho;
import com.iesb.api_loja.model.Cartao;
import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.model.Produto;
import com.iesb.api_loja.repository.CarrinhoRepository;
import com.iesb.api_loja.repository.CartaoRepository;
import com.iesb.api_loja.repository.PessoaRepository;
import com.iesb.api_loja.repository.ProdutoRepository;


@RestController
public class CarrinhoController {
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private static ProdutoRepository produtoService;
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private PessoaRepository pessoaRepository;
	
	//@Autowired
	//private CartaoRepository cartaoRepository;
	
	@PostMapping(value = "cadastrarCarrinho")
    @ResponseBody
    public ResponseEntity<?> cadastrarCarrinho(@RequestBody Carrinho car, String cpf){
		Cliente cli = pessoaRepository.buscaClientePorCpf(cpf);
		if(cli == null) {
			return new ResponseEntity<String>("ESTE CLIENTE NÃO EXISTE", HttpStatus.OK);
		}
		Carrinho carrinho = CadastroBo.INSTANCE.cadastraCarrinho(car,cli);
		
		if(carrinho != null) {
			carrinho = carrinhoRepository.save(carrinho);
			return new ResponseEntity<Carrinho>(carrinho, HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<List<String>>(ValidaDados.INSTANCE.messages, HttpStatus.OK);
    }
	
	@PutMapping(value = "finalizarCompra")
    @ResponseBody
    public ResponseEntity<?> finalizarCompra(@RequestBody Long id){
		Carrinho carrinho =  carrinhoRepository.findById(id).get();
		if(carrinho == null) {
			return new ResponseEntity<String>("ESTE CARRINHO NÃO EXISTE", HttpStatus.OK);
		}
		if(!(VerificaLogin.INSTANCE.verificaUsuarioLogado(carrinho.getCliente().getLogin()))) {
			return new ResponseEntity<String>("USUARIO NÃO ESTA LOGADO", HttpStatus.OK);
		}
		if(carrinho.getEstadoCarrinho() == 1) {
			carrinho.setEstadoCarrinho(2);
			return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("NÃO HÁ PRODUTOS NESTE CARRINHO", HttpStatus.OK);
		}
    }
	
	@PutMapping(value = "adicionarProdutoCarrinho")
    @ResponseBody
    public ResponseEntity<?> adicionarProdutoCarrinho(@RequestBody Long idCarrinho,String codProduto,int qtd){
		Carrinho carrinho =  carrinhoRepository.findById(idCarrinho).get();
		
		Produto produtoAdicionar = produtoService.buscaProdutoCodigo(codProduto.trim());
		if(carrinho != null) {
			if(carrinho.getEstadoCarrinho() != 2) {
				if(produtoAdicionar == null)
					return new ResponseEntity<String>("ESTE PRODUTO NÃO EXISTE", HttpStatus.OK);
					if(carrinho.getProdutoCarrinho().containsKey(produtoAdicionar)){
				
					}else {
						produtoAdicionar.setQuantidadeEstoque(produtoAdicionar.getQuantidadeEstoque() - qtd);
						carrinho.getProdutoCarrinho().put(produtoAdicionar, qtd);
						carrinho.setPrecoTotalCarrinho(carrinho.getPrecoTotalCarrinho() + (produtoAdicionar.getPreco() * qtd));
						carrinho.setQtdProdCarrinho(carrinho.getQtdProdCarrinho() + 1);
						carrinho.setEstadoCarrinho(1);
					}
	
					carrinhoRepository.saveAndFlush(carrinho);
					produtoService.saveAndFlush(produtoAdicionar);
					
					return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("ESTE CARRINHO JÁ FOI FINALIZADO A COMPRA", HttpStatus.OK);
				}
			
		
		}else
			return new ResponseEntity<String>("ESTE CARRINHO NÃO EXISTE", HttpStatus.OK);
			
    }
	
	@PutMapping(value = "mostraDadosCompra")
    @ResponseBody
    public ResponseEntity<?> mostraDadosCompra(@RequestBody Long id){
		Carrinho carrinho =  carrinhoRepository.buscaCarrinhoFinalizado(id);
		
		List<Object> retorno = new ArrayList<Object>();
		if(carrinho == null) {
			return new ResponseEntity<String>("ESTE CARRINHO NÃO EXISTE", HttpStatus.OK);
		}
		if(!(VerificaLogin.INSTANCE.verificaUsuarioLogado(carrinho.getCliente().getLogin()))) {
			return new ResponseEntity<String>("USUARIO NÃO ESTA LOGADO", HttpStatus.OK);
		}
		//Cartao card = cartaoRepository.buscaCartaoPorCliente(carrinho.getCliente().getId());
		
		
		if(carrinho.getEstadoCarrinho() == 2) {
			//retorno.add(card);
			retorno.add(carrinho);
			
			return new ResponseEntity<List<Object>>(retorno, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("ESTE CARRINHO NÃO ESTÁ PRONTO PARA SER LISTADO", HttpStatus.OK);
		}
    }
}
