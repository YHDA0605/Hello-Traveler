package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.RecruitsDTO;

public interface IRecruitsControlDAO {
	public List<RecruitsDTO> selectRecruitsList(@Param("common_code")String common_code, @Param("start")int start, @Param("end")int end, @Param("search_title") String search_title
			  , @Param("search_nick") String search_nick);
	
	public RecruitsDTO selectRecruitOne(@Param("common_code")String common_code, @Param("recu_code")String recu_code);
	
	public void delete(RecruitsDTO recu);
	
	public int countRecruits(@Param("common_code")String common_code, @Param("search_title") String search_title
			  , @Param("search_nick") String search_nick);
	
	public List<RecruitsDTO> newIndex();
}
