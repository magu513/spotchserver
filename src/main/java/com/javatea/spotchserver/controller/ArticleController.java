package com.javatea.spotchserver.controller;

import com.javatea.spotchserver.Article;
import com.javatea.spotchserver.db.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ArticleController {
	@Autowired
	ArticleDao ad;

	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public List<Article> read(@RequestParam double x,
							  @RequestParam double y,
							  @RequestParam double range) {
		return ad.findArticleAroundMe(x, y, range);
	}


	@RequestMapping(value = "/articles", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity create(@RequestParam long id,
								 @RequestParam String content,
								 @RequestParam double x,
								 @RequestParam double y) {

		/*
		TODO ユーザの認証の処理を行う
		 */
		HttpStatus status;
		try {
			ad.insert(new Article(id, x, y, content));

			status = HttpStatus.CREATED;
		} catch (SQLException e) {
			status = HttpStatus.CONFLICT;
			e.printStackTrace();
		}
		return new ResponseEntity(status);
	}

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
