package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.TravelDTO;

public interface ITravelControlDAO {
	
	public List<TravelDTO> selectTravelList(@Param("common_code")String common_code, @Param("start")int start, @Param("end")int end);
	
	public List<TravelDTO> selectTravelComList(@Param("mem_code")String mem_code, @Param("start")int start, @Param("end")int end);
	
	public TravelDTO selectTravelOne(@Param("common_code")String common_code, @Param("trav_code")String trav_code);
	
	public TravelDTO selectSaleOne(@Param("common_code")String common_code, @Param("trav_code")String trav_code);

	public void deleteTravel(TravelDTO trav);
	
	public int countTravel(@Param("common_code")String common_code);
	
	public int countTravelCom(@Param("mem_code")String mem_code);
	
	
	public int countTravComsTravelsCount(@Param("com_code")String com_code); // 02-22 추가(양)
	
	public List<TravelDTO> countTravComsTravels(@Param("start")int start, @Param("end")int end, @Param("com_code")String com_code); // 02-22 추가(양)
	
	//////////////////////////0222 추가/////////////////////////////////////
	public List<TravelDTO> selectTravelIndex();
	
	
	// 02-23 추가(양)
	public int max_trav_code(@Param("common_code")String common_code);
}

