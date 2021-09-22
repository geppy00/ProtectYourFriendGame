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
package controller;


public class FpsController {
    
    //Attributi per controllare ed impostare gli fps
    private double timePerUpdate; //tempo massimo consentito per eseguire metodo disegna() 
    private double delta;
    private long now;
    private long lastTime;
    
    //Costruttori
    public FpsController() {
        
    }
    
    public FpsController(int fps) {
        this.timePerUpdate = 1000000000L / fps;
        this.delta = 0;
        this.lastTime = System.nanoTime();
    }
    
    //Metodi
    public boolean checkDelta() {
        this.now = System.nanoTime();
        this.delta = (this.now - this.lastTime) / this.timePerUpdate;
        
        if(this.delta >= 1){
            this.lastTime = this.now;
            return true;
        }
        else
            return false;
    }
    
}
