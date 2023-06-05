package com.terminalmusicplayer.models;

/**
 *
 * @author Sanma
 */
public class Song {
    private String fileName;
    private String format;

    public Song(String nameFile, String format) {
        this.fileName = nameFile;
        this.format = format;
    }

    // GETTERS AND SETTERS.
    public String getFileName() {
        return this.fileName;
    }
    
    public String getFormat() {
        return this.format;
    }
    
    @Override
    public String toString() {
        String output = "";
        output += "\n_____________________________________________________________\n";
        output += "-File name: " + fileName + "\n-Format: " + format + "\n";
        output += "_____________________________________________________________\n";
        return output;
    }
    
}
