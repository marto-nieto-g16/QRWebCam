/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcam;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 * @web www.facebook.com/BytesCodes - 
 * @web www.facebook.com/marto.nieto.g16
 * @author Marto Nieto Guerrero
 * @email marto.nieto.g16@gmail.com
 * 
 * @name QR_WebCam
 * @version 1
 */

public class VideoCaptura {
    
    VideoCapture video;
    Mat imageMat = new Mat();
    BufferedImage imageBuffer;
    byte[] dat; 
    
    static String opencv_path = System.getProperty("user.dir");
    static File dir = new File("/java/x86/opencv_java249.dll");
    
    public VideoCaptura(){
        this.video = new VideoCapture();
        this.video.open(0);
        
    }
    
    public BufferedImage capturaQuadroBufferedImage(){
        this.video.read(this.imageMat); //Captura o quadro
        imageBuffer = this.matToBufferedImage(this.imageMat); //Converte para imageBuffer  
        org.opencv.highgui.Highgui.imwrite("QR.png", this.imageMat);
        
        return imageBuffer;
        
    }
    
    public Mat capturaQuadroMat(){
        this.video.read(this.imageMat); //Captura o quadro
        org.opencv.highgui.Highgui.imwrite("QR.png", this.imageMat);
        return imageMat;
    }
 
    public BufferedImage matToBufferedImage(Mat matrix) {  
     int cols = matrix.cols();  
     int rows = matrix.rows();  
     int elemSize = (int)matrix.elemSize();  
     byte[] data = new byte[cols * rows * elemSize];  
     int type;  
     matrix.get(0, 0, data);  
     switch (matrix.channels()) {  
       case 1:  
         type = BufferedImage.TYPE_BYTE_GRAY;  
         break;  
       case 3:  
         type = BufferedImage.TYPE_3BYTE_BGR;  
         // bgr to rgb  
         byte b;  
         for(int i=0; i<data.length; i=i+3) {  
           b = data[i];  
           data[i] = data[i+2];  
           data[i+2] = b;  
         }  
         break;  
       default:  
         return null;  
     }  
     BufferedImage image = new BufferedImage(cols, rows, type);  
     image.getRaster().setDataElements(0, 0, cols, rows, data);  
     
     return image;  
   }
    static{
        opencv_path = opencv_path + dir;
        if(new File(opencv_path).exists()){
            System.load(opencv_path);
        }else{
            JOptionPane.showMessageDialog(null, "Archivo No Encontrado : \n"+ opencv_path , "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }   
}