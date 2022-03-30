package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.MembersDTO;

public interface IMembersDAO {

	public MembersDTO selectOne(String code);

	public void insert(MembersDTO mem);
	
	public void insert_kakao(MembersDTO mem);

	public void update(MembersDTO mem);

	public void delete(String code);

	public List<MembersDTO> selectAll();

	public String maxCode();
/////////////////0221 추가 .............////////////////////////////////	
	public String maxId();

	// 로그인
	public MembersDTO loginCheck(@Param("id") String id, @Param("pw") String pw);
	
	//id로 mem_code가져오기
	public String getMem_code(@Param("mem_id") String mem_id);
	
	// AJAX 검사용 DAO
	public MembersDTO idCheck(String id);
	
	////////////////////////////////////////////0220 추가/////////////////////////////
	//카카오 아이디 체크
	public MembersDTO k_idCheck(String mem_kakao_id);

	public MembersDTO emailCheck(String email);

	public MembersDTO nickCheck(String nick);

	//02.22추가
	public void updateGrade(@Param("mem_code") String mem_code, @Param("common_code") String common_code);

	public List<MembersDTO> selectAll(
			@Param("start") int start
	        , @Param("end") int end
	        , @Param("search_title") String search_title
	        , @Param("search_nick") String search_nick);
		
	public int countMem(@Param("search_title") String search_title, @Param("search_nick") String search_nick);
		
	
}