/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avc9wbstopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Chavakula
 */
public class AnalogStopwatch {
    
    private double tickTimeInSeconds = 0.01;
    private double angleDeltaPerSecond = 6.00;
    private double secondsElapsed = 0.00;
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    
    private Timeline timeline;
    private KeyFrame keyFrame;
  
    public AnalogStopwatch(){
        setupUI();
        setupTimer();
    }
    
    public void setupUI() {
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        rootContainer.getChildren().addAll(dialImageView, handImageView);   
}
    
    public void setupTimer() {
       boolean running = false;
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
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
    
    private void update() {
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSecond;
        handImageView.setRotate(rotation);
    }
    
    public Parent getRootContainer(){
        return rootContainer;
    }
    
    public void start(){
        timeline.play();
    }
    
    public void setTickTimeInSeconds(double tickTimeInSeconds){
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    public void reset(){
        stop();
        secondsElapsed = 0;
        handImageView.setRotate(0);
    }
          
}