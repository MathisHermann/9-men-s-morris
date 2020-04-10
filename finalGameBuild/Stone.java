package finalGameBuild;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stone {

	private double posX;
	private double posY;

//	private String color;
	private Color fill;

	private String shape;
	private Rectangle shapeR;
	private ImageView shapeI;
	// private Circle shapeC;

	private String ID;
	private static int IDnr = 0;

	public Stone(double posX, double posY, String shape, String color) {
		this.posX = posX;
		this.posY = posY;
		if (shape == null) {
			this.shape = "rectangle";
		} else {
			this.shape = shape;
		}
		setID();
		setFill(color);
		IDnr++;
	}
	
	public static void reset() {
		IDnr = 0;
	}

	private void setID() {
		if (IDnr % 2 == 0) {
			ID = "playerOne";
		} else {
			ID = "playerTwo";
		}
	}

	public void setPos(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
		if (shape == "rectangle") {
			Rectangle r = (Rectangle) this.getStone();
			r.setX(posX);
			r.setY(posY);	
		} else if (shape == "image") {
			ImageView i = (ImageView) this.getStone();
			i.setX(posX);
			i.setY(posY);
		}
		
	}

	/**
	 * Get the position of the Stone as an Array
	 * 
	 * @return Array [posX, posY]
	 */
	public double[] getPos() {
		double[] position = { this.posX, this.posY };
		return position;
	}

	public double getX() {
		return posX;
	}

	public double getY() {
		return posY;
	}

	public String getId() {
		return ID;
	}

	public void setFill(String color) {

		if (color == "red") {
			fill = Color.RED;
		} else if (color == "black") {
			fill = Color.BLACK;
		} else if (color == "blue") {
			fill = Color.DEEPSKYBLUE;
		} else if (color == "turquoise") {
			fill = Color.DARKTURQUOISE;
		} else if (color == "green") {
			fill = Color.GREENYELLOW;
		} else {
			fill = (this.ID == "playerOne" ? Color.HOTPINK : Color.CYAN);
			// System.out.println(fill.toString());
		}
		/*
		 * else if (colOne.equals("cat")) { fill = null; }
		 */

//		this.color = color;
	}

	public void setFill(Color fill) {
		if(shape == "rectangle") {
			this.fill = fill;
			Rectangle r = (Rectangle) this.getStone();
			r.setFill(fill);
		}

	}

	public Color getFill() {
		return fill;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getShape() {
		return shape;
	}

	public Node getStone() {
		if (shape == "rectangle") {
			return shapeR;
		} else if (shape == "image") {
			return shapeI;
		}

		return null;
	}

	/**
	 * Create the object that is displayed later and give it the style class.
	 * 
	 * @param b
	 */
	public void create(boolean moveIsOne) {
		String styleClass = (moveIsOne ? "one" : "two");
		if (shape == "rectangle") {
			shapeR = new Rectangle(posX, posY, 80, 80);
			shapeR.setFill(fill);
			shapeR.getStyleClass().add(styleClass);
		} else if (shape == "image") {
		//	System.out.println("image :)");
			String path = (moveIsOne ? "dog" : "cat") + ".png";
			try {
			//	System.out.println(path);
				shapeI = new ImageView(new Image(getClass().getResourceAsStream(path), 80, 80, false, true));
				shapeI.getStyleClass().add(styleClass);
				shapeI.setX(posX);
				shapeI.setY(posY);
				// System.out.println("image :)");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
