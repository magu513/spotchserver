package com.javatea.spotchserver.db;

import java.sql.*;

public class DBConnector {
	private static DBConnector dbConnector = new DBConnector();
	private Connection conn = null;

	private String url = "jdbc:postgresql://localhost:5432/spotch";
	private String username = "spotch_admin";
	private String password = "ju78ik";

	private DBConnector() {
		createConnection();
	}

	static DBConnector getInstance() {
		return dbConnector;
	}

	private void createConnection() {
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
