package com.spring.mvc.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.ImageVO;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.commons.PageCreator;
import com.spring.mvc.commons.PageVO;
import com.spring.mvc.board.model.ArticleVO;


@Controller
public class BoardController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";

	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	
	
	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String listArticles(HttpServletRequest request, HttpServletResponse response, Model model, PageVO page) throws Exception {
	
		String condition = page.getCondition();
		
		System.out.println("URL: /board/listArticles.do -> result: ");
		System.out.println("parameter(페이지번호) : " + page.getPage() +"번");
		System.out.println("검색 조건: " + condition);
		System.out.println("검색어: " + page.getKeyword());
	
		PageCreator pc = new PageCreator();
		pc.setPaging(page);
		
		List <ArticleVO>articlesList = boardService.listArticles(page);
		pc.setArticleTotalCount(boardService.countArticles(page));
		
		model.addAttribute("articlesList", articlesList);
		model.addAttribute("pc" , pc);
		
		return "listArticles";
		
	}
	
	@RequestMapping(value= "/board/listArticles2.0.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String listArticles2(HttpServletRequest request, HttpServletResponse response, Model model, PageVO page) throws Exception {
	
		String condition = page.getCondition();
		
		System.out.println("URL: /board/listArticles.do -> result: ");
		System.out.println("parameter(페이지번호) : " + page.getPage() +"번");
		System.out.println("검색 조건: " + condition);
		System.out.println("검색어: " + page.getKeyword());
	
		PageCreator pc = new PageCreator();
		pc.setPaging(page);
		
		List <ArticleVO>articlesList = boardService.listArticles(page);
		pc.setArticleTotalCount(boardService.countArticles(page));
		
		model.addAttribute("articlesList", articlesList);
		model.addAttribute("pc" , pc);
		
		return "listArticles2.0";
		
	}
	
	// 게시판 리스트
	@RequestMapping(value ="/adminA/board/FN1001LS" , method = RequestMethod.GET)
	public String FN1001LS() {
		
		return "FN1001LS";
	}
	
//	//글쓰기 페이지 진입
//	@RequestMapping(value ="/adminA/board/FN1001WR" , method = RequestMethod.GET)
//	public String FN1001WR() {
//		
//		
//		return "FN1001WR";
//	}
	
