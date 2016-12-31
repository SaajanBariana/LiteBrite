/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS151.HW3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author narayan
 */
public class LiteBrite extends Application 
{
    
    @Override
    public void start(final Stage stage) throws Exception 
    {
        int rows = 50;
        int columns = 50;

        stage.setTitle("Enjoy your game");
        
        ColorPicker colorPicker = new ColorPicker();	//create a new ColorPicker
        colorPicker.setValue(Color.WHITE);				//initialize the color as white
        colorPicker.setMinHeight(30);					//sets the minimum height to make the box bigger
        
        GridPane grid = new GridPane();
        grid.getStyleClass().add("game-grid");
        
        VBox v = new VBox();							//create a VBox to store the ColorPicker and grid into
        v.setSpacing(5);								//create a space of 10 between the ColorPicker and the grid
        v.setPadding(new Insets(0, 10, 0, 5));			//sets the padding around the VBox
        v.getChildren().addAll(colorPicker, grid);		//adds the ColorPicker and grid into the VBox
        
        Scene scene = new Scene(v, (columns * 10)+30, (rows * 10)+50);
        for(int i = 0; i < columns; i++) 
        {
            ColumnConstraints column = new ColumnConstraints(10);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < rows; i++) 
        {
            RowConstraints row = new RowConstraints(10);
            grid.getRowConstraints().add(row);
        }

        for (int i = 0; i < columns; i++) 
        {
            for (int j = 0; j < rows; j++) 
            {
                Pane pane = new Pane();
                pane.setOnMouseReleased(e -> {
                    pane.getChildren().add(Anims.getAtoms(1, colorPicker));
                });
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                grid.add(pane, i, j);
            }
        }
        
        scene.getStylesheets().add(LiteBrite.class.getResource("game.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static class Anims 
    {

        public static Node getAtoms(final int number, ColorPicker p) 
        {        	
        	Rectangle rect = new Rectangle(0, 0, 10, 10);	//create a new rectangle
        	rect.setFill(p.getValue());						//fill that rectangle with the current value of the colorPicker
            return rect;
        }
    }
    
    public static void main(final String[] arguments) 
    {
        Application.launch(arguments);
    }
    
}
