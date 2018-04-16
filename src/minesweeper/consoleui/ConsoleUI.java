package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	private Minesweeper instance =  Minesweeper.getInstance();
	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			String stringInput= input.readLine();
			stringInput.trim();
			stringInput.toUpperCase();
			return stringInput;
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
		String timeStamp = new SimpleDateFormat("yy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("Welcome "  + System.getProperty("user.name"));		
		System.out.println(timeStamp);
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
		System.out.println("Hra trv� :" + ((System.currentTimeMillis())/1000 -( instance.getPlayingSeconds())/1000) + "s" );
		//System.out.println("Hra trv� :" + (instance.getPlayingSeconds()	- System.currentTimeMillis())/1000 + "s" );
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void handleInput(String input) throws WrongFormatException{
		if (input.length() == 1) {
			if (input.charAt(0) != 'X') {
				throw new WrongFormatException("Wrong key for exit game");
			}	
		}
		String pattern = "([MO]?)([A-I])([0-9]{1,2})";
		Pattern pattern2 = Pattern.compile(pattern);
		Matcher matcher = pattern2.matcher(input);
		if (matcher.matches()) {
			String row = matcher.group(2);
			String column = matcher.group(3);	
			
			if (Character.getNumericValue(row.charAt(0)) - 10 > field.getRowCount()-1 || 
					Integer.parseInt(column)  > field.getColumnCount()-1 || Character.getNumericValue(row.charAt(0)) - 10 < 0 ||  Integer.parseInt(column) < 0 ) {
				throw new WrongFormatException("Wrong index of tile, repeat Exit key");
			}
		}
		
		if (!matcher.matches()) {
			throw new WrongFormatException("Wrong input please write correctly !");
		}
		
		
	}
	private void processInput() {
		System.out.println("X - quit game \n" + "MA1 - mark row A and column 1 \n" + "OB4 - open row B and column 4");
		String string = readLine();
		try {
			handleInput(string);
		} catch (WrongFormatException ex) {
			System.err.println(ex.getMessage());
		}
				
		String pattern = "([MO]?)([A-I])([0-8])";
		Pattern pattern2 = Pattern.compile(pattern);
		Matcher matcher = pattern2.matcher(string);
	
		if (string.equals("X")) {
			System.exit(0);
			return;
		}

		if (matcher.matches()) {
			String action = matcher.group(1);
			String row = matcher.group(2);
			String column = matcher.group(3);
			int value = Character.getNumericValue(row.charAt(0)) - 10;
						
			if (action.equals("O")) {
				field.openTile(value, Integer.parseInt(column));
				return;
			} else if (action.equals("M")) {
				field.markTile(value, Integer.parseInt(column));
				return;
			}
		}
	}
}
