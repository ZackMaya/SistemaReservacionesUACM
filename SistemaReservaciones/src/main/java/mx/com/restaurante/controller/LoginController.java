package mx.com.restaurante.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.restaurante.domain.Cliente;
import mx.com.restaurante.domain.Reservacion;
import mx.com.restaurante.service.ClienteService;
import mx.com.restaurante.service.ReservacionService;

@Controller
@RequestMapping("/gestionReservaciones")
public class LoginController {

private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ReservacionService reservacionService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String gestionReservaciones(Map<String, Object> model){
		log.debug("Entre al gestionReservaciones");
		List<Reservacion> reservas = reservacionService.obtenerTodasReservaciones();
		List<Cliente> clientes = clienteService.obtenerTodos();
		model.put("clientes", clientes);
		model.put("reservas", reservas);
		return "gestionReservaciones";
	}
	
	

	
}
