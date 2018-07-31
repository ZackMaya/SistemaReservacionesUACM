package mx.com.restaurante.domain.repository;

import org.springframework.data.repository.CrudRepository;

import mx.com.restaurante.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	Cliente findByNombre(String nombre);
}
