package com.terminalmusicplayer.controller;

import com.terminalmusicplayer.models.Song;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
/**
 *
 * @author Sanma
 */
public class TMPController  {
    private Song song;
    private String playSoundMessage;
    
    public TMPController(String fileName, String format) { 
        this.song = new Song(fileName, format);
    }
    
    public String getFileName() {
        return this.song.getFileName();
    }
    
    public String getSongFormat() {
        return this.song.getFormat();
    }
    
    public String songToString() {
        return this.song.toString();
    }

    public String getPlaySoundMessage() {
        return this.playSoundMessage;
    }
    
    
    public synchronized void playSoundThread(final AudioInputStream ais) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {  
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
//                    for(int i = 0; i < 3; i ++) {
//                        System.out.println("ADIOS");
//                    }
                } catch(IOException ioe) {
                    System.out.println("ERROR: Input error.");
                } catch(LineUnavailableException lue) {
                    System.out.println("ERROR: Exception line.");
                }   
            }
        }).start(); 
    }
    
//    private String getFileNameWithPath(String filePath) {
//        int lastSeparatorPosition = filePath.lastIndexOf("\\");
//        return filePath.substring(lastSeparatorPosition + 1, filePath.length());
//    }
    
}
