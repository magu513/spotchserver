package com.javatea.spotchserver.db;

import com.javatea.spotchserver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User情報をDBで扱うためのクラス
 */
@Component
public class UserDao {
	@Autowired
	private DBConnector connector;

	/**
	 * ユーザ情報を取得するクラス
	 * @param id
	 * @return
	 */
	public User find(long id) {
		User user = null;
		try {
			String sql = "SELECT * FROM user where id = ?";
			PreparedStatement stmt = connector.getStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("id");
				String name = rs.getString("name");
				String mail = rs.getString("email");
				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				LocalDateTime createAt = rs.getTimestamp("create_at").toLocalDateTime();
				LocalDateTime updateAt = rs.getTimestamp("update_at").toLocalDateTime();
				short status = rs.getShort("status");

				user = new User(userId,name,mail,birthday,createAt,updateAt,status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 全ユーザ情報を取得する
	 * @return
	 */
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM user";
			PreparedStatement stmt = connector.getStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
				LocalDateTime updateAt = rs.getTimestamp("updated_at").toLocalDateTime();
				short status = rs.getShort("status");
				list.add(new User(id,name,email,birthday,createAt,updateAt,status));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ユーザ情報を登録する
	 * @param object
	 * @return
	 */
	public User insert(User object) throws SQLException {
		String sql = "INSERT INTO users (name,email,birthday,createAt) VALUES (?,?,?,?) returning id";
		long id = 0;
		try {
			object.setCreateAt(LocalDateTime.now());
			PreparedStatement ps = connector.getStatement(sql);

			ps.setString(1, object.getUserName());
			ps.setString(2, object.getEmail());
			ps.setDate(3, Date.valueOf(object.getBirthDay()));
			ps.setTimestamp(4, Timestamp.valueOf(object.getCreateAt()));
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				object.setUserId(rs.getLong("id"));
			}
			connector.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connector.rollback();

			throw new SQLException();
		}

		return object;
	}

	/**
	 * User情報を更新するクラス
	 * @param user
	 */
	public void update(User user){
		/* TODO 更新処理 */
		String sql = "UPDATE users SET name = ?,email = ?,birthday = ?,created_at = ?,updated_at = ?,status = ? where id = ?";
		try {
			PreparedStatement ps = connector.getStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getEmail());
			ps.setDate(3, Date.valueOf(user.getBirthDay()));
			ps.setTimestamp(4, Timestamp.valueOf(user.getCreateAt()));
			ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			ps.setShort(6,user.getStatus());
			ps.setLong(7,user.getUserId());

			ps.executeUpdate();
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
}
