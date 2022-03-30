package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.Trav_ComDTO;

public interface ITrav_ComDAO {

	public Trav_ComDTO selectOne(String com_code);

	public List<Trav_ComDTO> selectTravComs(@Param("start")int start, @Param("end") int end); // 02 - 22 추가함 (양)
	
	public List<Trav_ComDTO> selectAll(); // 02 - 22 수정함 (양)

	public void insert(Trav_ComDTO com);

	public void update(Trav_ComDTO com);

	public void delete(String com_code);
	
	public Trav_ComDTO comNoChk(String com_number);
	
	public int maxComCode();
	
	public Trav_ComDTO getComcode(String mem_code);
}
