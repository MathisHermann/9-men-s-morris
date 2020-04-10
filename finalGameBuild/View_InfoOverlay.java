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
		String text = "Love not me for comely grace,\n" + "For my pleasing eye or face,\n"
				+ "Nor for any outward part,\n" + "No, nor for my constant heart,\n"
				+ "For these may fail, or turn to ill.\n" + "So thou and I must sever.\n"
				+ "Keep therefore a true woman’s eye,\n" + "And love me still, but know not why,\n"
				+ "So hast thou the same reason still\n" + "To doat upon me ever.";
		String labelWinners = "\nWho is the better player:";
		String winnerStreak = model.winsOne + " wins for " + model.getNamePlayerOne() + " and " + model.winsTwo + " wins for " + model.getNamePlayerTwo();
		
		//text += winnerStreak;
		
		Label textHeader = new Label(header);
		textHeader.getStyleClass().add("info-header");
		Label lblText = new Label(text);
		Label winHeader = new Label(labelWinners);
		winHeader.getStyleClass().add("info-header");
		Label winners = new Label(winnerStreak);
		
		pane.add(textHeader, 0, 0);
		pane.add(lblText, 0, 1);
		pane.add(winHeader, 0, 2);
		pane.add(winners, 0, 3);
		
		overlay.getChildren().add(pane);

		return overlay;
	}

}