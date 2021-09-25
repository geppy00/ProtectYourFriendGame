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
import java.util.logging.Level;
import java.util.logging.Logger;


public class Proiettile extends Thread {
    
    //Attributi per rapprensatare un proiettile
    private Game game;
    private int x;
    private int y;
    private int larghezza;
    private int altezza;
    BufferedImage imgProiettile;
    private boolean attivo;
    private final float velocita = 1.5f;
    
    //Costruttori
    public Proiettile() {
        
    }
    
    public Proiettile(BufferedImage imgProiettile, int x, int y, int larghezza, int altezza, Game game) {
        this.imgProiettile = imgProiettile;
        this.x = x;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.y = y;
        this.game = game;
        
        this.start(); //quando viene aggiunto nell'arrayList deve partire
    } 
    
    
    //Metodi
    public Rectangle getBordi() {
        return new Rectangle(this.x, this.y, this.larghezza, this.altezza);
    }
    
    private void aggiorna() {
        this.y -= this.velocita;
        
        if(this.y + this.altezza < 0) { 
            this.setAttivo(false);
            Game.munizioniProiettili.remove(this);
        }
    }
   
    @Override
    public void run() {
        this.setAttivo(true);
        
        while(isAttivo()) {
            this.aggiorna();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Proiettile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void disegna(Graphics graphics) {
        graphics.drawImage(this.imgProiettile, this.x, this.y, this.larghezza, this.altezza, this.game);
    }
    
    //Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(int larghezza) {
        this.larghezza = larghezza;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }
    
    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
