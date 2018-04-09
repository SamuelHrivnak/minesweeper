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

	private void printField1(Field field) {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				switch (tile.getState()) {
				case CLOSED:
					System.out.print("-");
					break;
				case MARKED:
					System.out.print("M");
					break;
				case OPEN:
					if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
					} else {
						System.out.print("X");
					}
					break;
				}

			}
			System.out.println();
		}

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
