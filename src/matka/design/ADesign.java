/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka.design;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import matka.handlers.HandlerFile;
import matka.SingleLine;
import matka.handlers.HandlerLabel;

/**
 * 
 * @author Jiri Vrbka
 */
public abstract class ADesign implements IDesign{
    

    List<PauseTransition> transitions;
    
    public ADesign() {
        
        checkMandatories();
        
        
    }
    
    
    
    
    public void loadDesing(Stage stage){
        
       // transitions = loadTransitions(labelText);
        
        
    }
    
    
    public final void playTransitions(){
        transitions.forEach((tr) -> {
            tr.play();
        });
    }
    
    /**
     * Create transitions in ready state for labels.
     * Loaded from SingleLine files.
     * @param labelText where lines should be shown
     * @return list of transitions ready to play
     */
    private List<PauseTransition> loadTransitions(Label labelText){
        List<PauseTransition> transition = new ArrayList<>();
        
        for(SingleLine line : HandlerFile.getLines()){
            //Show text
            PauseTransition delay = new PauseTransition(Duration.seconds(line.getStarts()));
            delay.setOnFinished( event -> {
                HandlerLabel.LabelSetText(labelText, line.getLine());
            } );
            transition.add(delay);
            
            //make text dissappear after while
            PauseTransition delayDeleteText = new PauseTransition(Duration.seconds(line.getStarts()+line.getLatsts()));
            delayDeleteText.setOnFinished( event -> {
                HandlerLabel.LabelmakeDisapear(labelText).play();
                
            });
            transition.add(delayDeleteText);
            
        }
        
        return transition;
        
    }
    
    /**
     * Check if everything needed to work is OK
     * @return true if OK
     */
    @Override
    public boolean checkMandatories(){
        return HandlerFile.existFiles();
    }
    
    
    
}
