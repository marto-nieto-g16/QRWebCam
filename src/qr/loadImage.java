/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr;

/**
 * @web www.facebook.com/BytesCodes - 
 * @web www.facebook.com/marto.nieto.g16
 * @author Marto Nieto Guerrero
 * @email marto.nieto.g16@gmail.com
 * 
 * @name QR_WebCam
 * @version 1
 */

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.ImageIcon;

    public class loadImage extends JFrame {
        JLabel etiqueta = new JLabel();
        
        public void loadImageQR(ImageIcon imagen){
            this.setUndecorated(true);
            etiqueta.setIcon(imagen);
            etiqueta.setHorizontalAlignment(JLabel.CENTER);
            etiqueta.setVerticalAlignment(JLabel.CENTER);
            
            getContentPane().add(etiqueta);
            this.setIconImage(getIconImage());
            
            this.setSize(500, 500);
            this.setLocation(450, 60);
            this.setResizable(false);
            this.setBackground(new Color(0,0,0,0));
            this.setOpacity(0.6f);
         
           

    }
        
        @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resources/icon.png"));


        return retValue;
    }

}