package finalGameBuild;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

	
	// Defining the amount of max moves (per player)
	protected int maxMoves = 18;
	
	
	
	private static Model model;
	private static View view;
	private static Controller controller;
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}




	@Override
	public void start(Stage stage) throws Exception {
		model = new Model(maxMoves);
		view = new View(model, stage);
		controller = new Controller(model, view, maxMoves);
		view.start();
		
	}
	
	@Override
	public void stop() {
		if (view != null) {
			view.stop();
		}
	}

}
