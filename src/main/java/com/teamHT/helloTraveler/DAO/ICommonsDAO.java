package com.teamHT.helloTraveler.DAO;

import java.util.List;

import com.teamHT.helloTraveler.DTO.CommonsDTO;

public interface ICommonsDAO {
	
	public CommonsDTO getCommon(String common_code);
	
	public List<CommonsDTO> findByLikeCommonCode(String code);
	
}
