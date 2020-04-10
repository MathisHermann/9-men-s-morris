package finalGameBuild;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class View_SettingsOverlay {
	protected Button btnSaveSettings;
	protected Button btnBackToGame;
	protected TextField inPlayerOne;
	protected TextField inPlayerTwo;
	protected ComboBox<String> playerColorsOne;
	protected ComboBox<String> playerColorsTwo;
//	protected ComboBox<String> choosePvP;
	protected Label labelOne;
	protected Label labelTwo;
	protected Label labelGamemode;
//	protected Label labelPvP;
	protected Label alertWrong;
	protected Label alertRight;

	protected GridPane gridPane;

	public Group makeSettingsOverlay(Group settingsOverlay) {

		gridPane = new GridPane();
		btnSaveSettings = new Button("Save");
		btnSaveSettings.getStyleClass().add("button-settings");

		btnBackToGame = new Button("Back to Game");
		btnBackToGame.getStyleClass().add("button-settings"); // change CSS

		playerColorsOne = new ComboBox<String>();
		playerColorsOne.setPromptText("Color Player 1");
		playerColorsOne.getItems().addAll("red", "black", "blue", "turquoise", "green", "dog");
		playerColorsOne.setPrefWidth(163);

		playerColorsTwo = new ComboBox<String>();
		playerColorsTwo.setPromptText("Color Player 2");
		playerColorsTwo.getItems().addAll("red", "black", "blue", "turquoise", "green" , "cat");
		playerColorsTwo.setPrefWidth(163);

	//	choosePvP = new ComboBox<String>();
	//	choosePvP.setPromptText("PvP or PvC?");
	//	choosePvP.getItems().addAll("Player vs. Player", "Player vs. Computer");
	//	choosePvP.setPrefWidth(163);

		inPlayerOne = new TextField();
		inPlayerOne.setPromptText("woof");

		inPlayerTwo = new TextField();
		inPlayerTwo.setPromptText("miau");

		labelOne = new Label("Player 1");
		labelOne.getStyleClass().add("header-settings");
		labelTwo = new Label("Player 2");
		labelTwo.getStyleClass().add("header-settings");
		labelGamemode = new Label("Gamemode only PvP possible");
		labelGamemode.getStyleClass().add("header-settings");
	//	labelPvP = new Label("Choose a gamemode:");
		alertWrong = new Label();
		alertWrong.getStyleClass().add("alert-wrong");
		alertRight = new Label();
		alertRight.getStyleClass().add("alert-right");
		
		gridPane.add(labelOne, 0, 0);
		gridPane.add(alertWrong, 1, 0);
		gridPane.add(alertRight, 1, 0);
		gridPane.add(labelTwo, 0, 2);
		gridPane.add(inPlayerOne, 0, 1);
		gridPane.add(inPlayerTwo, 0, 3);
		gridPane.add(playerColorsOne, 1, 1);
		gridPane.add(playerColorsTwo, 1, 3);
		gridPane.add(labelGamemode, 0, 4);
	//	gridPane.add(labelPvP, 0, 5);
	//	gridPane.add(choosePvP, 1, 5);
		gridPane.add(btnSaveSettings, 0, 5);
		gridPane.add(btnBackToGame, 1, 5);

		gridPane.getStyleClass().add("settings-overlay");

		settingsOverlay.getChildren().add(gridPane);

		return settingsOverlay;

	}

}