package com.teamHT.helloTraveler.DAO;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.FilesDTO;

public interface IFilesDAO {
	public String selectOne(@Param("common_code")String common_code, @Param("code")String code);

	public FilesDTO selectAll(@Param("common_code")String common_code, @Param("code")String code);

	public void insert(FilesDTO file);
	
	public String maxCode(String common_code);
}
