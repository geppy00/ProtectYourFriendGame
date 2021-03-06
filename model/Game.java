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
import controller.*;
import java.util.*;

public class Game extends Canvas implements Runnable, MouseMotionListener, MouseListener {
    
    //Attributi per la finestra di gioco e delle componenti 
    private static final int larghezza = 1280;
    private static final int altezza = 720;
    private static final String nomeGioco = "Protect Your Friend Game";
    BufferedImage sfondo = null;
    BufferedImage protagonista = null;
    BufferedImage scudo = null;
    BufferedImage bomba = null;
    BufferedImage sfondoGameOver = null;
    BufferedImage proiettile = null;
    private static boolean giocoAttivo = false;
    private Protagonista oggettoProtagonista;
    private Giocatore oggettoGiocatore;
    private PioggiaBombe pioggiaBombe;
    public static ArrayList<Proiettile> munizioniProiettili;
    

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
        /*finestraGame.addKeyListener(game);*/ //diciamo di ascoltare il listener per verificare se l'utente ha premuto un tasto
        game.addMouseListener(game); 
        /*finestraGame.addMouseMotionListener(game);*/ //se aggiungiamo il MouseMotionListener direttamente al JFrame non viene riconosciuto, cio?? solo la prima volta poi basta, quindi la soluzione ?? aggiungerlo direttamente all'oggetto Game
        game.addMouseMotionListener(game);
        finestraGame.pack();
        finestraGame.setVisible(true);
        finestraGame.setLocationRelativeTo(null);

        //Creazione del thread del gioco invocando il metodo astratto run
        Thread threadGioco = new Thread(game);
        threadGioco.start();
        
    }
    
    
    //Metodi
    private void iniziaGioco() {
        this.oggettoProtagonista = new Protagonista(this.protagonista, 100, 250, 150, 430, this);
        this.oggettoProtagonista.start(); //essendo un thread possiamo farlo partire direttamente
        
        this.pioggiaBombe = new PioggiaBombe(this.bomba, 500, this);
        this.pioggiaBombe.start();
        
        this.oggettoGiocatore = new Giocatore(this.scudo, this.proiettile, 0, 100, 100, this);
    }
    
    private void caricaRisorse() {
        CaricatoreImmagini caricatoreImmagini = new CaricatoreImmagini();
        
        this.sfondo = caricatoreImmagini.caricaImmagine("/immagini/sfondo.jpg");
        this.protagonista = caricatoreImmagini.caricaImmagine("/immagini/chararcter.png");
        this.scudo = caricatoreImmagini.caricaImmagine("/immagini/shield.png");
        this.bomba = caricatoreImmagini.caricaImmagine("/immagini/bomb.png");
        this.sfondoGameOver = caricatoreImmagini.caricaImmagine("/immagini/game_over.png");
        this.proiettile = caricatoreImmagini.caricaImmagine("/immagini/bullet.png");
        
        
        System.out.print("Risorse caricate correttamente\n\n");
    }
    
    private void disegna() { //se utilizziamo solo l'ogetto Graphics per diseganre la protagonista essa sfarfallia ed risulta non ottimizzato, allora usiamo un altro oggeto BufferStrategy dove ci far?? risolvere questo problema con una tecnica chiamata "buffering sequenziale ad anello"
        BufferStrategy bufferStrategy = this.getBufferStrategy(); //possiamo farlo perch?? il nostro "Game" ?? di tipo Canvas
        if(bufferStrategy == null) {
            this.createBufferStrategy(2); //creamo 2 buffer su dove lavorare per adottare la strategia del "buffering sequenziale ad anello"
            return; //IMPORTANTE vuol dire che i buffer sono stati creati
        }
        
        //'graphics' NON lo prendiamo pi?? dal pannello ma per adottare la soluzione del "buffering sequenziale ad anello" lo prendiamo dall'ogetto BufferStrategy
        /*Graphics graphics = this.getGraphics();*/ //consentono a un'applicazione di disegnare su componenti realizzati su vari dispositivi, nonch?? su immagini fuori schermo. praticamente dice che lui ?? un oggetto Canvas su cui si pu?? disegnare
        
        Graphics graphics = bufferStrategy.getDrawGraphics(); //fa la stessa cosa di prima solo che questa volta abbiamo pi?? buffer
        
        graphics.drawImage(this.sfondo, 0, 0, this.getLarghezza(), this.getAltezza(), this);
        this.oggettoProtagonista.disegna(graphics);
        this.oggettoGiocatore.disegna(graphics);
        this.pioggiaBombe.disegna(graphics);
        graphics.setColor(Color.GREEN);
        graphics.drawString("Vita: "+this.oggettoProtagonista.getVita(), 25, 25);
        
        if(!this.giocoAttivo) {
            this.munizioniProiettili.clear(); //rimuove tutti i proiettili sullo schermo
            graphics.clearRect(0, 0, this.larghezza, this.altezza);
            graphics.drawImage(this.sfondoGameOver, 0, 0, this.getLarghezza(), this.getAltezza(), this);
            graphics.setColor(Color.red);
            graphics.drawString("!! HAI FATTO MORIRE LA TUA AMICA !!", 490, 620);
        }
        
        if(this.munizioniProiettili != null) {
            for(Proiettile p: munizioniProiettili) {
                p.disegna(graphics);
            }
        }
        
        graphics.dispose();
        bufferStrategy.show(); //Rende visibile il successivo buffer disponibile copiando la memoria (blitting) o modificando il puntatore del display (capovolgendo).
    }
