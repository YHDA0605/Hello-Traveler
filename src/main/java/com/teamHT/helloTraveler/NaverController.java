package com.teamHT.helloTraveler;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.Svc.IMembersService;
import com.teamHT.helloTraveler.naver.NaverLoginBO;

@Controller
public class NaverController {

	@Autowired
	private IMembersService mbrService;

	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
//	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	// 네이버 로그인 추가입력 처리
	@RequestMapping("regist_Naver")
	public String regist_NAVER() {
		return "regist/regist_Naver";
	}

	// 네이버 로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		// 네이버
		model.addAttribute("url", naverAuthUrl);
		return "login/login";
	}

	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session,
			HttpServletRequest request) throws IOException, org.json.simple.parser.ParseException {

		HashMap<String, Object> naver = mbrService.NaverMember(model, code, state, session, request);

		/* 네이버 로그인 성공 페이지 View 호출 */
		return mbrService.loginNaver(naver, request, model);
	}

	@RequestMapping(value = "doRegistNormal_Naver", method = RequestMethod.POST)
	public String doRegistNaver(MembersDTO mbr) {

		return mbrService.registNaverMember(mbr);
	}
}
