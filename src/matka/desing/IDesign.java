/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matka.desing;

import java.net.MalformedURLException;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import matka.SingleLine;

/**
 *
 * @author Jiri Vrbka
 */
public interface IDesign {
    
    void createParts(StackPane root);
    void setProperties(Scene scene);
    List<SingleLine> loadLines();
    void show();
    void hide();
    void start();
    void stop();
    void addCSS(Scene scene);
}
