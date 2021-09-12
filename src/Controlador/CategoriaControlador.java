/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CategoriaDAO;
import VO.Categoria;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class CategoriaControlador {
    
    
    public static ArrayList <Categoria> consultar(int rondaActual){
        CategoriaDAO catDAO = new CategoriaDAO();
        
        return catDAO.consultarDAO(rondaActual);
    }
    
}
