package com.spring.mvc.board.repository;

import java.util.List;

import com.spring.mvc.board.model.ArticleVO;

public interface IBoardMapper {

	

		void insert(ArticleVO article);
		

		List<ArticleVO> getArticleList();
		
	
		ArticleVO getArticle(Integer boardNo);
		
	
		void update(ArticleVO article);

	
		void delete(Integer boardNo);
		

	
}
