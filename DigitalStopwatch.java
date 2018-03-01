/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avc9wbstopwatch;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 *
 * @author Chavakula
 */
public class DigitalStopwatch {
    
    private double tickTimeInSeconds = 1.0;
    private int secondsElapsed = 0;
    private int minutesElapsed = 0;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private StackPane root;
    private Label digitalClock;
    
    
    public DigitalStopwatch(){
        setupUI();
        setupTimer();
    }
    
    public void setupUI(){
        root = new StackPane();
        digitalClock = new Label();
        digitalClock.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        root.getChildren().add(digitalClock);
    }
    
    public void setupTimer(){
        boolean running = false;
        if(timeline != null){
            if(timeline.getStatus() == Status.RUNNING){
                running = true;
                timeline.stop();
            }
        }
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        if(running){
            timeline.play();
        }
        
    }
    
    public void update(){
        secondsElapsed += tickTimeInSeconds;
        if(secondsElapsed > 59){
            minutesElapsed += 1;
            secondsElapsed = 0;
            digitalClock.setText(String.format("%02d", minutesElapsed) + ":" + String.format("%02d", secondsElapsed));
        }
        else{
            digitalClock.setText(String.format("%02d", minutesElapsed) + ":" + String.format("%02d", secondsElapsed));
        }  
    }
    
    public Parent getRoot(){
        return root;
    }
    
    public void start(){
        timeline.play();
    }
    
    public void stop(){
        timeline.stop();
    }
    
    public void reset() {
        stop();
        secondsElapsed = 0;
        minutesElapsed = 0;
        digitalClock.setText("00:00");
    }
             
}
