/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liracs.view.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Nelore
 */
public class SaveAudio {
    private File file;
    private String raiz;
    private String name;
    
    public SaveAudio(){
        this.raiz = "C:\\Users\\Nelore\\Documents\\NetBeansProjects1\\3 ano\\Tcc\\Liracs";
        file = null;
    }
    
    public void SaveToFile(AudioInputStream ais) {
        try {
            ais.reset();
        } catch (Exception e) { 
            System.out.println("Unable to reset stream " + e);
            
        }
        
        name = "LIRacsAUDIO"+UUID.randomUUID().toString()+".wav";

        File file = new File(raiz+"/"+name);
        try {
            if (AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file) == -1) {
                throw new IOException("Problems writing to file");
            }
            this.setFile(file);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public File getFile() {
        return file;
    }
    private void setFile(File file) {
        this.file = file;
    }
    
}
