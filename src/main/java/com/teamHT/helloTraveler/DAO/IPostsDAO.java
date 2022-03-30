package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.PostsDTO;

public interface IPostsDAO {

	public PostsDTO selectOne(@Param("common_code") String common_code, @Param("post_code") String post_code);
	
	public List<PostsDTO> selectAll(String p_num, String common_code);
						  
	public void insert(PostsDTO post);

	public void update(PostsDTO post);

	public void delete(String common_code, String post_code);
	
	public String maxCode();
	
	public String getRow(String common_code);
	
	//댓글maxComentCode
	
	public String maxComentCode(@Param("common_code") String common_code, @Param("post_code") String post_code);
	
	public void addUpdateComent(@Param("common_code") String common_code, @Param("post_code") String post_code, @Param("re_level") int re_level);
	
	
}
