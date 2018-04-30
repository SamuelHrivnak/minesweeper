package minesweeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
	/** List of best player times. */
	private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	private static final String URL = "jdbc:postgresql://localhost/register_database";
	private static final String USER = "postgres";
	private static final String PASSWORD = "mastic1994";

	
	
	
	/**
	 * 
	 */
	public BestTimes() {
		loadBestTimes();
	}


	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	public Iterator<PlayerTime> iterator() {
		return playerTimes.iterator();
	}

	
	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name ot the player
	 * @param time
	 *            player time in seconds
	 */
	public void addPlayerTime(String name, int time) {
		PlayerTime playerTime = new PlayerTime(name, time);
		playerTimes.add(playerTime);
		Collections.sort(playerTimes);
	}

	public void resetTimes() {
		playerTimes = new ArrayList<PlayerTime>();
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		return null;
	}

	public void saveBestTimes() {
		String DROP_STATEMENT = "DROP TABLE register.best_times";
		String INSERT_INTO_STATEMENT = "INSERT INTO register.best_times (name, time) VALUES (? , ?)";
		String CREATE_TABLE_STATEMENT = "CREATE TABLE register.best_times (name varchar(64), time int)";
		
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STATEMENT);
				Statement statement_update = connection.createStatement();	
						){
			
				statement_update.executeUpdate(DROP_STATEMENT);
				statement_update.executeUpdate(CREATE_TABLE_STATEMENT);
				
				
				for (PlayerTime playerTime : playerTimes) {
					statement.setString(1,playerTime.name);
					statement.setInt(2, playerTime.time);	
					statement.executeUpdate();
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 

	

	public void loadBestTimes() {
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement("SELECT name, time FROM register.best_times");
				ResultSet rs = statement.executeQuery()){			
				while(rs.next()) {		
					playerTimes.add(new PlayerTime(rs.getString(1), rs.getInt(2)));
				}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void printBestTimes() {
		for (PlayerTime playerTime : playerTimes) {
			System.out.println(playerTime.name +", "+ playerTime.time);
		}
	}

	/**
	 * Player time.
	 */
	public static class PlayerTime implements Comparable<PlayerTime> {
		/** Player name. */
		private final String name;

		/** Playing time in seconds. */
		private final int time;

		/**
		 * Constructor.
		 * 
		 * @param name
		 *            player name
		 * @param time
		 *            playing game time in seconds
		 */
		public PlayerTime(String name, int time) {
			this.name = name;
			this.time = time;
		}

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}

		@Override
		public int compareTo(PlayerTime arg0) {

			return this.getTime() - arg0.getTime();
		}

	}

}