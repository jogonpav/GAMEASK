/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.RondaDAO;
import VO.Ronda;
import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class RondaControlador {
    
    
      public static ArrayList<Ronda> consultarCon (){
        RondaDAO ronDAO = new RondaDAO();
    
        return ronDAO.consultarDAO();
    }
    
}
