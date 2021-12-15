package com.iesb.api_loja.controllers;

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
import com.iesb.api_loja.bo.VerificaLogin;
import com.iesb.api_loja.form.ClienteForm;
import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.model.Pessoa;
import com.iesb.api_loja.repository.ClienteRepository;
import com.iesb.api_loja.repository.PessoaRepository;


@RestController
public class ClienteController {
	
	@Autowired /*  CDI - INJEÇÃO DE DEPENDENCIA   */
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping(value = "cadastrarCliente")
    @ResponseBody
    public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteForm cli){
		Pessoa pessoa = pessoaRepository.buscaPessoaPorCpf(cli.getCpf());
		if(pessoa != null) {
			return new ResponseEntity<String>("ESTE CLIENTE JÁ ESTÁ CADASTRADO", HttpStatus.OK);
		}
		Cliente cliente = CadastroBo.INSTANCE.cadastroCliente(cli);
		
		if(cliente != null) {
			pessoaRepository.save(cliente.getDadosPessoa());
			cliente = clienteRepository.save(cliente);
			VerificaLogin.INSTANCE.registraLogin(cliente.getLogin().trim());
			return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<List<String>>(ValidaDados.INSTANCE.messages, HttpStatus.OK);
    }
	
	@PostMapping(value = "fazerLogin")
    @ResponseBody
    public ResponseEntity<?> fazerLogin(@RequestBody ClienteForm cli){
		Cliente cliente = clienteRepository.buscaClienteEmaileSenha(cli.getLogin(),cli.getPassword());
		
		if(cliente != null) {
			pessoaRepository.save(cliente.getDadosPessoa());
			cliente = clienteRepository.save(cliente);
			VerificaLogin.INSTANCE.registraLogin(cliente.getLogin().trim());
			return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<String>("Login e Senha errados", HttpStatus.OK);
    }
}
