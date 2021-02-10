package com.spring.mvc.board.service;

import java.util.List;
import java.util.Map;

import com.spring.mvc.board.model.ArticleVO;
import com.spring.mvc.commons.PageVO;

public interface BoardService {

	public List<ArticleVO> listArticles(PageVO page) throws Exception;

	public int addNewArticle(Map articleMap) throws Exception;

	public void modArticle(Map articleMap) throws Exception;

	// public ArticleVO viewArticle(int articleNO) throws Exception;
	public Map viewArticle(int articleNO) throws Exception;

	public void removeArticle(int articleNO) throws Exception;

	public Integer countArticles(PageVO page) throws Exception;
}
