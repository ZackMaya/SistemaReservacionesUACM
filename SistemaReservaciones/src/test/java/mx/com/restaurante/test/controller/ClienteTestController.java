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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class ClienteTestController {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void probarHome() throws Exception{
		
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}
	
	
	@Test
	public void recibirClienteHome() throws Exception{
		mockMvc.perform(post("/reservacion").
				param("nombre", "Hector").
				param("telefono", "1234567890").
				param("email", "hector@gmail.com").
				param("comentario", "Realizando pruebas recibirClienteHome")).
				andExpect(view().name("reservacion"));
	}
	
}
