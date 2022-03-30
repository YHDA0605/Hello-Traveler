package com.teamHT.helloTraveler;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.Svc.IMembersService;

@Controller
public class AjaxController {

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	private IMembersService mbrService;

	@RequestMapping(value = "IdChkCtrl.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String idChk(@ModelAttribute("mem_id") String mem_id) {
		MembersDTO dto = mbrService.idCheck(mem_id);

		boolean result = false;
		if (dto == null) {
			result = true;
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);

		String jsonOut = jsonObj.toString();
		System.out.println("====" + jsonOut);

		return jsonOut;
	}

	@RequestMapping(value = "NickChkCtrl.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String nickChk(@ModelAttribute("mem_nick") String mem_nick) {
		MembersDTO dto = mbrService.nickCheck(mem_nick);

		boolean result = false;
		if (dto == null) {
			result = true;
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);

		String jsonOut = jsonObj.toString();
		System.out.println("====" + jsonOut);

		return jsonOut;
	}
	
	@RequestMapping(value = "pwChkCtrl.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String pwChk(@ModelAttribute("mem_prevPw") String mem_prevPw, @ModelAttribute("mem_pw") String mem_pw, HttpServletRequest request) {
		HttpSession session = request.getSession();

		String code = String.valueOf(session.getAttribute("CODE"));
		
		MembersDTO dto = mbrService.codePwCheck(code, mem_prevPw, mem_pw);
		
		
		boolean result = false;
		if (dto != null) {
			result = true;
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);

		String jsonOut = jsonObj.toString();
		System.out.println("====" + jsonOut);

		return jsonOut;
	}
	

	@RequestMapping(value = "mailSend.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String mailSend(@ModelAttribute("email")String email) throws MessagingException {
		Random r = new Random();
        int dice = r.nextInt(458936) + 49311; //이메일로 받는 인증코드 부분 (난수)
        
        String setfrom = "teamhellotraveler@gamil.com";
        String tomail = email; // 받는 사람 이메일
        String title = "[HelloTraveler] 인증 이메일입니다."; // 제목
        String content =
        
        System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
        
        System.getProperty("line.separator")+
                
        "안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"
        
        +System.getProperty("line.separator")+
        
        System.getProperty("line.separator")+

        " 인증번호는 " +dice+ " 입니다. "
        
        +System.getProperty("line.separator")+
        
        System.getProperty("line.separator")+
        
        "받으신 인증번호를 입력해 주시면 다음으로 넘어갑니다."; // 내용
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        
        messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
        messageHelper.setTo(tomail); // 받는사람 이메일
        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
        messageHelper.setText(content); // 메일 내용
        
        mailSender.send(message);
		
        JSONObject jsonObj = new JSONObject();
		jsonObj.put("certifyNumber", dice);
        
		return jsonObj.toString();
	}
	
	
	@RequestMapping(value = "findIdByEmail.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String findIdbyEmail(@ModelAttribute("email") String email) {

		MembersDTO member = mbrService.findByEmail(email);
		
		String result = "";
		
		if (member != null) {
			for(int i=0;i<member.getMem_id().length(); i++) {
				if(i>3) {
					result += "*";
					continue;
				}
				result += member.getMem_id().substring(i,i+1);
			}
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);

		String jsonOut = jsonObj.toString();
		System.out.println("====" + jsonOut);

		return jsonOut;
	}
	
	@RequestMapping(value = "findPwByEmail.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String findPwByEmail(@ModelAttribute("email") String email) {

		MembersDTO member = mbrService.findByEmail(email);

		String result = "";
		
		if (member != null) {
			result = member.getMem_pw();
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);

		String jsonOut = jsonObj.toString();
		System.out.println("====" + jsonOut);

		return jsonOut;
	}
	
	@RequestMapping(value = "pwChangeAction.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String pwChangeAction(@ModelAttribute("email") String email, @ModelAttribute("mem_prevPw") String mem_prevPw, @ModelAttribute("mem_pw") String mem_pw) {
		
		boolean result = false;
		MembersDTO member = mbrService.findByEmail(email);
		
		if (member != null) {
			MembersDTO dto = mbrService.codePwCheck(member.getMem_code(), mem_prevPw, mem_pw);
			if (dto != null) {
				result = true;
			}
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);

		String jsonOut = jsonObj.toString();
		System.out.println("====" + jsonOut);

		return jsonOut;
	}
	
	@RequestMapping(value = "certifyEmail.do", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String certifyEmail(@ModelAttribute("email") String email, @ModelAttribute("certifyValue") String certifyValue) {
		
		boolean result = false;
		MembersDTO member = mbrService.findByEmail(email);
		
		if (member == null) {
			result = true;
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("certifyResult", result);

		String jsonOut = jsonObj.toString();
		System.out.println("====" + jsonOut);

		return jsonOut;
	}
}
