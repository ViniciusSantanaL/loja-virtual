package com.iesb.api_loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iesb.api_loja.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
	
	@Query(value = "select car from Carrinho car where car.id = :id and car.estadoCarrinho = 1")
	Carrinho buscaCarrinhoFinalizado(@Param("id") Long id);

}
