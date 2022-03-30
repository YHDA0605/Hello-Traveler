package com.teamHT.helloTraveler.Svc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.MessagesDTO;
import com.teamHT.helloTraveler.common.PagingVO;

public interface IMessageService {
	//0이면 1로 1이면 0으로
	public String getToMSG(PagingVO paging, String to_mem_code, String cntPage, Model model, HttpServletRequest request);
	
	public String getFromMSG(PagingVO paging, String from_mem_code, String cntPage, Model model);
	
	public String getMSG(MessagesDTO msg);
	
	public String insertMSG(MessagesDTO msg, String mem_id);

	public String getMsgCont(String msg_code, Model model, HttpServletRequest request);
	
	public String getReply(String mem_id, String mem_nick, Model model);
	
	public String msgUpdateState(String msg_code, String mem_code, Model model);
	
	public String msgSeeUpdateState(String msg_code, String mem_code, Model model);
	
	
	
	// 02-16 양지현 추가 아마 recruCtrlService로 옮겨야함
	public String applyMessage(String mem_code, String common_code, String recu_code, Model model);

	public String applyMessageInsert(MessagesDTO msg, String common_code, String recu_code);
	
}
