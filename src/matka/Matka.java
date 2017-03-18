/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matka;

import java.io.File;
import static java.lang.Character.toLowerCase;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
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


//TODO: predelat cesty k souborum na relativni
//TODO: kontrolovat existenci souboru
/**
 *
 * @author Jiri Vrbka
 */
public class Matka extends Application {
    
    private static final String MATRIX_VIDEO = Paths.VIDEO_BACKGROUND.getPath();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        MediaView viewer = prepareMediaView();
        Label labelText = new Label("");
        
        
        labelText.setFont(new Font("Courier", 45));
       // labelText.setTextFill(Color.RED);
        labelText.setAlignment(Pos.CENTER);
        labelText.setMaxWidth(Double.MAX_VALUE);
        labelText.setVisible(true);
        labelText.setWrapText(true);
        labelText.setTextAlignment(TextAlignment.JUSTIFY);
        labelText.getStyleClass().add("label");
        
        addLineTransitions(labelText);
        
        StackPane root = new StackPane();
        root.getChildren().add(viewer);
        root.getChildren().add(labelText);

        //set the Scene
        Scene scenes = new Scene(root, 500, 500, Color.BLACK);
        
        addCSS(scenes);
        
        stage.setScene(scenes);
        stage.setTitle("Matka");
        stage.setFullScreen(true);
        stage.show();   
    
    
    
    }
    
    private void addCSS(Scene scene){
        File f = new File(Paths.CSS_FILE.getPath());
        try {
            scene.getStylesheets().setAll(f.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Matka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addLineTransitions(Label labelText){
        HandlerFile handlerFile = new HandlerFile();
        List<SingleLine> lines = handlerFile.getLines();
        
        for(SingleLine line : lines){
            PauseTransition delay = new PauseTransition(Duration.seconds(line.getStarts()));
            delay.setOnFinished( event -> {
                labelText.setText(line.getLine());
                labelText.setOpacity(1);
            } );
            delay.play();
            
            PauseTransition delayDeleteText = new PauseTransition(Duration.seconds(line.getStarts()+line.getLatsts()));
            delayDeleteText.setOnFinished( event -> {
                makeLabelDisapear(labelText);
               
                
            });
            delayDeleteText.play();
            
        }
        
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
