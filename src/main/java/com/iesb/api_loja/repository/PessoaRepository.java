package com.iesb.api_loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.model.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query(value = "select pes from Pessoa pes where pes.cpf like :numCpf ")
	Pessoa buscaPessoaPorCpf(@Param("numCpf") String CPF);
	
	@Query(value = "select cli from Cliente cli left join cli.pessoa pes where pes.cpf like :numCpf ")
	Cliente buscaClientePorCpf(@Param("numCpf") String CPF);
	

}
