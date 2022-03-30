package com.teamHT.helloTraveler.Svc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.TravelDTO;
import com.teamHT.helloTraveler.common.PagingVO;

public interface ITravelService {
	
	public String travList(String memCode, PagingVO paging, String cntPage, Model model);

	public String getTrav(String commonCode, String TravCode, Model model);
	
	public String writeSale(String commonCode, String TravCode, Model model);
	
	public String insertTrav(TravelDTO trav, HttpServletRequest request, String dbImg);
	
	public String deleteTrav(String commonCode, String travCode, HttpServletRequest request);
	
	public String travelEdit(Model model, String commonCode, String travCode);

	public String saleTravList(PagingVO paging, String cntPage, String common_code, Model model);
	
	
	public String getTravComsTravels(PagingVO paging, String cntPage, String com_code, Model model); // 02-22 추가(양)
	
	public void selectTravelIndex(Model model);
	
	
	//02-23 추가(양)
	public String travUpdateState(String common_code, String trav_code, String cmd);
}
