/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * @author Jiri Vrbka
 */
public class FileLogger {
    public static final Logger LOGGER = initFileLogger();
    
    public static Logger initFileLogger(){
        Logger logger = Logger.getLogger("Mother file logger");  
        FileHandler fh;  

        
        try {  
            createLogDirectory();
            String fileName = createLogFile();

           


            // This block configure the logger with handler and formatter  
            fh = new FileHandler("./Logs/"+fileName);  
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  

        } catch (SecurityException | IOException e) { 
            System.out.println(e.toString());
        }  

        logger.info("Logger established"); 


        return logger;
    }
    
    /**
     * Create directory "Logs" if not exist
     * @return true if now directory "Logs" exist
     */
    private static boolean createLogDirectory(){
         File loggDirectory = new File("Logs");

            
            if (!loggDirectory.exists()) {
                System.out.println("creating directory: " + loggDirectory.getName());

                try{
                    loggDirectory.mkdir();
                } 
                catch(SecurityException e){
                    System.out.println(e.toString());
                }        
                
            }
            
        return loggDirectory.exists();

    }
    
    /**
     * Creates new log file
     * @return Name of it
     */
    private static String createLogFile(){
        String fileName = "Log_"+new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Timestamp(System.currentTimeMillis()))+ ".log";
        
        File logFile = new File(".Logs/"+fileName);
        try {
            System.out.println("Creating file "+fileName);
            logFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        return fileName;
    }
}
