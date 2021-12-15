package com.iesb.api_loja.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iesb.api_loja.bo.CadastroBo;
import com.iesb.api_loja.bo.ValidaDados;
import com.iesb.api_loja.bo.VerificaLogin;
import com.iesb.api_loja.model.Carrinho;
import com.iesb.api_loja.model.Cartao;
import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.model.Endereco;
import com.iesb.api_loja.model.Produto;
import com.iesb.api_loja.repository.CarrinhoRepository;
import com.iesb.api_loja.repository.CartaoRepository;
import com.iesb.api_loja.repository.EnderecoRepository;
import com.iesb.api_loja.repository.PessoaRepository;
import com.iesb.api_loja.repository.ProdutoRepository;


@RestController
public class CarrinhoController {
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private ProdutoRepository produtoService;
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private PessoaRepository pessoaRepository;
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@GetMapping(value = "cadastroCarrinho")
    @ResponseBody
    public ResponseEntity<?> cadastrarCarrinho(@RequestParam(name = "cupom") Long cupom, @RequestParam(name = "cpfCliente") String cpfCliente){
		Cliente cli = pessoaRepository.buscaClientePorCpf(cpfCliente);
		if(cli == null) {
			return new ResponseEntity<String>("ESTE CLIENTE NÃO EXISTE", HttpStatus.OK);
		}
		Endereco enderecoCobranca = enderecoRepository.buscaEnderecoCobrancaPorCliente(cpfCliente);
		Endereco enderecoEntrega = enderecoRepository.buscaEnderecoEntregaPorCliente(cpfCliente);
		Carrinho carrinho = CadastroBo.INSTANCE.cadastraCarrinho(0,cli,enderecoCobranca,enderecoEntrega);
		
		if(carrinho != null) {
			carrinho = carrinhoRepository.save(carrinho);
			return new ResponseEntity<Carrinho>(carrinho, HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<List<String>>(ValidaDados.INSTANCE.messages, HttpStatus.OK);
    }
	
	@PutMapping(value = "finalizarCompra")
    @ResponseBody
    public ResponseEntity<?> finalizarCompra(@RequestParam( name = "idCarrinho") Long idCarrinho){
		Carrinho carrinho =  carrinhoRepository.findById(idCarrinho).get();
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
    public ResponseEntity<?> adicionarProdutoCarrinho(@RequestParam (name = "idCarrinho")Long idCarrinho,@RequestParam (name = "codProdutoCompra")String codProduto, @RequestParam (name = "qtdProduto")int qtd){
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
					produtoService.saveAndFlush(produtoAdicionar);
					carrinhoRepository.saveAndFlush(carrinho);
					
					
					return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("ESTE CARRINHO JÁ FOI FINALIZADO A COMPRA", HttpStatus.OK);
				}
			
		
		}else
			return new ResponseEntity<String>("ESTE CARRINHO NÃO EXISTE", HttpStatus.OK);
			
    }
	
	@GetMapping(value = "mostraDadosCompra")
    @ResponseBody
    public ResponseEntity<?> mostraDadosCompra(@RequestParam (name = "idCarrinho")Long idCarrinho){
		Carrinho carrinho =  carrinhoRepository.buscaCarrinhoFinalizado(idCarrinho);
		
		List<Object> retorno = new ArrayList<Object>();
		if(carrinho == null) {
			return new ResponseEntity<String>("ESTE CARRINHO NÃO EXISTE", HttpStatus.OK);
		}
		if(!(VerificaLogin.INSTANCE.verificaUsuarioLogado(carrinho.getCliente().getLogin()))) {
			return new ResponseEntity<String>("USUARIO NÃO ESTA LOGADO", HttpStatus.OK);
		}
		Cartao card = cartaoRepository.buscaCartaoPorCliente(carrinho.getCliente().getId());
		
		
		if(carrinho.getEstadoCarrinho() == 1) {
			retorno.add(card);
			retorno.add(carrinho);
			
			return new ResponseEntity<List<Object>>(retorno, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("ESTE CARRINHO NÃO ESTÁ PRONTO PARA SER LISTADO", HttpStatus.OK);
		}
    }
}
