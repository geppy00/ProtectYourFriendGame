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

public class PioggiaProiettili extends Thread {
    
    //Attributi per rappresentare la pioggia di proiettili
    private int quantita;
    private int attesa; //valore per aspettare la prossima linea di proiettili
    BufferedImage imgProiettile;
    private Game game;
    private boolean piove;
    private ArrayList<Proiettile> pioggia;
    private Random random;
    private final int maxVerlocita = 15;
    
    //Costruttori
    public PioggiaProiettili() {
        
    }
    
    public PioggiaProiettili(BufferedImage impProiettile, int quantita, int attesa, Game game) {
        this.imgProiettile = impProiettile;
        this.quantita = quantita;
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
            for(int i = 0; i < this.quantita; i++) {
                this.getPioggia().add(new Proiettile(this.imgProiettile, 20, 50, random.nextInt(this.game.getLarghezza()), -50, random.nextInt(this.maxVerlocita) + 2, game));
            }
            
            try {
                Thread.sleep(attesa);
            } catch (InterruptedException ex) {
                Logger.getLogger(PioggiaProiettili.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void disegna(Graphics graphics) {
        for(int i = 0; i < this.getPioggia().size(); i++) {
            Proiettile tmpProiettile = getPioggia().get(i);
            tmpProiettile.disegna(graphics);
        }
    }
    
    //Getters and Setters
    public ArrayList<Proiettile> getPioggia() {
        return pioggia;
    }

    public void setPioggia(ArrayList<Proiettile> pioggia) {
        this.pioggia = pioggia;
    }
}
