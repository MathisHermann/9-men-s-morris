package finalGameBuild;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View_StartOverlay {
	protected Button btnStartGame;
	protected Button btnSettings;
	protected Button btnExit;
	protected Label labelHelloWorld;
	protected Label labelTextOne;
	protected Label labelTextTwo;
	protected Label labelTextThree;
	
	protected VBox vBox;
	protected HBox hBox;

	public Group makeStartOverlay(Group startOverlay) {

		hBox = new HBox();
		vBox = new VBox();
		
		btnStartGame = new Button("Start");
		btnStartGame.getStyleClass().add("button-settings");

		btnSettings = new Button("Settings");
		btnSettings.getStyleClass().add("button-settings"); // change CSS

		btnExit = new Button("Exit");
		btnExit.getStyleClass().add("button-settings");


		labelHelloWorld = new Label("Welcome to the Game 'Mühle' or 'Nine Men'e Morris' :-)");
		labelHelloWorld.getStyleClass().add("header-start");
		labelTextOne = new Label("You can start right away!");
		labelTextOne.getStyleClass().add("text-start");
		labelTextTwo = new Label("Or look into the settings and change them...");
		labelTextTwo.getStyleClass().add("text-start");
		labelTextThree = new Label("Or exit.");
		labelTextThree.getStyleClass().add("text-start");
			
		/*hBox.getChildren().addAll(btnStartGame, btnSettings, btnExit);
		hBox.setSpacing(20);
		hBox.setAlignment(Pos.CENTER);*/
		vBox.getChildren().addAll(labelHelloWorld, labelTextOne, btnStartGame, labelTextTwo, btnSettings, labelTextThree, btnExit);
		vBox.setAlignment(Pos.CENTER);

		vBox.getStyleClass().add("start-overlay");

		startOverlay.getChildren().add(vBox);

		return startOverlay;

	}

}