package com.teamHT.helloTraveler;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamHT.helloTraveler.Svc.IKakaoService;
import com.teamHT.helloTraveler.Svc.IMembersService;

@Controller
public class KakaoController {
	@Autowired
	private IKakaoService kakaoService;
	@Autowired
	private IMembersService mbrService;

	@RequestMapping("/kakaologin")
	public String kakaologin(@RequestParam(value = "code", required = false) String code, HttpServletRequest request,
			Model model) throws Exception {
		System.out.println("#########" + code);
		String access_Token = kakaoService.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("###userInfo#### : " + userInfo.get("email"));
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###profile_image#### : " + userInfo.get("gender"));

		String nickname = (String) userInfo.get("nickname");
		String email = (String) userInfo.get("email");
		String gender = (String) userInfo.get("gender");
		////////////////////////// 0221추가//////////////////////////////////
		String id = (String) userInfo.get("id");

		return mbrService.login_SNS(nickname, email, gender, id, request, model);

	}

}