package com.spring.mvc.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.member.service.MemberService;
import com.spring.mvc.member.vo.MemberVO;

@Controller("memberController")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

	// 로그인 페이지에 진입하는 메서드
	//리다이렉트로 인해서 다시 한번 재요청이 된다. 그러나 이번엔 result에 loginFailed의 파라미터를 붙여진 상태이다.
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result, // @RequestParam: 단일// 파라미터를 전달 받을때 사용한다.
							  @RequestParam(value = "action", required = false) String action, // listArticles2.0.jsp에서 전달 받은 action의 // 파라미터값을 저장한다
																				
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		System.out.println("최초 action값: " + action);
		session.setAttribute("action", action); // isLogOn이 true가아닐때에 전달받은 action값을 세션에 바인딩을 한다.
		ModelAndView mav = new ModelAndView();
		System.out.println("result값: " + result); //최초 이 메서드를 글쓰기 버튼에서 이동됬을때는 null값. 그리고 아이디 또는 비번이 잘못되었을 경우 result값은 loginFailed
		mav.addObject("result", result); //보내고 
		mav.setViewName(viewName); //뷰페이지로 이동한다. 
		return mav;
	}
	 

	// 로그인 할때 작동되는 메서드
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, // MemberVO member의 객체를 member라는 이름으로 뷰단에 전송.
			RedirectAttributes rAttr, // ReirectAttributes rAttr: 리다이렉트 시 매개변수를 전달한다고 한다.
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();

		// String viewName = getViewName(request); // 인터셉터에서 getViewName 메서드 호출.
		// String viewName = (String)request.getAttribute("viewName"); // 인터셉터에서 바인딩된
		// 뷰이름을 가져온다.

		memberVO = memberService.login(member);
		//System.out.println("아이디" + memberVO.getId());
		//System.out.println("비밀번호" + memberVO.getPwd());
		if (memberVO != null) { //리턴된 membervo 객체가 null이다?
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true); // 여기서 isLogOn이라는 세션을 만들어서 true로 설정했구나.
			mav.setViewName("redirect:/board/listArticles2.0.do"); //로그인 성공시 진입할 페이지
			String action = (String) session.getAttribute("action"); // action 세션값을 가져온다.
			// session.removeAttribute("action");
			if (action != null) {
				mav.setViewName("redirect:" + action); //action 파라미터값: /board/articleForm 으로 리다이렉트 해준다.
			} else {
				mav.setViewName("redirect:/board/listArticles2.0.do"); //action이 null이라는 얘기는 isLogOn이 true란 얘기잖아.
			}

		} else { //memberVo가 null일 경우 이 else문으로 빠지는데, 이때
			//리다이렉트에 대한 정확한 이해가 필요하다.
			//result?=loginFailed
			rAttr.addAttribute("result", "loginFailed"); //리다이렉트로 데이터를 전달(변수명,값) URL에 붙어서 전달되어 값이 유지된다.
														 //또한 이 addAttribute()는 밑에 redirect되는 부분에 전달된다.
			mav.setViewName("redirect:/member/loginForm.do"); //리다이렉트해서,  result에 loginFailed를 보냈고, 다시 한번 loginForm.do를 요청하게 되면, result == 'loginFailed'가 장착된 상태가되므로, alert창이 뜨고 result에는 loginFailed
		
		}
		
		return mav;

	}
	
	
	@RequestMapping(value = "/member/logout.do", method =  RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); //HttpSession객체로 session을 가져오도록 함.
		session.removeAttribute("member"); //member session: membberVO의 정보가 들어있는객체 이 정보를 삭제한다.
		session.removeAttribute("isLogOn"); //isLogOn 세션 삭제한다.
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/listArticles2.0.do");
		return mav;
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}