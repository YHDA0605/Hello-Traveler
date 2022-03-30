package com.teamHT.helloTraveler.Svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DAO.ICommonsDAO;
import com.teamHT.helloTraveler.DAO.IFilesDAO;
import com.teamHT.helloTraveler.DAO.IGoodDAO;
import com.teamHT.helloTraveler.DAO.IPostsControllerDAO;
import com.teamHT.helloTraveler.DAO.IPostsDAO;
import com.teamHT.helloTraveler.DTO.CommonsDTO;
import com.teamHT.helloTraveler.DTO.FilesDTO;
import com.teamHT.helloTraveler.DTO.PostsDTO;
import com.teamHT.helloTraveler.common.PagingVO;

@Service("centerService")
public class CenterServiceImpl implements ICenterService{
	@Autowired
	public IPostsDAO postDao;

	@Autowired
	public ICommonsDAO comDao;

	@Autowired
	public IGoodDAO goodDao;

	@Autowired
	public IPostsControllerDAO posConDao;
	
	@Autowired
	public IFilesDAO fileDao;
	
	//공지사항
	@Override
	public String getPosts(PagingVO paging, String common_code, String cntPage, String search_type, String search_text, Model model) {
		CommonsDTO common = comDao.getCommon(common_code);
		
		String search_nick = "";
		String search_title = "";
		if(search_type.equals("search_title")) {
			search_title = search_text;
		}else if(search_type.equals("search_nick")){
			search_nick = search_text;
		}
		
		int total = posConDao.countPosts(common_code, search_title, search_nick);
		if(cntPage == null) {
			cntPage = "1";
		}
		
		paging = new PagingVO(total, Integer.parseInt(cntPage));
		model.addAttribute("LIST", posConDao.getEvents(paging.getStart(), paging.getEnd(), common_code, search_title, search_nick));
		model.addAttribute("COMMON", common);
		model.addAttribute("PAGE", paging);
		model.addAttribute("TYPE", search_type);
		model.addAttribute("TEXT", search_text);
		
		String view = null;
		if(common_code.equals("50002")) {
			view = "center/event";
		}else {
			view = "center/center";
		}
		return view;
	}

	@Override
	public String getPost(String common_code, String post_code, Model model) {
		PostsDTO post = posConDao.getGoodCount(common_code, post_code);
		post.setPost_hit(post.getPost_hit() + 1);
		postDao.update(post);

		List<PostsDTO> list = posConDao.getComment(common_code, post_code);

		
		//이미지 가져오는 로직
		String img = fileDao.selectOne(common_code, post_code);
				
		model.addAttribute("IMG", img);
		model.addAttribute("POST", post);
		model.addAttribute("LIST", list);
		
		return "center/centerContent";
	}

	@Override
	@Transactional
	public String insertPost(PostsDTO post, String dbImg) {
		// 새로운 글이라면
		if (post.getPost_code().equals("")) {
			String maxCode = postDao.maxCode();
			int code = 0;

			if (maxCode == null) {
				code = 1;
			} else {
				code = Integer.parseInt(maxCode) + 1;
			}
			// 공통코드 바꿔야함
			// 게시글이라면
			post.setCommon_code(post.getCommon_code());
			post.setPost_code(String.valueOf(code));
			post.setMem_code(post.getMem_code());
			post.setPost_title(post.getPost_title());
			post.setPost_cont(post.getPost_cont());
			post.setPost_state('0');
			post.setPost_hit(0);
			post.setRe_common_code(post.getCommon_code());
			post.setRe_post_code(post.getPost_code());
			post.setRe_step(1);
			post.setRe_level(1);
			postDao.insert(post);
		} else {
			// 수정한 글이라면
			PostsDTO temp = postDao.selectOne(post.getCommon_code(), post.getPost_code());
			temp.setPost_title(post.getPost_title());
			temp.setPost_cont(post.getPost_cont());
			postDao.update(temp);
		}
		
		//이미지 넣는 로직
		if(dbImg != "") {
		FilesDTO file = new FilesDTO();
		String maxCode = fileDao.maxCode(post.getCommon_code());
		int code = 0;

		if (maxCode == null) {
			code = 1;
		} else {
			code = Integer.parseInt(maxCode) + 1;
		}
		
		file.setCommon_code(post.getCommon_code());
		file.setCode(post.getPost_code());
		file.setFile_code(String.valueOf(code));
		file.setFile_name(dbImg);
		
		fileDao.insert(file);
		}
		
		return "redirect:/centerContent?common_code=" + post.getCommon_code() + "&POST_CODE=" + post.getPost_code();
		
	}

