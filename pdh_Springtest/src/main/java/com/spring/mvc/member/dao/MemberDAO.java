package com.spring.mvc.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.mvc.member.vo.MemberVO;

@Repository
public class MemberDAO {
		
	@Autowired
	private SqlSession sqlSession;
	
	
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		System.out.println("리턴된 dao의 객체들" + memberVO.getId());
		System.out.println("리턴된 dao의 객체들" + memberVO.getPwd());
		  MemberVO vo = sqlSession.selectOne("mapper.member.loginById",memberVO);
		  System.out.println("리턴된 dao의 vo: " + vo);
		return vo;
	}
		

	
	
}
