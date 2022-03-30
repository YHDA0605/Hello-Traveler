package com.teamHT.helloTraveler.Svc;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.teamHT.helloTraveler.DAO.IMembersDAO;
import com.teamHT.helloTraveler.DAO.IMessagesDAO;
import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.common.PagingVO;
import com.teamHT.helloTraveler.naver.NaverLoginBO;

@Service("mbrService")
public class MembersServiceImpl implements IMembersService {

	@Autowired
	private IMembersDAO mbrDao;
	
	@Autowired
	private IMessagesDAO msgDao;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	// 회원가입
	@Override
	@Transactional
	public String registNormalMember(MembersDTO mbr) {
		MembersDTO member = mbrDao.idCheck(mbr.getMem_id());

		if (member != null) {
			return "regist/registNormal";
		}

		String maxCode = mbrDao.maxCode();
		int code = 0;

		if (maxCode == null) {
			code = 1;
		} else {
			code = Integer.parseInt(maxCode) + 1;
		}

		char gender = mbr.getGender();

		mbr.setMem_code(String.valueOf(code));
		mbr.setMem_state('0');
		mbr.setCommon_code("10003");
		mbr.setGender(gender);
		mbr.setMem_sns_id("없음");

		mbrDao.insert(mbr);

		return "regist/registChk";
	};

	@Override
	public String loginCheck(String id, String pw, HttpServletRequest request) {
		MembersDTO dto = mbrDao.loginCheck(id, pw);
		if (dto == null) {
			request.setAttribute("FAIL", "fail");
			return "login/loginForm";
		}
		System.out.println("===================================" + dto.toString());
		// 아이디가 있을 경우
		HttpSession session = request.getSession(true);
		session.setAttribute("CODE", dto.getMem_code());
		session.setAttribute("NICK", dto.getMem_nick());
		session.setAttribute("COMMON_CODE", dto.getCommon_code());
		
		int noRead = msgDao.getNoRead(dto.getMem_code());
		session = request.getSession();
		session.setAttribute("NOREAD", noRead);
		
		return "redirect:/";
	}

	@Override
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	@Override
	public String showMyPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		String code = String.valueOf(session.getAttribute("CODE"));

		MembersDTO mem = mbrDao.selectOne(code);
		
		model.addAttribute("mbr", mem);

		return "myPage/mypage";
	};

	@Override
	@Transactional
	// 정보변경 변경
	public void editMem(MembersDTO mbr, HttpServletRequest request) {
		HttpSession session = request.getSession();

		String code = String.valueOf(session.getAttribute("CODE"));

		MembersDTO mem = mbrDao.selectOne(code);

		String pw = mbr.getMem_pw();
		String nick = mbr.getMem_nick();
		String phone = mbr.getMem_phone();
		String email = mbr.getMem_email();

		if (pw != null) {
			mem.setMem_pw(pw);
		}

		if (nick != null) {
			mem.setMem_nick(nick);
		}

		if (phone != null) {
			mem.setMem_phone(phone);
		}

		if (email != null) {
			mem.setMem_email(email);
		}

		mbrDao.update(mem);
	}

	// ajax id/nick중복 체크용
	@Override
	public MembersDTO idCheck(String mem_id) {
		MembersDTO dto = mbrDao.idCheck(mem_id);

		return dto;
	};

	@Override
	public MembersDTO nickCheck(String mem_Nick) {
		MembersDTO dto = mbrDao.nickCheck(mem_Nick);

		return dto;
	};
	
	@Override
	public MembersDTO codePwCheck(String mem_code, String prevPw, String pw) {
		MembersDTO dto = mbrDao.selectOne(mem_code);
		
		if(prevPw.equals(dto.getMem_pw())) {
			dto.setMem_pw(pw);
			mbrDao.update(dto);
			
			return dto;
		}

		return null;
	};
	
	@Override
	public void delMem(MembersDTO mbr, HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();

		String code = String.valueOf(session.getAttribute("CODE"));
		
		MembersDTO mem = mbrDao.selectOne(code);
		
		mem.setMem_state('1');
		
		model.addAttribute("MSG","회원 탈퇴가 완료되었습니다.\\n그동안 이용해주셔서 감사합니다.");
		
		mbrDao.update(mem);
	
	}

	@Override
	public MembersDTO findByEmail(String email) {
		MembersDTO member = mbrDao.emailCheck(email);
		
		if(member == null) {
			return null;
		}
		
		return member;
	}

	@Override
	public String login_SNS(String nickname, String email, String gender,String id, HttpServletRequest request,Model model) {
		MembersDTO dto = mbrDao.k_idCheck(id);
		
		if(dto==null) {
			request.setAttribute("FAIL", "fail");
			model.addAttribute("nickname",nickname);
			model.addAttribute("email",email);
			model.addAttribute("gender",gender);
			model.addAttribute("id",id);
			
			return "regist/regist_SNS";
		}
		System.out.println("===================================" + dto.toString());
		
		HttpSession session = request.getSession(true);
		session.setAttribute("CODE", dto.getMem_code());
		session.setAttribute("NICK", dto.getMem_nick());
		session.setAttribute("COMMON_CODE", dto.getCommon_code());
		
		return "redirect:/";
	}

	@Override  
	public String registNormalMember_SNS(MembersDTO mbr) {

	    String maxId = mbrDao.maxId();
	    int num = 0;
	    String id = "SNS";
	    
	    if(maxId == null) {
	    	num = 1;
	    }else {
	    	num = Integer.parseInt(maxId) + 1;
	    }
	    
		
		String maxCode = mbrDao.maxCode();
		int code = 0;

		if (maxCode == null) {
			code = 1;
		} else {
			code = Integer.parseInt(maxCode) + 1;
		}
		
		char gender = mbr.getGender();
		
		mbr.setMem_code(String.valueOf(code));
		mbr.setMem_state('0');
		mbr.setCommon_code("10003");
		mbr.setGender(gender);
		mbr.setMem_pw("SNS");
		mbr.setMem_id(id+num);

		mbrDao.insert(mbr);

		return "regist/registChk";
	}

	@Override
	public String loginNaver(HashMap<String, Object> naver, HttpServletRequest request, Model model) {
		String id = (String) naver.get("id");
		String email = (String) naver.get("email");
		String mobile = (String) naver.get("mobile");
		
		MembersDTO dto = mbrDao.k_idCheck(id);
		
		if (dto == null) {
			request.setAttribute("FAIL", "fail");
			model.addAttribute("id", id);
			model.addAttribute("email", email);
			model.addAttribute("mobile", mobile);
			
			return "regist/regist_Naver";
		}
		System.out.println("===================================" + dto.toString());
		// 아이디가 있을 경우
		HttpSession session = request.getSession(true);
		session.setAttribute("CODE", dto.getMem_code());
		session.setAttribute("NICK", dto.getMem_nick());
		session.setAttribute("COMMON_CODE", dto.getCommon_code());

		return "redirect:/";
	}

	@Override
	public HashMap<String, Object> NaverMember(Model model, String code, String state, HttpSession session,
			HttpServletRequest request) throws IOException, ParseException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);

		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");

		// response의 nickname값 파싱
		String id = (String) response_obj.get("id");
		String email = (String) response_obj.get("email");
		String mobile = (String) response_obj.get("mobile");
	
		String[] phone = mobile.split("-");
		String realPhoneNum = "";
		
		for(int i=0;i<phone.length;i++) {
			realPhoneNum += phone[i];
		}
		// 4.파싱 닉네임 세션으로 저장
		// 세션 생성