	@Override
	@Transactional
	public String deletePost(String common_code, String post_code) {
		PostsDTO post = postDao.selectOne(common_code, post_code);
		post.setPost_state('1');
		postDao.update(post);
		
		if(post.getRe_step() > 1) {
			return  "redirect:/centerContent?common_code=" + post.getCommon_code() + "&POST_CODE=" + post.getRe_post_code();
		}
		
		return "redirect:/center?common_code=" + post.getCommon_code();
	}

	@Override
	@Transactional
	public String updatePost(String common_code, String post_code, Model model) {
		CommonsDTO common = comDao.getCommon(common_code);
		PostsDTO post = postDao.selectOne(common_code, post_code);
		model.addAttribute("UPDATE", post);
		model.addAttribute("COMMON", common);
		return "center/centerWrite";
	}

	// 댓글용
	@Override
	@Transactional
	public String insertComent(PostsDTO post) {
		String firstPostCode = post.getPost_code();
		// 여기에 한번 정보 받아와야함 DTO
		// 댓글(커먼코드랑 포스트코드 넘어온거 받기) 이미 댓글이라면 대댓글처리
			String maxCode = postDao.maxCode();
			int code = Integer.parseInt(maxCode) + 1;

			String maxComentCode = postDao.maxComentCode(post.getCommon_code(),post.getPost_code());
			int Comentcode = 0;

			if (maxComentCode.equals("0")) {
				Comentcode = 1;
			} else {
				Comentcode = Integer.parseInt(maxComentCode) + 1;
			}
			post.setCommon_code(post.getCommon_code());

			post.setPost_code(String.valueOf(code));
			post.setMem_code(post.getMem_code());
			post.setPost_title("댓글");
			post.setPost_cont(post.getPost_cont());
			post.setPost_state('0');
			post.setPost_hit(0);
			post.setRe_common_code(post.getCommon_code());
			post.setRe_post_code(firstPostCode);
			post.setRe_step(2);
			post.setRe_level(Comentcode);
			postDao.insert(post);

		return "redirect:/centerContent?common_code=" + post.getCommon_code() + "&POST_CODE=" + firstPostCode;
	}
	
	@Override
	@Transactional
	public String re_insertComent(PostsDTO post) {
		String firstPostCode = post.getPost_code();
		// 여기에 한번 정보 받아와야함 DTO
		// 댓글(커먼코드랑 포스트코드 넘어온거 받기) 이미 댓글이라면 대댓글처리
			// post_code는 무조건 1씩 상승이니깐
			String maxCode = postDao.maxCode();
			int Comentcode = post.getRe_level() + 1;
			int code = 0;

			if (maxCode == null) {
				code = 1;
			} else {
				code = Integer.parseInt(maxCode) + 1;
			}
			
			// max를 받는게 아닌 날라온 댓글의 level정보를 받아서
			//넣기전에 이부분 날라온 레벨보다 큰 레벨들 다 +1하고 해당 댓글도 +1해서 넣으면됨
			postDao.addUpdateComent(post.getCommon_code(), post.getPost_code(), post.getRe_level());//1씩 더해주는 로직
			
			post.setCommon_code(post.getCommon_code());// 동일
			post.setPost_code(String.valueOf(code));
			post.setMem_code(post.getMem_code());// 동일
			post.setPost_title("대댓글");// 동일
			post.setPost_cont(post.getPost_cont());// 동일
			post.setPost_state('0');// 동일
			post.setPost_hit(0);// 동일
			post.setRe_common_code(post.getCommon_code());// 동일
			post.setRe_post_code(firstPostCode);// 동일
			post.setRe_step(3);// 동일
			post.setRe_level(Comentcode);
			postDao.insert(post);
			
			//이부분 날라온 레벨보다 +1하면?
			
			return "redirect:/centerContent?common_code=" + post.getCommon_code() + "&POST_CODE=" + firstPostCode;
	}
	
	@Override
	public String getCommon(String common_code, Model model) {
		CommonsDTO common = comDao.getCommon(common_code);
		model.addAttribute("COMMON", common);
		return "center/centerWrite";
	}

	@Override
	@Transactional
	public String comentUpdate(PostsDTO post) {
		String firstContent = post.getPost_cont();
		
		post = postDao.selectOne(post.getCommon_code(), post.getPost_code());
		post.setPost_cont(firstContent);
		
		postDao.update(post);
		return "redirect:/centerWrite?common_code=" + post.getCommon_code() + "&POST_CODE=" + post.getRe_post_code();
	}

}
