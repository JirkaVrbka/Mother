/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka.handlers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * 
 * @author Jiri Vrbka
 */
public class HandlerLabel {

    /**
     * Set text into label and opacity to 1
     * @param label to be added to
     * @param text to set
     */
    public static final void LabelSetText(Label label, String text){
        label.setText(text);
        label.setOpacity(1);
    }
    
    /**
     * Create timeline that slowly makes label dissappear.
     * After dissappearing set label text to "".
     * Needs to be started manually!
     * @param label to make dissappear slowly
     * @return Timeline used. Ready to play
     */
    public static final Timeline LabelmakeDisapear(Label label){
        IntegerProperty opacityProcent = new SimpleIntegerProperty(100);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(75), event -> {
            opacityProcent.set(opacityProcent.get() - 5);
            label.setOpacity(opacityProcent.doubleValue()/(double)100);
        }));
        
        timeline.setCycleCount(20);
        timeline.setOnFinished(event -> label.setText(""));
        
        return timeline;        
        
    }
}
