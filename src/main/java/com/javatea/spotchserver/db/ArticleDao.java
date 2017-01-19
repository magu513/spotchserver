package com.javatea.spotchserver.db;

import com.javatea.spotchserver.Article;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class ArticleDao implements Dao<Article> {
	public List<Article> findArticleAroundMe(double x,
											 double y,
											 double range) {
		List<Article> list = new ArrayList<>();
		try {
			String sql = "SELECT articleid,userid,content,ST_AsText(point) AS location,postdate ";
			sql += "FROM article WHERE ST_DWithin(point,ST_GeographyFromText('POINT(";
			sql += BigDecimal.valueOf(x) + " " + BigDecimal.valueOf(y) + ")'),"+range+")";
			PreparedStatement stmt = CONNECTOR.getStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long articleId = rs.getLong("articleid");
				long userId = rs.getLong("userid");
				String content = rs.getString("content");
				String location = rs.getString("location");
				String postDate = rs.getString("postdate");

				list.add(new Article(articleId, userId, location, content, postDate));
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
			String sql = "SELECT articleid,userid,content,ST_AsText(point) AS point,postdate ";
			sql += "FROM article WHERE userid = ?";

			PreparedStatement stmt = CONNECTOR.getStatement(sql);
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				long articleId = rs.getLong("artcleid");
				String content = rs.getString("content");
				String location = rs.getString("point");
				String postdate = rs.getString("postdate");
				list.add(new Article(articleId, userId, location, content, postdate));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Article find(long id) {
		return null;
	}

	@Override
	public List<Article> findAll() {
		return null;
	}

	@Override
	public void update(Article object) {
	}

	@Override
	public void delete(Article object) {

	}

	public void delete(long id) throws SQLException {
		String sql = "DELETE FROM article where articleid = ?";
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


	@Override
	public void insert(Article object) throws SQLException {
		String sql = "INSERT INTO article(userid,content,point,postdate) ";
		sql += "VALUES (?,?,ST_GeomFromText(?,4326),?)";

		try {
			PreparedStatement ps = CONNECTOR.getStatement(sql);
			ps.setLong(1,object.getUserId());
			ps.setString(2,object.getContent());
			ps.setString(3,"POINT("+object.getLocationX()+" "+object.getLocationY()+")");

			String datestr = object.getPostDate();
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
