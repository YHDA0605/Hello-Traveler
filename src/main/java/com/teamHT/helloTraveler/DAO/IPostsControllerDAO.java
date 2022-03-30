package com.teamHT.helloTraveler.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamHT.helloTraveler.DTO.PostsDTO;

public interface IPostsControllerDAO {
	public List<PostsDTO> getAllGoodCounts(@Param("start") int start, @Param("end") int end, @Param("common_code") String common_code, @Param("allCommon_code") String allCommon_code, @Param("search_title") String search_title, @Param("search_nick") String search_nick);
	
	 public List<PostsDTO> getGoodCounts( @Param("start") int start
             , @Param("end") int end
             , @Param("common_code") String common_code
             , @Param("search_title") String search_title
             , @Param("search_nick") String search_nick);

	   public PostsDTO getGoodCount( @Param("common_code") String common_code, @Param("post_code") String post_code);
	   
	   public List<PostsDTO> getComment(@Param("common_code") String common_code, @Param("post_code") String post_code);
	   
	   public int countPosts(@Param("common_code") String common_code, @Param("search_title") String search_title, @Param("search_nick") String search_nick);
	   
	   public List<PostsDTO> selectIndex();
	   
	   public List<PostsDTO> getadminPosts( @Param("start") int start
	             , @Param("end") int end
	             , @Param("search_title") String search_title
	             , @Param("search_nick") String search_nick);
	   
		 public List<PostsDTO> getEvents( @Param("start") int start
				 , @Param("end") int end
				 , @Param("common_code") String common_code
				 , @Param("search_title") String search_title
				 , @Param("search_nick") String search_nick);
}
