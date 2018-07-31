package mx.com.restaurante.test.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.com.restaurante.Application;
import mx.com.restaurante.domain.Cliente;
import mx.com.restaurante.domain.Reservacion;
import mx.com.restaurante.domain.repository.ClienteRepository;
import mx.com.restaurante.domain.repository.ReservacionRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ClienteDomainTest {

	public static final Logger log = Logger.getLogger(ClienteDomainTest.class);
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ReservacionRepository reservacionRepository;
	
	@Autowired
	DataSource dataSource;
	
	@Before
	public  void configurar() throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		
		Connection conn = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(conn);
		DatabaseConfig dbConfig = connection.getConfig();
		 dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		 FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		 
		 IDataSet dataSetReservacion = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/reservacion.xml"));
		 
		 IDataSet dataSetCliente = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/cliente.xml"));
		
		
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetReservacion);
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetCliente);
		 

		 DatabaseOperation.INSERT.execute(connection, dataSetCliente);
		 DatabaseOperation.INSERT.execute(connection, dataSetReservacion);		
		
		 
	}
	
	@Test
	public void actualizarClienteTest(){
		log.debug("Entrando al metodo actualizarClienteTest");
		
		String nombreCliente = "Chris";
		String nombreABuscar = "Christian";
		
		Cliente clienteObtenido = clienteRepository.findByNombre(nombreABuscar);
		
		Assert.assertNotNull(clienteObtenido);
		
		clienteObtenido.setNombre(nombreCliente);
		
		Reservacion reservacion1 = new Reservacion(new Date(), 4, 1, "20:30", clienteObtenido);
		
		List<Reservacion> reservaciones = new ArrayList<Reservacion>();
		
		reservaciones.add(reservacion1);
		
		clienteObtenido.setReservaciones(reservaciones);
		
		clienteRepository.save(clienteObtenido);
		
		
		
		clienteObtenido = clienteRepository.findByNombre(nombreCliente);
		
		Assert.assertTrue(nombreCliente.equals(clienteObtenido.getNombre()));
		
	}
	
	@After
	public  void terminar() throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		
		Connection conn = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(conn);
		DatabaseConfig dbConfig = connection.getConfig();
		 dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		 FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		 
		 IDataSet dataSetReservacion = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/reservacion.xml"));
		 
		 IDataSet dataSetCliente = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/cliente.xml"));
		
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetReservacion);
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetCliente);
		
	}
	
	
}
