package com.terminalmusicplayer.view;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import com.terminalmusicplayer.controller.TMPController;
import javax.sound.sampled.AudioInputStream; // flujo de entrada de audio.
import javax.sound.sampled.AudioSystem;
//La clase AudioSystem actúa como punto de entrada a los recursos del sistema de audio muestreado. 
//Esta clase le permite consultar y acceder a los mezcladores que están instalados en el sistema. 

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Sanma
 */
public class TerminalMusicPlayer {
    public static final String CANCEL_OPERATION = "OPERATIONCANCELLED";

    public static void main(String[] args) {
        String fileName = "";
        File fileSong = null;
        System.out.println("SANMA MUSIC PLAYER!");
        JFileChooser openFileDialog = new JFileChooser();
        do { // Control de entrada de datos.
            if(openFileDialog.showOpenDialog(openFileDialog) != 1) {
                fileSong = openFileDialog.getSelectedFile();
                fileName = fileSong.getName();
                if(!fileSong.exists()) {
                    JOptionPane.showMessageDialog(openFileDialog, "ERROR: The file doesn't exists.", "ERROR", 0);
                    //En el método showMessageDialog(), en el parámetro "parentComponent" se le puede poner un null y también funciona 
                    //(En el caso de que no haya un componente "padre" como aquí es el caso del openFileDialog).
                    fileName = "";
                } else if(!fileName.endsWith(".wav")) { 
                    JOptionPane.showMessageDialog(openFileDialog, "ERROR: Incorrect format.", "ERROR", 0);
                    fileName = "";
                }
            } else {
                JOptionPane.showMessageDialog(openFileDialog, "Operation cancelled.", "Operation cancelled", 0);
                fileName = ""; 
                int option = JOptionPane.showConfirmDialog(openFileDialog, "Do you want to close the program?", "Select an Option", JOptionPane.OK_CANCEL_OPTION);
                if(option == 0) { fileName = CANCEL_OPERATION; }
            } 
        } while(!fileName.endsWith(".wav") && !fileName.equals(CANCEL_OPERATION));
        if(!fileName.equals(CANCEL_OPERATION)) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(fileSong.getAbsoluteFile());
                TMPController controller = new TMPController(fileName, ais.getFormat().toString());
                controller.playSoundThread(ais);
                // AL HACER EL TEST, PRIMERO SALE EL "HOLA" Y DESPUÉS EL "ADIÓS" 
                //(QUE ES EL QUE ESTÁ EN EL HILO LLAMADO EN LA LÍNEA controller.playSoundThread(ais)),
                // ESTO DEMUESTRA QUE EL THREAD FUNCIONA, SI NO PRIMERO IRÍA EL "ADIOS" Y DESPUÉS EL "HOLA".
    //            for(int i = 0; i < 3; i ++) { 
    //                System.out.println("HOLA");
    //            }
            } catch(UnsupportedAudioFileException uafe) {
                System.out.println("ERROR: Unsupported song file.");        
            }   catch(IOException ioe) {
                System.out.println("ERROR: Input error.");
            }   
        } else {
            System.out.println("SE HA CERRADO EL PROGRAMA");
        }      
    }
}
