/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import webcam.jfmPrincipal;

/**
 * @web www.facebook.com/BytesCodes - 
 * @web www.facebook.com/marto.nieto.g16
 * @author Marto Nieto Guerrero
 * @email marto.nieto.g16@gmail.com
 * 
 * @name QR_WebCam
 * @version 1
 */

public class readQR {
   static loadImage imgQR = new loadImage();
   static ImageIcon img = null;
    public static String readQRs(String pathname) {
        Result stringBarCode = null;
        InputStream qrInputStream = null;
        try {
            qrInputStream = new FileInputStream(pathname);
            BufferedImage qrBufferedImage = ImageIO.read(qrInputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(qrBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            stringBarCode = reader.decode(bitmap);
            jfmPrincipal.campo_QR.setText(stringBarCode.getText()); 
            if(new File("imgQR/"+stringBarCode.getText()+".png").exists()){
                img = new ImageIcon("imgQR/"+stringBarCode.getText()+".png");
                imgQR.loadImageQR(img);
                imgQR.show();
            }
        } catch ( IOException | NotFoundException | ChecksumException | FormatException   ex) {
            readFileImage(pathname);
       
        } finally {
            try {
                qrInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(readQR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringBarCode.getText();
    }
public static String readFileImage(String files){
    Result qrCodeResult = null;
    try {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(files)))));
        qrCodeResult = new MultiFormatReader().decode(binaryBitmap);             
        jfmPrincipal.campo_QR.setText(qrCodeResult.getText()); 
        if(new File("imgQR/"+qrCodeResult.getText()+".png").exists()){
            img = new ImageIcon("imgQR/"+qrCodeResult.getText()+".png");;
            imgQR.loadImageQR(img);
        }
    } catch (IOException | NotFoundException ex) {
        jfmPrincipal.campo_QR.setText("Codigo QR No Reconocible"); 
        imgQR.show(false);
            
        }
    return qrCodeResult.getText();
}
    
}