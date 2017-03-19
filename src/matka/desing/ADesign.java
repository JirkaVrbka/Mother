/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka.desing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import matka.SingleLine;
import matka.enums.Paths;

/**
 * 
 * @author Jiri Vrbka
 */
public abstract class ADesign implements IDesign{

    
    @Override
    public List<SingleLine> loadLines() {
       File fileLines = new File(Paths.TXT_UPOZORNENI.getPath());
       List<SingleLine> linesFromFile = new ArrayList<>();
       
       try(BufferedReader reader = new BufferedReader(new FileReader(fileLines))){
           
           String line;
           while((line = reader.readLine()) != null){
               if(!line.startsWith("//")){
                   linesFromFile.add(new SingleLine(line));
               }
           }
           
       }catch(IOException ex){
           System.out.println(ex.toString());
       }
       
       return linesFromFile;
    }


    @Override
    public void addCSS(Scene scene) {
        File fileCSS = new File(Paths.CSS_FILE.getPath());
        try {
            scene.getStylesheets().setAll(fileCSS.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    
    protected MediaView prepareMediaView() {
        try {
            File f = new File(Paths.VIDEO_BACKGROUND.getPath());
            
            
            //Converts media to string URL
            Media media = new Media(f.toURI().toURL().toString());
            MediaPlayer player = new MediaPlayer(media);
            MediaView viewer = new MediaView(player);
            
            //change width and height to fit video
            
            viewer.setPreserveRatio(true);
            player.setCycleCount(MediaPlayer.INDEFINITE);
           
            return viewer;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ADesign.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
