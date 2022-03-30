package com.teamHT.helloTraveler.Svc;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.common.PagingVO;

public interface IMembersService {

	// 로그인
	public String loginCheck(String code, String pw, HttpServletRequest request);
	//!!!!!!!!!!!!!!!!2018 추가 ///////////////////////////////////////////////////////////////
	//SNS 로그인
	public String login_SNS(String nickname, String email, String gender,String id, HttpServletRequest request,Model model);

	// 로그아웃
	public String logOut(HttpServletRequest request);

	// 회원가입
	public String registNormalMember(MembersDTO mbr);

	//!!!!!!!!!!!!!!!!2018 추가 ///////////////////////////////////////////////////////////////
		//sns로그인 추가입력 회원가입
	public String registNormalMember_SNS(MembersDTO mbr);
	
	//네이버 회원가입<추가된 것!!!>
	public String loginNaver(HashMap<String, Object> naver, HttpServletRequest request, Model model);
	
	public HashMap<String, Object> NaverMember(Model model, String code, String state, HttpSession session, HttpServletRequest request)throws IOException, ParseException;
	
	public String registNaverMember(MembersDTO mbr);
	
	// 마이페이지
	public String showMyPage(Model model, HttpServletRequest request);	
	
	// ID체크
	public MembersDTO idCheck(String mem_id);

	// NickName 체크
	public MembersDTO nickCheck(String mem_Nick);
	
	// PK체크로 DTO 리턴
	public MembersDTO codePwCheck(String mem_code, String prevPw, String pw);
	
	// 정보 변경
	public void editMem(MembersDTO mbr, HttpServletRequest request);

	// 회원 삭제 처리(실제 쿼리는 업데이트)
	void delMem(MembersDTO mbr, HttpServletRequest request, Model model);
	
	// 이메일로 멤버 찾기
	public MembersDTO findByEmail(String email);
	//admin에 멤버 주기
	
	public String adminMemAll(PagingVO paging
				, String cntPage
				, String search_type
				, String search_text
				, Model model);

	public String updateGrade(String mem_code, String common_code);

}