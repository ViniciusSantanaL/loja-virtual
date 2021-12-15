package com.iesb.api_loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iesb.api_loja.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{
	
	@Query(value = "select card from Cartao card where card.numCartao = :numCartao ")
	Cartao buscaCartaoPorNum(@Param("numCartao") int numCartao);
	
	@Query(value = "select card from Cartao card join card.cliente cli where cli.id = :idCliente ")
	Cartao buscaCartaoPorCliente(@Param("idCliente") Long idCliente);
	

}
