package com.teamHT.helloTraveler.DTO;

import java.util.Date;

public class MessagesDTO {

	String from_mem_code;
	String to_mem_code;
	String msg_code;
	String msg_title;
	String msg_cont;
	Date msg_date;
	String common_code;

	String from_mem_nick;
	String to_mem_nick;
	
	String from_common_code;
	String to_common_code;
	
	
	
	public String getFrom_common_code() {
		return from_common_code;
	}

	public void setFrom_common_code(String from_common_code) {
		this.from_common_code = from_common_code;
	}

	public String getTo_common_code() {
		return to_common_code;
	}

	public void setTo_common_code(String to_common_code) {
		this.to_common_code = to_common_code;
	}

	public String getFrom_mem_nick() {
		return from_mem_nick;
	}

	public void setFrom_mem_nick(String from_mem_nick) {
		this.from_mem_nick = from_mem_nick;
	}

	public String getTo_mem_nick() {
		return to_mem_nick;
	}

	public void setTo_mem_nick(String to_mem_nick) {
		this.to_mem_nick = to_mem_nick;
	}

	public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	public String getFrom_mem_code() {
		return from_mem_code;
	}

	public void setFrom_mem_code(String from_mem_code) {
		this.from_mem_code = from_mem_code;
	}

	public String getTo_mem_code() {
		return to_mem_code;
	}

	public void setTo_mem_code(String to_mem_code) {
		this.to_mem_code = to_mem_code;
	}

	public String getMsg_code() {
		return msg_code;
	}

	public void setMsg_code(String msg_code) {
		this.msg_code = msg_code;
	}

	public String getMsg_cont() {
		return msg_cont;
	}

	public void setMsg_cont(String msg_cont) {
		this.msg_cont = msg_cont;
	}

	public Date getMsg_date() {
		return msg_date;
	}

	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}

	public String getCommon_code() {
		return common_code;
	}

	public void setCommon_code(String common_code) {
		this.common_code = common_code;
	}

}
