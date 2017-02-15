package com.javatea.spotchserver.db;

import com.javatea.spotchserver.opt.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class SessionDao {
	@Autowired
	private DBConnector connector;

	public String insert(long id) {
		String sql = "INSERT INTO session VALUES (?,?,?)";
		LocalDateTime now = LocalDateTime.now();
		String token = Hash.getHashStr(id + now.toString());
		try {
			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setLong(1,id);
			stmt.setString(2,token);
			stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().plusDays(5)));
			connector.commit();
		} catch (SQLException e) {
			token = "";
			try {
				connector.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return token;
	}

	public void update(long id) {
		String sql = "UPDATE session SET token = ?,expiration_date = ? where user_id = ?";

		try {
			PreparedStatement stmt = connector.getStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
