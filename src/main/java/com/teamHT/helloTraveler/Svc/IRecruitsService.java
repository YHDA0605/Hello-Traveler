package com.teamHT.helloTraveler.Svc;

import java.text.ParseException;

import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.RecruitsDTO;
import com.teamHT.helloTraveler.common.PagingVO;

public interface IRecruitsService {
	
	// 모집 게시판 목록 출력
	public String getRecrus(Model model, PagingVO paging, String cntPage, String common_code, String search_type, String search_text);

	// 모집 게시판 단건 출력
	public String getRecru(Model model, String commonCode, String recuCode) throws ParseException;
	
	// 모집 게시물 등록
	public String insertRecu(RecruitsDTO recu) throws Exception;

	// 모집 게시글 삭제
	public String deleteRecu(String commonCode, String recuCode);
	
	// 모집 편집 페이지로 이동
	public String recuritEdit(Model model, String common_code, String recu_code) throws Exception;
	
	
	// 모집 작성 페이지로 이동
	public String reruitWriteForm(Model model, String common_code);
	
	
	//모집 종료 02-17 추가
	public String recuritEnd(Model model, String common_code, String recu_code) throws Exception;
	
	public void selectIndex(Model model);
}