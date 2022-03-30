package com.teamHT.helloTraveler.Svc;

import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.DTO.Trav_ComDTO;
import com.teamHT.helloTraveler.common.PagingVO;

public interface ITrav_ComService {

	// 회원가입
	public String registComMember(MembersDTO mbr, Trav_ComDTO tCom);
	
	public String adminTravComs(PagingVO paging, String cntPage, Model model);

}