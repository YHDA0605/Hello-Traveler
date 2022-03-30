package com.teamHT.helloTraveler.DAO;

import java.util.List;

import com.teamHT.helloTraveler.DTO.ReservationDTO;

public interface IReservationDAO {
	
public ReservationDTO selectOne(String mem_code, String res_code);
	
	public int maxRecvCode(String mem_code);

	public void insert(ReservationDTO resv);
	
	public void update(ReservationDTO resv);
	
	public void delete(String mem_code, String res_code);
	
	public List<ReservationDTO> selectAll();
	
}
