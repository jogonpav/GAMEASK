/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONEXION.conexion;
import VO.Ronda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class RondaDAO {
    
    
    public static ArrayList<Ronda> consultarDAO (){
        ArrayList<Ronda> rondaList = new ArrayList<>();
        
        try {
            conexion cn = new conexion();
            Connection conn = cn.conectar();
            Statement stmt = conn.createStatement();      
            String query = "SELECT * FROM ronda";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Ronda ron = new Ronda();
                ron.setId(rs.getInt(1));
                ron.setNivel(rs.getInt(2));
                ron.setPremio(rs.getInt(3));
                rondaList.add(ron);
            }
            conn.close();
            }catch(SQLException e){
                System.out.println(e);}    
    
        return rondaList;
    }
    
}
