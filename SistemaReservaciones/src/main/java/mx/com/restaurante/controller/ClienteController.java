package mx.com.restaurante.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.restaurante.domain.Cliente;
import mx.com.restaurante.service.ClienteService;


@Controller
@RequestMapping("/")
public class ClienteController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String, Object> model){
		log.debug("Entre al home");
		return "home";
	}
	
	@RequestMapping(value="/reservacion", method=RequestMethod.POST)
	public String registrarCliente(@RequestParam("nombre") String nombre, @RequestParam("telefono") String tel, 
			@RequestParam("email") String email, @RequestParam("comentario") String comen, Model model){
		model.addAttribute("nombre", nombre);
		Cliente cliente = new Cliente(nombre, tel, email, comen);
		
		clienteService.guardarCliente(cliente);
		log.debug("*************");
		log.debug("El cliente tiene el nombre de "+cliente.getNombre() + cliente.getId());
		log.debug("*************");
		model.addAttribute("id", cliente.getId());
		model.addAttribute("nombre", cliente.getNombre());
		model.addAttribute("telefono", cliente.getTelefono());
		model.addAttribute("email", cliente.getEmail());
		model.addAttribute("comentario", cliente.getComentario());
		
		return "reservacion";
	}
	
	
	
}
