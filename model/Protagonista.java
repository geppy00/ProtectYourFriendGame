/*******************************************************
 * Copyright (C) 2021 -Giuseppe Malafronte- <email: giuseppe.malafronte2@studenti.unina.it>
 * 
 * This file is part of {Protect Your Friend Game Project}.
 * 
 * Protect Your Friend Game can not be copied and/or distributed without the express
 * permission of -Giuseppe Malafronte-
 *******************************************************/

package model;

import java.awt.*;
import java.awt.image.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Protagonista extends Thread {
    
    //Attributi per la componente protagonista
    private int x;
    private int y;
    private int larghezza;
    private int altezza;
    private boolean attivo;
    BufferedImage imgProtagonista;
    
    //Costruttori
    public Protagonista() {
        
    }
    
    public Protagonista(BufferedImage image, int larghezza, int altezza, int x, int y) { 
        this.x = x;
        this.y = y;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.imgProtagonista = image;
        attivo = true;
    }
    
    //Metodi
    private void aggiorna() {
        this.x++; //cambia la posizione della protagonista
    }
    
    public void disegna(Graphics graphics) { 
        graphics.drawImage(this.imgProtagonista, this.x, this.y, this.larghezza, this.altezza, null); //disegnamo la protagonista a schermo
    }
    
    @Override
    public void run() { //gestione del thread per la protagonista
        attivo = true;
        while(attivo) {
            this.aggiorna();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Protagonista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
