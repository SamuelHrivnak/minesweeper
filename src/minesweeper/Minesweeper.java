package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		userInterface = new ConsoleUI();

		Field field = new Field(9, 9, 10);
		// field.printField();
//		userInterface.update();
		userInterface.newGameStarted(field);
		userInterface.update();
	}


	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		new Minesweeper();
	}
}
