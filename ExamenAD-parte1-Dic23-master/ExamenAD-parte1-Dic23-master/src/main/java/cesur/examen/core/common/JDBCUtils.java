package cesur.examen.core.common;

import cesur.examen.App;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Cayetano Perez Bueno
 * Fecha: 11/12/2023
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */
@Log
public class JDBCUtils {

    /**
     * Connection is stored as a static final object accessible by all other classes.
     * Connection data is retrieved from db.properties file located in resource folder.
     *
     * Remember to open an InputStream to a file located in resource folder using
     * JDBCUtils.class.getClassLoader().getResourceAsStream()
     */
    private static final Connection conn;

    private static Logger logg;


    static{
        logg= Logger.getLogger(JDBCUtils.class.getName());

        String url;
        String user;
        String password;

        var cfg= new Properties();

        try{

            conn = null;
            cfg.load( JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            logg.info("Configuracion cargada");
            url= "jdbc:mysql://"+ cfg.getProperty("host") + ":" + cfg.getProperty("port") + "/" + cfg.getProperty("dbname");
            logg.info(url);
            user= cfg.getProperty("user");
            logg.info(user);
            password= cfg.getProperty("pass");
            logg.info(password);




            if(conn==null) logg.info("JDBCUtils Not implemented yet!");
            else logg.info("Succesfully connected!");

        }catch( Exception ex){
            logg.severe("Error connecting to database");
            throw new RuntimeException("Error connecting to database");
        }
    }


    public static Connection getConn() {
        return conn;
    }

    /**
     * Conversion utility from util.Date to sql.Date
     * Remember that sql.Date should be used in Jdbc queries but
     * class java.util.Date cannot be cast to class java.sql.Date
     * We can do the cast (util.Date) sql.Date safely, but not backwards.
     *
     * @param d date in util.Date format
     * @return the same date in sql.Date date
     */
    public static java.sql.Date dateUtilToSQL( java.util.Date d){
        return new java.sql.Date(d.getTime());
    }

}
