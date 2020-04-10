package finalGameBuild;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class View {

//	Grid
	private Rectangle innerRectangle;
	private Rectangle middleRectangle;
	private Rectangle outerRectangle;

	private Line topLine;
	private Line rightLine;
	private Line bottomLine;
	private Line leftLine;

//	Top navigation box
	private HBox topBox;
	protected Button btnRestart;
	protected Button btnSettings;
	protected Button btnExit;
	protected Button btnInfo;
	private Tooltip ttRestart;
	private Tooltip ttSettings;
	private Tooltip ttInfo;

//	Bottom box with infos etc
	private HBox bottomBox;
	protected Label lblPlayerNameOne;
	protected Label lblPlayerNameTwo;
	protected Label lblPlayerNumberStonesOne;
	protected Label lblPlayerNumberStonesTwo;
	protected Label lblWinStreakOne;
	protected Label lblWinStreakTwo;
	protected Label lblPlayerInfo;
	


	// The main Pane where everything is fit in -> borderPane
	protected BorderPane borderPane;
	// Is shown, when a winner is detected
	private BorderPane winnerPane;
	private Group startOverlay;

	protected Group gameAll;
	protected Group grid;
	protected Group stones;
	protected Group animatedStone;
	protected Group clickable;
	protected Group settingsOverlay;
	protected Group infoOverlay;

	private final Model model;
	private View_InfoOverlay info;
	protected View_SettingsOverlay settings;
	protected View_WinnerOverlay winner;
	protected View_StartOverlay start;
	private Stage stage;

//	Animation
	protected View_AnimateMovement animation;
	// protected boolean animationDone = false;
	protected Group animate;

	protected ArrayList<Rectangle> clickableAll;

	public View(Model model, Stage stage) {
		this.stage = stage;
		this.model = model;

		animation = new View_AnimateMovement();

		info = new View_InfoOverlay(model);
		settings = new View_SettingsOverlay();
		winner = new View_WinnerOverlay();
		start = new View_StartOverlay();

		clickableAll = new ArrayList<>();
		stage.setTitle("Muehle");
		borderPane = new BorderPane();

		// borderPane.getStyleClass().add("game-all-group");

//		create all groups
		grid = new Group();
		stones = new Group();
		animatedStone = new Group();
		clickable = new Group();
		gameAll = new Group(grid, stones, animatedStone, clickable);
		infoOverlay = new Group();
		settingsOverlay = new Group();
		startOverlay = new Group();

//		Create the playing grid
		grid = makeGrid(grid);

//		Create the clickable layer
		clickable = makeClickable(clickable);

//		Create the top header banner
		topBox = new HBox();
		topBox = makeTopBox();

//		Create the bottom box with player info etc
		bottomBox = new HBox();
		bottomBox = makeBottomBox();

//		Build the start overlay
		startOverlay = start.makeStartOverlay(startOverlay);
		
		
//		Build the settings overlay
		settingsOverlay = settings.makeSettingsOverlay(settingsOverlay);

//		Build the winner overlay
		winnerPane = winner.makeWinnerOverlay(winnerPane);

//		Build the info overlay
		infoOverlay = info.createOverlay(infoOverlay);

//		Set the items in the (main) BorderPane
		borderPane.setTop(null);
		borderPane.setCenter(startOverlay);
		borderPane.setBottom(null);
		borderPane.getStyleClass().add("border-pane");

//		Create the scene and set the stage
		Scene scene = new Scene(borderPane, 720, 850);

		// add the CSS Stylesheet to the scene
		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		stage.setMinHeight(850);
		stage.setMinWidth(720);
		stage.setScene(scene);
	}

	private HBox makeTopBox() {
		btnRestart = new Button("Restart");
		btnRestart.getStyleClass().add("button");

		btnSettings = new Button("Settings");
		btnSettings.getStyleClass().add("button");

		btnExit = new Button("Exit");
		btnExit.getStyleClass().add("button");

		btnInfo = new Button("Info");
		btnInfo.getStyleClass().add("button");

		ttRestart = new Tooltip();
		ttRestart.setText("Restart your game here");

		ttSettings = new Tooltip();
		ttSettings.setText("To access the settings\n" + "Please restart first!");

		ttInfo = new Tooltip();
		ttInfo.setText("Need some help?");

		btnRestart.setTooltip(ttRestart);
		btnSettings.setTooltip(ttSettings);
		btnInfo.setTooltip(ttInfo);

		topBox = new HBox();
		topBox.getStyleClass().add("topBox");
		topBox.getChildren().addAll(btnRestart, btnSettings /* , btnRemove */, btnInfo, btnExit);
		return topBox;
	}

	private HBox makeBottomBox() {
		lblPlayerNameOne = new Label("Player 1");
		lblPlayerNameOne.getStyleClass().add("lblPlayer");
		lblPlayerNameTwo = new Label("Player 2");
		lblPlayerNameTwo.getStyleClass().add("lblPlayer");
		lblPlayerNumberStonesOne = new Label("0");
		lblPlayerNumberStonesOne.getStyleClass().add("lblScore");
		lblPlayerNumberStonesOne.setPrefWidth(30);
		lblPlayerNumberStonesTwo = new Label("0");
		lblPlayerNumberStonesTwo.getStyleClass().add("lblScore");
		lblPlayerNumberStonesTwo.setPrefWidth(30);
		lblWinStreakOne = new Label("0");
		lblWinStreakOne.getStyleClass().add("win-streak");
		lblWinStreakTwo = new Label("0");
		lblWinStreakTwo.getStyleClass().add("win-streak");

		lblPlayerInfo = new Label("Player 1 set a stone");
		lblPlayerInfo.getStyleClass().add("playing-info");
		lblPlayerInfo.setWrapText(true);
		lblPlayerInfo.setTextAlignment(TextAlignment.CENTER);
		lblPlayerInfo.setPrefWidth(250);
		lblPlayerInfo.setAlignment(Pos.CENTER);

		// winnerBox.setAlignment(Pos.CENTER);

		BorderPane bottomPane = new BorderPane();

		GridPane bottomGridLeft = new GridPane();
		bottomGridLeft.add(lblWinStreakOne, 0, 1);
		bottomGridLeft.add(lblPlayerNameOne, 0, 0);
		bottomGridLeft.add(lblPlayerNumberStonesOne, 1, 1);

		GridPane bottomGridRight = new GridPane();
		bottomGridRight.add(lblPlayerNameTwo, 0, 0);
		bottomGridRight.add(lblPlayerNumberStonesTwo, 0, 1);
		bottomGridRight.add(lblWinStreakTwo, 1, 1);

		bottomPane.setLeft(bottomGridLeft);
		bottomPane.setCenter(lblPlayerInfo);
		bottomPane.setRight(bottomGridRight);

		bottomGridLeft.getStyleClass().add("bottom-box-left");
		bottomGridRight.getStyleClass().add("bottom-box-right");

		bottomBox.getStyleClass().add("hbox-bottom-box");
		bottomBox.getChildren().add(bottomPane);
		bottomBox.setAlignment(Pos.CENTER);

		return bottomBox;
	}

	public void setNameColor(String one, String two) {
		lblPlayerNameOne.getStyleClass().remove("hot");
		lblPlayerNameOne.getStyleClass().add(one);
		lblWinStreakOne.getStyleClass().remove("hot");
		lblWinStreakOne.getStyleClass().add(one);
		lblPlayerNumberStonesOne.getStyleClass().remove("hot");
		lblPlayerNumberStonesOne.getStyleClass().add(one);

		lblPlayerNameTwo.getStyleClass().remove("sexy");
		lblPlayerNameTwo.getStyleClass().add(two);
		lblWinStreakTwo.getStyleClass().remove("sexy");
		lblWinStreakTwo.getStyleClass().add(two);
		lblPlayerNumberStonesTwo.getStyleClass().remove("sexy");
		lblPlayerNumberStonesTwo.getStyleClass().add(two);
	}

	/*
	 * Make the layer with all clickable fields
	 */
	private Group makeClickable(Group clickable) {
		for (int i = 0; i < Model.positions.length; i++) {
			Rectangle rect = new Rectangle(Model.positions[i][0], Model.positions[i][1], Model.positions[i][2],
					Model.positions[i][2]);
			rect.getStyleClass().add("clickable");
			clickable.getChildren().add(rect);
			clickableAll.add(rect);
		}
		return clickable;
	}

	/*
	 * Make the playing grid
	 */
	private Group makeGrid(Group grid) {
		innerRectangle = new Rectangle(300, 300, 200, 200);
		innerRectangle.getStyleClass().add("gridLine");

		middleRectangle = new Rectangle(200, 200, 400, 400);
		middleRectangle.getStyleClass().add("gridLine");

		outerRectangle = new Rectangle(100, 100, 600, 600);
		outerRectangle.getStyleClass().add("gridLine");

		grid.getChildren().addAll(innerRectangle, middleRectangle, outerRectangle);

		topLine = new Line(400, 100, 400, 300);
		rightLine = new Line(500, 400, 700, 400);
		bottomLine = new Line(400, 500, 400, 700);
		leftLine = new Line(100, 400, 300, 400);

		grid.getChildren().addAll(topLine, rightLine, bottomLine, leftLine);

		return grid;
	}

	public void refreshDisplay(boolean remove) {

		boolean yesRemove = false;

		if (remove) {
			lblPlayerInfo.setText(
					(!model.moveIsOne ? model.getNamePlayerOne() : model.getNamePlayerTwo()) + " remove a stone from "
							+ (model.moveIsOne ? model.getNamePlayerOne() : model.getNamePlayerTwo()));
			yesRemove = true;
		} else if (!model.allStonesSet) {
			lblPlayerInfo
					.setText((model.moveIsOne ? model.getNamePlayerOne() : model.getNamePlayerTwo()) + " set a stone.");
		} else {
			lblPlayerInfo
					.setText((model.moveIsOne ? model.getNamePlayerOne() : model.getNamePlayerTwo()) + " make a move.");
		}

		if (lblPlayerInfo.getStyleClass().contains("hot")) lblPlayerInfo.getStyleClass().remove("hot"); 
		if (lblPlayerInfo.getStyleClass().contains("sexy")) lblPlayerInfo.getStyleClass().remove("sexy"); 
		
		lblPlayerInfo.getStyleClass().remove(model.getColor(yesRemove));

		//System.out.println(model.getColor(true));
		
		if (!lblPlayerInfo.getStyleClass().contains(model.getColor(true && !yesRemove))) {
			lblPlayerInfo.getStyleClass().add(model.getColor(true && !yesRemove));
		}
		// System.out.println("" + remove);
		// System.out.println(lblPlayerInfo.getText());

		lblWinStreakOne.setText("" + model.winsOne);
		lblWinStreakTwo.setText("" + model.winsTwo);

		lblPlayerNumberStonesOne.setText("" + model.stonesOne.size());
		lblPlayerNumberStonesTwo.setText("" + model.stonesTwo.size());

	}

	public void showStones() {
		stones.getChildren().clear();

		for (Stone s : model.stonesAll) {
			stones.getChildren().add(s.getStone());
		}
	}

	public void winner(String winnerName) {
		if (winnerName == "one") {
			showWinner(model.getNamePlayerOne());
		} else if (winnerName == "two") {
			showWinner(model.getNamePlayerTwo());
		}
	}

	public void showSettings() {
		if (!borderPane.getCenter().equals(settingsOverlay)) {
			borderPane.setCenter(settingsOverlay);
			borderPane.setBottom(null);
		} else {
			borderPane.setCenter(gameAll);
			if (borderPane.getBottom() == null) {
				borderPane.setBottom(bottomBox);
			}
			if (borderPane.getTop() == null) {
				borderPane.setTop(topBox);
			}
		}

	}
	
	public void openGame() {
		borderPane.setTop(topBox);
		borderPane.setCenter(gameAll);
		borderPane.setBottom(bottomBox);
		refreshDisplay(false);
	}

	public void openInfo() {
		if (!borderPane.getCenter().equals(infoOverlay)) {
			borderPane.setCenter(infoOverlay);
			borderPane.setBottom(null);
		} else {
			borderPane.setCenter(gameAll);
			borderPane.setBottom(bottomBox);
		}

	}

	public void showWinner(String winnerName) {
		if (!borderPane.getCenter().equals(winnerPane) && winnerName != null) {
			lblPlayerInfo.setText("");
			winner.setWinner(winnerName);
			borderPane.setCenter(winnerPane);
		} else {
			borderPane.setCenter(gameAll);
			borderPane.setBottom(bottomBox);
		}

	}

	public void resetGame() {
		stones.getChildren().clear();
		refreshDisplay(false);
		showWinner(null);
		animatedStone.getChildren().clear();
	}

	public void animate(double startX, double startY, double endX, double endY, Stone s, Color c) {
		animate = animation.makeMovement(startX, startY, endX, endY, s, c);
		animatedStone.getChildren().add(animate);
		animation.play();

	}

	public void animationCleanUp() {
		animation.time.getKeyFrames().clear();
		animatedStone.getChildren().clear();
	}

	public void start() {
		stage.show();
	}

	public void stop() {
		stage.hide();
	}

	public Stage getStage() {
		return stage;
	}

}
