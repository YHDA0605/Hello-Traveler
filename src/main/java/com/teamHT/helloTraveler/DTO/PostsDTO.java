package com.teamHT.helloTraveler.DTO;

public class PostsDTO {

	String common_code;
	String post_code;
	String mem_code;
	String post_title;
	String post_cont;
	String post_date;
	char post_state;
	int post_hit;
	String re_common_code;
	String re_post_code;
	int re_step;
	int re_level;
	
	//good테이블용
	int count;
	
	//댓글 수 가져올 테이블용
	int coment_count;
	
	//members테이블용
	String mem_nick;
	
	//common테이블용
	String common_name;
		
	//사진용
	String file_name;
		
	MembersDTO membersDto;
	GoodDTO goodDto;

	
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getComent_count() {
		return coment_count;
	}

	public void setComent_count(int coment_count) {
		this.coment_count = coment_count;
	}

	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public GoodDTO getGoodDto() {
		return goodDto;
	}

	public void setGoodDto(GoodDTO goodDto) {
		this.goodDto = goodDto;
	}

	public MembersDTO getMembersDto() {
		return membersDto;
	}

	public void setMembersDto(MembersDTO membersDto) {
		this.membersDto = membersDto;
	}

	public String getCommon_code() {
		return common_code;
	}

	public void setCommon_code(String common_code) {
		this.common_code = common_code;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getMem_code() {
		return mem_code;
	}

	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_cont() {
		return post_cont;
	}

	public void setPost_cont(String post_cont) {
		this.post_cont = post_cont;
	}



	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public char getPost_state() {
		return post_state;
	}

	public void setPost_state(char post_state) {
		this.post_state = post_state;
	}

	public int getPost_hit() {
		return post_hit;
	}

	public void setPost_hit(int post_hit) {
		this.post_hit = post_hit;
	}

	public String getRe_common_code() {
		return re_common_code;
	}

	public void setRe_common_code(String re_common_code) {
		this.re_common_code = re_common_code;
	}

	public String getRe_post_code() {
		return re_post_code;
	}

	public void setRe_post_code(String re_post_code) {
		this.re_post_code = re_post_code;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

}
