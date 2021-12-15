package com.iesb.api_loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iesb.api_loja.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	@Query(value = "select ed from Endereco ed where ed.numCep = :numCep ")
	Endereco buscaEnderecoPorCep(@Param("numCep") int numCep);
	
	@Query(value = "select ed from Endereco ed left join ed.pessoa pes where pes.cpf like :numCpf  and ed.tipoEndereco = 2")
	Endereco buscaEnderecoCobrancaPorCliente(@Param("numCpf") String numCpf);
	
	@Query(value = "select ed from Endereco ed left join ed.pessoa pes where pes.cpf like :numCpf  and ed.tipoEndereco = 1")
	Endereco buscaEnderecoEntregaPorCliente(@Param("numCpf") String numCpf);

	

}
