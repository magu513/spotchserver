package com.javatea.spotchserver.db;

import com.javatea.spotchserver.objects.PreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class PreUserDao {
	@Autowired
	private DBConnector connector;

	public void insert(PreUser p) {
		String sql = "INSERT INTO preuser VALUES (?,?,?)";
		try {
			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setLong(1,p.getUserId());
			stmt.setTimestamp(2, Timestamp.valueOf(p.getExpirationDate()));
			stmt.setString(3,p.getToken());

			connector.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connector.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public long find(String token) {
		String sql = "SELECT user_id FROM preuser where token = ?";
		long userId = 0;
		try {
			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setString(1,token);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				userId = rs.getLong("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userId;
	}

	public void delete(long userId) {
		String sql = "DELETE FROM preuser where user_id = ?";

		try {
			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setLong(1,userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			try {
				connector.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
