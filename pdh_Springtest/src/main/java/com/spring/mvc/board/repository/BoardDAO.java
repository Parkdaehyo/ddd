package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.mvc.board.model.ArticleVO;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;


public interface BoardDAO {
	public List selectAllArticlesList(SearchVO search) throws DataAccessException;
	
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public void insertNewImage(Map articleMap) throws DataAccessException;
	
	//다중 이미지 수정문
	public void updateNewImage(Map articleMap) throws DataAccessException;
	
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	
	//글 수정문
	//public void updateArticle(Map articleMap) throws DataAccessException;
	
	public void updateArticle(Map articleMap) throws DataAccessException;
	
	
	public void deleteArticle(int articleNO) throws DataAccessException;
	public List selectImageFileList(int articleNO) throws DataAccessException;
	public Integer countArticles(SearchVO search) throws DataAccessException;
	
	//게시물 조회수 상승처리
    //void updateViewCnt(Integer articleNO);
	
	
	
	
}
