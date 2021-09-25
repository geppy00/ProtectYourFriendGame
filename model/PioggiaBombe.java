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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PioggiaBombe extends Thread {
    
    //Attributi per rappresentare la pioggia di proiettili
    private int quantita = 1;
    private int attesa; //valore per aspettare la prossima linea di proiettili
    BufferedImage imgBomba;
    private Game game;
    private boolean piove;
    private ArrayList<Bomba> pioggia;
    private Random random;
    private final int maxVerlocita = 15;
    
    //Costruttori
    public PioggiaBombe() {
        
    }
    
    public PioggiaBombe(BufferedImage imgBomba, int attesa, Game game) {
        this.imgBomba = imgBomba;
        this.attesa = attesa;
        this.game = game;
        this.pioggia = new ArrayList();
        this.random = new Random();
    }
    
    //Metodi
    @Override
    public void run() {
        this.piove = true;
        
        while(piove) {
            this.quantita = this.random.nextInt(2);
            for(int i = 0; i < this.quantita; i++) {
                this.getPioggia().add(new Bomba(this.imgBomba, 80, 80, random.nextInt(this.game.getLarghezza()), -50, random.nextInt(this.maxVerlocita) + 2, game));
            }
            
            try {
                Thread.sleep(attesa);
            } catch (InterruptedException ex) {
                Logger.getLogger(PioggiaBombe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void disegna(Graphics graphics) {
        for(int i = 0; i < this.getPioggia().size(); i++) {
            Bomba tmpProiettile = getPioggia().get(i);
            tmpProiettile.disegna(graphics);
        }
    }
    
    //Getters and Setters
    public ArrayList<Bomba> getPioggia() {
        return pioggia;
    }

    public void setPioggia(ArrayList<Bomba> pioggia) {
        this.pioggia = pioggia;
    }
}
