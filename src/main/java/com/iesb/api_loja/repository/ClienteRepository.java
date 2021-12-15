package com.iesb.api_loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesb.api_loja.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
		

}
