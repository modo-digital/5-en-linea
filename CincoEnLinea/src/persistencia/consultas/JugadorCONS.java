/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.consultas;

import controladores.JugadoresJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.JugadorLOG;
import persistencia.Jugadores;

/**
 *
 * @author marianacro
 */
public class JugadorCONS {
    JugadoresJpaController controllerJugador;
    
    
    public boolean registrarJugador(Jugadores jugador) throws Exception{
        controllerJugador.create(jugador);
        return true;
    }
    
    public String validarInisioSesion(JugadorLOG jugador){
      String message = "Unknow";
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CincoEnLineaPU", null);
      JugadoresJpaController controller = new JugadoresJpaController();
      Jugadores jugadores = null;        
      try {
          jugadores = controller.findJugadores(jugador.getUsuario());
         if (jugadores.getUsuario().equals(jugador.getUsuario())) {
            if (jugadores.getClave().equals(jugador.getClave())) {
               return "1";
            } else {
               return "2";
            }
         }
      } catch (NullPointerException e) {
         return "3";
      }
      return message;
    }
    }
    
    