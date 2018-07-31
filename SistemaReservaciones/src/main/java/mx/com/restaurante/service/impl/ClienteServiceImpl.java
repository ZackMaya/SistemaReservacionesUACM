package mx.com.restaurante.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import mx.com.restaurante.domain.Cliente;
import mx.com.restaurante.domain.repository.ClienteRepository;
import mx.com.restaurante.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private static final int COMENTARIO_MAX = 100;

	private static final int COMENTARIO_MIN = 5;

	private static final int EMAIL_MAX = 70;

	private static final int EMAIL_MIN = 8;

	private static final int TELEFONO_MAX = 15;

	private static final int TELEFONO_MIN = 8;

	private static final int NOMBRE_MAX = 70;

	private static final int NOMBRE_MIN = 3;

	public static final Logger log = Logger.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	MessageSource messages;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> obtenerTodosClientes(){
		
		return Lists.newArrayList(clienteRepository.findAll());		
	}

	public Cliente buscarCliente(Long id) {
		return clienteRepository.findOne(id);
	}

	public String guardarCliente(Cliente cliente) {
		
		String msg;
		
		if(log.isDebugEnabled())
			log.debug("Entrando a guardarCliente");
		String nombre = String.valueOf(cliente.getNombre());
		String tel = String.valueOf(cliente.getTelefono());
		String email = String.valueOf(cliente.getEmail());
		
		
		if(nombre.length() < NOMBRE_MIN ){
			msg =messages.getMessage("message.min.nombre", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(nombre.length() > NOMBRE_MAX  ){
			msg =messages.getMessage("message.max.nombre", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(!(cliente.getTelefono().matches("[0-9]+"))){
			msg =messages.getMessage("message.digitos", null, LocaleContextHolder.getLocale());
			log.debug("********"+msg+"*************");
			return msg;
		}
		if(tel.length() < TELEFONO_MIN ){
			msg =messages.getMessage("message.tel.min", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(tel.length() > TELEFONO_MAX ){
			msg =messages.getMessage("message.tel.max", null, LocaleContextHolder.getLocale());
			return msg;
		}		
		if(!(cliente.getEmail().matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))){
			msg =messages.getMessage("message.email", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(email.length() < EMAIL_MIN){
			msg =messages.getMessage("message.email.min", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(email.length() > EMAIL_MAX ){
			msg =messages.getMessage("message.email.max", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(cliente.getComentario().length() < COMENTARIO_MIN){
			msg = messages.getMessage("message.min.coment",null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(cliente.getComentario().length() > COMENTARIO_MAX){
			msg = messages.getMessage("message.max.coment", null, LocaleContextHolder.getLocale());
			return msg;
		}
		
		
		
		clienteRepository.save(cliente);		
		
		return null;
	}
	
	
	public Cliente buscarClienteXNombre(String nombre){
		if(log.isDebugEnabled())
			log.debug("Entrando a buscarClienteXNombre");
		Cliente c = clienteRepository.findByNombre(nombre);
		return c;
	}

	public List<Cliente> obtenerTodos() {		
		return Lists.newArrayList(clienteRepository.findAll());
	}
	

}
