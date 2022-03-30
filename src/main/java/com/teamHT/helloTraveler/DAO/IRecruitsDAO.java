package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.RecruitsDTO;

public interface IRecruitsDAO {

	public RecruitsDTO selectOne(@Param("common_code")String common_code, @Param("recu_code")String recu_code);

	public void insert(RecruitsDTO recr);

	public void update(RecruitsDTO recr);

	public void delete(@Param("common_code")String common_code, @Param("recu_code")String recu_code);
	
	
	public List<RecruitsDTO> selectAll();
	
	public String maxRecuCode();
	
	
}
