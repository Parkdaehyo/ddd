package com.spring.mvc.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.board.repository.BoardDAO;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.board.model.ArticleVO;
import com.spring.mvc.board.model.ImageVO;


@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	
	/*
	 * @Override public Map viewArticle(int articleNO) throws Exception { Map
	 * articleMap = new HashMap(); ArticleVO articleVO =
	 * boardDAO.selectArticle(articleNO); //글 조회문 System.out.println("articleVO" +
	 * articleVO); List<ImageVO> imageFileList =
	 * boardDAO.selectImageFileList(articleNO); articleMap.put("article",
	 * articleVO); articleMap.put("imageFileList", imageFileList); return
	 * articleMap; }
	 */
   
	public List selectImageFileList3() throws DataAccessException {
	
		return boardDAO.selectImageFileList3();
	}

	
	
	public List selectImageFileNO(int articleNO) throws DataAccessException {
		System.out.println("서비스 selectImageFileNO articleNO: " + articleNO);
	
		return boardDAO.selectImageFileNO(articleNO);
	}

	
	
	
	public List<ArticleVO> listArticles(PageVO page) throws Exception{
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList(page);
        return articlesList;
	}


	public Integer countArticles(PageVO page) {
		
		return boardDAO.countArticles(page);
	}


//	@Override
//	public int addNewArticle(Map articleMap) throws Exception{
//		return boardDAO.insertNewArticle(articleMap);
//	}
	
	 //���� �̹��� �߰��ϱ�
	
	//글쓰기

	public int addNewArticle(Map articleMap) throws Exception{
		int articleNO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("articleNO", articleNO);
		boardDAO.insertNewImage(articleMap);
		return articleNO;
	}

	
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}
	
	public List selectImageFileList(int articleNO) throws DataAccessException {		
		
		return boardDAO.selectImageFileList(articleNO);
	}
	

	
	
	/*
	//글 수정
	@Override
	public void modArticle(Map articleMap) throws Exception {
		//int articleNO = boardDAO.updateArticle(articleMap);
		//articleMap.put("articleNO", articleNO);
		boardDAO.updateNewImage(articleMap);
		return articleNO;
		
	}
	*/

	
	//다중 이미지
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		ArticleVO articleVO = boardDAO.selectArticle(articleNO); //글 조회문
		System.out.println("articleVO" + articleVO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		//articleMap.compute("imageFileNO", )
		return articleMap;
	}
   
	
	
	 //���� ���� ���̱�
//	@Override
//	public ArticleVO viewArticle(int articleNO) throws Exception {
//		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
//		return articleVO;
//	}
	


	

	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}



	
}
