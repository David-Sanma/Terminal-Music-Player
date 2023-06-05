package com.terminalmusicplayer.controller;
import com.terminalmusicplayer.models.Song;
/**
 *
 * @author Sanma
 */
public class TMPController {
    private Song song;
    
    public TMPController(String path, String format) { 
        this.song = new Song(getFileNameWithPath(path), format);
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
    
    private String getFileNameWithPath(String filePath) {
        int lastSeparatorPosition = filePath.lastIndexOf("\\");
        return filePath.substring(lastSeparatorPosition + 1, filePath.length());
    }
    
}
