package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.mvc.board.model.ArticleVO;
import com.spring.mvc.commons.PageVO;


public interface BoardDAO {
	public List selectAllArticlesList(PageVO page) throws DataAccessException;
	
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public void insertNewImage(Map articleMap) throws DataAccessException;
	
	//다중 이미지 수정문
	public void updateNewImage(Map articleMap) throws DataAccessException;
	
	//글번호 구하기
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	
	//글 수정문
	//public void updateArticle(Map articleMap) throws DataAccessException;
	
	public void updateArticle(Map articleMap) throws DataAccessException;
	
	//이미지 조회문
	public List selectImageFileList(int articleNO) throws DataAccessException;
	
	//이미지 전체 조회문
	public List selectImageFileList3() throws DataAccessException;
	
	public List selectImageFileNO(int articleNO) throws DataAccessException;
	
	public void deleteArticle(int articleNO) throws DataAccessException;

	public Integer countArticles(PageVO page) throws DataAccessException;

	
	//게시물 조회수 상승처리
    //void updateViewCnt(Integer articleNO);
	
	

}
