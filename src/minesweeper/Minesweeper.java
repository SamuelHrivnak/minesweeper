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
	private long startMillis;
	private BestTimes bestTimes = new BestTimes();
	private static Minesweeper instance;
	/**
	 * 
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();
		Field field = new Field(9, 9, 10);	
		startMillis = System.currentTimeMillis();
		userInterface.newGameStarted(field);
		userInterface.update();
	}


	public static Minesweeper getInstance() {
		return instance;
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
	public int getPlayingSeconds() {
		return (int) startMillis;
	}
	
	public BestTimes getBestTimes() {
		return bestTimes;
	}
}
