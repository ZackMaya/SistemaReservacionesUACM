package mx.com.restaurante.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBUnitData {

	private final static Logger log = LoggerFactory.getLogger(DBUnitData.class);

	public static void extraerDatasetsUnoxUno(Connection conn) throws SQLException, DatabaseUnitException, FileNotFoundException, IOException  {
		if(log.isDebugEnabled()) {
			log.debug("Entrando a extraer...");
		}
		
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		IDatabaseConnection connection = new DatabaseConnection(conn);
		DatabaseConfig dbConfig = connection.getConfig();
	    dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		QueryDataSet queryDataSet = null;
		String rutaArchivo;
		while (rs.next()) {
			queryDataSet = new QueryDataSet(connection);
			queryDataSet.addTable(rs.getString(3));
			rutaArchivo = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/" + rs.getString(3) + ".xml";
			FlatXmlDataSet.write(queryDataSet,
	                    new FileOutputStream(rutaArchivo));
			if(log.isDebugEnabled()) {
				log.debug("Extrayendo " + rutaArchivo);
			}
		}
		
		if(log.isDebugEnabled()){
			log.debug("Terminando de extraer...");
		}
		
	}
	
}
