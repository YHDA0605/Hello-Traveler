package com.teamHT.helloTraveler.DTO;

public class CommonsDTO {

	String common_code;
	String common_name;

	public String getCommon_code() {
		return common_code;
	}

	public void setCommon_code(String common_code) {
		this.common_code = common_code;
	}

	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}

	@Override
	public String toString() {
		return "CommonsDTO [common_code=" + common_code + ", common_name=" + common_name + "]";
	}
	
}
