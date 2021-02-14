package com.spring.mvc.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.member.vo.MemberVO;
import com.spring.mvc.member.dao.MemberDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	public MemberVO login(MemberVO memberVO) throws Exception{
		System.out.println("서비스에서 MemberVO의 객체가 들어왔을까?" + memberVO.getId());
		System.out.println("서비스에서 MemberVO의 객체가 들어왔을까?" + memberVO.getPwd());
		return memberDAO.loginById(memberVO);
	}
	
}
