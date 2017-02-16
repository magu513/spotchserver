package com.javatea.spotchserver.db;

import com.javatea.spotchserver.objects.websocket.FavoriteMessage;
import com.javatea.spotchserver.objects.websocket.FindMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class FavoriteDao {
	@Autowired
	private DBConnector connector;
	public void insert(FavoriteMessage message) throws SQLException{
		String sql = "INSERT INTO favorite VALUES (?,?,1)";
		PreparedStatement stmt = connector.getStatement(sql);

		stmt.setLong(1,message.getUserId());
		stmt.setLong(2,message.getArticleId());
		stmt.executeUpdate();
	}

	public void delete(FavoriteMessage message) throws SQLException {
		String sql = "DELETE FROM favorite where user_id = ? and article_id = ?";
		PreparedStatement stmt = connector.getStatement(sql);

		stmt.setLong(1,message.getUserId());
		stmt.setLong(2,message.getArticleId());
		stmt.executeUpdate();
	}
}