//		//글쓰기 DB 등록 요청
//		@RequestMapping(value ="/adminA/board/FN1001WR" , method = RequestMethod.POST)
//		public String FN1001WR(BoardVO article, RedirectAttributes ra) {
//			
//			service.insert(article);
//			ra.addFlashAttribute("msg","regSuccess");
//			
//			return "redirect:/FN1001LS";
//		}
//		
	
	
	//글쓰기 페이지 진입
	@RequestMapping(value ="/board/articleForm.do" , method = RequestMethod.GET)
	public String FN1001WR() {
		
		
		return "articleForm";
	}
	
	
	//게시글 상세 조회 요청
	@RequestMapping(value ="/adminA/board/FN1001VE" , method = RequestMethod.GET)
	public String FN1001VE() {
		
		
		return "FN1001VE";
	}
	
	
	//responseEntity: http의 요청 또는 응답을 포함하는 클래스.
	//상태코드, 응답 메시지 등이 포함될 수 있겠다.
	//그럴때 사용되는 것이 ResponseEntity Class 이다. 
	//
	
	// 다중 글 이미지 추가하기
	  @RequestMapping(value="/board/addNewArticle.do" ,method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		String imageFileName=null;
		
		Map articleMap = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		
		
		List<String> fileList =upload(multipartRequest);
		List<ImageVO> imageFileList = new ArrayList<ImageVO>();
		if(fileList!= null && fileList.size()!=0) {
			for(String fileName : fileList) {
				ImageVO imageVO = new ImageVO(); //판다.jpg
				imageVO.setImageFileName(fileName);
				imageFileList.add(imageVO);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int articleNO = boardService.addNewArticle(articleMap); //글쓰기
			if(imageFileList!=null && imageFileList.size()!=0) {
				for(ImageVO  imageVO:imageFileList) {
					imageFileName = imageVO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
					//destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
				}
			}
			    
			message = "<script>";
			message += " alert('등록 되었습니다.');";
			System.out.println("여기타는가?");
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do';";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    
			 
		}catch(Exception e) {
			if(imageFileList!=null && imageFileList.size()!=0) {
			  for(ImageVO  imageVO:imageFileList) {
			  	imageFileName = imageVO.getImageFileName();
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			 	srcFile.delete();
			  }
			}
			
			message = " <script>";
			message += " alert('등록 되었습니다.');";
			System.out.println("여기타는가2?");
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	  }
	  
	  	//다중이미지 보여주기
		@RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
		public String viewArticle(@RequestParam("articleNO") int articleNO,
				  HttpServletRequest request, HttpServletResponse response, Model model, PageVO page) throws Exception{
		
			Map articleMap=boardService.viewArticle(articleNO); //글 조회문
			System.out.println("articleMap: " + articleMap);
			model.addAttribute("p", page);
			model.addAttribute("articleMap", articleMap);
			return "viewArticle";
		}
		
		
		/*
		 //수정 dh
		 @RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
		  @ResponseBody
		  public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  
		    HttpServletResponse response) throws Exception{
		    multipartRequest.setCharacterEncoding("utf-8");
		    String imageFileName = null;
			Map<String,Object> articleMap = new HashMap<String, Object>();
			Enumeration enu=multipartRequest.getParameterNames();
			while(enu.hasMoreElements()){
				String name=(String)enu.nextElement();
				String value=multipartRequest.getParameter(name);
				articleMap.put(name,value);
			}
			//	String imageFileName= upload(multipartRequest);
			//List<String> imageFileName= upload(multipartRequest);
			
			//HttpSession session = multipartRequest.getSession();
			//MemberVO memberVO = (MemberVO) session.getAttribute("member");
			//String id = memberVO.getId();
			//articleMap.put("id", id);
			//articleMap.put("imageFileName", imageFileName);
			
			String articleNO=(String)articleMap.get("articleNO");
			System.out.println("/board/modArticle.do의 글번호" + articleNO); //글번호가져오고
			//selectImageFileList(articleNO);
			String message;
			ResponseEntity resEnt=null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		    try {
		       boardService.modArticle(articleMap);
		       
		       if(imageFileName!=null && imageFileName.length()!=0) { //if(imageFileName!=null && imageFileName.length()!=0)
		         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
		         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
		         FileUtils.moveFileToDirectory(srcFile, destDir, true);
		         
		         String originalFileName = (String)articleMap.get("originalFileName");
		         System.out.println("originalFileName: " + originalFileName);
		         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
		         oldFile.delete();
		       }	
		       
		       message = "<script>";
		       message += " alert('수정 되었습니다.');";
		       System.out.println("Controller의 articleMap" + articleMap);
			   message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			   message +=" </script>";
		       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }catch(Exception e) {
		    	e.printStackTrace();
		      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
		      srcFile.delete();
		      message = "<script>";
		      message += " alert('실패 했습니다.');";
		      System.out.println("Controller의 articleMap2" + articleMap);
			  message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			  message +=" </script>";
		      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }
		    return resEnt;
		  }
		  */
		  
		  
		
		
		
		
		 @RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
		  @ResponseBody
		  public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  
		    HttpServletResponse response) throws Exception{
		    multipartRequest.setCharacterEncoding("utf-8");
			Map<String,Object> articleMap = new HashMap<String, Object>();
			Enumeration enu=multipartRequest.getParameterNames();
			while(enu.hasMoreElements()){
				String name=(String)enu.nextElement();
				String value=multipartRequest.getParameter(name);
				articleMap.put(name,value);
			}
			//�߰�
			String _articleNO = (String) articleMap.get("articleNO");
			int articleNO = Integer.parseInt(_articleNO);
					
			// 1. imageFileName -> imageFileList�� ������
			// fileList�� upload�޼��带 ������ ��, List<String> fileList�� String�� ���� ��, Enhanced for�� �ٽ� List<ImageVO> imageFileList�� ����.
			List<String> fileList= upload(multipartRequest);
			List<ImageVO> imageFileList = new ArrayList<ImageVO>();
			if(fileList != null && fileList.size()!=0) { //��ȿ���˻�. fileList ���� �־�� (�̹����� �÷���) �۵�)
					for(String fileName : fileList) {
						ImageVO imageVO = new ImageVO();
						imageVO.setImageFileName(fileName);
						
						//�߰�
					    imageVO.setArticleNO(articleNO);
					    				
						imageFileList.add(imageVO);
					}
					
					articleMap.put("imageFileList", imageFileList);
			}
			//HttpSession session = multipartRequest.getSession();
			//MemberVO memberVO = (MemberVO) session.getAttribute("member");
			//String id = memberVO.getId();
			//articleMap.put("id", id);		
			
			//String articleNO=(String)articleMap.get("articleNO");
			//-> �� String ������ �� ����? int������ �޾Ƽ� imageVO int articleNO���� ���ÿ� �� �� �ִ°ǵ� 
			
			String message;
			ResponseEntity resEnt=null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		    try {
		       boardService.modArticle(articleMap);
		       // imageFileName -> imageFileList�� �ٲ��ְ�, ��ȿ���˻� .length()���� .size()�� 
		       if(imageFileList!=null && imageFileList.size()!=0) {
		//TEST2
		System.out.println("if�� ���� �۵�");
		    	   
			       //�����̹��� �� �� �߰� ����. '����' �̹����̹Ƿ� for������ 
			       for(ImageVO imageVO : imageFileList) {
			    	 String imageFileName = imageVO.getImageFileName(); 
			    	 
			         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
			         FileUtils.moveFileToDirectory(srcFile, destDir, true);
	    System.out.println("for�� �۵�");         
	/// '���� �̹��� ����', �� viewArticle.jsp���� �޾ƿͼ� getParameterNames�� articleMap�� ������ ���������� orginalFileName�� ����� ���� ���� ��� ȹ�� -> ���� 
			         String originalFileName = (String)articleMap.get("originalFileName");
			         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
			         oldFile.delete();
	    System.out.println("delete �۵�");
			       }
		       }
		       message = "<script>";
			   message += " alert('이미지 수정');";
			   System.out.println("Controller의 articleMap" + articleMap);
			   message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			   message +=" </script>";
		       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }catch(Exception e) {
		    	e.printStackTrace();
		      // catch~�� ���� �̹��� ���ε��� ���� ���� (if, for��) / try���� �ö� ���� ������ �ش� ���� ����� 
			      if(imageFileList != null && imageFileList.size() != 0) {
			    	  for(ImageVO imageVO : imageFileList) {
				    	  String imageFileName = imageVO.getImageFileName();
					      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					      srcFile.delete();
			    	  }
			      }
		      message = "<script>";
		      message += " alert('이미지수정2');";
			  System.out.println("Controller의 articleMap22" + articleMap);
			  message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			  message +=" </script>";
		      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }
		    return resEnt;
		  }
		  
		
		//수정문
		
		//////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		@RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
		  @ResponseBody
		  public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
		                              HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setContentType("text/html; charset=UTF-8");
			String message;
			ResponseEntity resEnt=null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			try {
				boardService.removeArticle(articleNO);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
				FileUtils.deleteDirectory(destDir);
				
				message = "<script>";
				message += " alert('삭제 되었습니다.');";
				message += " location.href='"+request.getContextPath()+"/board/listArticles.do';";
				message +=" </script>";
			    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			       
			}catch(Exception e) {
				message = "<script>";
				message += " alert('삭제 되었습니다.');";
				message += " location.href='"+request.getContextPath()+"/board/listArticles.do';";
				message +=" </script>";
			    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			    e.printStackTrace();
			}
			return resEnt;
		  }  
		
		
		
	   
		//다중 이미지 업로드하기
		private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			List<String> fileList= new ArrayList<String>();
			Iterator<String> fileNames = multipartRequest.getFileNames();
			while(fileNames.hasNext()){
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);
				String originalFileName=mFile.getOriginalFilename();
				fileList.add(originalFileName);
				File file = new File(ARTICLE_IMAGE_REPO +"\\"+ fileName);
				if(mFile.getSize()!=0){ //File Null Check
					if(! file.exists()){ //��λ� ������ �������� ���� ���
						if(file.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
								file.createNewFile(); //���� ���� ����
						}
					}
					mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+originalFileName)); 
				}
			}
			return fileList;
		}
		
}

		
	
	


		


