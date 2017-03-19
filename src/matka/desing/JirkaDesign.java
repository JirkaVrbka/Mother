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
public class JirkaDesign extends ADesign implements IDesign{
    private static final double LABEL_OPACITY_LVL1 = 1;
    private static final double LABEL_OPACITY_LVL2 = 0.7;
    private static final double LABEL_OPACITY_LVL3 = 0.4;
    private static final double LABEL_OPACITY_LVL4 = 0.2;
    private static final double LABEL_OPACITY_LVL5 = 0.1;
    
    private Label labelLvl1 = new Label("Ahojky");
    private Label labelLvl2 = new Label("dva");
    private Label labelLvl3 = new Label("tri");
    private Label labelLvl4 = new Label("ctyri");
    private Label labelLvl5 = new Label("pet");
    private MediaView viewer = prepareMediaView(); 
    
    
  
    /**
     * Creates a sets components to root
     * @param root to be added in
     */
    @Override
    public void createParts(StackPane root) {
        labelLvl1.getStyleClass().add("labelLvl");
        labelLvl2.getStyleClass().add("labelLvl");
        labelLvl3.getStyleClass().add("labelLvl");
        labelLvl4.getStyleClass().add("labelLvl");
        labelLvl5.getStyleClass().add("labelLvl");
        
        labelLvl1.getStyleClass().add("labelLvl1");
        labelLvl2.getStyleClass().add("labelLvl2");
        labelLvl3.getStyleClass().add("labelLvl3");
        labelLvl4.getStyleClass().add("labelLvl4");
        labelLvl5.getStyleClass().add("labelLvl5");
        
        labelLvl1.setOpacity(LABEL_OPACITY_LVL1);
        labelLvl2.setOpacity(LABEL_OPACITY_LVL2);
        labelLvl3.setOpacity(LABEL_OPACITY_LVL3);
        labelLvl4.setOpacity(LABEL_OPACITY_LVL4);
        labelLvl5.setOpacity(LABEL_OPACITY_LVL5);
        
        root.getChildren().add(viewer);
        root.getChildren().add(labelLvl1);
        root.getChildren().add(labelLvl2);
        root.getChildren().add(labelLvl3);
        root.getChildren().add(labelLvl4);
        root.getChildren().add(labelLvl5);
        
        StackPane.setAlignment(labelLvl1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(labelLvl2, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(labelLvl3, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(labelLvl4, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(labelLvl5, Pos.BOTTOM_LEFT);
        
        
        
    }

   
    /**
     * Show all components from this desing
     */
    @Override
    public void show() {
        labelLvl1.setVisible(true);
        labelLvl2.setVisible(true);
        labelLvl3.setVisible(true);
        labelLvl4.setVisible(true);
        labelLvl5.setVisible(true);
        
        viewer.setVisible(true);
    }

    /**
     * Hide all components from this design
     */
    @Override
    public void hide() {
        labelLvl1.setVisible(false);
        labelLvl2.setVisible(false);
        labelLvl3.setVisible(false);
        labelLvl4.setVisible(false);
        labelLvl5.setVisible(false);
        
        viewer.setVisible(false);
    }

    /**
     * Start running scripts and background video
     */
    @Override
    public void start() {
        viewer.getMediaPlayer().play();
        initTransitions();
    }

    /**
     * Pause running scripts and background video
     */
    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private List<PauseTransition> initTransitions(){
        HandlerFile handlerFile = new HandlerFile();
        List<SingleLine> lines = handlerFile.loadFile();
        List<PauseTransition> tr = new ArrayList<>();
        
        for(SingleLine line : lines){
            PauseTransition delay = new PauseTransition(Duration.seconds(line.getStarts()));
            delay.setOnFinished( event -> {
                moveTextUpFromLvl1();
                labelLvl1.setText("");
                writeTextMatriX(labelLvl1, line.getLine());
                //labelLvl1.setText(line.getLine());
                labelLvl1.setOpacity(1);
            } );
            tr.add(delay);
            delay.play();
            
            PauseTransition delayDeleteText = new PauseTransition(Duration.seconds(line.getStarts()+line.getLatsts()));
            delayDeleteText.setOnFinished( event -> {
                //makeLabelDisapear(labelText);
               
                
            });
            tr.add(delayDeleteText);
            
        }
        
        
        return tr;
    }
    
    /**
     * Moves texts up form lvl 2 to lvl 5
     */
    private void moveTextUpFromLvl1(){
        labelLvl5.setText(labelLvl4.getText());
        labelLvl4.setText(labelLvl3.getText());
        labelLvl3.setText(labelLvl2.getText());
        labelLvl2.setText(labelLvl1.getText());
    }
    
    
      private final void writeTextMatriX(Label label, String text){
        
        final char[] charText = text.toCharArray();
        final int o = 1;
        final Label labelToWrite = label;
        
        
        
        
        
        

        
        Timeline toPlay;
        Timeline currentTimeline = new Timeline(new KeyFrame(Duration.millis(0),event -> 
                {
                    //none
                }));
        currentTimeline.setCycleCount(1);
        
        toPlay = currentTimeline;
        
        
        for(IntegerProperty i = new SimpleIntegerProperty(0) ; i.get() < text.length(); i.add(1)){
            
            char finalLetter = text.charAt(i.get());
            final IntegerProperty counter = new SimpleIntegerProperty(0);
            String oldText =  label.getText();
            
            Timeline newTimeline = new Timeline(
            new KeyFrame(
                Duration.millis(50) ,
                event -> {
                    char a = (char) ('A' + (char)counter.getValue().intValue());
                    label.setText(oldText + a);
                    counter.set(i.get() + 1);
                    
                } 
                )
            );
            newTimeline.setCycleCount(finalLetter - 'A');
            
            
            
            currentTimeline.setOnFinished(event -> { 
                newTimeline.play();
            });
            
            currentTimeline = newTimeline;
        }
        
        
        toPlay.play();
        
        
        
        
        
        
        
        
        
        
        /*

        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.seconds(2),
                event -> {
                    
                    writeTextMatriXEvent(label, charText[i.getValue()]);
                    
                    i.set(i.get() + 1);
                    
                    //label.setText("Elapsed time: " + i.get() + " seconds");
                } 
            )
        );
        timeline.setCycleCount(charText.length);
        timeline.play();

        */
        
    }
    
    private void writeTextMatriXEvent(Label label,char finalLetter){
        String text = label.getText();
        
        final IntegerProperty i = new SimpleIntegerProperty(0);
        
        Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.millis(50) ,
                event -> {
                    char a = (char) ('A' + (char)i.getValue().intValue());
                    label.setText(text + a);
                    
                    i.set(i.get() + 1);
                    
                    //label.setText("Elapsed time: " + i.get() + " seconds");
                } 
            )
        );
        timeline.setCycleCount(finalLetter - 'A');
        timeline.play();
        
        
        if(finalLetter == ' '){
            label.setText(text + finalLetter);
        }
        
    }

    /**
     * Some properties needs to be set after initialize
     * - bind
     * - label max width
     * 
     * @param scene get important info from
     */
    @Override
    public void setProperties(Scene scene) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        labelLvl1.setMaxWidth(screenBounds.getWidth());
        
        DoubleProperty width = viewer.fitWidthProperty();
        DoubleProperty height = viewer.fitHeightProperty();
        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
    }

}
