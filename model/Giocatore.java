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
    
    //Costruttori
    public Giocatore() {
        
    }
    
    public Giocatore(BufferedImage imgScudo, int x, int larghezza, int altezza) {
        this.imgScudo = imgScudo;
        this.x = x;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.y = 325;
    }
    
    //Metodi
    public void spostaDestra() {
        //si muove verso destra
    }
    
    public void spostaSinistra() {
        //si muove verso sinistra
    }
    
    public void disegna(Graphics graphics) {
        graphics.drawImage(this.imgScudo, this.x, this.y, this.larghezza, this.altezza, null);
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
