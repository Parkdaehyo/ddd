package com.spring.mvc.board.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.stereotype.Component;


/*
 * 
 * 이 주석은 스프링 구성요소를 나타내기 위해 클래스에 사용됩니다. 
 * @Component 주석은 Java 클래스를 bean 또는 component로 표시하여
 *  Spring의 구성 요소 검색 메커니즘이 애플리케이션 컨텍스트에 추가할 수 있도록 합니다.
 * 
 */

@Component("articleVO") 
public class ArticleVO {

	private int  level;
	private int articleNO;
	private String title;
	private String content;
	private String imageFileName;
	private String id;
	private Date  writeDate;
	private Integer viewCnt;
	private int board_type;
	
	
	
	public ArticleVO() {
	
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName!= null && imageFileName.length()!=0) {
				this.imageFileName = URLEncoder.encode(imageFileName,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	

	public Date getWriteDate() {
		return writeDate;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getBoard_type() {
		return board_type;
	}

	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}


	
	
	
	
	
	
	
	
}
