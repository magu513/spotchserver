package com.javatea.spotchserver.db;

import com.javatea.spotchserver.Article;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class ArticleDao implements Dao {
	public List<Article> findArticleAroundMe(double x,
											 double y,
											 double range) {
		List<Article> list = new ArrayList<>();
		try {
			String sql = "SELECT article_id,user_id,content,ST_AsText(point) AS location,created_at ";
			sql += "FROM article WHERE ST_DWithin(point,ST_GeographyFromText(?),?)";

			PreparedStatement stmt = CONNECTOR.getStatement(sql);
			stmt.setString(1,"POINT("+x+" "+y+")");
			stmt.setDouble(2,range);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long articleId = rs.getLong("article_id");
				long userId = rs.getLong("user_id");
				String content = rs.getString("content");
				String location = rs.getString("location");
				String createAt = rs.getString("created_at");

				list.add(new Article(articleId, userId, location, content, createAt));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Article> findByUserId(long userId) {
		List<Article> list = new ArrayList<>();
		try {
			String sql = "SELECT article_id,user_id,content,ST_AsText(point) AS point,created_at ";
			sql += "FROM article WHERE user_id = ?";

			PreparedStatement stmt = CONNECTOR.getStatement(sql);
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long articleId = rs.getLong("article_id");
				String content = rs.getString("content");
				String location = rs.getString("point");
				String createAt = rs.getString("created_at");
				list.add(new Article(articleId, userId, location, content, createAt));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public void delete(long id) throws SQLException {
		String sql = "DELETE FROM article where article_id = ?";
		PreparedStatement ps = null;
		try {
			ps = CONNECTOR.getStatement(sql);
			ps.setLong(1,id);
			ps.executeUpdate();

			CONNECTOR.commit();
		} catch (SQLException e) {
			try {
				CONNECTOR.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			throw new SQLException();
		}
	}

	public void insert(Article object) throws SQLException {
		String sql = "INSERT INTO article(user_id,content,point,created_at) ";
		sql += "VALUES (?,?,ST_GeomFromText(?,4326),?)";

		try {
			PreparedStatement ps = CONNECTOR.getStatement(sql);
			ps.setLong(1,object.getUserId());
			ps.setString(2,object.getContent());
			ps.setString(3,"POINT("+object.getX()+" "+object.getY()+")");

			String datestr = object.getCreateAt();
			Calendar cal = new GregorianCalendar();
			cal.setTime(DateFormat.getDateInstance().parse(datestr.replaceAll("-","/")));
			ps.setDate(4,new java.sql.Date(cal.getTimeInMillis()));
			ps.executeUpdate();
			CONNECTOR.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				CONNECTOR.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			throw new SQLException();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