/*
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int keyCode = e.getKeyCode(); //Codice del tasto premuto
       
       switch(keyCode) {
           case KeyEvent.VK_LEFT:
                this.oggettoGiocatore.spostaSinistra();
            break;
            
           case KeyEvent.VK_RIGHT:
               this.oggettoGiocatore.spostaDestra();
            break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
 */
    private boolean controllaSconfitta() {
        if(this.oggettoProtagonista.getVita() <= 0) 
            return true;
        
        return false;
    }
    
    private void aggiorna() {
        ArrayList<Bomba> pioggia = this.pioggiaBombe.getPioggia();
        ArrayList<Proiettile> munizioni = this.munizioniProiettili;
        
        for(Bomba b: pioggia) {
            for(Proiettile p: munizioni) {
                if(GestoreCollisioni.controllaCollissioniProiettile(p, b)) {
                    munizioni.remove(p);
                    pioggia.remove(b);
                    break;
                }
            }
            
            if(GestoreCollisioni.controllaCollisioneGiocatore(oggettoGiocatore, b)) {
                pioggia.remove(b); //se la collisione avviene rimuviamo l'oggetto proiettile dall'arraylist di consegunza anche nello schermo
                break; //questo break ?? importante in quanto un oggetto viene modificato contemporaneamente da un thread diverso quindi per evitare l'ecccezione si fa un salto per poi ricontrollarlo al prossimo aggiornamento
            }
            if(GestoreCollisioni.controllaCollisioniProtagonista(oggettoProtagonista, b)) {
                pioggia.remove(b);
                this.oggettoProtagonista.setVita(oggettoProtagonista.getVita() - 5);
                break; //questo break ?? importante perch?? togliendo un elemento dall'arraylist la dimensione cambia ma il for questo al momento non lo sa quindi usciamo e lo rifacciamo
            }
            
        }
        
        if(this.controllaSconfitta()) {
            this.giocoAttivo = false;
            this.disegna();
        }
            
    }

    @Override
    public void run() {
        FpsController fpsController = new FpsController(60);
        this.giocoAttivo = true;
        
        while(giocoAttivo) {
            if(fpsController.checkDelta()) {
                this.aggiorna(); //controlla le collisioni
                this.disegna();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int posizione = (e.getPoint().x)-(this.oggettoGiocatore.getLarghezza() / 2); //andiamo a trovare il punto dove si trova il mouse per?? lo scudo ha una certa larghezza quindi per farlo comparire esattamente nel punto indicato dobbiamo sottrarre la meta della sua larghezza
        
        if(posizione >= 0 && (posizione + this.oggettoGiocatore.getLarghezza()) <= this.larghezza) //effettuiamo un controllo se il cursore esca fuori dai bordi della finestra per sinistra basta che l'asse delle x sia maggiore di 0 per quanto riguarda a destra dobbiamo controllare se la posizione ?? minore della larghezza della finestra per?? bisogna sommare la larghezza dell'oggetto giocatore se no uscira fuori
            this.oggettoGiocatore.setX(posizione);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.oggettoGiocatore.spara();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
    
    //Getter and Setters
    public int getLarghezza() {
        return larghezza;
    }

    public int getAltezza() {
        return altezza;
    }
}

/*
TEORIE=>
    BufferStrategy: The main reason for using a BufferStrategy is because you want to take control of the painting process (active painting) away from Swing's painting algorithm (which is passive)
    MouseMotionListener: Ho usato questo tipo di dato astratto perch?? rispetto a MouseListener esso ci vede la posizione in tempo reale del mouse e ce lo monitora (controlla) soltanto all'interno del frame (il nostro JFrame) quindi in pratica, monitorandolo costantemente, non appena il giocatore clicca sul frame andremo a prendere questo punto dove il mouse si trova ed assegnarlo al giocatore 
                         Da Oracle = "Possiamo implementare un'interfaccia MouseListener quando il mouse ?? stabile durante la gestione dell'evento del mouse mentre possiamo implementare un'interfaccia MouseMotionListener  quando il mouse ?? in movimento durante la gestione dell'evento del mouse."
*/