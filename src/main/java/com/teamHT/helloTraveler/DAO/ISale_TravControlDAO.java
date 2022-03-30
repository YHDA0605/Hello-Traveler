package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.Sale_TravDTO;

public interface ISale_TravControlDAO {
	
	public void insertSaleList(List<Sale_TravDTO> list) throws Exception;
	
	public List<Sale_TravDTO> selectSaleList(@Param("common_code")String common_code, @Param("trav_code")String trav_code);
	
	public Sale_TravDTO selectSaleOne(@Param("common_code")String common_code, @Param("trav_code")String trav_code, @Param("sale_code")String sale_code);

	public void deleteSale(Sale_TravDTO sale);
	
	public int countSale(@Param("common_code")String common_code);
	
	
	
}
