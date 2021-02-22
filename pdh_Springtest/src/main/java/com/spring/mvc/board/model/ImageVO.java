package com.spring.mvc.board.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ImageVO {
	
		private Integer imageFileNO;
		private String imageFileName;
		private Date regDate;
		private int articleNO;
		
		public int getImageFileNO() {
			return imageFileNO;
		}
		public void setImageFileNO(Integer imageFileNO) {
			this.imageFileNO = imageFileNO.intValue();
		}
		
		
		public String getImageFileName() {
			return imageFileName;
		}
		public void setImageFileName(String imageFileName) {
//			try {
//				if(imageFileName!= null && imageFileName.length()!=0) {
//					this.imageFileName = URLEncoder.encode(imageFileName,"UTF-8");
//				}
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
			this.imageFileName =imageFileName;
		}
		
		public Date getRegDate() {
			return regDate;
		}
		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}
		public int getArticleNO() {
			return articleNO;
		}
		public void setArticleNO(int articleNO) {
			
						
			this.articleNO = articleNO;
		}
	
	
}
