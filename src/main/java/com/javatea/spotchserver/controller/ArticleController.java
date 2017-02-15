package com.javatea.spotchserver.controller;

import com.javatea.spotchserver.db.ArticleDao;
import com.javatea.spotchserver.objects.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 投稿関係の通信
 */
@RestController
public class ArticleController {
	@Autowired
	private ArticleDao ad;

	/**
	 * ユーザの周囲の情報を取得する
	 * @param latitude
	 * @param longitude
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public List<Article> read(@RequestParam double latitude,
							  @RequestParam double longitude,
							  @RequestParam double range) {
		return ad.findArticleAroundMe(latitude, longitude, range);
	}

	/**
	 * 新規投稿を行う
	 * @param id
	 * @param content
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity create(@RequestParam long id,
								 @RequestParam String content,
								 @RequestParam double latitude,
								 @RequestParam double longitude) {

		/*
		TODO ユーザの認証の処理を行う
		TODO SQLインジェクション対策
		 */
		HttpStatus status;
		try {
			ad.insert(new Article(id, latitude, longitude, content));

			status = HttpStatus.CREATED;
		} catch (SQLException e) {
			status = HttpStatus.CONFLICT;
			e.printStackTrace();
		}
		return new ResponseEntity(status);
	}

	/**
	 * 投稿を削除する
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/articles/{id}",method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable long id) {
		/*
		TODO ユーザの認証の処理を行う
 		 */
		HttpStatus status;
		try {
			ad.delete(id);
			status = HttpStatus.NO_CONTENT;
		} catch (SQLException e) {
			e.printStackTrace();
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity(status);
	}
}
