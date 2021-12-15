package com.iesb.api_loja.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iesb.api_loja.bo.CadastroBo;
import com.iesb.api_loja.bo.ValidaDados;
import com.iesb.api_loja.model.Produto;
import com.iesb.api_loja.repository.ProdutoRepository;



@RestController
public class ProdutoController {
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private ProdutoRepository produtoService;
	
	@PostMapping(value = "cadastrarProduto")
    @ResponseBody
    public ResponseEntity<?> cadastrarProduto(@RequestBody Produto prod){
	
		Produto pro = CadastroBo.INSTANCE.cadastroProduto(prod);	
		if(pro != null) {
			if(produtoService.buscaProdutoCodigo(pro.getCodigoProduto()) != null)
				return new ResponseEntity<String>("ESTE PRODUTO JA FOI CADASTRADO", HttpStatus.OK);
			pro = produtoService.save(prod);
			return new ResponseEntity<Produto>(pro, HttpStatus.CREATED);
		}
		else {
			List<String> messages = new ArrayList<String>(ValidaDados.INSTANCE.messages) ;
			ValidaDados.INSTANCE.messages.clear();
			return new ResponseEntity<List<String>>(messages, HttpStatus.OK);
		}
			
    }
	
	@GetMapping(value = "listarProduto")
    @ResponseBody /*  Retorna os dados paara o corpo da resposta   */
    public ResponseEntity<List<Produto>> listaProduto(){
    	/*  Chama a interface que usa o metodo extendido da Interface JPArEPOSITORY  */
    	List<Produto> produtos = produtoService.findAll();
    	return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK); /*  Retorna a lista em json  */
    }
	
	/*@DeleteMapping(value = "deletarProduto")
    @ResponseBody /*  Retorna os dados paara o corpo da resposta   */
	/*public ResponseEntity<String> delete(@RequestParam String codProduto){
		Produto prod  = produtoService.buscaProdutoCodigo(codProduto);
		if(prod == null) {
			return new ResponseEntity<String>("ESTE NÃO EXISTE", HttpStatus.OK);
		}
		produtoService.
	 	if(idProdDel != null) {
	 		produtoService.deleteById(idProdDel);
	 		return new ResponseEntity<String>("Produto Deletado com sucesso", HttpStatus.OK);
	 	}else {
	 		return new ResponseEntity<String>("COMPRAS JÁ CADASTRADAS NESTE PRODUTO!! NÃO É POSSIVEL DELETAR !!", HttpStatus.OK);
	 	}
    	
    }*/
	
	@PutMapping(value = "atualizarProduto")
    @ResponseBody /*  Retorna os dados paara o corpo da resposta   */
    public ResponseEntity<?> atualizar(@RequestBody Produto prod){
		Produto produto = CadastroBo.INSTANCE.cadastroProduto(prod);
		if(produto != null) {
			if(produtoService.buscaProdutoCodigo(prod.getCodigoProduto().trim()) != null)
				return new ResponseEntity<String>("ESTE PRODUTO JA FOI CADASTRADO", HttpStatus.OK);
			//if(prod)
			produto = produtoService.saveAndFlush(prod);
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		}
		else {
			List<String> messages = new ArrayList<String>(ValidaDados.INSTANCE.messages) ;
			ValidaDados.INSTANCE.messages.clear();
			return new ResponseEntity<List<String>>(messages, HttpStatus.OK);
		}
    }

}
