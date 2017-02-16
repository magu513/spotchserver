package com.javatea.spotchserver.db;

import com.javatea.spotchserver.objects.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PassDao {
	@Autowired
	private DBConnector connector;

	/*TODO rollback周りの実装をどうにかする*/
	public void insert(long id,String password,String salt) throws SQLException {
		String sql = "INSERT INTO password VALUES (?,?,?)";
		PreparedStatement stmt = connector.getStatement(sql);
		stmt.setLong(1,id);
		stmt.setString(2,password);
		stmt.setString(3,salt);

		stmt.executeUpdate();
	}

	public Password find(long id) throws SQLException {
		String sql = "SELECT pass,salt FROM password where id = ?";
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
