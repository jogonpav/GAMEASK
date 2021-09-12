/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONEXION.conexion;
import VO.Categoria;
import VO.Opcion;
import VO.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class PreguntaDAO {
    
    
    public static ArrayList<Pregunta> consultar(ArrayList <Categoria> listaCategoria){
         ArrayList<Pregunta> listaPregunta = new ArrayList<>();
        try {
            conexion cn = new conexion();
            Connection conn = cn.conectar();
            PreparedStatement stmt;
            ResultSet rs;
            System.out.println("tamaño lista categoria" + listaCategoria.size());
            for (int i = 0; i < listaCategoria.size(); i++) {
                String query = "SELECT * FROM pregunta WHERE categoria_id =?";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, String.valueOf(listaCategoria.get(i).getId()));     
                //rs = stmt.executeQuery(query);
                rs = stmt.executeQuery();
                while (rs.next()){
                    Pregunta cat = new Pregunta();
                    cat.setId(rs.getInt(1));
                    cat.setEnunciado(rs.getString(2));
                    cat.setCategoria_id(rs.getInt(3));
                    listaPregunta.add(cat);
                }
                
            }
            
            conn.close();
            }catch(SQLException e){
                System.out.println("dao pregunta");
                System.out.println(e);}

        return listaPregunta;
            
            
    }
    
    
}
