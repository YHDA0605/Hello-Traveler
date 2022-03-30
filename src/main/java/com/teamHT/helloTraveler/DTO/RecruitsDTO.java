package com.teamHT.helloTraveler.DTO;

import java.util.Date;

public class RecruitsDTO {

	String common_code;
	String recu_code;
	String mem_code;
	String recu_title;
	String recu_cont;
	Date recu_reg_date;
	String recu_start_date;
	String recu_end_date;
	String recu_deadline;
	int recu_apply_n;
	int recu_max_n;
	String recu_common_code;
	int recu_hit;
	
	private MembersDTO membersDTO;
	private CommonsDTO commonsDTO;
	
	public CommonsDTO getCommonsDTO() {
		return commonsDTO;
	}

	public void setCommonsDTO(CommonsDTO commonsDTO) {
		this.commonsDTO = commonsDTO;
	}

	public MembersDTO getMembersDTO() {
		return membersDTO;
	}

	public void setMembersDTO(MembersDTO membersDTO) {
		this.membersDTO = membersDTO;
	}

	public String getCommon_code() {
		return common_code;
	}

	public void setCommon_code(String common_code) {
		this.common_code = common_code;
	}

	public String getRecu_code() {
		return recu_code;
	}

	public void setRecu_code(String recu_code) {
		this.recu_code = recu_code;
	}

	public String getMem_code() {
		return mem_code;
	}

	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}

	public String getRecu_title() {
		return recu_title;
	}

	public void setRecu_title(String recu_title) {
		this.recu_title = recu_title;
	}

	public String getRecu_cont() {
		return recu_cont;
	}

	public void setRecu_cont(String recu_cont) {
		this.recu_cont = recu_cont;
	}

	public Date getRecu_reg_date() {
		return recu_reg_date;
	}

	public void setRecu_reg_date(Date recu_reg_date) {
		this.recu_reg_date = recu_reg_date;
	}
	
	public String getRecu_start_date() {
		return recu_start_date;
	}

	public void setRecu_start_date(String recu_start_date) {
		this.recu_start_date = recu_start_date;
	}

	public String getRecu_end_date() {
		return recu_end_date;
	}

	public void setRecu_end_date(String recu_end_date) {
		this.recu_end_date = recu_end_date;
	}
	
	public String getRecu_deadline() {
		return recu_deadline;
	}

	public void setRecu_deadline(String recu_deadline) {
		this.recu_deadline = recu_deadline;
	}

	public int getRecu_apply_n() {
		return recu_apply_n;
	}

	public void setRecu_apply_n(int recu_apply_n) {
		this.recu_apply_n = recu_apply_n;
	}

	public int getRecu_max_n() {
		return recu_max_n;
	}

	public void setRecu_max_n(int recu_max_n) {
		this.recu_max_n = recu_max_n;
	}

	public String getRecu_common_code() {
		return recu_common_code;
	}

	public void setRecu_common_code(String recu_common_code) {
		this.recu_common_code = recu_common_code;
	}

	public int getRecu_hit() {
		return recu_hit;
	}

	public void setRecu_hit(int recu_hit) {
		this.recu_hit = recu_hit;
	}

}
