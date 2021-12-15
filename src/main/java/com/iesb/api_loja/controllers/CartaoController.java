package com.iesb.api_loja.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iesb.api_loja.bo.CadastroBo;
import com.iesb.api_loja.bo.ValidaDados;
import com.iesb.api_loja.model.Cartao;
import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.repository.CartaoRepository;
import com.iesb.api_loja.repository.PessoaRepository;

@RestController
public class CartaoController {
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	
	@PostMapping(value = "cadastrarCartaoPagamento")
    @ResponseBody
    public ResponseEntity<?> cadastrarCartao(@RequestBody Cartao card, @RequestBody String cpf){
		
		Cliente cli = pessoaRepository.buscaClientePorCpf(cpf);
		if(cli == null) {
			return new ResponseEntity<String>("ESTE CLIENTE NÃO ESTÁ CADASTRADO", HttpStatus.OK);
		}
		card.setCliente(cli);
	
		Cartao cartao = CadastroBo.INSTANCE.cadastrarCartao(card);
		
		if(cartao != null) {
			if(cartaoRepository.buscaCartaoPorNum(card.getNumCartao()) != null)
				return new ResponseEntity<String>("ESTE ENDERECO JA FOI CADASTRADO", HttpStatus.OK);
			cartao = cartaoRepository.save(cartao);
			return new ResponseEntity<Cartao>(cartao, HttpStatus.CREATED);
		}
		else {
			List<String> messages = new ArrayList<String>(ValidaDados.INSTANCE.messages) ;
			ValidaDados.INSTANCE.messages.clear();
			return new ResponseEntity<List<String>>(messages, HttpStatus.OK);
		}
    }
	
}
