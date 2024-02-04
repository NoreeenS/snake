package com.noreen.snake;

import javax.swing.JFrame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

import java.util.Random;

public class SnakeApp extends Application{
	
	//rectangle
	Rectangle rectangle; 
	
	//button
	Button buttonHaut;
	Button buttonBas;
	Button buttonGauche;
	Button buttonDroite;
	
	//food
	Rectangle pixel;
	
	//pixel
	public static void main(String args[]){
		launch(args);
	}

	@SuppressWarnings("incomplete-switch")
	@Override 
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Snake fx");
		
		//2. utiliser la classe rectangle pour représenter un segment du serpent
		rectangle = new Rectangle(10, 10, Color.GREEN);
		
		//5. Ajouter 4 boutons dans la scene (haut, bas, gauche, droite)
		Button buttonHaut = new Button();
		buttonHaut.setText("Haut");
		
		Button buttonBas = new Button();
		buttonBas.setText("Bas");
		
		Button buttonGauche = new Button();
		buttonGauche.setText("Gauche");
		
		Button buttonDroite = new Button();
		buttonDroite.setText("Droit");
		
		//3. Pour le layout on va utiliser un StackPane
		StackPane layout = new StackPane();

		//4. Afficher un carré noir dans votre scene.
		layout.setStyle("-fx-background-color: black;");
		
        // Create and add the food
        pixel = createPixel();
        layout.getChildren().add(pixel);
		
		layout.getChildren().add(rectangle);
//		layout.getChildren().add(buttonHaut);	
//		layout.getChildren().add(buttonBas);	
//		layout.getChildren().add(buttonGauche);	
//		layout.getChildren().add(buttonDroite);	
		
		Scene scene = new Scene(layout, 300, 300);
		
        // handle keyboard input for moving the rectangle
        scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            switch (keyCode) {
                case UP:
                    rectangle.setY(rectangle.getY() - 10);
                    break;
                case DOWN:
                    rectangle.setY(rectangle.getY() + 10);
                    break;
                case LEFT:
                    rectangle.setX(rectangle.getX() - 10);
                    break;
                case RIGHT:
                    rectangle.setX(rectangle.getX() + 10);
                    break;
            }
            
            // Check for collision with food
            if (rectangle.getBoundsInParent().intersects(pixel.getBoundsInParent())) {
                // If collision, move the food to a new random position
                layout.getChildren().remove(pixel);
                pixel = createPixel();
                layout.getChildren().add(pixel);
            }
        
        });
        
        layout.requestFocus();
        
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	//9. Ajouter un pixel de couleur rouge manière aléatoire, dans la scène, attention on ne doit pas faire spawn le pixel rouge sur le serpent.
    private Rectangle createPixel() {
        Rectangle food = new Rectangle(10, 10, Color.RED);
        Random rand = new Random();
        food.setX(rand.nextInt(29) * 10); // 30 columns (300 / 10)
        food.setY(rand.nextInt(29) * 10); // 30 rows (300 / 10)
        return food;
    }
    //13. Se documenter sur l’event Timeline. Faire en sorte que le serpent se déplace à
//    vitesse constante, lorsque l’on appuie sur les touches on change la direction de
//    déplacement.
}


