package com.iesb.api_loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iesb.api_loja.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
		
	@Query(value = "select cli from Cliente cli where cli.login like :login and cli.password like :senha ")
	Cliente buscaClienteEmaileSenha(@Param("login") String login,@Param("senha")String senha);
	
}
