package com.teamHT.helloTraveler.Svc;

import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.ReservationDTO;
import com.teamHT.helloTraveler.common.PagingVO;

public interface IReservationService {
	public String insertResc(ReservationDTO resv);
	
	public String myReservationList(String mem_code, PagingVO paging, String cntPage, Model model);
}
