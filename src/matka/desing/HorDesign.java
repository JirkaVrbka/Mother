/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka.desing;

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
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.util.Duration;
import matka.SingleLine;
import matka.handlers.HandlerFile;

/**
 * 
 * @author Jiri Vrbka
 */
public class HorDesign extends ADesign implements IDesign{

    private MediaView viewer = prepareMediaView();
    private Label labelText = new Label("");
    private List<PauseTransition> transitions;
    
    
    
    @Override
    public void createParts(StackPane root){
        
        labelText.getStyleClass().add("label");        
        
        root.getChildren().add(viewer);
        root.getChildren().add(labelText);
        
        transitions = initTransitions(labelText);
        
        DoubleProperty width = viewer.fitWidthProperty();
        DoubleProperty height = viewer.fitHeightProperty();
        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
        
        root.setAlignment(labelText, Pos.CENTER);
    }
    
    
    @Override
    public void start(){
        transitions.forEach(t -> t.play());
        viewer.getMediaPlayer().play();
    }
    
    @Override
    public void stop(){
        transitions.forEach(t -> t.stop());
        viewer.getMediaPlayer().pause();
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
    
    
    @Override
    public void show() {
        labelText.setVisible(true);
        viewer.setVisible(true);
    }

    @Override
    public void hide() {
        labelText.setVisible(false);
        viewer.setVisible(false);
    }

    @Override
    public void setProperties(Scene scene) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        labelText.setMaxWidth(screenBounds.getWidth());
    }

    
}


