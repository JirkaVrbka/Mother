/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import matka.enums.Paths;
import matka.handlers.HandlerFile;

/**
 * 
 * @author Jiri Vrbka
 */
public class HorDesign {

    private MediaView viewer;
    private Label labelText;
    private List<PauseTransition> transitions;
    
    
    
    public void createDesign(StackPane root) throws MalformedURLException{
        viewer = prepareMediaView();
        labelText = new Label("");
        labelText.getStyleClass().add("label");
        
        root.getChildren().add(viewer);
        root.getChildren().add(labelText);
        
        transitions = initTransitions(labelText);
        
        DoubleProperty width = viewer.fitWidthProperty();
        DoubleProperty height = viewer.fitHeightProperty();
        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
    }
    
    
    public void addCSS(Scene scene){
        File f = new File(Paths.CSS_FILE.getPath());
        try {
            scene.getStylesheets().setAll(f.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
        }
    }
    
    public void start(){
        transitions.forEach(t -> t.play());
    }
    
    public void stop(){
        transitions.forEach(t -> t.stop());
    }
    
    
    private List<PauseTransition> initTransitions(Label labelText){
        HandlerFile handlerFile = new HandlerFile();
        List<SingleLine> lines = handlerFile.loadFile();
        List<PauseTransition> tr = new ArrayList<>();
        
        for(SingleLine line : lines){
            PauseTransition delay = new PauseTransition(Duration.seconds(line.getStarts()));
            delay.setOnFinished( event -> {
                labelText.setText(line.getLine());
                labelText.setOpacity(1);
            } );
            tr.add(delay);
            
            PauseTransition delayDeleteText = new PauseTransition(Duration.seconds(line.getStarts()+line.getLatsts()));
            delayDeleteText.setOnFinished( event -> {
                makeLabelDisapear(labelText);
               
                
            });
            tr.add(delayDeleteText);
            
        }
        
        
        return tr;
    }
    
    private void makeLabelDisapear(Label label){
        IntegerProperty opacityProcent = new SimpleIntegerProperty(100);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(75), event -> {
            opacityProcent.set(opacityProcent.get() - 5);
            label.setOpacity(opacityProcent.doubleValue()/(double)100);
        }));
        
        timeline.setCycleCount(20);
        timeline.setOnFinished(event -> label.setText(""));
        timeline.play();
        
    }
    
    
    
    
    private MediaView prepareMediaView() throws MalformedURLException{
        File f = new File(Paths.VIDEO_BACKGROUND.getPath());


        //Converts media to string URL
        Media media = new Media(f.toURI().toURL().toString());
        MediaPlayer player = new MediaPlayer(media);
        MediaView viewer = new MediaView(player);

        //change width and height to fit video
        
        viewer.setPreserveRatio(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);

        //starts playing
        player.play();
        
        return viewer;
    }
    
}


