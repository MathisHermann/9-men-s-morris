package finalGameBuild;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class View_WinnerOverlay {

	protected Label labelWinner;
	protected Button restartAfterWin;

	protected BorderPane borderPane;
	protected GridPane gridPane;
	private VBox winnerBox;

	public BorderPane makeWinnerOverlay(BorderPane winnerPane) {

		winnerPane = new BorderPane();
		winnerBox = new VBox();
		winnerBox.setPrefWidth(Double.MAX_VALUE);

		labelWinner = new Label();
		labelWinner.getStyleClass().add("winner-text");
		winnerBox.setAlignment(Pos.CENTER);

		restartAfterWin = new Button("Restart");
		restartAfterWin.getStyleClass().add("winner-restart");

		winnerBox.getChildren().addAll(labelWinner, restartAfterWin);

		winnerPane.setCenter(winnerBox);

		winnerPane.getStyleClass().add("winner-overlay");

		return winnerPane;
	}

	public void setWinner(String winnerName) {
		String win = "Congratulations " + winnerName.toUpperCase();
		labelWinner.setText(win);
	}
}