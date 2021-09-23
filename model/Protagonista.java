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
import java.util.*;
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
    private int velocitaProtagonista = 2; //velocità deve avere un valore perchè altrimenti non essendo niente non riuscirà a muoversi
    Game game;
    private final int maxVelocita = 9;
    
    //Costruttori
    public Protagonista() {
        
    }
    
    public Protagonista(BufferedImage image, int larghezza, int altezza, int x, int y, Game game) { 
        this.x = x;
        this.y = y;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.imgProtagonista = image;
        attivo = true;
        this.game = game;
    }
    
    //Metodi
    private void aggiorna() {
        /*this.setX(this.getX() + 1);*/ //cambia la posizione della protagonista
        Random random = new Random();
        
        if(this.x <= 0) //in questo if non bisogna negare la velocità perchè altrimenti il personaggio esce fuori dai bordi 
            this.velocitaProtagonista = random.nextInt(this.maxVelocita) + 1;
        
        else if(this.x >= (this.game.getLarghezza() - this.larghezza)) {
            this.velocitaProtagonista = random.nextInt(this.maxVelocita) + 1;
            this.velocitaProtagonista *= -1;
        }
        
        this.x += this.velocitaProtagonista;
    }
    
    public void disegna(Graphics graphics) { 
        graphics.drawImage(this.imgProtagonista, this.getX(), this.getY(), this.getLarghezza(), this.getAltezza(), null); //disegnamo la protagonista a schermo
    }
    
    @Override
    public void run() { //gestione del thread per la protagonista
        setAttivo(true);
        
        while(isAttivo()) {
            this.aggiorna();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Protagonista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Getter And Setters
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
