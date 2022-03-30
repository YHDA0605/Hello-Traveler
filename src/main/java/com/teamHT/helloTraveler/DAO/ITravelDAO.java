package com.teamHT.helloTraveler.DAO;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.TravelDTO;

public interface ITravelDAO {

	public TravelDTO sltOne(@Param("common_code")String common_code, @Param("trav_code")String trav_code);

	public TravelDTO selectAll();

	public void insert(TravelDTO trav);

	public void update(TravelDTO trav);

	public void delete(@Param("common_code")String common_code, @Param("trav_code")String trav_code);
	
	public String tcodeCount();
}
