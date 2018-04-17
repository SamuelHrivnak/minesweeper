package minesweeper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {
	private final int rowCount;
	private final int columnCount;
	private final int mineCount;

	public static final Settings BEGGINER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);
	// private static final String SETTING_FILE = System.getProperty("user.home")
	// + System.getProperty("file.separator") + "minesweeper.settings";
	private static final String SETTING_FILE = "out.bin";

	public Settings(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	public void save() {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SETTING_FILE))) {
			objectOutputStream.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Settings load() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SETTING_FILE))) {
			return (Settings) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return EXPERT;
		}
	}

	@Override
	public int hashCode() {
		return rowCount * columnCount * mineCount;
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof Settings)) {
			return false;
		}
		Settings os = (Settings) arg0;
		return rowCount == os.getRowCount() && columnCount == os.getColumnCount() && mineCount == os.getMineCount();
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

}
