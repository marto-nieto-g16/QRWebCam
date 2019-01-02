/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcam;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import qr.readQR;

/**
 * @web www.facebook.com/BytesCodes - 
 * @web www.facebook.com/marto.nieto.g16
 * @author Marto Nieto Guerrero
 * @email marto.nieto.g16@gmail.com
 * 
 * @name QR_WebCam
 * @version 1
 */

public class ExibeQuadro implements Runnable {

    VideoCaptura webCam;
    JLabel jlbQuadro;
    int cont =1;
            
    public ExibeQuadro(VideoCaptura cam, JLabel label){
        this.webCam = cam;
        this.jlbQuadro = label;
    }
    
    @Override
    public void run() {
        while(webCam.video.isOpened()){
            Icon icon = new ImageIcon(webCam.capturaQuadroBufferedImage());
            this.jlbQuadro.setIcon(icon);
            this.jlbQuadro.repaint();
            
            try{
              jfmPrincipal.campo_QR.setText(readQR.readQRs("QR.png"));  
            }catch(NullPointerException ex){}
            
            
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExibeQuadro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
