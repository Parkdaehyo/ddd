package com.spring.mvc.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.board.repository.BoardDAO;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.commons.SearchVO;
import com.spring.mvc.board.model.ArticleVO;
import com.spring.mvc.board.model.ImageVO;


@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl  implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	public List<ArticleVO> listArticles(SearchVO search) throws Exception{
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList(search);
        return articlesList;
	}

	@Override
	public Integer countArticles(SearchVO search) {
		
		return boardDAO.countArticles(search);
	}


//	@Override
//	public int addNewArticle(Map articleMap) throws Exception{
//		return boardDAO.insertNewArticle(articleMap);
//	}
	
	 //���� �̹��� �߰��ϱ�
	
	//글쓰기
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		int articleNO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("articleNO", articleNO);
		boardDAO.insertNewImage(articleMap);
		return articleNO;
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
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
	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		ArticleVO articleVO = boardDAO.selectArticle(articleNO); //글 조회문
		System.out.println("articleVO" + articleVO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
   
	
	
	 //���� ���� ���̱�
//	@Override
//	public ArticleVO viewArticle(int articleNO) throws Exception {
//		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
//		return articleVO;
//	}
	


	
	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}
	

	
}
