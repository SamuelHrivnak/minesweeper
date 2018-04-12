package minesweeper.core;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.sql.RowSet;

import org.junit.Before;
import org.junit.Test;

import minesweeper.core.Tile.State;

public class FieldTest {

	private final static int ROWS = 9;
	private final static int COLUMNS = 9;
	private final static int MINES = 10;
	Field field;

	@Before
	public void Init() {
		field = new Field(ROWS, COLUMNS, MINES);
	}

	// @Test
	public void test() {

	}

	// @Test
	public void isSolved() {
		assertEquals(GameState.PLAYING, field.getState());

		int open = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Clue) {
					field.openTile(row, column);
					open++;
				}
				if (field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
					assertEquals(GameState.SOLVED, field.getState());
				} else {
					assertNotSame(GameState.FAILED, field.getState());
				}
			}
		}

		assertEquals(GameState.SOLVED, field.getState());
	}

	@Test
	public void openTileTest() {
		Tile tile = field.getTile(0, 0);
		assertEquals(Tile.State.CLOSED, tile.getState());
		field.openTile(0, 0);
		assertEquals(Tile.State.OPEN, tile.getState());
	}

	@Test
	public void markTileTest() {
		Tile tile = field.getTile(0, 0);
		assertEquals(Tile.State.CLOSED, tile.getState());
		field.markTile(0, 0);
		assertEquals(Tile.State.MARKED, tile.getState());
		field.markTile(0, 0);
		assertEquals(Tile.State.CLOSED, tile.getState());
	}

	@Test
	public void isFailedWhenMineOpen() {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Mine) {
					field.openTile(row, column);
					assertEquals(GameState.FAILED, field.getState());
					return;
				}
			}
		}
	}
}
