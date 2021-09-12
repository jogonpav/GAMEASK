/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.HistoricoDAO;
import VO.Historico;

/**
 *
 * @author josep
 */
public class HistoricoControlador {
    
        public static boolean validarCaracteresAlfa(Historico hist){ //validar que todos los carácteres sean alfabeticos
        boolean respuesta = true;
        for(int i = 0; i <hist.getJugador().length();i++){
            char caracter = hist.getJugador().toUpperCase().charAt(i); //divide cada cadena a caracteres con charAt(i)    
            int valorASCII = (int) caracter; // convierte los caracteres a valor ASCII
            if (valorASCII != 165 && (valorASCII < 65 || valorASCII>90) && valorASCII != 32){// Compara que los caracteres alfabeticos estén dentro de la cadena
                respuesta = false;
                break;
            }
        }
        return respuesta;
    }
        
        
    public static Historico insertControlador (Historico His){        
        HistoricoDAO hisDAO =  new HistoricoDAO();  
        His = hisDAO.insertarDAO(His);
        return His;
    }
    
    public static Historico consultarCon(){
        Historico his = new Historico();
        HistoricoDAO hisDAO =  new HistoricoDAO(); 
        his = hisDAO.consultarDAO();
        
        return his;
    
    }
    
    
        
    
}
