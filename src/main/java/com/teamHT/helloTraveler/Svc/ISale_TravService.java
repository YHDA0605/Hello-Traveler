package com.teamHT.helloTraveler.Svc;

import java.util.List;

import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.Sale_TravDTO;

public interface ISale_TravService {

	// 모집 게시판 단건 출력
	public String getSale(String commonCode, String travCode, Model model);
	
	// 여행상품 게시물 등록
	public String insertSale(Sale_TravDTO sale
			, List<String> day
			, String start_date
			, String end_date
			, Model model) throws Exception;

	// 여행상품 게시글 삭제
	public String deleteSale(String commonCode, String travCode, String saleCode);

	public String saleEdit(Model model, String commonCode, String travCode, String saleCode) throws Exception;

}