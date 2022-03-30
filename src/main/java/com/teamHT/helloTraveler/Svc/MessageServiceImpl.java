package com.teamHT.helloTraveler.Svc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DAO.IMembersDAO;
import com.teamHT.helloTraveler.DAO.IMessagesDAO;
import com.teamHT.helloTraveler.DAO.IRecruitsDAO;
import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.DTO.MessagesDTO;
import com.teamHT.helloTraveler.DTO.RecruitsDTO;
import com.teamHT.helloTraveler.common.PagingVO;

@Service("msgService")
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private IMembersDAO mbrDao;
	
	@Autowired
	private IMessagesDAO msgDao;
	
	@Autowired
	private IRecruitsDAO recuDao;
	
	@Override
	public String getToMSG(PagingVO paging, String to_mem_code, String cntPage, Model model, HttpServletRequest request) {
		int to_total = msgDao.countToMessage(to_mem_code);

		if(cntPage == null) {
			cntPage = "1";
		}
		paging = new PagingVO(to_total, Integer.parseInt(cntPage));
		
		List<MessagesDTO> list = msgDao.to_msg(paging.getStart(), paging.getEnd(), to_mem_code);
		for(int i = 0; i < list.size(); i++) {
			String temp = list.get(i).getCommon_code();
			list.get(i).setFrom_common_code(temp.substring(1, 3));
			list.get(i).setTo_common_code(temp.substring(3));
			System.out.println(temp.substring(3));
		}
		
		model.addAttribute("LIST",list);
		model.addAttribute("MSG","보낸사람");
		model.addAttribute("PAGE", paging);
		
		int noRead = msgDao.getNoRead(to_mem_code);
		HttpSession session = request.getSession();
		session.setAttribute("NOREAD", noRead);
		
		return "message/message";
	}

	@Override
	public String getFromMSG(PagingVO paging, String from_mem_code, String cntPage, Model model) {
		int from_total = msgDao.countFromMessage(from_mem_code);
			if(cntPage == null) {
				cntPage = "1";
			}
			paging = new PagingVO(from_total, Integer.parseInt(cntPage));
			
			List<MessagesDTO> list = msgDao.from_msg(paging.getStart(), paging.getEnd(), from_mem_code);
			for(int i = 0; i < list.size(); i++) {
				String temp = list.get(i).getCommon_code();
				list.get(i).setFrom_common_code(temp.substring(1, 3));
				list.get(i).setTo_common_code(temp.substring(3));
			}
			
		 model.addAttribute("LIST",list);
		 model.addAttribute("MSG","받은사람");
		 model.addAttribute("PAGE", paging);
		return "message/message";
	}

	@Override
	public String getMSG(MessagesDTO msg) {
		// TODO Auto-generated method stub
		return "redirect:/message";
	}

	@Override
	@Transactional
	public String insertMSG(MessagesDTO msg, String mem_id) {
		//mem_code로 멤버 아이디 가져오기 나머지 인설트
		String mem_code;
		
		if(msg.getTo_mem_code() != null) {
			mem_code = msg.getTo_mem_code();
		}else {
			mem_code = mbrDao.getMem_code(mem_id);
		}
		//msg의 맥스코드
		int maxcode = msgDao.maxCode();
		maxcode = maxcode + 1;
		msg.setMsg_code(String.valueOf(maxcode));
		msg.setTo_mem_code(mem_code);
		msg.setCommon_code("60101");

		msgDao.insert(msg);
		return "redirect:/to_message?to_mem_code="+msg.getFrom_mem_code();
	}

	@Override
	public String getMsgCont(String msg_code, Model model, HttpServletRequest request) {
		MessagesDTO msg = msgDao.getMsgCont(msg_code);
		model.addAttribute("MSG", msg);
		
		int noRead = msgDao.getNoRead(msg.getTo_mem_code());
		HttpSession session = request.getSession();
		session.setAttribute("NOREAD", noRead);
		
		return "message/messageContent";
	}

	@Override
	public String getReply(String mem_code, String mem_nick, Model model) {
		model.addAttribute("MEM_CODE", mem_code);
		model.addAttribute("MEM_NICK", mem_nick);
		return "message/messageWrite";
	}

	@Override
	@Transactional
	public String msgUpdateState(String msg_code, String mem_code, Model model) {
		//매시지 코드로 정보를 다 가져오고 
		//to랑 from이랑 내 코드 비교해서 앞부분 뒷부분 바꾸고 융합
		//그 후 업데이트
		MessagesDTO msg = msgDao.getMsgCont(msg_code);
		String common_code = null;
		String view = null;
		
		if(msg.getFrom_mem_code().equals(mem_code)) {
			common_code = msg.getCommon_code();
			
			String newString = common_code.substring(0, 2)+ '3' +common_code.substring(3);
			msg.setCommon_code(newString);
			view = "redirect:/from_message?from_mem_code="+msg.getFrom_mem_code();
		}
		
		if(msg.getTo_mem_code().equals(mem_code)) {
			common_code = msg.getCommon_code();
			
			String newString = common_code.substring(0 , 4)+ '3';
			msg.setCommon_code(newString);
			view = "redirect:/to_message?to_mem_code="+msg.getTo_mem_code();
		}
		
		msgDao.msgUpdateState(msg_code, msg.getCommon_code());

		return view;
	
	}
	
	@Override
	@Transactional
	public String msgSeeUpdateState(String msg_code, String mem_code, Model model) {
		//매시지 코드로 정보를 다 가져오고 
		//to랑 from이랑 내 코드 비교해서 앞부분 뒷부분 바꾸고 융합
		//그 후 업데이트
		MessagesDTO msg = msgDao.getMsgCont(msg_code);
		String common_code = null;
		String view = null;
		
		if(msg.getFrom_mem_code().equals(mem_code)) {
			common_code = msg.getCommon_code();
			
			String newString = common_code.substring(0, 2)+ '2' +common_code.substring(3);
			msg.setCommon_code(newString);
			view = "redirect:/from_message?from_mem_code="+msg.getFrom_mem_code();
		}
		
		if(msg.getTo_mem_code().equals(mem_code)) {
			common_code = msg.getCommon_code();
			
			String newString = common_code.substring(0 , 4)+ '2';
			msg.setCommon_code(newString);
			view = "redirect:/to_message?to_mem_code="+msg.getTo_mem_code();
		}
		
		msgDao.msgUpdateState(msg_code, msg.getCommon_code());
		
		return view;
		
	}
	
	
	
	
	// 02-16 양지현 추가
	@Override
	public String applyMessage(String mem_code, String common_code, String recu_code, Model model) {
		String action = "apply";
		MembersDTO mbr = new MembersDTO();
		
		mbr.setMem_code(mem_code);
		mbr.setMem_nick(mbrDao.selectOne(mem_code).getMem_nick());
		
		model.addAttribute("action", action);
		model.addAttribute("common_code", common_code);
		model.addAttribute("recu_code", recu_code);
		model.addAttribute("mbr", mbr);
		
		return "common/messagePopup";
	}
	
	@Transactional
	@Override
	public String applyMessageInsert(MessagesDTO msg, String common_code, String recu_code) {
		
		int maxcode = msgDao.maxCode();
		maxcode = maxcode + 1;
		msg.setMsg_code(String.valueOf(maxcode));
		msg.setCommon_code("60101");

		msgDao.insert(msg);
		
		// 신청/post등록
		if (!recu_code.equals("")) {
			RecruitsDTO recuDto = recuDao.selectOne(common_code, recu_code);

			recuDto.setRecu_apply_n(recuDto.getRecu_apply_n() + 1);
			recuDao.update(recuDto);
		}

		return "common/messagePopup";

		//리턴하고 자동으로 닫히게 해야하는데 아니면 새로운 창을 띄워서 거기서 닫기 버튼을 누르게 한다?
	}

}