/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matka;

/**
 *
 * @author Jiri Vrbka
 */
public enum Paths {
    VIDEO_MATRIX_CODE("./Nastavitelne veci/Matrix Raining Code.mp4"),
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
