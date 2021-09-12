/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.OpcionDAO;
import VO.Opcion;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class OpcionControlador {
    
    public static ArrayList<Opcion> consultar(int preguntaID){
        
        OpcionDAO opcDAO = new OpcionDAO();
    
        
           
    
        return opcDAO.consultar(preguntaID);
    }
    

}