//      session.setAttribute("NICK", email); 
//		session.setAttribute("CODE", apiResult);
		
		
		
		
		HashMap<String, Object> naver = new HashMap<String, Object>();
		
		naver.put("id", id);
		naver.put("email", email);
		naver.put("mobile", realPhoneNum);

//		model.addAttribute("id", id);
//		model.addAttribute("email", email);
//		model.addAttribute("mobile", mobile);

		return naver;
	}

	@Override
	public String registNaverMember(MembersDTO mbr) {
		String maxId = mbrDao.maxId();
		int num = 0;
		String id = "SNS";

		if (maxId == null) {
			num = 1;
		} else {
			num = Integer.parseInt(maxId) + 1;
		}

		String maxCode = mbrDao.maxCode();
		int code = 0;

		if (maxCode == null) {
			code = 1;
		} else {
			code = Integer.parseInt(maxCode) + 1;
		}
		
/*		String[] phone = mbr.getMem_phone().split("-");
		String realPhoneNum = "";
		
		for(int i=0;i<phone.length;i++) {
			realPhoneNum += phone[i];
		}*/
        
		char gender = mbr.getGender();
		
		mbr.setMem_code(String.valueOf(code));
		mbr.setMem_state('0');
		mbr.setCommon_code("10003");
		mbr.setGender(gender);
		mbr.setMem_id(id + num);
		mbr.setMem_pw("SNS");

		mbrDao.insert(mbr);

		return "regist/registChk";
	}
	
	@Override
	public String adminMemAll(PagingVO paging
			, String cntPage
			, String search_type
			, String search_text
			, Model model) {
		
		 int total = 0;
	      String search_nick = "";
	      String search_title = "";
	      
	      if(search_type.equals("search_title")) {
	         search_title = search_text;
	      }else if(search_type.equals("search_nick")){
	         search_nick = search_text;
	      }
	      
	      if(cntPage == null) {
	         cntPage = "1";
	      }
	      
	      total = mbrDao.countMem(search_title, search_nick);
	      paging = new PagingVO(total, Integer.parseInt(cntPage));
	      
	      model.addAttribute("LIST", mbrDao.selectAll(paging.getStart(), paging.getEnd(), search_title, search_nick));
	      model.addAttribute("PAGE", paging);
	      model.addAttribute("TYPE", search_type);
	      model.addAttribute("TEXT", search_text);
	      
		return "admin/adminMem";
	}

	@Override
	public String updateGrade(String mem_code, String common_code) {
		mbrDao.updateGrade(mem_code, common_code);
		return "redirect:/adminMem";
	}

}