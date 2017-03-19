/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matka.enums;

/**
 *
 * @author Jiri Vrbka
 */
public enum Paths {
    VIDEO_BACKGROUND("./Nastavitelne veci/video_pozadi.mp4"),
    CSS_FILE("src/matka/css/styles.css"),
    TXT_UPOZORNENI("./Nastavitelne veci/upozorneni.txt");
    
    private final String path;
    
    private Paths(String path){
        this.path = path;
    }
    
    public String getPath(){
        return path;
    }
}
