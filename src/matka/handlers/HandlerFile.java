/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka.handlers;

import matka.enums.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import matka.SingleLine;

/**
 * 
 * @author Jiri Vrbka
 */
public final class HandlerFile {

    private static final String PATH_TO_FILE = Paths.TXT_UPOZORNENI.getPath();
    private static File file;
    
    public HandlerFile(){
    }
    
    public List<SingleLine> loadFile(){
       file = new File(PATH_TO_FILE);
       List<SingleLine> linesFromFile = new ArrayList<>();
       
       try(BufferedReader reader = new BufferedReader(new FileReader(file))){
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
    
    
    
}
