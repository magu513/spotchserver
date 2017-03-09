package com.javatea.spotchserver.db;

import com.javatea.spotchserver.config.DBConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * DBとの接続を確立するクラス
 */
@Component
public class DBConnector {
	/** DBとのコネクションを保持する */
	private Connection conn = null;
	/** DBのコンフィグを保持する */
	private final DBConf dbConf;


	@Autowired
	private DBConnector(DBConf dbConf) {
		this.dbConf = dbConf;
		createConnection();
	}

	/**
	 * DBとの接続を確立する
	 */
	private void createConnection() {
		String url = "jdbc:postgresql://"+dbConf.getUrl()+"/"+dbConf.getDbName();

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url,dbConf.getUser(),dbConf.getPass());

			conn.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			//TODO 例外処理の記述
			e.printStackTrace();
		}
	}

	Statement getStatement() throws SQLException {
		return conn.createStatement();
	}

	/**
	 * ConnectionからPreparedStatementオブジェクトを取得する
	 * @param sql SQL文
	 * @return SQL文を設定したPreparedStatement型インスタンス
	 * @throws SQLException インスタンスの生成に失敗した場合に発生する例外
	 */
	PreparedStatement getStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}


	/**
	 * Connectionを閉じる
	 */
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

	/**
	 * DBのコミットを行う
	 * @throws SQLException コミットに失敗した場合に発生する例外
	 */
	public void commit() throws SQLException {
		conn.commit();
	}

	/**
	 * DBのロールバックを行う
	 * @throws SQLException ロールバックに失敗した場合に発生する例外
	 */
	public void rollback() throws SQLException {
		conn.rollback();
	}
}
