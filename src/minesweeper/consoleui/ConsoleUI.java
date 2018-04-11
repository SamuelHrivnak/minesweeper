package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.Minesweeper;
import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field)
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {

			processInput();
			update();

			if (field.getState() == GameState.FAILED) {
				System.out.println("Game over !");
				System.exit(0);
			}
			if (field.getState() == GameState.SOLVED) {
				System.out.println("You win game!");
				System.exit(0);
			}
			// throw new UnsupportedOperationException("Resolve the game state - winning or
			// loosing condition.");
		} while (true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {
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
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out.println("X - quit game \n" + "MA1 - mark row A and column 1 \n" + "OB4 - open row B and column 4");
		String string = readLine();
		String pattern = "([MO]?)([A-I])([0-8])";
		Pattern pattern2 = Pattern.compile(pattern);
		Matcher matcher = pattern2.matcher(string);

		if (string.equals("X")) {
			return;
		}

		if (matcher.matches()) {
			String action = matcher.group(1);
			String row = matcher.group(2);
			String column = matcher.group(3);
			int value = 0;

			if (row.equals("A")) {
				value = 0;
			} else if (row.equals("B")) {
				value = 1;
			} else if (row.equals("C")) {
				value = 2;
			} else if (row.equals("D")) {
				value = 3;
			} else if (row.equals("E")) {
				value = 4;
			} else if (row.equals("F")) {
				value = 5;
			} else if (row.equals("G")) {
				value = 6;
			} else if (row.equals("H")) {
				value = 7;
			} else if (row.equals("I")) {
				value = 8;
			}

			if (action.equals("O")) {
				field.openTile(value, Integer.parseInt(column));

				return;
			} else if (action.equals("M")) {
				field.markTile(value, Integer.parseInt(column));
				return;
			}
		} else {
			System.out.println("Wrong input, please write correctly !");
		}
	}
}
