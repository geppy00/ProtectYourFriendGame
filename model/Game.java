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
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class Game extends Canvas implements KeyListener, Runnable {
    
    //Attributi per la finestra di gioco e delle componenti 
    private static final int larghezza = 1280;
    private static final int altezza = 720;
    private static final String nomeGioco = "Protect Your Friend Game";
    BufferedImage sfondo = null;
    BufferedImage protagonista = null;
    BufferedImage scudo = null;
    private boolean giocoAttivo = false;
    private Protagonista oggettoProtagonista;
    

    //Costruttori
    public Game() {
        this.caricaRisorse(); //ci carica al momento dell'avvio tutti le componenti del gioco
        this.iniziaGioco();
    }
    
    /*MAIN*/
    public static void main(String[] args) {
        Game game = new Game();
        JFrame finestraGame = new JFrame(nomeGioco);
        Dimension dimensioneFinestra = new Dimension(larghezza, altezza);
        
        //Creazione della finestra di gioco
        finestraGame.setPreferredSize(dimensioneFinestra);
        finestraGame.setMaximumSize(dimensioneFinestra);
        finestraGame.setResizable(false);
        finestraGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraGame.add(game); //diciamo al gioco che deve disegnarci sopra facendo visualizzare le immagini
        finestraGame.addKeyListener(game); //diciamo di ascoltare il listener per verificare se l'utente ha premuto un tasto
        finestraGame.pack();
        finestraGame.setVisible(true);
        
        //Creazione del thread del gioco invocando il metodo astratto run
        Thread threadGioco = new Thread(game);
        threadGioco.start();
    }
    
    
    //Metodi
    private void iniziaGioco() {
        this.oggettoProtagonista = new Protagonista(this.protagonista, 100, 200, 100, 480);
        this.oggettoProtagonista.start(); //essendo un thread possiamo farlo partire direttamente 
    }
    
    private void caricaRisorse() {
        CaricatoreImmagini caricatoreImmagini = new CaricatoreImmagini();
        
        this.sfondo = caricatoreImmagini.caricaImmagine("/immagini/sfondo.png");
        this.protagonista = caricatoreImmagini.caricaImmagine("/immagini/chararcter.png");
        this.scudo = caricatoreImmagini.caricaImmagine("/immagini/shield.png");
        
        System.out.print("Risorse caricate correttamente\n\n");
    }
    
    private void disegna() { //se utilizziamo solo l'ogetto Graphics per diseganre la protagonista essa sfarfallia ed risulta non ottimizzato, allora usiamo un altro oggeto BufferStrategy dove ci farà risolvere questo problema con una tecnica chiamata "buffering sequenziale ad anello"
        BufferStrategy bufferStrategy = this.getBufferStrategy(); //possiamo farlo perchè il nostro "Game" è di tipo Canvas
        if(bufferStrategy == null) {
            this.createBufferStrategy(2); //creamo 2 buffer su dove lavorare per adottare la strategia del "buffering sequenziale ad anello"
            return; //IMPORTANTE vuol dire che i buffer sono stati creati
        }
        
        //'graphics' NON lo prendiamo più dal pannello ma per adottare la soluzione del "buffering sequenziale ad anello" lo prendiamo dall'ogetto BufferStrategy
        /*Graphics graphics = this.getGraphics();*/ //consentono a un'applicazione di disegnare su componenti realizzati su vari dispositivi, nonché su immagini fuori schermo. praticamente dice che lui è un oggetto Canvas su cui si può disegnare
        
        Graphics graphics = bufferStrategy.getDrawGraphics(); //fa la stessa cosa di prima solo che questa volta abbiamo più buffer
        
        graphics.drawImage(this.sfondo, 0, 0, this.larghezza, this.altezza, this);
        this.oggettoProtagonista.disegna(graphics);
        
        graphics.dispose();
        bufferStrategy.show(); //Rende visibile il successivo buffer disponibile copiando la memoria (blitting) o modificando il puntatore del display (capovolgendo).
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void run() {
        this.giocoAttivo = true;
        
        while(giocoAttivo) {
            this.disegna();
        }
    }
    
}

/*
TEORIE=>
    BufferStrategy: The main reason for using a BufferStrategy is because you want to take control of the painting process (active painting) away from Swing's painting algorithm (which is passive)
*/