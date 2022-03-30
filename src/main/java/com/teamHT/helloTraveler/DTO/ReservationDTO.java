package com.teamHT.helloTraveler.DTO;
import java.sql.Date;

public class ReservationDTO {

	String mem_code;
	String res_code;
	String common_code;
	String trav_code;
	String sale_code;
	int person_num;
	Date res_date;
	
	String trav_name;
	
	int sale_price;
	Date start_day;
	Date end_day;
	String mem_nick;
	
	public String getMem_code() {
		return mem_code;
	}
	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
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
	public String getSale_code() {
		return sale_code;
	}
	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}
	public int getPerson_num() {
		return person_num;
	}
	public void setPerson_num(int person_num) {
		this.person_num = person_num;
	}
	public Date getRes_date() {
		return res_date;
	}
	public void setRes_date(Date res_date) {
		this.res_date = res_date;
	}
	public String getTrav_name() {
		return trav_name;
	}
	public void setTrav_name(String trav_name) {
		this.trav_name = trav_name;
	}
	public int getSale_price() {
		return sale_price;
	}
	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
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
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
}
