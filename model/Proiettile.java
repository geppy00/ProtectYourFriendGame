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
package model;

import java.awt.*;
import java.awt.image.*;
import java.util.logging.*;


public class Proiettile extends Thread {
    
    //Attributi per rappresentare il proiettile
    private int x;
    private int y;
    private int larghezza;
    private int altezza;
    BufferedImage imgProiettile;
    private boolean attivo;
    private Game game;
    private int velocitaProiettile;
    
    //Costruttori
    public Proiettile() {
        
    }
    
    public Proiettile(BufferedImage imgProiettile, int larghezza, int altezza, int x, int y, int velocitaProiettile, Game game) {
        this.x = x;
        this.y = y;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.imgProiettile = imgProiettile;
        attivo = true;
        this.game = game;
        this.start();
    }
    
    //Metodi
    private void aggiorna() {
        this.y += this.velocitaProiettile;
    }
    
    @Override
    public void run() {
        this.attivo = true;
        
        while(attivo) {
            this.aggiorna();
            
            try {
                Thread.sleep(200);
            } catch(InterruptedException ex) {
                Logger.getLogger(Protagonista.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void disegna(Graphics graphics) {
        graphics.drawImage(this.imgProiettile, this.x, this.y, this.larghezza, this.altezza, this.game);
    }
    
    
    
}
