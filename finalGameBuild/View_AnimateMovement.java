package finalGameBuild;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class View_AnimateMovement {
	protected Timeline time;
	private KeyFrame keyFrame;
	private KeyValue width;
	private KeyValue height;
	KeyValue transPos;
	private double travel;

	public View_AnimateMovement() {
		// Timeline animation
		time = new Timeline();
		time.setCycleCount(0);
		time.setAutoReverse(false);

	}

	public Group makeMovement(double startX, double startY, double endX, double endY, Stone s, Color c) {

		Group animatedStone = new Group();

		if (s.getShape() == "rectangle") {

			// Create new object with initial position and size
			Rectangle ellipse = new Rectangle(startX, startY, 80, 80);
			// Rectangle ellipse = (Rectangle) s.getStone();
			ellipse.setFill(c);
			ellipse.setOpacity(0.75);

			// Create a group to hold our animated objects

			animatedStone.getChildren().add(ellipse);

			if (startY == endY && startX != endX) { // Movement in X axis

				travel = (startX < endX ? (int) endX - startX : (int) endX - startX);
				transPos = new KeyValue(ellipse.translateXProperty(), travel);

			} else {

				travel = (startY < endY ? (int) endY - startY : (int) endY - startY);
				transPos = new KeyValue(ellipse.translateYProperty(), travel);

			}

			

			width = new KeyValue(ellipse.scaleXProperty(), 1);
			height = new KeyValue(ellipse.scaleYProperty(), 1);

		} else if (s.getShape() == "image") {
			// System.out.println(s.getId());
			String path = (s.getId() == "playerOne" ? "dog" : "cat") + ".png";
			ImageView image = new ImageView(new Image(getClass().getResourceAsStream(path), 80, 80, false, true));
			image.setX(startX);
			image.setY(startY);
			image.setOpacity(0.75);
			animatedStone.getChildren().add(image);

			if (startY == endY && startX != endX) { // Movement in X axis

				travel = (startX < endX ? (int) endX - startX : (int) endX - startX);
				transPos = new KeyValue(image.translateXProperty(), travel);

			} else {

				travel = (startY < endY ? (int) endY - startY : (int) endY - startY);
				transPos = new KeyValue(image.translateYProperty(), travel);
			}

			width = new KeyValue(image.scaleXProperty(), 1);
			height = new KeyValue(image.scaleYProperty(), 1);
		}
		
		// t = s/v v = s/t -> sec = pix / ( pix / s )
		Duration duration = Duration.millis(Math.abs(travel / 0.6));
		keyFrame = new KeyFrame(duration, transPos, width, height);
		time.getKeyFrames().add(keyFrame);

		return animatedStone;

	}

	public void play() {
		time.play();

	}

}