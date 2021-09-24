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


public class Bomba extends Thread {

    //Attributi per rappresentare il proiettile
    private int x;
    private int y;
    private int larghezza;
    private int altezza;
    BufferedImage imgBomba;
    private boolean attivo;
    private Game game;
    private int velocitaProiettile;
    
    
    //Costruttori
    public Bomba() {
        
    }
    
    public Bomba(BufferedImage imgBomba, int larghezza, int altezza, int x, int y, int velocitaProiettile, Game game) {
        this.x = x;
        this.y = y;
        this.velocitaProiettile = velocitaProiettile;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.imgBomba = imgBomba;
        attivo = true;
        this.game = game;
        this.start();
    }
    
    
    //Metodi
    private void aggiorna() {
        this.setY(this.getY() + this.velocitaProiettile);
    }
    
    @Override
    public void run() {
        this.attivo = true;
        
        while(attivo) {
            this.aggiorna();
            
            try {
                Thread.sleep(100);
            } catch(InterruptedException ex) {
                Logger.getLogger(Protagonista.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void disegna(Graphics graphics) {
        graphics.drawImage(this.imgBomba, this.getX(), this.getY(), this.getLarghezza(), this.getAltezza(), this.game);
    }
    
    public Rectangle getBordi() {
        return new Rectangle(this.x, this.y, this.larghezza, this.altezza); //A Rectangle specifies an area in a coordinate space that is enclosed by the Rectangle object's upper-left point (x,y) in the coordinate space, its width, and its height.
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
    
    
}
