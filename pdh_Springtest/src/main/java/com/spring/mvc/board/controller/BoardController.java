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
import com.spring.mvc.member.vo.MemberVO;
import com.spring.mvc.board.model.ArticleVO;


@Controller
public class BoardController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";

	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	
	
	@RequestMapping(value = "/board/*Form.do", method =  {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName"); //인터셉터에서 요청객체의 URL인 viewName의 jsp를 세션에 바인딩 되었던 viewName 세션을 가져온다.
		System.out.println("board/articleForm.do에 대한 viewName" + viewName); 
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName); //ModelandView의 객체인 setViewName에 url을 셋팅시키면,
		return mav; // ModelAndView의 속성에 따라서 해당 jsp를 찾아준다. | .do는 인터셉터에서 제거한다.
	}
	
	
	//게시판 2.0
	@RequestMapping(value= "/board/listArticles2.0.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String listArticles2(HttpServletRequest request, HttpServletResponse response, Model model, PageVO page
			) throws Exception {
		
		//System.out.println("board/listArticles2.0.do의 articleNO: " + articleNO);
	
		String condition = page.getCondition();
		
		System.out.println("URL: /board/listArticles.do -> result: ");
		System.out.println("parameter(페이지번호) : " + page.getPage() +"번");
		System.out.println("검색 조건: " + condition);
		System.out.println("검색어: " + page.getKeyword());
	
		PageCreator pc = new PageCreator();
		pc.setPaging(page);
		
		List <ArticleVO>articlesList = boardService.listArticles(page); // articlesList에 모든 글정보를 담았다.
		List<ImageVO>ImageFileList = boardService.selectImageFileList3();
		
		pc.setArticleTotalCount(boardService.countArticles(page));
		
		model.addAttribute("articlesList", articlesList); // 조회한글 정보를 바인딩 해서 JSP로 전달한다. 
		model.addAttribute("ImageFileList", ImageFileList); 
		model.addAttribute("pc" , pc);
		
		return "listArticles2.0";
		
	}
	
	/*
	 * //글쓰기 페이지 진입
	 * 
	 * @RequestMapping(value ="/board/articleForm.do" , method = RequestMethod.GET)
	 * public String FN1001WR() {
	 * 
	 * 
	 * return "articleForm"; }
	 */

	
	//responseEntity: http의 요청 또는 응답을 포함하는 클래스.
	//상태코드, 응답 메시지 등이 포함될 수 있겠다.
	//그럴때 사용되는 것이 ResponseEntity Class 이다. 
	//
	
	// 다중 글 이미지 추가하기
	  @RequestMapping(value="/board/addNewArticle.do" ,method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, HttpServletRequest request) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
			

		
		/*
		 * Object board_type2 = multipartRequest.getInputStream(); Object All_file =
		 * multipartRequest.getInputStream(); System.out.println("board_typ2의 정체" +
		 * board_type2); System.out.println("All_file의 정체" + All_file); String aaa
		 * =board_type2.toString(); //toString: 객체가 가지고 있는 정보나 값들을 문자열로 리턴하는 메소드? int
		 * board_type = Integer.parseInt(aaa);
		 * 
		 * System.out.println("board_type" + board_type);
		 */
		
		
		
		String imageFileName=null;
		
		Map articleMap = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member"); 
		String id = memberVO.getId();
		articleMap.put("id",id); //imageFileList에 값이 있든 없든 Map에는 항상 id가 입력되도록 선언.
		
		System.out.println("글쓰기 할때 id: " + id);
		
		List<String> fileList =upload(multipartRequest); //업로드 메서드를 호출해야 비로소 첨부파일이 fileList에 담기기 시작한다.
		List<ImageVO> imageFileList = new ArrayList<ImageVO>();
		if(fileList!= null && fileList.size()!=0) {
			for(String fileName : fileList) {
				ImageVO imageVO = new ImageVO(); //판다.jpg
				imageVO.setImageFileName(fileName);
				imageFileList.add(imageVO);
			}
			articleMap.put("imageFileList", imageFileList);
			articleMap.put("id", id); //이미지파일이 업로드메서드를 탔을 경우에만 셋팅이된다. 
			System.out.println("맵에 담긴 id" + id);
			
		}
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int articleNO = boardService.addNewArticle(articleMap); //글쓰기
			if(imageFileList!=null && imageFileList.size()!=0) { // imageFileList에 데이터가 있다면
				for(ImageVO  imageVO:imageFileList) {
					imageFileName = imageVO.getImageFileName(); 
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); //temp 폴더에 파일 저장.
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
					//destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir,true); // moveFileToDirectory: 글번호 폴더로 폴더를 이동시킨다.
				}
			}
			    
			message = "<script>";
			message += " alert('등록 되었습니다.');";
			System.out.println("여기타는가?");
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles2.0.do';"; // 등록이 된 이후에 이동될 URL을 message에 담았다.
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED); //message를 ResponseEntity(반응 독립체) 객체에 
		    
			 
		}catch(Exception e) { //예외가 발생했을 경우
			if(imageFileList!=null && imageFileList.size()!=0) {
			  for(ImageVO  imageVO:imageFileList) {
			  	imageFileName = imageVO.getImageFileName();
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			 	srcFile.delete(); //예외 발생시 temo로 이동된 이미지파일들을 삭제 시켜준다. 만일 하지 않았을 경우에 이미지파일들이 계속 temp폴더 안에 쌓일 것이다.
			  }
			}
			
			message = " <script>";
			message += " alert('글이 등록 되었습니다.');";
			System.out.println("여기타는가2?");
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles2.0.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	  }
	  	
	  /*
	   * @RequestParam: JSP에서 전달받은 파라미터값을 전달받는다.
	   * 
	   */
	  	
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
		
		  
		//글 수정과 다중이미지 수정하기. ㅎㅎ
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
			
			String message;
			ResponseEntity resEnt=null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		    try {
		    	
		    	String beforeservice =  (String) articleMap.get("imageFileName");
		       System.out.println("서비스 갔다온 이전의" + beforeservice);
		       boardService.modArticle(articleMap);
		    	String afterservice =  (String) articleMap.get("imageFileName");
		       System.out.println("서비스 갔다온 이후의" + afterservice);
		      
		      Object map =  articleMap.get("imageFileList");
		      System.out.println("오브젝트: " + map); //객체가 2개가 리턴되는거같은데
		      
		      
		      
		      
		       if(imageFileList!=null && imageFileList.size()!=0) {
		    	
			       for(ImageVO imageVO : imageFileList) { //pro30과 달리 for문이 추가됨.
			    	 String imageFileName = imageVO.getImageFileName(); 
			    	 
			    	 System.out.println("서비스 이후 for문을 탄 imageFileName의 객체들: " + imageFileName);
			    	 
			         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); //짱구 들어옴.
			         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
			         FileUtils.moveFileToDirectory(srcFile, destDir, true);
	      
			         String originalFileName = (String)articleMap.get("originalFileName");
			         String imageFileNames = (String) articleMap.get("imageFileName");
			         
			         System.out.println("orkginalFileName들은?" + originalFileName);
			         System.out.println("imageFileNames들은?: " + imageFileNames);
			         
			         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
			         oldFile.delete();

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
		
			      if(imageFileList != null && imageFileList.size() != 0) {
			    	  for(ImageVO imageVO : imageFileList) { //여기까지도 짱구와 고릴라가 전달이 됬어요.
				    	  String imageFileName = imageVO.getImageFileName();
					      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
					      srcFile.delete();
			    	  }
			      }
		      message = "<script>";
		      message += " alert('수정 실패');";
			  System.out.println("Controller의 수정실패" + articleMap);
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
		
		
		@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
		public String listArticles(HttpServletRequest request, HttpServletResponse response, Model model, PageVO page,
				@RequestParam("articleNO") int articleNO) throws Exception {
		
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
		
		
		
}

		
	
	


		


