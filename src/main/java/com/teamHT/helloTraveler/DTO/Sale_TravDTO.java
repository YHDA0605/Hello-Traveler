package com.teamHT.helloTraveler.DTO;

import java.util.Date;

public class Sale_TravDTO {

	String common_code;
	String trav_code;
	String sale_code;
	Date start_day;
	Date end_day;
	int sale_price;
	int crt_num; //인원
	int max_num; //인원
	char sale_state;
	
	String trav_name;
	
	String com_name; // 회사명
	String mem_code;
	
	String common_name; // 나라 / 지역명

	public String getCommon_code() {
		return common_code;
	}

	public void setCommon_code(String common_code) {
		this.common_code = common_code;
	}

	public String getTrav_code() {
		return trav_code;
	}

	public void setTrav_code(String trav_code) {
		this.trav_code = trav_code;
	}

	public String getSale_code() {
		return sale_code;
	}

	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}

	public Date getStart_day() {
		return start_day;
	}

	public void setStart_day(Date start_day) {
		this.start_day = start_day;
	}

	public Date getEnd_day() {
		return end_day;
	}

	public void setEnd_day(Date end_day) {
		this.end_day = end_day;
	}

	public int getSale_price() {
		return sale_price;
	}

	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}

	public int getCrt_num() {
		return crt_num;
	}

	public void setCrt_num(int crt_num) {
		this.crt_num = crt_num;
	}

	public int getMax_num() {
		return max_num;
	}

	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}

	public char getSale_state() {
		return sale_state;
	}

	public void setSale_state(char sale_state) {
		this.sale_state = sale_state;
	}

	public String getTrav_name() {
		return trav_name;
	}

	public void setTrav_name(String trav_name) {
		this.trav_name = trav_name;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getMem_code() {
		return mem_code;
	}

	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}

	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}
	
	
	//	private TravelDTO travelDTO;
	

	//	public TravelDTO getTravelDTO() {
//		return travelDTO;
//	}
//	public void setTravelDTO(TravelDTO travelDTO) {
//		this.travelDTO = travelDTO;
//	}

}
