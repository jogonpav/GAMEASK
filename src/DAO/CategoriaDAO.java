/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONEXION.conexion;
import VO.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class CategoriaDAO {
    
    public static ArrayList<Categoria> consultarDAO(int rondaActual){
        ArrayList<Categoria> categoriaList = new ArrayList<>();
        
        try {
            conexion cn = new conexion();
            Connection conn = cn.conectar();
            String query = "SELECT * FROM categoria WHERE RONDA_ID =?";
            PreparedStatement stmt = conn.prepareStatement(query);
            System.out.println("ronda actual: DAO" + rondaActual);
            stmt.setString(1, String.valueOf(rondaActual));     
            ResultSet rs = stmt.executeQuery();
            //ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setNombre(rs.getString(2));
                cat.setRonda_id(rs.getInt(3));
                categoriaList.add(cat);
            }
            conn.close();
            }catch(SQLException e){
                System.out.println(e);}
        return categoriaList;
    }
    
}
