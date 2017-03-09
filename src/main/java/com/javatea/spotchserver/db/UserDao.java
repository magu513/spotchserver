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
	 * @param id ユーザーID
	 * @return 該当するユーザーのインスタンス
	 */
	public User find(long id) {
		User user = null;
		try {
			String sql = "SELECT * FROM users where id = ?";
			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setLong(1,id);
			ResultSet rs = stmt.executeQuery();

			user = getUserObj(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * メールアドレスからユーザーを検索する
	 * @param mail メールアドレス
	 * @return 該当するユーザーのインスタンス
	 */
	public User findWhereMail(String mail) {
		User user = null;
		try {
			String sql = "SELECT * FROM users where email = ?";
			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setString(1,mail);
			ResultSet rs = stmt.executeQuery();

			user = getUserObj(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 全ユーザ情報を取得する
	 * @return ユーザインスタンスが入ったList
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
				LocalDateTime birthday = rs.getTimestamp("birthday").toLocalDateTime();
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
	 * @param object 登録するユーザのインスタンス
	 * @return ユーザIDを付与したユーザインスタンス
	 * @throws SQLException 登録に失敗した場合に発生する例外
	 */
	public User insert(User object) throws SQLException {
		String sql = "INSERT INTO users (name,email,birthday,created_at,updated_at,status) VALUES (?,?,?,?,?,?) returning id";
		long id = 0;
		object.setCreateAt(LocalDateTime.now());
		PreparedStatement ps = connector.getStatement(sql);

		ps.setString(1, object.getUserName());
		ps.setString(2, object.getEmail());
		ps.setDate(3, Date.valueOf(object.getBirthDay().toLocalDate()));
		ps.setTimestamp(4, Timestamp.valueOf(object.getCreateAt()));
		ps.setTimestamp(5, Timestamp.valueOf(object.getCreateAt()));
		ps.setShort(6,(short) 1);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			object.setUserId(rs.getLong("id"));
		}
		connector.commit();

		return object;
	}

	/**
	 * User情報を更新するクラス
	 * @param user ユーザー情報を変更済みのユーザインスタンス
	 */
	public void update(User user){
		/* TODO 更新処理 */
		String sql = "UPDATE users SET name = ?,email = ?,birthday = ?,created_at = ?,updated_at = ?,status = ? where id = ?";
		try {
			PreparedStatement ps = connector.getStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getEmail());
			ps.setDate(3, Date.valueOf(user.getBirthDay().toLocalDate()));
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

	/**
	 * ResultSetからユーザー情報を取得する
	 * @param rs DBへの問い合わせの結果
	 * @return Userインスタンス
	 * @throws SQLException 処理に失敗した場合に発生する例外
	 */
	private User getUserObj(ResultSet rs) throws SQLException {
		User user = null;
		while (rs.next()) {
			long userId = rs.getLong("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			LocalDateTime birthday = rs.getTimestamp("birthday").toLocalDateTime();
			LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
			LocalDateTime updateAt = rs.getTimestamp("updated_at").toLocalDateTime();
			short status = rs.getShort("status");

			user = new User(userId,name,email,birthday,createAt,updateAt,status);
		}
		return user;
	}
}
