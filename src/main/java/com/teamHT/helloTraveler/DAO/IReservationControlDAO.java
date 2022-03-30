package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.ReservationDTO;

public interface IReservationControlDAO {
	
public ReservationDTO selectOne(String mem_code, String res_code);
	
	public int maxMyRecvs(String mem_code);

	public List<ReservationDTO> selectMyResvList(@Param("mem_code")String mem_code, @Param("start")int start, @Param("end")int end);
	
}
