/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka;

import matka.handlers.HandlerFile;
import matka.enums.Paths;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Jiri Vrbka
 */
public class Matka extends Application {
    
    @Override
    public void start(Stage stage) throws MalformedURLException {
        
        StackPane root = new StackPane();
        HorDesign hd = new HorDesign();
        
        hd.createDesign(root);
        
        //set the Scene
        Scene scenes = new Scene(root, 500, 500, Color.BLACK);
        
        hd.addCSS(scenes);
        
//        DoubleProperty width = viewer.fitWidthProperty();
//        DoubleProperty height = viewer.fitHeightProperty();
//        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
//        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
        
        stage.setScene(scenes);
        stage.setTitle("Matka");
        stage.setFullScreen(true);
        
        System.out.println(stage.getHeight());
        
        stage.show();   
    
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

     
    
    
    
    private final void writeTextMatriX(Label label, String text){
        
        final char[] charText = text.toCharArray();
        final int o = 1;
        final Label labelToWrite = label;
//        
//        for(int i = 0; i < charText.length; i++){
//            final char c = charText[i];
//            //Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> writeTextMatriXEvent(labelToWrite ,c)));
//            PauseTransition delay = new PauseTransition(Duration.seconds(1));
//            delay.setOnFinished( event -> writeTextMatriXEvent(labelToWrite ,c) );
//            delay.play();
//        }
//        
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
    
    

    
}


