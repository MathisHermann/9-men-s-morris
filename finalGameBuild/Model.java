package finalGameBuild;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Model {

	protected final int maxMoves;
	protected int stonesCreated;
	protected boolean allStonesSet;
	protected boolean moveIsOne;

	protected String shapeOne;
	protected String shapeTwo;

	private String colorPlayerOne;
	private String namePlayerOne;
	private String colorPlayerTwo;
	private String namePlayerTwo;
	private Color colorOne;
	private Color colorTwo;

	// Number of wins
	protected int winsOne;
	protected int winsTwo;

	// private ArrayList<Rectangle> allStones;
	protected ArrayList<Stone> stonesOne;
	protected ArrayList<Stone> stonesTwo;
	protected ArrayList<Stone> stonesAll;

	protected int[] counterArr = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	protected boolean[] alreadyUsedOne = { false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false };

	protected boolean[] alreadyUsedTwo = { false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false };

	protected final static double[][] positions = { { 60, 60, 80 }, { 360, 60, 80 }, { 660, 60, 80 },

			{ 160, 160, 80 }, { 360, 160, 80 }, { 560, 160, 80 },

			{ 260, 260, 80 }, { 360, 260, 80 }, { 460, 260, 80 },

			{ 60, 360, 80 }, { 160, 360, 80 }, { 260, 360, 80 }, { 460, 360, 80 }, { 560, 360, 80 }, { 660, 360, 80 },

			{ 260, 460, 80 }, { 360, 460, 80 }, { 460, 460, 80 },

			{ 160, 560, 80 }, { 360, 560, 80 }, { 560, 560, 80 },

			{ 60, 660, 80 }, { 360, 660, 80 }, { 660, 660, 80 } };
	private final static int[][] validMovePositions = { { 2, 10, 0, 0 }, // 1
			{ 1, 3, 5, 0 }, { 2, 15, 0, 0 },

			{ 5, 11, 0, 0 }, // 4
			{ 2, 4, 6, 8 }, { 5, 14, 0, 0 },

			{ 8, 12, 0, 0 }, // 7
			{ 5, 7, 9, 0 }, { 8, 13, 0, 0 },

			{ 1, 11, 22, 0 }, // 10
			{ 4, 10, 12, 19 }, { 7, 11, 16, 0 }, { 9, 14, 18, 0 }, { 6, 13, 15, 21 }, { 3, 14, 24, 0 },

			{ 12, 17, 0, 0 }, // 16
			{ 16, 18, 20, 0 }, { 13, 17, 0, 0 },

			{ 11, 20, 0, 0 }, // 19
			{ 17, 19, 21, 23 }, // ->
			{ 14, 20, 0, 0 },

			{ 10, 23, 0, 0 }, // 22
			{ 20, 22, 24, 0 }, // ->
			{ 15, 23, 0, 0 } };
	private final static int[][] rows = { { 1, 10, 22 }, { 4, 11, 19 }, { 7, 12, 16 }, { 9, 13, 18 }, { 6, 14, 21 },
			{ 3, 15, 24 }, { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 16, 17, 18 }, { 19, 20, 21 }, { 22, 23, 24 },
			{ 2, 5, 8 }, { 17, 20, 23 }, { 10, 11, 12 }, { 13, 14, 15 } };

	protected Model(int maxMoves) {
		this.maxMoves = maxMoves;

		this.stonesOne = new ArrayList<>();
		this.stonesTwo = new ArrayList<>();
		this.stonesAll = new ArrayList<>();

		this.moveIsOne = true;

		namePlayerOne = "Player 1";
		namePlayerTwo = "Player 2";
		winsOne = 0;
		winsTwo = 0;
		stonesCreated = 0;

	}

	/**
	 * Set the Name of the player. If nothing set, by default "Player 1" and "Player
	 * 2" is already set.
	 * 
	 * @param one Name player one
	 * @param two Name player two
	 */
	public void setPlayerNames(String one, String two) {
		this.namePlayerOne = one;
		this.namePlayerTwo = two;
	}

	/**
	 * Get the name of player one
	 * 
	 * @return
	 */
	public String getNamePlayerOne() {
		return namePlayerOne;
	}

	/**
	 * Get the name of player two
	 * 
	 * @return
	 */
	public String getNamePlayerTwo() {
		return namePlayerTwo;
	}

	public int getNumberStones() {
		return stonesAll.size();
	}

	public int getNumberStonesOne() {
		return stonesOne.size();
	}

	public int getNumberStonesTwo() {
		return stonesTwo.size();
	}

	public void countStoneUp() {
		stonesCreated++;
	}

	public int getStoneCounter() {
		return stonesCreated;
	}

	public void setShapeOne(String shape) {
		this.shapeOne = shape;
	}

	public void setColor(String colorPlayerOne, String colorPlayerTwo) {
		if (colorPlayerOne == "dog" || colorPlayerOne == "cat") {
			shapeOne = "image";
		}

		if (colorPlayerTwo == "dog" || colorPlayerTwo == "cat") {
			shapeTwo = "image";
		}

		this.colorPlayerOne = colorPlayerOne;
		this.colorPlayerTwo = colorPlayerTwo;

	}

	/**
	 * Return the player color (String) of the player currently moving.
	 * <p>
	 * The color is stored as a String, if the player chose a color. If none, return
	 * is null.
	 * 
	 * @param inverted
	 * 
	 * @return String
	 */
	public String getColor(boolean inverted) {
		if ((inverted ? moveIsOne : !moveIsOne)) {
			return colorPlayerOne;
		} else {
			return colorPlayerTwo;
		}
	}

	/**
	 * Return the color according to the player id that.
	 * 
	 * @param id
	 * @return
	 */
	public Color getColorByPlayerId(String id) {
		if (id == "playerOne") {
			return colorOne;
		} else if (id == "playerTwo") {
			return colorTwo;
		}

		return null;
	}

	/**
	 * Reset all ArrayLists, Arrays and so on, to start a new game.
	 */
	public void resetGame() {
		Stone.reset();
		stonesAll.clear();
		stonesOne.clear();
		stonesTwo.clear();
		moveIsOne = true;
		allStonesSet = false;
		stonesCreated = 0;
	}

	/**
	 * Create a Stone and add it to the according ArrayList (stonesAll and
	 * stonesOne/stonesTwo
	 * 
	 * @param posX
	 * @param posY
	 */
	public void createStone(double posX, double posY) {
		// String ID = (moveIsOne ? "playerOne" : "playerTwo");
		Stone temp = null;

		temp = new Stone(posX, posY, (moveIsOne ? shapeOne : shapeTwo), (moveIsOne ? colorPlayerOne : colorPlayerTwo));

		temp.create(moveIsOne);

		stonesAll.add(temp);
		if (moveIsOne) {
			moveIsOne = false;
			stonesOne.add(temp);
		} else {
			moveIsOne = true;
			stonesTwo.add(temp);
		}

	}

	/**
	 * Check in which group the Stone is. Returns true, if the Stone is player one.
	 * 
	 * @param stone
	 * @return boolean
	 */
	public boolean isStoneOne(Stone stone) {
		boolean isOne = false;

		for (Stone s : stonesOne) {
			if (s.equals(stone)) {
				isOne = true;
			}
		}

		return isOne;

	}

	/**
	 * Check if the chosen position is a stone. If it is a stone, return true.
	 * 
	 * @param tempRect
	 * @return
	 */
	public boolean equalsOne(Rectangle tempRect) {

		for (Stone s : stonesOne) {
			if (s.getX() == tempRect.getX() && s.getY() == tempRect.getY()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if on this position a stone of the according color is set
	 * 
	 * @param x Position x-Axis
	 * @param y Position y-Axis
	 * @return
	 */
	public boolean checkPositionAndColor(Double x, Double y) {
		for (Stone s : (moveIsOne ? stonesOne : stonesTwo)) {
			if (s.getX() == x && s.getY() == y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the Stone from the ArrayList model.stonesAll with the same position
	 * as the tempStone. Returns null if not found.
	 * 
	 * @param tempStone
	 * @return Stone -> null if nothing found
	 */
	public Stone checkMovableStone(Rectangle tempRect) {
		boolean found = false;
		Stone movingStone = null;
		for (int i = 0; i < getNumberStones() && !found; i++) {
			if (stonesAll.get(i).getX() == tempRect.getX() && stonesAll.get(i).getY() == tempRect.getY()) {

				movingStone = stonesAll.get(i);

				if (isStoneOne(movingStone) && moveIsOne) {
					found = true;

				} else if (!isStoneOne(movingStone) && !moveIsOne) {
					found = true;

				}
				if (found) {
					return movingStone;
				}

			}
		}

		return null;
	}

	/**
	 * Check if the chosen position is free. If there is no stone set, return true.
	 * 
	 * @param tempStone
	 * @return
	 */
	public boolean checkIfFree(Stone tempStone) {
		for (Stone s : stonesAll) {
			if (tempStone.getX() == s.getX() && tempStone.getY() == s.getY()) {
				return false;
			}
		}
		return true;
	}

	public boolean checkIfFree(Rectangle tempRect) {
		for (Stone s : stonesAll) {
			if (tempRect.getX() == s.getX() && tempRect.getY() == s.getY()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check the position of the current Stone to be moved and the position where
	 * the stone should go. If the stone is more than one step away, the move is
	 * invalid.
	 * 
	 * @param movingRect Rectangle to be moved.
	 * @param tempRect   Rectangle, where the other Rectangle should be moved to.
	 * @return boolean -> True if the move is only one step.
	 */
	public boolean movePossible(Stone movingStone, Rectangle tempRect) {

		// System.out.println("Move?");

		double posX = tempRect.getX();
		double posY = tempRect.getY();

		int posTempStone = 0;
		int posMovingStone = 1;

		boolean posFound = false;
		boolean moveIsValid = false;
		boolean findMovingRect = false;

//		Check if the goal position is a real position. When found, the position is stored as numberRect.
		for (int i = 0; i < positions.length && !posFound; i++) {
			if (positions[i][0] == posX && positions[i][1] == posY) {
				posFound = true;
			}
			posTempStone++;
		}

//		Get the position of the rectangle to be moved
		for (int i = 0; i < positions.length && !findMovingRect; i++) {
			if (positions[i][0] == movingStone.getX() && positions[i][1] == movingStone.getY()) {
				posMovingStone += i;
				findMovingRect = true;
			}
		}
//		Check if the positions are valid -> moveIsValid = true if found
		for (int i = 0; i < 4 && !moveIsValid; i++) {
			if (posMovingStone == validMovePositions[posTempStone - 1][i]) {
				moveIsValid = true;
				// System.out.println("Move?");
			}
		}

		return moveIsValid;
	}

	/**
	 * Check if the player can fly.
	 * 
	 * @return Boolean -> true if the player can fly
	 */
	public boolean canFly() {
		if (moveIsOne) {
			if (getNumberStonesOne() < 4)
				return true;
		} else {
			if (getNumberStonesTwo() < 4)
				return true;
		}
		return false;
	}

	/**
	 * Sets the position of the Rectangle to be moved.
	 * 
	 * @param tempRect
	 */
	public Stone setNewPosition(Stone movingStone, Rectangle tempRect) {

		movingStone.setPos(tempRect.getX(), tempRect.getY());

		if (moveIsOne) {
			moveIsOne = false;
		} else {
			moveIsOne = true;
		}

		return movingStone;
	}

	/**
	 * Check if there are the same number of groups of three and
	 * 
	 * @return boolean
	 */
	public boolean rowsAreTheSame() {
		int groupsOfThree = 0;

		makeCountsCall(true);

		for (int i : counterArr) {
			if (i == 3) {
				// System.out.println("lol");
				groupsOfThree++;
			}
		}
		// System.out.println(groupsOfThree * 3 + ", " + (moveIsOne ? stonesOne.size() :
		// stonesTwo.size()));
		if (groupsOfThree * 3 >= (moveIsOne ? stonesOne.size() : stonesTwo.size())) {
			return true;
		}

		return false;

	}

	/**
	 * This method checks whether there is a stone that can be removed.
	 * 
	 * @return Boolean true if a stone can be removed
	 * 
	 */
	public boolean checkRemove() {

		boolean remove = false;

		makeCountsCall(false);

		for (int i = 0; i < counterArr.length; i++) {
			// System.out.println(i + " | " + counterArr[i]);

			if (counterArr[i] == 3 && !(moveIsOne ? alreadyUsedTwo[i] : alreadyUsedOne[i])) {

				remove = true;

				if (!moveIsOne) {
					alreadyUsedOne[i] = true;
				} else {
					alreadyUsedTwo[i] = true;
				}
			}

			if (counterArr[i] != 3 && (moveIsOne ? alreadyUsedTwo[i] : alreadyUsedOne[i])) {
				if (!moveIsOne) {
					alreadyUsedOne[i] = false;
				} else {
					alreadyUsedTwo[i] = false;
				}
			}
		}
		return remove;
	}

	/**
	 * Remove the Rectangle from the ArrayList (color specific and all)
	 * 
	 * @param tempRect
	 */
	public void removeStone(Rectangle tempRect) {

		boolean found = false;
		for (int i = 0; i < stonesAll.size() && !found; i++) {
			if (stonesAll.get(i).getX() == tempRect.getX() && stonesAll.get(i).getY() == tempRect.getY()) {
				if (isStoneOne(stonesAll.get(i))) {
					stonesOne.remove(stonesAll.get(i));

				} else {
					stonesTwo.remove(stonesAll.get(i));
				}
				found = true;

				stonesAll.remove(stonesAll.get(i));
			}
		}

	}

	/**
	 * Call the function makeCounts. Takes a boolean to check if it is inverted.
	 * 
	 * @param invertedMoveIsOne
	 */
	public void makeCountsCall(boolean invertedMoveIsOne) {

		for (int i = 0; i < counterArr.length; i++) {
			counterArr[i] = 0;
		}

		for (Stone s : stonesAll) {

			// System.out.println(s.getX() + " | " + s.getY());
			// System.out.println(s.getId());
			if (s.getId().equals("playerOne") && (invertedMoveIsOne ? moveIsOne : !moveIsOne)) {
				// System.out.println("one");
				counterArr = makeCounts(s, counterArr);

			} else if (s.getId().equals("playerTwo") && (invertedMoveIsOne ? !moveIsOne : moveIsOne)) {
				// System.out.println("two");
				counterArr = makeCounts(s, counterArr);

			}

		}

	}

	/**
	 * This method counts how many Stones of the same color there are per row and
	 * column.
	 * <p>
	 * It returns an array with the values.
	 * 
	 * @param Stone     s
	 * @param tempArray
	 * @return Array (there are 16 rows and columns on the grid)
	 */
	private int[] makeCounts(Stone s, int[] tempArray) {

		if (s.getX() == 60.0 && (s.getY() == 60.0 || s.getY() == 360.0 || s.getY() == 660.0)) {
			tempArray[0]++;
		} else if (s.getX() == 160.0 && (s.getY() == 160.0 || s.getY() == 360.0 || s.getY() == 560.0)) {
			tempArray[1]++;
		} else if (s.getX() == 260.0 && (s.getY() == 260.0 || s.getY() == 360.0 || s.getY() == 460.0)) {
			tempArray[2]++;
		} else if (s.getX() == 460.0 && (s.getY() == 260.0 || s.getY() == 360.0 || s.getY() == 460.0)) {
			tempArray[3]++;
		} else if (s.getX() == 560.0 && (s.getY() == 160.0 || s.getY() == 360.0 || s.getY() == 560.0)) {
			tempArray[4]++;
		} else if (s.getX() == 660.0 && (s.getY() == 60.0 || s.getY() == 360.0 || s.getY() == 660.0)) {
			tempArray[5]++;
		}
		if (s.getY() == 60.0 && (s.getX() == 60.0 || s.getX() == 360.0 || s.getX() == 660.0)) {
			tempArray[6]++;
		} else if (s.getY() == 160.0 && (s.getX() == 160.0 || s.getX() == 360.0 || s.getX() == 560.0)) {
			tempArray[7]++;
		} else if (s.getY() == 260.0 && (s.getX() == 260.0 || s.getX() == 360.0 || s.getX() == 460.0)) {
			tempArray[8]++;
		} else if (s.getY() == 460.0 && (s.getX() == 260.0 || s.getX() == 360.0 || s.getX() == 460.0)) {
			tempArray[9]++;
		} else if (s.getY() == 560.0 && (s.getX() == 160.0 || s.getX() == 360.0 || s.getX() == 560.0)) {
			tempArray[10]++;
		} else if (s.getY() == 660.0 && (s.getX() == 60.0 || s.getX() == 360.0 || s.getX() == 660.0)) {
			tempArray[11]++;
		}
		if (s.getX() == 360.0 && (s.getY() == 60.0 || s.getY() == 160.0 || s.getY() == 260.0)) {
			tempArray[12]++;
		}
		if (s.getX() == 360.0 && (s.getY() == 460.0 || s.getY() == 560.0 || s.getY() == 660.0)) {
			tempArray[13]++;
		}
		if (s.getY() == 360.0 && (s.getX() == 60.0 || s.getX() == 160.0 || s.getX() == 260.0)) {
			tempArray[14]++;
		}
		if (s.getY() == 360.0 && (s.getX() == 460.0 || s.getX() == 560.0 || s.getX() == 660.0)) {
			tempArray[15]++;
		}

		return tempArray;

	}

	/**
	 * This method checks if there are three stones of one color in one row or
	 * column.
	 * <p>
	 * If there is a 'threeInARow' the method returns true.
	 * 
	 * @return boolean
	 */
	public boolean threeInARow(Rectangle tempRect) {
		boolean inARow = false;
		boolean rowing = false;

		int[] tempArray = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (Stone s : stonesAll) {

			if (s.getId().equals("playerOne") && moveIsOne) {

				tempArray = makeCounts(s, tempArray);

			} else if (s.getId().equals("playerTwo") && !moveIsOne) {

				tempArray = makeCounts(s, tempArray);

			}

		}

		for (int i = 0; i < tempArray.length && !inARow; i++) {

			// System.out.println(tempArray[i]);
			if (tempArray[i] == 3) {

				for (int j = 0; j < 3; j++) {

					if (positions[rows[i][j] - 1][0] == tempRect.getX()
							&& positions[rows[i][j] - 1][1] == tempRect.getY()) {

						rowing = true;
					}
				}

				if (rowing) {
					for (int j = 0; j < 3; j++) {

						if (!checkPositionAndColor(positions[rows[i][j] - 1][0], positions[rows[i][j] - 1][1])) {
							return false;
						} else {
							inARow = true;
						}

					}
				}

			}

		}

		return inARow;
	}

	/**
	 * Check if the current moving player has a possibility to move. If not, he
	 * loses.
	 * 
	 * @return true if current player cannot move anymore
	 */
	public boolean noMoreMove() {

		// boolean noMoreMovePossible = false;
		boolean rFound = false;

		double posXr = 0;
		double posYr = 0;

		double tempX = 0;
		double tempY = 0;

		int rPosInPositions = 0;
		int modelValidMoves = 0;

		for (Stone s : (moveIsOne ? stonesOne : stonesTwo)) {

			posXr = s.getX();
			posYr = s.getY();

			for (int i = 0; i < positions.length && !rFound; i++) {
				if (posXr == positions[i][0] && posYr == positions[i][1]) {
					rFound = true;
					rPosInPositions = i;
				}
			}

			if (rFound) {

				// System.out.println(posXr + " | " + posYr);

				for (int i = 0; i < 4; i++) {

					modelValidMoves = validMovePositions[rPosInPositions][i];

					if (modelValidMoves != 0) {

						// System.out.println(modelValidMoves);

						tempX = positions[modelValidMoves - 1][0];
						tempY = positions[modelValidMoves - 1][1];

						// System.out.println(tempX + " | " + tempY);

						boolean isNotFree = false;
						boolean isFree = true;

						for (int j = 0; j < stonesAll.size() && !isNotFree; j++) {

							isNotFree = (tempX == stonesAll.get(j).getX() && tempY == stonesAll.get(j).getY());

							if (isNotFree) {
								isFree = false;
							}

						}

						// System.out.println("Not free " + isNotFree);
						// System.out.println("Is free " + isFree);

						if (isFree) {
							return false;
						}

					}

				}

				rFound = false;

			}

		}

		return true;
	}

	/**
	 * Detect and return a winner if there is one. If winner, the win streak is
	 * incremented by 1.
	 * 
	 * @return String with the name of the winner
	 */
	public String detectWinner() {
		String winner = "";
		if (getNumberStonesOne() < 3) {
			winsTwo++;
			winner = namePlayerTwo;
		} else if (getNumberStonesTwo() < 3) {
			winsOne++;
			winner = namePlayerOne;
		}

		if (noMoreMove() && !canFly()) {
			if (moveIsOne) {
				winsTwo++;
				winner = namePlayerTwo;
			} else {
				winsOne++;
				winner = namePlayerOne;
			}
		}
		return winner;
	}

}
