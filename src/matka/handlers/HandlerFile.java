/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static matka.FileLogger.LOGGER;
import matka.Paths;
import matka.SingleLine;

/**
 * 
 * @author Jiri Vrbka
 */
public final class HandlerFile {

    public static final String PATH_BACKGROUND_VIDEO = Paths.VIDEO_BACKGROUND.getPath();
    public static final String PATH_LINES = Paths.TXT_UPOZORNENI.getPath();
    public static final String PATH_CSS = Paths.CSS_FILE.getPath();
    
    
    public static File fileBackground_video = new File(PATH_BACKGROUND_VIDEO); 
    public static File fileLines = new File(PATH_LINES);
    public static File fileCss = new File(PATH_CSS);

    
    private static final List<SingleLine> lines = loadFile();
    
    
    
    
    public static List<SingleLine> getLines(){
        return lines;
    }
    
    public static List<SingleLine> loadFile(){
       
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
    
    
    /**
     * Checks if all mandatory files exist
     * @return true if exist
     */
    public static boolean existFiles(){
        
        if(fileBackground_video.exists() && ! fileBackground_video.isDirectory()){
            LOGGER.info("Background video OK");
        }else{
            LOGGER.severe("No background video to load!");
            return false;
        }
        
        if(fileLines.exists() && ! fileLines.isDirectory()){
            LOGGER.info("File with lines is OK");
        }else{
            LOGGER.severe("No file with lines! There will be no text!");
            return false;
            
        }
        
        if(fileCss.exists() && ! fileCss.isDirectory()){
            LOGGER.info("File with css is OK");
        }else{
            LOGGER.severe("No file with CSS! Mother will be ugly!");
            return false;
        }
        
        return true;
    }
    
    
    
}
