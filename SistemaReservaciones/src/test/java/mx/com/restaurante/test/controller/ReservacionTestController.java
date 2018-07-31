package mx.com.restaurante.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import mx.com.restaurante.Application;
import mx.com.restaurante.domain.Cliente;
import mx.com.restaurante.service.ClienteService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class ReservacionTestController {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ClienteService clienteService;
	
	@Test
	public void probarReservacion() throws Exception{
		
		mockMvc.perform(get("/reservacion")).andExpect(view().name("reservacion"));
	}
	
	@Test
	public void recibirClienteHome() throws Exception{
		Cliente c = new Cliente();
	    c = clienteService.buscarClienteXNombre("Christian");
	    String id = String.valueOf(c.getId());
	    System.out.println("******* ID:"+ id +" ******");
		mockMvc.perform(post("/reservacion/confirmacion").
				param("fecha", "11/12/2017").
				param("noPersonas", "3").
				param("noMesas", "1").
				param("horario", "17:30").
				param("idCliente", id)).
				andExpect(view().name("confirmacion"));
	}
}
