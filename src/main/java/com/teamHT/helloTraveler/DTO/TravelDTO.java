package com.teamHT.helloTraveler.DTO;

import java.util.Date;

public class TravelDTO {

	String common_code;
	String trav_code;
	String com_code;
	String trav_name;
	int trav_price;
	int trav_nights;
	Date trav_reg_date;
	char trav_state;
	String trav_cont;
	String trav_desc;
	
	String mem_code;
	String com_name;
	
	String common_name;
	
	//사진용
	String file_name;
	
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}

	public String getMem_code() {
		return mem_code;
	}

	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}
	
	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

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

	public String getCom_code() {
		return com_code;
	}

	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}

	public String getTrav_name() {
		return trav_name;
	}

	public void setTrav_name(String trav_name) {
		this.trav_name = trav_name;
	}

	public int getTrav_price() {
		return trav_price;
	}

	public void setTrav_price(int trav_price) {
		this.trav_price = trav_price;
	}
		
	public int getTrav_nights() {
		return trav_nights;
	}

	public void setTrav_nights(int trav_nights) {
		this.trav_nights = trav_nights;
	}
	
	public Date getTrav_reg_date() {
		return trav_reg_date;
	}

	public void setTrav_reg_date(Date trav_reg_date) {
		this.trav_reg_date = trav_reg_date;
	}

	public char getTrav_state() {
		return trav_state;
	}

	public void setTrav_state(char trav_state) {
		this.trav_state = trav_state;
	}

	public String getTrav_cont() {
		return trav_cont;
	}

	public void setTrav_cont(String trav_cont) {
		this.trav_cont = trav_cont;
	}
	
	public String getTrav_desc() {
		return trav_desc;
	}

	public void setTrav_desc(String trav_desc) {
		this.trav_desc = trav_desc;
	}
}
