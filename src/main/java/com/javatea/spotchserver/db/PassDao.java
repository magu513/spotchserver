package com.javatea.spotchserver.db;

import com.javatea.spotchserver.objects.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBでのパスワードを扱うクラス
 */
@Component
public class PassDao {
	/** DB接続を保持する */
	@Autowired
	private DBConnector connector;

	/**
	 * パスワードをDBに登録する
	 * @param id ユーザID
	 * @param password パスワード
	 * @param salt ソルト
	 * @throws SQLException 登録に失敗した場合に発生する例外
	 */
	/*TODO rollback周りの実装をどうにかする*/
	public void insert(long id,String password,String salt) throws SQLException {
		String sql = "INSERT INTO password VALUES (?,?,?)";
		PreparedStatement stmt = connector.getStatement(sql);
		stmt.setLong(1,id);
		stmt.setString(2,password);
		stmt.setString(3,salt);

		stmt.executeUpdate();
	}

	/**
	 * パスワードを検索する
	 * @param id ユーザID
	 * @return パスワードを保持したPasswordインスタンス
	 * @throws SQLException 検索に失敗した場合に発生する例外
	 */
	public Password find(long id) throws SQLException {
		String sql = "SELECT pass,salt FROM password where user_id = ?";
		PreparedStatement stmt = connector.getStatement(sql);
		stmt.setLong(1,id);

		ResultSet rs = stmt.executeQuery();

		Password password = new Password();
		while (rs.next()) {
			password.setPassword(rs.getString("pass"));
			password.setSalt(rs.getString("salt"));
		}

		return password;
	}

	public void delete(long id) throws SQLException {
		String sql = "DELETE FROM password where user_id = ?";
		try {
			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			connector.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connector.rollback();
		}
	}
}
