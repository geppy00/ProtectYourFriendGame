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

public class Giocatore {

    
    //Attributi per la componente giocatore (lo scudo)
    private int x;
    private int y;
    private int larghezza;
    private int altezza;
    BufferedImage imgScudo;
    private final int velocita = 10;
    private Game game;
    
    //Costruttori
    public Giocatore() {
        
    }
    
    public Giocatore(BufferedImage imgScudo, int x, int larghezza, int altezza, Game game) {
        this.imgScudo = imgScudo;
        this.x = x;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.y = 325;
        this.game = game;
    }
    
    //Metodi
    public void spostaDestra() {
        if((this.x + this.larghezza) < this.game.getLarghezza()) //effettuiamo un controllo se l'immagine esca fuori dai bordi della finestra per destra dobbiamo controllare se la posizione è minore della larghezza della finestra però bisogna sommare la larghezza dell'oggetto giocatore se no uscira fuori
            this.x += this.velocita;
    }
    
    public void spostaSinistra() {
        if(this.x > 0)  //effettuiamo un controllo se l'immagine esca fuori dai bordi della finestra per sinistra basta che l'asse delle x sia maggiore di 0 
            this.x -= this.velocita;
    }
    
    public void disegna(Graphics graphics) {
        graphics.drawImage(this.imgScudo, this.x, this.y, this.getLarghezza(), this.getAltezza(), null);
    }
    
    public Rectangle getBordi() {
        return new Rectangle(this.x, this.y, this.larghezza, this.altezza);
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
    
}
