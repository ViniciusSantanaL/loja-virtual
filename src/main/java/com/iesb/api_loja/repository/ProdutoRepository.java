package com.iesb.api_loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iesb.api_loja.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query(value = "select prod from Produto prod where prod.codigoProduto = :codProd ")
	Produto buscaProdutoCodigo(@Param("codProd") String codProd);
	
//	@Query(value = "select prod.codigoProduto from Carrinho as ca inner join ca.produtoCarrinho as prod"
	//		+ " where prod.codigoProduto = :codProd and ca.estadoCarrinho = 3 ")
	//String buscaProdutoDeletar(@Param("codProd")String codProd);
	
	@Query(value = "select prod.id from Produto prod where prod.codigoProduto = :codProd ")
	Long buscaIdProdPorCod(@Param("codProd") String codProd);

}
