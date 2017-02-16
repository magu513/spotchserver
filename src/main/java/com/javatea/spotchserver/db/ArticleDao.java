package com.javatea.spotchserver.db;

import com.javatea.spotchserver.objects.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 投稿情報をDBで扱うためのクラス
 */
@Component
public class ArticleDao {
	@Autowired
	private DBConnector connector;

	/**
	 * ユーザーの周囲の投稿を取得する
	 * @param x
	 * @param y
	 * @param range
	 * @return
	 */
	public List<Article> findArticleAroundMe(double x,
											 double y,
											 double range) {
		List<Article> list = new ArrayList<>();
		try {
			String sql = "SELECT article_id,user_id,content,ST_AsText(point) AS location,created_at," +
					"CASE WHEN article.article_id = favorite.article_id AND users.id = favorite.user_id THEN 'true'" +
					"else 'false' end as fav";
			sql += "FROM article,users,favorite WHERE ST_DWithin(point,ST_GeographyFromText(?),?) order by article_id desc";

			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setString(1,"POINT("+x+" "+y+")");
			stmt.setDouble(2,range);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long articleId = rs.getLong("article_id");
				long userId = rs.getLong("user_id");
				String content = rs.getString("content");
				String location = rs.getString("location");
				String createAt = rs.getString("created_at");
				boolean favorite = rs.getBoolean("fav");
				list.add(new Article(articleId, userId, location, content, createAt,favorite));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 指定したユーザーの投稿を取得する
	 * @param userId
	 * @return
	 */
	public List<Article> findByUserId(long userId) {
		List<Article> list = new ArrayList<>();
		try {
			String sql = "SELECT article_id,user_id,content,ST_AsText(point) AS point,created_at ";
			sql += "FROM article WHERE user_id = ?";

			PreparedStatement stmt = connector.getStatement(sql);
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long articleId = rs.getLong("article_id");
				String content = rs.getString("content");
				String location = rs.getString("point");
				String createAt = rs.getString("created_at");
				list.add(new Article(articleId, userId, location, content, createAt,false));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 指定した投稿を削除する
	 * @param id
	 * @throws SQLException
	 */
	public void delete(long id) throws SQLException {
		String sql = "DELETE FROM article where article_id = ?";
		PreparedStatement ps = null;
		try {
			ps = connector.getStatement(sql);
			ps.setLong(1,id);
			ps.executeUpdate();

			connector.commit();
		} catch (SQLException e) {
			try {
				connector.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			throw new SQLException();
		}
	}

	/**
	 * 投稿を新規登録する
	 * @param object
	 * @throws SQLException
	 */
	public void insert(Article object) throws SQLException {
		String sql = "INSERT INTO article(user_id,content,point,created_at) ";
		sql += "VALUES (?,?,ST_GeomFromText(?,4326),?)";

		try {
			PreparedStatement ps = connector.getStatement(sql);
			ps.setLong(1,object.getUserId());
			ps.setString(2,object.getContent());
			ps.setString(3,"POINT("+object.getLatitude()+" "+object.getLongitude()+")");

			ps.setTimestamp(4, Timestamp.valueOf(object.getCreateAt()));
			ps.executeUpdate();
			connector.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connector.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			throw new SQLException();
		}
	}
}
