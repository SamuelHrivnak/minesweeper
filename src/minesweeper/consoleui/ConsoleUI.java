package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.Minesweeper;
import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
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
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field)
	 */
    @Override
	public void newGameStarted(Field field) {
        this.field = field;
        do {
            update();
            processInput();
            throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while(true);
    }
    
    /* (non-Javadoc)
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
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
        throw new UnsupportedOperationException("Method processInput not yet implemented");
    }
}
