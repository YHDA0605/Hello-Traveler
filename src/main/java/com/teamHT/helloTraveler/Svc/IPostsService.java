package com.teamHT.helloTraveler.Svc;

import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DTO.PostsDTO;
import com.teamHT.helloTraveler.common.PagingVO;

public interface IPostsService {
	public String getPosts(PagingVO paging, String common_code, String cntPage, String search_title, String search_nick, Model model);
	
	public String getadminPosts(PagingVO paging, String cntPage, String search_title, String search_nick, Model model);
	
	public String getPost(String common_code, String post_code, Model model);
	
	public String insertPost(PostsDTO post, String dbImg);
	
	public String updatePost(String common_code, String post_code, Model model);
	
	public String deletePost(String common_code, String post_code);
	
	//댓글용
	public String insertComent(PostsDTO post);
	
	public String re_insertComent(PostsDTO post);
	
	public String comentUpdate(PostsDTO post);
	
	//common용
	public String getCommon(String common_code, Model model);
	
	//index용
	public void selectIndex(Model model);
	
}
