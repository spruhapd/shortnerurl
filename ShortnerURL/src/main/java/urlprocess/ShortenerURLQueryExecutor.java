package urlprocess;

import model.ShortenerURLInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShortenerURLQueryExecutor {

	/**
	 * Loading the cache for URLS.
	 */
	public static ShortenerURLSCache loadURLSCache() {
		Statement stmt = null;
		Connection c = null;
		ShortenerURLSCache uc = new ShortenerURLSCache();

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:core.db");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM urls");
			while (rs.next()) {
				uc.put(rs.getString("URL"), new ShortenerURLInfo(rs.getString("URL"), rs.getInt("REDIRECT_TYPE"),
						rs.getString("SHORT_URL"), rs.getInt("COUNTER")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(stmt, c);
		}
		return uc;
	}

	public static int update(String query) {
		Statement stmt = null;
		Connection c = null;
		int rows = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:core.db");
			stmt = c.createStatement();
			rows = stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(stmt, c);
		}

		return rows;
	}

	private static void closeConnection(Statement stmt, Connection c) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}

}
