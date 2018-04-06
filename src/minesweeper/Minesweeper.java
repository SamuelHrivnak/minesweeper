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
	private ConsoleUI userInterface;

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		userInterface = new ConsoleUI();

		Field field = new Field(9, 9, 10);
		// field.printField();
		printField1(field);
		// userInterface.newGameStarted(field);
	}

	private void printField1(Field field) {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);

				if (tile.getState() == Tile.State.OPEN) {
					if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
					}
					if (tile instanceof Mine) {
						System.out.print("X");
					}

				}
				
				if (tile.getState() == Tile.State.CLOSED) {
					System.out.print("-");
				}
				if (tile.getState() == Tile.State.MARKED) {
					System.out.print("M");
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
