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
	private Settings settings;
	
	private static Minesweeper instance = null;
	
	/**
	 * 
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();
//		settings = settings.BEGGINER;
//	    settings.save();
		Settings setting = Settings.load();
		Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());	
		startMillis = System.currentTimeMillis();
		userInterface.newGameStarted(field);
		userInterface.update();
	}


	public static Minesweeper getInstance() {
		if(instance == null) {
	         instance = new Minesweeper();
	      }
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


	public Settings getSettings() {
		return settings;
	}


	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
	
}
