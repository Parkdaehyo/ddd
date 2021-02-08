package com.spring.mvc.board.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.mvc.board.model.ArticleVO;
import com.spring.mvc.board.model.ImageVO;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;


@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

//	@Override
//	public List selectAllArticlesList() throws DataAccessException {
//		List<ArticleVO> articlesList = articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
//		return articlesList;
//	}


	@Override
public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
		System.out.println("BoardDAOImpl의 aritlceMap" + articleMap);
		
		
		List<ImageVO> imageFileList = (List) articleMap.get("imageFileList");
		
		 for(ImageVO imageVO : imageFileList) { 
		String imageFileName = imageVO.getImageFileName();
		int articleNO = imageVO.getArticleNO(); 
		int imageFileNO = imageVO.getImageFileNO(); 
		Date regDate = imageVO.getRegDate();
		  
		 System.out.println("DAO" + imageFileName); 
		 System.out.println("DAO"+ imageFileNO); 
		 System.out.println("DAO" +articleNO);
		 System.out.println("DAO" +regDate);
		 }
		sqlSession.update("mapper.board.updateImage", imageFileList);
	}
	
	/*
	//글 수정문
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
	}
	*/
	

	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		return articleNO;
	}

   
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int articleNO = (Integer)articleMap.get("articleNO");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setArticleNO(articleNO);
			System.out.println("파일 이름" + imageVO.getImageFileName());	
		}
		
		sqlSession.insert("mapper.board.insertNewImage",imageFileList);
	}
	
	/*
	@Override
	public void updateNewImage(Map articleMap) throws DataAccessException {
	
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int articleNO = (Integer)articleMap.get("articleNO");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setArticleNO(articleNO);
			System.out.println("파일 이름" + imageVO.getImageFileName());	
		}
		sqlSession.update("mapper.board.updateNewImage",imageFileList);
	}
	*/
 
	
//	@Override
//	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
//		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
//	}


	

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
		
	}
	
	//다중이미지
	@Override
	public List selectImageFileList(int articleNO) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList",articleNO);
		return imageFileList;
	}
	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}

	@Override
	public List selectAllArticlesList(SearchVO search) throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList", search);
		return articlesList;
	} 

	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}


	@Override
	public Integer countArticles(SearchVO search) throws DataAccessException {
	
		return sqlSession.selectOne("mapper.board.countArticles", search);
	}

	@Override
	public void updateNewImage(Map articleMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}




	
	

}
