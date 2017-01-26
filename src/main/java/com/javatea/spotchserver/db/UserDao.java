package com.javatea.spotchserver.db;

import com.javatea.spotchserver.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao implements Dao<User> {
	@Override
	public User find(long id) {
		User user = null;
		try {
			String sql = "SELECT * FROM user where id = ?";
			PreparedStatement stmt = CONNECTOR.getStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("id");
				String name = rs.getString("name");
				String mail = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				Date createAt = rs.getDate("create_at");
				short status = rs.getShort("status");

				user = new User(userId,name,mail,birthday,createAt,status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM user";
			PreparedStatement stmt = CONNECTOR.getStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				Date createAt = rs.getDate("create_at");
				short status = rs.getShort("status");
				list.add(new User(id,name,email,birthday,createAt,status));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void update(User object) {

	}

	@Override
	public void delete(User object) {

	}

	@Override
	public void insert(User object) {
		Connection conn = CONNECTOR.getConnection();
		String sql = "INSERT INTO users (name,email,birthday,status) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, object.getUserName());
			ps.setString(2, object.getEmail());
			ps.setDate(3, (java.sql.Date) object.getBirthDate());
			ps.setShort(4,object.getStatus());

			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
