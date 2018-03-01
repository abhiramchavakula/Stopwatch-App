/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avc9wbstopwatch;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
/**
 *
 * @author Chavakula
 */
public class Avc9wbStopwatch extends Application {
    private String appName = "Java Stopwatch";

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        
        AnalogStopwatch analogStopwatch = new AnalogStopwatch();
        analogStopwatch.setTickTimeInSeconds(1.0);
       
        DigitalStopwatch digitalStopwatch = new DigitalStopwatch();
        
        Button btn = new Button();
        btn.setText("Stop");
        btn.setTextFill(Color.RED);
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        btn.setOnAction((ActionEvent event)->{
            analogStopwatch.stop();
            digitalStopwatch.stop();
        });
        
        Button btn1 = new Button();
        btn1.setText("Start");
        btn1.setTextFill(Color.GREEN);
        btn1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        btn1.setOnAction((ActionEvent event)->{
            analogStopwatch.start();
            digitalStopwatch.start();
        });
        
        Button btn2 =  new Button();
        btn2.setText("Reset");
        btn2.setTextFill(Color.BLUE);
        btn2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        btn2.setOnAction((ActionEvent event)->{
            analogStopwatch.reset();
            digitalStopwatch.reset();
        });
       
        root.add(analogStopwatch.getRootContainer(), 2, 0);
        
        root.add(btn1, 0, 0);
        root.add(btn, 3, 0);
        root.add(btn2, 4, 0);
        
        root.add(digitalStopwatch.getRoot(), 2, 1);
        
        root.setHgap(5);
        root.setVgap(15);
        root.setAlignment(Pos.CENTER);
       
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
