package com.javatea.spotchserver.db;

import com.javatea.spotchserver.objects.websocket.FavoriteMessage;
import com.javatea.spotchserver.objects.websocket.FindMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * お気に入りをDBで扱うためのクラス
 */
@Component
public class FavoriteDao {
	/** DB接続を保持する */
	@Autowired
	private DBConnector connector;

	/**
	 * お気に入り情報をDBに登録する
	 * @param message ユーザIDと投稿IDを保持したFavoriteMessageインスタンス
	 * @throws SQLException 登録に失敗した場合に発生する例外
	 */
	public void insert(FavoriteMessage message) throws SQLException{
		String sql = "INSERT INTO favorite VALUES (?,?,1)";
		PreparedStatement stmt = connector.getStatement(sql);

		stmt.setLong(1,message.getUserId());
		stmt.setLong(2,message.getArticleId());
		stmt.executeUpdate();
	}

	/**
	 * お気に入りを削除する
	 * @param message ユーザIDと投稿IDを保持したFavoriteMessageインスタンス
	 * @throws SQLException 削除に失敗した場合に発生する例外
	 */
	public void delete(FavoriteMessage message) throws SQLException {
		String sql = "DELETE FROM favorite where user_id = ? and article_id = ?";
		PreparedStatement stmt = connector.getStatement(sql);

		stmt.setLong(1,message.getUserId());
		stmt.setLong(2,message.getArticleId());
		stmt.executeUpdate();
	}
}
