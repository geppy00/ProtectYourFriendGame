/** *****************************************************
 * Copyright (C) 2021 -Giuseppe Malafronte-
 * <email: giuseppe.malafronte2@studenti.unina.it>
 *
 * This file is part of {Protect Your Friend Game Project}.
 *
 * Protect Your Friend Game can not be copied and/or distributed without the
 * express permission of -Giuseppe Malafronte-
 ******************************************************
 */
package controller;

import model.*;

public class GestoreCollisioni {
    
    //Metodi per gestire le collissioni tra gli oggetti
    public static boolean controllaCollisioneGiocatore(Giocatore giocatore, Bomba bomba) {
        if(giocatore.getBordi().intersects(bomba.getBordi())) { //Si ha una collisione tra lo scudo e il proiettile
            return true;
        }
        
        return false;
    }
    
    public static boolean controllaCollisioniProtagonista(Protagonista protagonista, Bomba bomba) {
        if(protagonista.getBordi().intersects(bomba.getBordi())) {
            return true;
        }
        
        return false;
    }
    
    public static boolean controllaCollissioniProiettile(Proiettile proiettile, Bomba bomba) {
        if(proiettile.getBordi().intersects(bomba.getBordi())) {
            return true;
        }
        
        return false;
    }
    
}
