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
public class SingleLine {
    private int starts;
    private String line;
    private int lasts;

    
    public SingleLine(String fullLine){
        
        String[] parts = fullLine.split(";");
        
        starts = timeToSeconds(parts[0]);
        line = lineToString(parts[1]);
        lasts = Integer.parseInt(parts[2].replace("\"",""));
        
    }
    
    private String lineToString(String Oneline){
        String pes = Oneline.replaceAll("\"", "");
        String pes2 = pes.replace("\\n", "\n");
        
        return pes2;
        
    }
    
    private int timeToSeconds(String time){
        int hour,minute, second;
        
        time = time.replaceAll("\"", "");
        String parts[] = time.split(":");
        
        hour = Integer.parseInt(parts[0]);
        minute = Integer.parseInt(parts[1]);
        second = Integer.parseInt(parts[2]);
        
        return second + minute*60 + hour*3600;
    }
    
    public int getStarts() {
        return starts;
    }
//
//    public void setStarts(int starts) {
//        this.starts = starts;
//    }

    public String getLine() {
        return line;
    }
    
    public int getLatsts(){
        return lasts;
    }
//
//    public void setLine(String line) {
//        this.line = line;
//    }
    
    
    
}
