/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import CONEXION.conexion;
import VO.Historico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author josep
 */
public class HistoricoDAO {
    
    public static Historico insertarDAO (Historico his){
        try {
            conexion cn = new conexion();
            Connection conn = cn.conectar();
            //Statement stmt = conn.createStatement();          
//            String query = "INSERT INTO usuarios (jugador) VALUES('"+ est.getUser() +"','"+ est.getContrasena()+"','"+ est.getTipo() + "')";
            String query = "INSERT INTO historico (jugador) VALUES(?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, his.getJugador());     
            stmt.executeUpdate(); 
            query = "SELECT COUNT(*) FROM historico";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                his.setId(rs.getInt(1));
            conn.close();
            }catch(SQLException e){
                System.out.println(e);}
    return his;    
    }
    
    public static Historico consultarDAO(){
        Historico his = new Historico();
        try {
            conexion cn = new conexion();
            Connection conn = cn.conectar();
            String query = "SELECT * FROM historico WHERE id=(SELECT max(id) FROM historico);";
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                his.setId(rs.getInt(1));
                his.setJugador(rs.getString(2));
                his.setRonda_alcanzada(rs.getInt(3));
                his.setPremio(rs.getInt(4));
                his.setClasificacion(rs.getString(5));
            }
            conn.close();
            }catch(SQLException e){
                System.out.println(e);}
    return his;
    }
    
}
