package com.teamHT.helloTraveler.DAO;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.GoodDTO;

public interface IGoodDAO {
	public GoodDTO selectOne(@Param("common_code")String common_code, @Param("code")String code, @Param("mem_code")String mem_code);

	public GoodDTO selectAll(@Param("common_code")String common_code, @Param("code")String code);

	public void insert(GoodDTO good);

	public void update(GoodDTO good);

	public void delete(@Param("common_code")String common_code, @Param("mem_code")String mem_code, @Param("code")String code);
	
	public String maxCode();
	
	public String getGoodState(@Param("common_code") String common_code, @Param("code") String code, @Param("mem_code") String mem_code);
}
