package com.teamHT.helloTraveler.DAO;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.Sale_TravDTO;

public interface ISale_TravDAO {
	public Sale_TravDTO selectOne(@Param("common_code")String common_code, @Param("trav_code")String trav_code, @Param("sale_code")String sale_code);

	public Sale_TravDTO selectAll();

	public void insert(Sale_TravDTO trav);

	public void update(Sale_TravDTO trav);

	public void delete(@Param("common_code")String common_code, @Param("trav_code")String trav_code, @Param("sale_code")String sale_code);
	
	public String codeCount();
}
