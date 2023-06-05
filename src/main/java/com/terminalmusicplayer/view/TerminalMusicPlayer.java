package com.terminalmusicplayer.view;

import com.terminalmusicplayer.controller.TMPController;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream; // flujo de entrada de audio.
import javax.sound.sampled.AudioSystem;
//La clase AudioSystem actúa como punto de entrada a los recursos del sistema de audio muestreado. 
//Esta clase le permite consultar y acceder a los mezcladores que están instalados en el sistema. 

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 *
 * @author Sanma
 */
public class TerminalMusicPlayer {

    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        String path = "";
        File fileSong = null;
        System.out.println("SANMA MUSIC PLAYER!");

        while(path.equals("")) { // Control de entrada de datos.
            System.out.print("Your *.wav path file?: "); // SI SE PUEDEN LEER PONER MÁS TARDE MÁS FORMATOS.
            path = key.nextLine();  
            if(path.equals("")) {
                System.out.println("ERROR: Empty path.");           
            } else if(!path.endsWith(".wav")) { 
                path = ""; 
                System.out.println("ERROR: Incorrect format.");
            } else {
                fileSong = new File(path);
                if(!fileSong.exists()) {
                    path = "";
                    fileSong.delete();
                    System.out.println("ERROR: Path doesn't exists.");
                }  
            }  
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(fileSong.getAbsoluteFile());
            TMPController controller = new TMPController(path, ais.getFormat().toString());
            System.out.println(controller.getFileName());
            
            System.out.println(ais.getFrameLength());

//            Clip clip = AudioSystem.getClip();
//            clip.open(ais);
//            clip.start();

            } catch(UnsupportedAudioFileException uafe) {
                System.out.println("ERROR: Unsupported song file.");
            } catch(IOException ioe) {
                System.out.println("ERROR: Input error.");
            } //catch(LineUnavailableException lue) {
//                        System.out.println("ERROR: Exception line.");
//                    }
    }
}
