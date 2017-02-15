package com.javatea.spotchserver.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class PassDao {
	@Autowired
	private DBConnector CONNECTOR;

	/*TODO rollback周りの実装をどうにかする*/
	public void insert(long id,String password,String salt) {
		String sql = "INSERT INTO password VALUES (?,?,?)";
		try {
			PreparedStatement stmt = CONNECTOR.getStatement(sql);
			stmt.setLong(1,id);
			stmt.setString(2,password);
			stmt.setString(3,salt);

			stmt.executeUpdate();
			CONNECTOR.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				CONNECTOR.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void delete(long id) throws SQLException {
		String sql = "DELETE FROM password where user_id = ?";
		try {
			PreparedStatement stmt = CONNECTOR.getStatement(sql);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			CONNECTOR.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CONNECTOR.rollback();
		}
	}
}
