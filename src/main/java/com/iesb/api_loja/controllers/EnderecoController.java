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
import com.iesb.api_loja.model.Endereco;
import com.iesb.api_loja.model.Pessoa;
import com.iesb.api_loja.repository.EnderecoRepository;
import com.iesb.api_loja.repository.PessoaRepository;

@RestController
public class EnderecoController {
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@PostMapping(value = "cadastrarEnderecoEntrega")
    @ResponseBody
    public ResponseEntity<?> cadastrarEnderecoEntrega(@RequestBody Endereco end, @RequestBody String cpf){
		
		Pessoa pessoa = pessoaRepository.buscaPessoaPorCpf(cpf);
		if(pessoa == null) {
			return new ResponseEntity<String>("ESTE CLIENTE NÃO ESTÁ CADASTRADO", HttpStatus.OK);
		}
		pessoa.getEnderecosUsuario().add(end);
		end.setPessoa(pessoa);
		Endereco endereco = CadastroBo.INSTANCE.cadastrarEnderecoEntrega(end);
		
		if(endereco != null) {
			if(enderecoRepository.buscaEnderecoPorCep(end.getNumCep()) != null)
				return new ResponseEntity<String>("ESTE ENDERECO JA FOI CADASTRADO", HttpStatus.OK);
			
			endereco = enderecoRepository.save(endereco);
			pessoa = pessoaRepository.saveAndFlush(pessoa);
			return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
		}
		else {
			List<String> messages = new ArrayList<String>(ValidaDados.INSTANCE.messages) ;
			ValidaDados.INSTANCE.messages.clear();
			return new ResponseEntity<List<String>>(messages, HttpStatus.OK);
		}
    }
	
	@PostMapping(value = "cadastrarEnderecoCobranca")
    @ResponseBody
    public ResponseEntity<?> cadastrarEnderecoCobranca(@RequestBody Endereco end, @RequestBody String cpf){
		
		Pessoa pessoa = pessoaRepository.buscaPessoaPorCpf(cpf);
		if(pessoa == null) {
			return new ResponseEntity<String>("ESTE CLIENTE NÃO ESTÁ CADASTRADO", HttpStatus.OK);
		}
		pessoa.getEnderecosUsuario().add(end);
		end.setPessoa(pessoa);
		Endereco endereco = CadastroBo.INSTANCE.cadastrarEnderecoCobranca(end);
		
		if(endereco != null) {
			if(enderecoRepository.buscaEnderecoPorCep(end.getNumCep()) != null)
				return new ResponseEntity<String>("ESTE ENDERECO JA FOI CADASTRADO", HttpStatus.OK);
			endereco = enderecoRepository.save(endereco);
			pessoa = pessoaRepository.saveAndFlush(pessoa);
			return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
		}
		else {
			List<String> messages = new ArrayList<String>(ValidaDados.INSTANCE.messages) ;
			ValidaDados.INSTANCE.messages.clear();
			return new ResponseEntity<List<String>>(messages, HttpStatus.OK);
		}
	
	}
}
