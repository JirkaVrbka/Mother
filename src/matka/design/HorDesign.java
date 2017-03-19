/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka.design;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import matka.SingleLine;
import matka.handlers.HandlerFile;
import matka.handlers.HandlerLabel;

/**
 * 
 * @author Jiri Vrbka
 */
public class HorDesign {

    public HorDesign() {
        MediaView viewer = prepareMediaView();
        Label labelText = new Label("");
        
        
        labelText.setFont(new Font("Courier", 45));
        labelText.setAlignment(Pos.CENTER);
        labelText.setMaxWidth(Double.MAX_VALUE);
        labelText.setVisible(true);
        labelText.setWrapText(true);
        labelText.setTextAlignment(TextAlignment.JUSTIFY);
        labelText.getStyleClass().add("label");
        
        //addLineTransitions(labelText);
        
        
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
    
    
    private MediaView prepareMediaView() throws MalformedURLException{
        File f = new File(MATRIX_VIDEO);


        //Converts media to string URL
        Media media = new Media(f.toURI().toURL().toString());
        MediaPlayer player = new MediaPlayer(media);
        MediaView viewer = new MediaView(player);

        //change width and height to fit video
        DoubleProperty width = viewer.fitWidthProperty();
        DoubleProperty height = viewer.fitHeightProperty();
        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
        viewer.setPreserveRatio(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);

        //starts playing
        player.play();
        
        return viewer;
    }

    
    
}
