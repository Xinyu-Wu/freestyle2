/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author wuxinyu
 */
public class FreestylePictureServer {
    public static int num = 0;
    public static int users = 0;
    public static BufferedImage image;
    
    public static ServerSocket serverSpeak;
    public static ServerSocket serverListen;
    
    public static class ServerReceiveThread extends Thread{
        
        public ServerReceiveThread(int port){
            try {
                serverListen = new ServerSocket(port);
            } catch (IOException ex) {
                Logger.getLogger(FreestylePictureServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run(){
            while (true) {   
                try {
                    Socket clientUploadSocket = serverListen.accept();
                    InputStream is = clientUploadSocket.getInputStream();
                    image = ImageIO.read(is);
                    ImageIO.write(image, "jpg", new File("temp.jpg"));
                    clientUploadSocket.close();
                } catch (IOException ex) {
//                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static class ServerDispatchThread extends Thread{
        public ServerDispatchThread(int port){
            try {
                serverSpeak = new ServerSocket(port);
            } catch (IOException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run(){
            while(true){
                try {
                    Socket clientDownloadSocket = serverSpeak.accept();
                    OutputStream os = clientDownloadSocket.getOutputStream();
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    BufferedImage tempImage = ImageIO.read(new File("temp.jpg"));
                    System.out.println(tempImage.getHeight() * tempImage.getWidth());
                    if(tempImage.getHeight() * tempImage.getWidth() != 0){
                        ImageIO.write(tempImage, "jpg", output);
                        os.write(output.toByteArray());
                        os.flush();
                    }
                    clientDownloadSocket.close();
                } catch (Exception e) {
                }
            }
        }
    }
    
    
    public static void main(String[] args) throws InterruptedException{
        while(true){
            try {
                ServerReceiveThread receiveThread = new ServerReceiveThread(8001);
                receiveThread.start();
                Thread.sleep(1000);
                serverListen.close();
                
                ServerDispatchThread dispatchThread = new ServerDispatchThread(8002);
                dispatchThread.start();
                Thread.sleep(1000);
                serverSpeak.close();
            } catch (IOException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
