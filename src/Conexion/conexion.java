
package CONEXION;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @jogonpav
 */
public class conexion {
      public static Connection conn;
      
      @SuppressWarnings("empty-statement")
      public Connection conectar(){
        try {
            String bd = "db_games";
            String direccion="jdbc:mysql://localhost:3306/";
            String user="root"; //se cambia por el usuario  del gestor de base de datos
            String password="12345"; //se cambia por la contraseña del gestor de base de datos
            Class.forName("com.mysql.jdbc.Driver");
            String databaseURL = direccion + bd;
            conn = java.sql.DriverManager.getConnection(databaseURL, user,password);       
        } catch (SQLException ex) {
             System.out.println("" + ex);
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex);
              Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
          }
        return conn;
    }

}  
