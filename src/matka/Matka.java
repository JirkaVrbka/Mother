/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matka;

import matka.desing.HorDesign;
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
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import matka.desing.IDesign;
import matka.desing.JirkaDesign;

/**
 * 
 * @author Jiri Vrbka
 */
public class Matka extends Application {
    
    @Override
    public void start(Stage stage) throws MalformedURLException {
        
        StackPane root = new StackPane();
        IDesign desing = new JirkaDesign();
        
        desing.createParts(root);
        
        
        //set the Scene
        Scene scenes = new Scene(root, 500, 500, Color.BLACK);
        
        desing.addCSS(scenes);
        
        desing.show();
        desing.start();
//        DoubleProperty width = viewer.fitWidthProperty();
//        DoubleProperty height = viewer.fitHeightProperty();
//        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
//        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
        
        stage.setScene(scenes);
        stage.setTitle("Matka");
        stage.setFullScreen(true);
        
        
        
        stage.show();   
        
        System.out.println(stage.getHeight());
        desing.setProperties(scenes);
        
        
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

     
    
    

    
}


