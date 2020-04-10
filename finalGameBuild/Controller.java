package finalGameBuild;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller implements EventHandler<MouseEvent> {

	private final Model model;
	private final View view;
	protected Rectangle tempRect; // in the tempRect, the selected clickable Rectangle is stored
	protected Stone movingStone = null; // in this variable, the rectangle to be moved is stored
	private boolean gameIsOn; // True when the first move is made
	// private boolean pvp; // True when Player vs. Player is chosen, is standard
	// private boolean gameModeSet; // True when a gamemode is set
	private boolean remove; // True when a stone can be removed
	private boolean colorSet;
	private boolean animationDone;

	private final int maxMoves;

	public Controller(Model model, View view, int maxMoves) {
		this.model = model;
		this.view = view;
		this.maxMoves = maxMoves;

		animationDone = true;
		gameIsOn = false;
		remove = false;

		for (Rectangle r : view.clickableAll) {
			r.setOnMouseClicked(this);
		}

		// view.btnRemove.setOnAction(restartGame -> {
		// view.animate(200, 100, 200, 400);
		// });

//		Start the game
		view.start.btnStartGame.setOnAction(startGame -> {
			openGame();
		});

//		End the game at the start screen
		view.start.btnExit.setOnAction(close -> {
			stopGame();
		});

//		Settings from startscreen
		view.start.btnSettings.setOnAction(settings -> {
			openSettings();
		});

//		Reset the game, clear board. Colors and player names are saved.
		view.btnRestart.setOnAction(restartGame -> {
			resetGame();
		});

		view.winner.restartAfterWin.setOnAction(restartGame -> {
			resetGame();
		});

//		Access the settings and if chosen save it in the model
		view.btnSettings.setOnAction(closeSettings -> {
			openSettings();
		});

		view.settings.btnBackToGame.setOnAction(chooseSettings -> {
			openGame();
		});

		view.btnInfo.setOnAction(chooseInfo -> {
			view.openInfo();
		});

//		register ourselves to handle window-closing event etc...
		view.btnExit.setOnAction(exitProgram -> {
			stopGame();
		});

		view.getStage().setOnCloseRequest((event) -> {
			view.stop();
			Platform.exit();
		});

	}

	private void openGame() {
		view.openGame();
	}

	private void stopGame() {
		view.stop();
		Platform.exit();
		System.exit(0);
	}

	@Override
	public void handle(MouseEvent event) {

		if (animationDone) {
			gameIsOn = true;
			tempRect = (Rectangle) event.getSource();

			if (!colorSet) {
				view.setNameColor("hot", "sexy");
				model.setColor("hot", "sexy");
				colorSet = true;
			}

			if (model.getStoneCounter() < model.maxMoves && !model.allStonesSet && !remove) {
				if (model.checkIfFree(tempRect)) {
					// Rectangle rect = new Rectangle(tempRect.getX(), tempRect.getY(),
					// tempRect.getHeight(),
					// tempRect.getWidth());

					// if (!model.getColor().equals("dog") && !model.getColor().equals("cat")) {
					model.createStone(tempRect.getX(), tempRect.getY());

					// }
					view.showStones();
					model.countStoneUp();
				}

				if (model.getStoneCounter() == maxMoves)
					model.allStonesSet = true;

			} else {

// 			After setting the stones, moving is made

				if (movingStone == null && !remove) { // when no stone is chosen to change in position
					try {
						movingStone = model.checkMovableStone(tempRect);
					} catch (Exception e) {
						System.out.println(e);
					}

				} else {
					if (!remove) {

						// System.out.println("remove is: " + remove);
						if ((model.canFly() || model.movePossible(movingStone, tempRect))
								&& model.checkIfFree(tempRect)) {

							if (model.canFly()) {

								movingStone = model.setNewPosition(movingStone, tempRect);
								movingStone = null;

							} else {
								animationDone = false;
								Color colorStone = movingStone.getFill();
								movingStone.setFill(Color.TRANSPARENT);
								view.animate(movingStone.getX(), movingStone.getY(), tempRect.getX(), tempRect.getY(),
										movingStone, colorStone);

								view.animation.time.setOnFinished(done -> {
									model.setNewPosition(movingStone, tempRect);
									movingStone.setFill(colorStone);
									movingStone = null;
									animationCleanUp();
									animationDone = true;
									view.refreshDisplay(remove);
									if (!remove) {
										remove = model.checkRemove();
										view.refreshDisplay(remove);
									}
								});
							}

						} else if (movingStone.getId() == (model.moveIsOne ? "playerOne" : "playerTwo")) {
							movingStone = model.checkMovableStone(tempRect);
						}

					}

				}

			}

//		Remove stones
			// System.out.println("lol" + model.rowsAreTheSame());

			if (remove && !model.checkIfFree(tempRect)) {
				if ((model.moveIsOne && model.equalsOne(tempRect))
						|| (!model.moveIsOne && !model.equalsOne(tempRect))) {
					if (!model.threeInARow(tempRect) || model.rowsAreTheSame()) {
						removeStone(tempRect);
						remove = false;
					}

				}
			}

//		After every move, check if a stone can be removed and the information gets refreshed
			if (!remove)
				remove = model.checkRemove();

			if (movingStone == null) {
				view.refreshDisplay(remove);
				view.showStones();

//			Detect and announce a winner
				if (model.allStonesSet) {
					String winner = model.detectWinner();
					if (!winner.equals(""))
						view.showWinner(winner);
				}

			}
		}

	}

	private void resetGame() {
		if (gameIsOn) {
			tempRect = null;
			movingStone = null;
			remove = false;
			gameIsOn = false;
			animationDone = true;
			model.resetGame();
			view.resetGame();
			view.refreshDisplay(false);
		}

		for (int i = 0; i < model.counterArr.length; i++) {
			model.alreadyUsedOne[i] = false;
			model.counterArr[i] = 0;
		}
	}

	/**
	 * Clean up after the animation
	 */
	private void animationCleanUp() {
		view.refreshDisplay(remove);
		view.animationCleanUp();
	}

	/**
	 * Remove the chosen stone from the ArrayList model.allStones in the model
	 * itself.
	 * 
	 * @param tempRect -> the current (chosen) Rectangle
	 */
	private void removeStone(Rectangle tempRect) {
		try {
			model.removeStone(tempRect);

		} catch (Exception e) {
			System.out.println(e);
		}

		view.showStones();
	}

	/**
	 * Open the settings. Either fill out everything or nothing.
	 */
	private void openSettings() {

		if (!gameIsOn) {

			view.showSettings();

			view.settings.btnSaveSettings.setOnAction(saveSettings -> {

				try {

					String colOne = view.settings.playerColorsOne.getValue().toString();
					String colTwo = view.settings.playerColorsTwo.getValue().toString();

					if (!view.settings.inPlayerOne.getText().equals("")
							&& !view.settings.inPlayerTwo.getText().equals("") && colOne != colTwo) {
						model.setPlayerNames(view.settings.inPlayerOne.getText(), view.settings.inPlayerTwo.getText());
						model.setColor(colOne, colTwo);
						// model.setRealColor(colOne, colTwo);

						view.lblPlayerNameOne.setText(model.getNamePlayerOne());
						view.lblPlayerNameTwo.setText(model.getNamePlayerTwo());

						view.setNameColor(colOne, colTwo);

						view.settings.alertWrong.setText(null);
						view.settings.alertRight.setText("Settings saved!");
						colorSet = true;

					} else if (colOne != colTwo) {
						view.settings.alertWrong.setText("Choose player names!");
					} else {
						view.settings.alertWrong.setText("Choose different colors!");
					}
				} catch (Exception e) {
					view.settings.alertWrong.setText(null);
					view.settings.alertWrong.setText("Please choose a color");
				}

			});

			view.settings.alertRight.setText(null);
		} else {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Open the settings?");
			alert.setHeaderText("The game is on. If you open the settings, the game is lost..");
			alert.setContentText("Are you ok with this?");
			
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(
			   getClass().getResource("styles.css").toExternalForm());
			dialogPane.getStyleClass().add("dialog-pane");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    resetGame();
			    openSettings();
			} else {
			    // ... user chose CANCEL or closed the dialog
			}
			
			
			
		}
	}

}
