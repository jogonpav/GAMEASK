/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CONEXION.conexion;
import VO.Opcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class OpcionDAO {
    
    public static ArrayList<Opcion> consultar(int preguntaID){
        ArrayList<Opcion> listaOpciones = new ArrayList<>();
        
        try {
            conexion cn = new conexion();
            Connection conn = cn.conectar();
            String query = "SELECT * FROM opcion WHERE pregunta_id =?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, String.valueOf(preguntaID));    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Opcion opc = new Opcion();
                opc.setId(rs.getInt(1));
                opc.setRespuesta(rs.getString(2));
                opc.setEs_correcto(rs.getBoolean(3));
                opc.setPregunta_id(rs.getInt(1));
                listaOpciones.add(opc);
            }
            conn.close();
            }catch(SQLException e){
                System.out.println("consulta opciones");
                System.out.println(e);}
        return listaOpciones;
    }
    
}
