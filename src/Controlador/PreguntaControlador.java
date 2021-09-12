/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.PreguntaDAO;
import VO.Categoria;
import VO.Pregunta;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class PreguntaControlador {
    
    public static ArrayList<Pregunta> consultar(ArrayList <Categoria> listaCategoria){
        PreguntaDAO opcDAO = new PreguntaDAO();
        
        return opcDAO.consultar(listaCategoria);
    }
    
}
