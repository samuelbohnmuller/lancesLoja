package br.com.alura.mvc.springMVC.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.springMVC.model.Pedido;
import br.com.alura.mvc.springMVC.model.StatusPedido;

@Repository //repositório gerenciada pelo spring, criando instancias quando outra classe pedir
public interface PedidoRepository extends JpaRepository<Pedido, Long>{ //para conexão do modelo com o banco, extendendo classe que faz JPA com Spring

	@Cacheable("books") //cachê(para carregar em memória a informação, ao invés de ir no banco de dados)
	List<Pedido> findByStatus(StatusPedido status, Pageable sort); //select no banco pelo status, com ordenação

	@Query("SELECT p FROM p JOIN p.user u WHERE u.username = :username") //criar query na mão
	List<Pedido> findAllByUser(@Param("username")String username ); //Param username é o da query(:username) que buscará no parâmetro da chamada do método

	@Query("SELECT p FROM p JOIN p.user u WHERE u.username = :username and p.status = :status")
	List<Pedido> findByStatusAndUser(@Param("status")StatusPedido status, @Param("username")String username);

}
