package mx.com.restaurante.service;


import java.util.List;

import mx.com.restaurante.domain.Cliente;

public interface ClienteService {

	/**
	 * Metodo utilizado para buscar cliente por el id
	 * @param id identificador del objeto
	 * @return cliente retorna el objeto
	 */
		
	Cliente buscarCliente(Long id);
	
	String guardarCliente(Cliente cliente);
	
	Cliente buscarClienteXNombre(String nombre);
	
	List<Cliente> obtenerTodos();
}
