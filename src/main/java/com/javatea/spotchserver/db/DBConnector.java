package com.javatea.spotchserver.db;

import com.javatea.spotchserver.config.DBConf;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

public class DBConnector {
	@Autowired
	DBConf dbconf;
	private static DBConnector dbConnector = new DBConnector();
	private Connection conn = null;

	private DBConnector() {
		createConnection();
	}

	static DBConnector getInstance() {
		return dbConnector;
	}

	private void createConnection() {
		String url = dbconf.getUrl();
		String username = dbconf.getUser();
		String password = dbconf.getPass();

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url,username,password);

			conn.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			//TODO 例外処理の記述
			e.printStackTrace();
		}
	}

	Statement getStatement() throws SQLException {
		return conn.createStatement();
	}

	Connection getConnection() {
		return conn;
	}

	PreparedStatement getStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			//TODO なにかしらの例外処理を記述する
			e.printStackTrace();
		}
	}

	void commit() throws SQLException {
		conn.commit();
	}

	void rollback() throws SQLException {
		conn.rollback();
	}
}
