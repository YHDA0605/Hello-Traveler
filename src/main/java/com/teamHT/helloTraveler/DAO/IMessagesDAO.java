package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.MessagesDTO;

public interface IMessagesDAO {
	public int maxCode();
	
	public List<MessagesDTO> to_msg(@Param("start") int start, @Param("end") int end, @Param("to_mem_code") String to_mem_code);
	
	public List<MessagesDTO> from_msg(@Param("start") int start, @Param("end") int end, @Param("from_mem_code") String from_mem_code);
	
	public MessagesDTO getMsgCont(@Param("msg_code") String msg_code);
	
	public void insert(MessagesDTO msg);
	
	public void msgUpdateState(@Param("msg_code") String msg_code, @Param("common_code") String common_code);
	
	public int countToMessage(String to_mem_code);
	
	public int countFromMessage(String from_mem_code);
	
	//추가
	public int getNoRead(String to_mem_code);
}
