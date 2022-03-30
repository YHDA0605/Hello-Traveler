package com.teamHT.helloTraveler.DTO;

public class GoodDTO {
	String common_code;
	String code;
	String good_code;
	String mem_code;
	String good_state;
	
	
	
	
	
	@Override
	public String toString() {
		return "GoodDTO [common_code=" + common_code + ", code=" + code + ", good_code=" + good_code + ", mem_code="
				+ mem_code + ", good_state=" + good_state + "]";
	}
	public String getCommon_code() {
		return common_code;
	}
	public void setCommon_code(String common_code) {
		this.common_code = common_code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGood_code() {
		return good_code;
	}
	public void setGood_code(String good_code) {
		this.good_code = good_code;
	}
	public String getMem_code() {
		return mem_code;
	}
	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}
	public String getGood_state() {
		return good_state;
	}
	public void setGood_state(String good_state) {
		this.good_state = good_state;
	}
	
}
