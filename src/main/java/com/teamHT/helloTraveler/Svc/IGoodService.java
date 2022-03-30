package com.teamHT.helloTraveler.Svc;

public interface IGoodService {
	//0이면 1로 1이면 0으로
	public String updateGood(String common_code, String code, String mem_code);

	public String centerUpdateGood(String common_code, String code, String mem_code);
}
