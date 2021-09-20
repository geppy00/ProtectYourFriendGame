/** *****************************************************
 * Copyright (C) 2021 {Giuseppe Malafronte}
 * <giuseppe.malafronte2@studenti.unina.it>
 *
 * This file is part of {FirstGame}.
 *
 * {FirstGame} can not be copied and/or distributed without the express
 * permission of {Giuseppe Malafronte}
 ******************************************************
 */
package model;

import java.awt.image.*;


public class Protagonista {
    
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
    }
}
