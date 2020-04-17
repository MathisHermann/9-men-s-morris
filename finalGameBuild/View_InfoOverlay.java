package finalGameBuild;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class View_InfoOverlay {
	
	private Model model;
	
	public View_InfoOverlay(Model model) {
		this.model = model;
	}

	public Group createOverlay(Group overlay) {

		GridPane pane = new GridPane();
		
		String header = "Info";
		
		String labelWinners = "\nWho is the better player:";
		String winnerStreak = model.winsOne + " wins for " + model.getNamePlayerOne() + " and " + model.winsTwo + " wins for " + model.getNamePlayerTwo();
		String text = "\nGamers can feel\n" + "when developers are passionate about their games.";
		
		
		
		Label textHeader = new Label(header);
		textHeader.getStyleClass().add("info-header");
		Label lblText = new Label(text);
		Label winHeader = new Label(labelWinners);
		winHeader.getStyleClass().add("info-header");
		Label winners = new Label(winnerStreak);
		
		pane.add(textHeader, 0, 0);
		pane.add(winHeader, 0, 1);
		pane.add(winners, 0, 2);
		pane.add(lblText, 0, 3);
		
		overlay.getChildren().add(pane);

		return overlay;
	}

}