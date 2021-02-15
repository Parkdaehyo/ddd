package com.spring.mvc.board.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.mvc.board.model.ArticleVO;
import com.spring.mvc.board.model.ImageVO;
import com.spring.mvc.commons.PageVO;


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
	public List selectImageFileList3() throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList3");
		return imageFileList;
	}


@Override
public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
		System.out.println("BoardDAOImpl의 aritlceMap" + articleMap);
		
		//int imageFileNO = selectNewImageFileNO();
	
		
		int _articleNO = (int) articleMap.get("articleNO"); //정상적으로 글번호 가져왔고.
 

		System.out.println("글번호다 _articleNO: " + _articleNO);
//		      
        //articleMap  = (Map) selectImageFileNO(_articleNO);
		
		
		//List<ImageVO> 
		List<ImageVO> imageFileList = (List) articleMap.get("imageFileList"); 
		
		
		//imageFileList = (List) articleMap.get("imageFileList");//여기서 글번호에 따른 imageFileNO들이 정확히 리턴된다.
		
		 
		 for(ImageVO imageVO : imageFileList) { // 팩트 1. imageFileList에 정상적으로 짱구와 고릴라가 넘어온다는것.
		
		String imageFileName = imageVO.getImageFileName();
		int articleNO = imageVO.getArticleNO(); 
		int imageFileNO = imageVO.getImageFileNO();
		  
		 System.out.println("DAO까지 파일이름이 넘어왔는가?: " + imageFileName); //imageFileName의 객체가 2개가 넘어오는것을 확인했는데
		 System.out.println("DAO:imageFileNO "+ imageFileNO); //36하고 //38
		 System.out.println("DAO:articleNO " +articleNO); //41
	
		 }
		 
		 //imageFileList = selectImageFileNO(_articleNO);
		//sqlSession.update("mapper.board.updateImage", imageFileList); 원래꺼
		 sqlSession.update("mapper.board.updateImage", articleMap); //팩트2. articleMap을 넘겨도 실제로 한개가 전부 수정이 될 뿐, 확실히 sql문까지 imageFileList으 객체 정보가 넘어간다는것.
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
		Map<String,Object> articleMap = new HashMap<String, Object>();
		articleMap.put("imageFileList", imageFileList);
		return imageFileList;
	}
	
	
	
	
	
	
	
	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}
	

	@Override
	public List selectAllArticlesList(PageVO page) throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList", page);
		return articlesList;
	} 
	
	
	//글번호 구하기
	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		sqlSession.update("mapper.board.updateViewCnt", articleNO);
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO); 
	}


	@Override
	public Integer countArticles(PageVO page) throws DataAccessException {
	
		return sqlSession.selectOne("mapper.board.countArticles", page);
	}

	@Override
	public void updateNewImage(Map articleMap) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List selectImageFileNO(int articleNO) throws DataAccessException {
		System.out.println("selectImageFileNO articleNO: " + articleNO);
		List<ImageVO> aaaList = sqlSession.selectList("mapper.board.selectImageFileNO", articleNO);
		return aaaList;
	}


	/*
	 * @Override public void updateViewCnt(Integer articleNO) throws
	 * DataAccessException {
	 * 
	 * sqlSession.update("mapper.board.updateViewCnt", articleNO);
	 * 
	 * }
	 */



	
	

}
