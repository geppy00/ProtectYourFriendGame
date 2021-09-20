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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class CaricatoreImmagini {
    
    //Atttributi per caricare le immagini
    BufferedImage image;
    
    //Costruttori
    public CaricatoreImmagini() {
        
    }
    
    //Metodi
    public BufferedImage caricaImmagine(String posizione) {
        try {
            this.image = ImageIO.read(getClass().getResource(posizione)); //ci porta alla cartella src dove ho messo le immagini questo è possibile ottenerlo  grazie a getClass() che restituisce l'istanza della classe
        } catch (IOException ex) {
            System.err.print("Immagine non caricata correttamente, in posizione: "+posizione);
            Logger.getLogger(CaricatoreImmagini.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return image;
    }
    
    
    
}
