package com.teamHT.helloTraveler;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.DTO.MessagesDTO;
import com.teamHT.helloTraveler.DTO.PostsDTO;
import com.teamHT.helloTraveler.DTO.RecruitsDTO;
import com.teamHT.helloTraveler.DTO.ReservationDTO;
import com.teamHT.helloTraveler.DTO.Sale_TravDTO;
import com.teamHT.helloTraveler.DTO.Trav_ComDTO;
import com.teamHT.helloTraveler.DTO.TravelDTO;
import com.teamHT.helloTraveler.Svc.ICenterService;
import com.teamHT.helloTraveler.Svc.IGoodService;
import com.teamHT.helloTraveler.Svc.IMembersService;
import com.teamHT.helloTraveler.Svc.IMessageService;
import com.teamHT.helloTraveler.Svc.IPostsService;
import com.teamHT.helloTraveler.Svc.IRecruitsService;
import com.teamHT.helloTraveler.Svc.IReservationService;
import com.teamHT.helloTraveler.Svc.ISale_TravService;
import com.teamHT.helloTraveler.Svc.ITrav_ComService;
import com.teamHT.helloTraveler.Svc.ITravelService;
import com.teamHT.helloTraveler.common.PagingVO;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private String file_Path = "C:/2108KGH/workspace/Teams_HT_D215_merge/src/main/webapp/resources/images";
	@Autowired
	private IMembersService mbrService;
	
	@Autowired
	private ITrav_ComService tComService;
	
	@Autowired
	private IRecruitsService recuService;
	
	@Autowired
	private IPostsService postService;
	
	@Autowired
	private IGoodService goodService;
	
	@Autowired
	private ISale_TravService saleService;
	
	@Autowired
	private ITravelService travelService;
	
	@Autowired
	private IMessageService msgService;
	
	@Autowired
	private ICenterService centerService;
	
	@Autowired
	private IReservationService resvService;
	
	// 메인페이지로 이동
		////////////////////////////////////0222 추가 //////////////////////////////////////////////////
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String home(Model model) {
			logger.info("Welcome home!");
			postService.selectIndex(model);
			recuService.selectIndex(model);
			travelService.selectTravelIndex(model);
			return "index";
		}

	////////////////////////////////////0222 추가 //////////////////////////////////////////////////

	
	// 로그인 처리
	@RequestMapping("login")
	public String login() {
		return "login/login";
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "login/loginForm";
	}

	@RequestMapping("loginAction")
	public String loginAction(@RequestParam("ID") String id, @RequestParam("PW") String pw,
			HttpServletRequest request) {
		return mbrService.loginCheck(id, pw, request);
	}

	@RequestMapping("logOut")
	public String logOut(HttpServletRequest request) {
		return mbrService.logOut(request);
	}

	
	// 회원가입처리
	@RequestMapping("regist")
	public String regist() {
		return "regist/regist";
	}

	@RequestMapping("registNormal")
	public String registNormal() {
		return "regist/registNormal";
	}

	@RequestMapping(value = "doRegistNormal", method = RequestMethod.POST)
	public String doRegistNormal(MembersDTO mbr) {

		return mbrService.registNormalMember(mbr);
	}

	// !!!!!!!!!!!!!!!!!!!2017 추가 !!!!!!!!!!!!!!!!!!!!!!!!!!
	@RequestMapping(value = "doRegistNormal_SNS", method = RequestMethod.POST)
	public String doRegistNormal_SNS(MembersDTO mbr) {

		return mbrService.registNormalMember_SNS(mbr);
	}

	@RequestMapping("registCompany")
	public String registCompany() {
		return "regist/registCompany";
	}
	
	@RequestMapping(value = "doRegistCompany", method = RequestMethod.POST)
	public String doRegistCompany(MembersDTO mbr, Trav_ComDTO tCom) {

		return tComService.registComMember(mbr, tCom);
	}
	
	
	// 게시판처리
		@RequestMapping("post")
		public String post(PagingVO page, @RequestParam("common_code") String common_code,
				@RequestParam(value = "cntPage", defaultValue = "1") String cntPage,
				@RequestParam(value = "search_type", defaultValue = "search_title") String search_type,
				@RequestParam(value = "search_text", defaultValue = "") String search_text, Model model) {
			String view = postService.getPosts(page, common_code, cntPage, search_type, search_text, model);
			return view;
		}

		@RequestMapping("postContent")
		public String postContent(@RequestParam("common_code") String common_code,
				@RequestParam("POST_CODE") String post_code, Model model) {
			String view = postService.getPost(common_code, post_code, model);
			return view;
		}

		@RequestMapping("postWrite")
		public String postWrite(@RequestParam("common_code") String common_code, Model model) {
			String view = postService.getCommon(common_code, model);
			return view;
		}

		@RequestMapping("postWtiteAction")
		public String postWtiteAction(PostsDTO post, @RequestParam MultipartFile file) throws IllegalStateException, IOException {
			String dbImg = "";
			if(!file.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
	        File converFile = new File(file_Path, uuid + file.getOriginalFilename());
	        file.transferTo(converFile);
	        dbImg = "resources/imgUpload/" + uuid + file.getOriginalFilename();
			}
			String view = postService.insertPost(post, dbImg);
			return view;
		}

		@RequestMapping("postUpdateAction")
		public String postUpdateAction(@RequestParam("common_code") String common_code,
				@RequestParam("POST_CODE") String post_code, Model model) {
			String view = postService.updatePost(common_code, post_code, model);
			return view;
		}

		@RequestMapping("postDeleteAction")
		public String postDeleteAction(@RequestParam("common_code") String common_code,
				@RequestParam("POST_CODE") String post_code) {
			String view = postService.deletePost(common_code, post_code);
			return view;
		}

		@RequestMapping("comentAction")
		public String comentAction(PostsDTO post) {
			String view = postService.insertComent(post);
			return view;
		}

		@RequestMapping("re_comentAction")
		public String re_comentAction(PostsDTO post) {
			String view = postService.re_insertComent(post);
			return view;
		}

		@RequestMapping("comentUpdateAction")
		public String comentUpdateAction(PostsDTO post) {
			String view = postService.comentUpdate(post);
			return view;
		}

		@RequestMapping("goodAction")
		public String goodAction(@RequestParam("common_code") String common_code, @RequestParam("CODE") String code,
				@RequestParam("MEM_CODE") String mem_code) {
			String view = goodService.updateGood(common_code, code, mem_code);
			return view;
		}
	// 마이페이지 처리
	@RequestMapping("mypage")
	public String mypage(Model model, HttpServletRequest request) {
		String view = mbrService.showMyPage(model, request);
		
		return view;
	}
	
	@RequestMapping(value = "editNick", method = RequestMethod.POST)
	public String editNick(MembersDTO mbr, HttpServletRequest request) {
		
		mbrService.editMem(mbr, request);
		
		return "redirect:/mypage";
	}
	
	@RequestMapping(value = "editPhone", method = RequestMethod.POST)
	public String editPhone(MembersDTO mbr, HttpServletRequest request) {
		
		mbrService.editMem(mbr, request);
		
		return "redirect:/mypage";
	}
	
	@RequestMapping(value = "editEmail", method = RequestMethod.POST)
	public String editEmail(MembersDTO mbr, HttpServletRequest request) {
		
		mbrService.editMem(mbr, request);
		
		return "redirect:/mypage";
	}
	
	@RequestMapping(value = "dodelmem", method = RequestMethod.POST)
	public String delmem (MembersDTO mbr, HttpServletRequest request, Model model) {
	
		mbrService.delMem(mbr, request, model);
		mbrService.logOut(request);

		
		return "index" ;
	}
	
	@RequestMapping("myReservation")
	public String myReservation(@RequestParam("mem_code")String mem_code, @RequestParam(value="cntPage", required=false)String cntPage, PagingVO paging, Model model) {
		String view = resvService.myReservationList(mem_code, paging, cntPage, model);
		
		return view;
	}
	
	// 모집 게시판 처리
	@RequestMapping("recruit") //!!
	public String recruit(PagingVO paging
						, Model model
						, @RequestParam(value="cntPage", required=false)String cntPage
						, @RequestParam("common_code")String common_code
						, @RequestParam(value = "search_type", defaultValue = "search_title") String search_type,
						  @RequestParam(value = "search_text", defaultValue = "") String search_text) {
		
		String view = recuService.getRecrus(model, paging, cntPage, common_code, search_type, search_text);
		
		return view;
	}
	
	@RequestMapping("recruitContent") //!!
	public String recuritContent(Model model
								, @ModelAttribute("common_code")String common_code
								, @ModelAttribute("recu_code")String recuCode) throws ParseException {
		
		String view = recuService.getRecru(model, common_code, recuCode);
		
		return view;
	}
	
	@RequestMapping("recruitDelete")
	public String recruitDelete(@ModelAttribute("common_code")String common_code
							  , @ModelAttribute("recu_code")String recuCode) {
		String view = recuService.deleteRecu(common_code, recuCode);
		
		return view;
	}
	
	@RequestMapping("recruitWrite")
	public String recruitWrite(Model model, @ModelAttribute("common_code")String common_code) {
		String view = recuService.reruitWriteForm(model, common_code);
		
		return view;
	}
	
	
	@RequestMapping(value="recruitWriteAction", method = RequestMethod.POST)
	public String recruitWriteAction(RecruitsDTO recu) throws Exception {
		
		String view = recuService.insertRecu(recu);
		
		return view;
	}
	
	@RequestMapping("recruitEdit")
	public String recruitEdit(Model model, @ModelAttribute("common_code")String common_code, @ModelAttribute("recu_code")String recu_code) throws Exception {
		
		String view = recuService.recuritEdit(model, common_code, recu_code);
		
		return view;
		
	}
	
	@RequestMapping("recruitEnd")
	public String recruitEnd(Model model, @ModelAttribute("common_code")String common_code, @ModelAttribute("recu_code")String recu_code) throws Exception {
		
		String view = recuService.recuritEdit(model, common_code, recu_code);
		
		return view;
		
	}

	
	// 여행상품 처리
	@RequestMapping("mypagetravel")
	public String mypagetravel(PagingVO paging, Model model,
			@RequestParam(value = "cntPage", required = false) String cntPage,
			@ModelAttribute("mem_code") String mem_code) {
		String view = travelService.travList(mem_code, paging, cntPage, model);

		return view;
	}

	@RequestMapping("travelWrite")
	public String tarvelWrite() {
		return "travel/travelWrite";
	}

	// 여행사가 검수용 상품 등록
	@RequestMapping(value = "travelWriteAction", method = RequestMethod.POST)
	public String travelWtiteAction(TravelDTO trav, HttpServletRequest request, @RequestParam MultipartFile file) throws IllegalStateException, IOException {
		String dbImg = "";
		
		if(!file.isEmpty()) {
		String uuid = UUID.randomUUID().toString();
        File converFile = new File(file_Path, uuid + file.getOriginalFilename());
        file.transferTo(converFile);
        dbImg = "resources/imgUpload/" + uuid + file.getOriginalFilename();
		}
		
		String view = travelService.insertTrav(trav, request, dbImg);
		return view;
	}

	@RequestMapping("travelEdit")
	public String travelEdit(Model model, @ModelAttribute("common_code") String common_code,
			@ModelAttribute("trav_code") String trav_code) {
		String view = travelService.travelEdit(model, common_code, trav_code);

		return view;

	}

	// 아마 수정완료
	@RequestMapping("travelContent")
	public String tarvelContent(Model model, @ModelAttribute("common_code") String common_code,
			@ModelAttribute("trav_code") String trav_code) {
		String view = travelService.getTrav(common_code, trav_code, model);

		return view;
	}

	@RequestMapping("travelDelete")
	public String travelDelete(@ModelAttribute("common_code") String common_code,
			@ModelAttribute("trav_code") String trav_code, HttpServletRequest request) {
		String view = travelService.deleteTrav(common_code, trav_code, request);

		return view;
	}
	
	// 소비자들이 보는 화면
	@RequestMapping("travel")
	public String travel(PagingVO paging, Model model,
			@RequestParam(value = "cntPage", required = false) String cntPage,
			@RequestParam("common_code") String common_code) {
		String view = travelService.saleTravList(paging, cntPage, common_code, model);

		return view;
	}

	@RequestMapping("saleWriteAction")
	public String saleWriteAction(Model model, Sale_TravDTO sale_trav, @RequestParam("day") List<String> day,
			@RequestParam("start_date") String start_date, @RequestParam("end_date") String end_date) throws Exception {

		String view = saleService.insertSale(sale_trav, day, start_date, end_date, model);

		return view;
	}

	@RequestMapping("saleTravelContent")
	public String tarvelCoContent(Model model, @ModelAttribute("common_code") String common_code, @ModelAttribute("trav_code") String trav_code) {
		String view = saleService.getSale(common_code, trav_code, model);

		return view;
	}

	@RequestMapping("reservationAction")
	public String reservationAction(ReservationDTO resv) {
		String view = resvService.insertResc(resv);

		return view;
	}

	// 메세지 처리

	@RequestMapping("applyMessageForm")
	public String applyMessageForm(@ModelAttribute("mem_code") String mem_code, @RequestParam("common_code") String common_code, @RequestParam(value = "recu_code", defaultValue = "") String recu_code, Model model) {
		String view = msgService.applyMessage(mem_code, common_code, recu_code, model);
		return view;
	}

	@RequestMapping("applyAction")
    public String applyAction(MessagesDTO msg,  @RequestParam("common_code") String common_code,  @RequestParam("recu_code") String recu_code) {
       
       String view = msgService.applyMessageInsert(msg, common_code, recu_code);
       
       return view; 
    }

	@RequestMapping("to_message")
	public String to_message(PagingVO page, @RequestParam("to_mem_code") String to_mem_code, @RequestParam(value = "cntPage", defaultValue = "1") String cntPage, Model model, HttpServletRequest request) {
		String view = msgService.getToMSG(page, to_mem_code, cntPage, model, request);
		return view;
	}
	
	@RequestMapping("from_message")
	public String from_message(PagingVO page, @RequestParam("from_mem_code") String from_mem_code, @RequestParam(value = "cntPage", defaultValue = "1") String cntPage, Model model) {
		String view = msgService.getFromMSG(page, from_mem_code, cntPage, model);
		return view;
	}
	
	@RequestMapping("messageWrite")
	public String messageWrite() {
		return "message/messageWrite";
	}
	
	@RequestMapping("messageWriteAction")
	public String messageWriteAction(MessagesDTO msg, @RequestParam("mem_id") String mem_id) {
		String view = msgService.insertMSG(msg, mem_id);
		return view;
	}
	
	@RequestMapping("messageContent")
	public String messageContent(@RequestParam("msg_code") String msg_code, @RequestParam("mem_code") String mem_code, Model model, HttpServletRequest request) {
		//읽음 표시 처리
		msgService.msgSeeUpdateState(msg_code, mem_code, model);
		//content로 이동
		String view = msgService.getMsgCont(msg_code, model, request);

		return view;
	}
	
	@RequestMapping("messageReply")
	public String messageReply(@RequestParam("mem_code") String mem_code, @RequestParam("mem_nick") String mem_nick, Model model) {
		String view = msgService.getReply(mem_code, mem_nick, model);
		return view;
	}
	
	@RequestMapping("messageUpdateState")
	public String messageUpdateState(@RequestParam("msg_code") String msg_code, @RequestParam("mem_code") String mem_code, Model model) {
		String view = msgService.msgUpdateState(msg_code, mem_code,model);
		return view;
	}

	//고객센터
	//공지사항
	@RequestMapping("center")
	public String announcement(PagingVO page,
			@RequestParam("common_code") String common_code,
			@RequestParam(value = "cntPage", defaultValue = "1") String cntPage,
			@RequestParam(value = "search_type", defaultValue = "search_title") String search_type,
			@RequestParam(value = "search_text", defaultValue = "") String search_text, Model model) {
		String view = centerService.getPosts(page, common_code, cntPage, search_type, search_text, model);
		return view;
	}
	
	@RequestMapping("centerContent")
	public String announcementContent(@RequestParam("common_code") String common_code,
			@RequestParam("POST_CODE") String post_code, Model model) {
		String view = centerService.getPost(common_code, post_code, model);
		return view;
	}

	@RequestMapping("centerWrite")
	public String announcementWrite(@RequestParam("common_code") String common_code, Model model) {
		String view = centerService.getCommon(common_code, model);
		return view;
	}

	@RequestMapping("centerWtiteAction")
	public String announcementWtiteAction(PostsDTO post,  @RequestParam MultipartFile file) throws IllegalStateException, IOException {
		String dbImg = "";
		if(!file.isEmpty()) {
		String uuid = UUID.randomUUID().toString();
        File converFile = new File(file_Path, uuid + file.getOriginalFilename());
        file.transferTo(converFile);
        dbImg = "resources/imgUpload/" + uuid + file.getOriginalFilename();
		}
		String view = centerService.insertPost(post, dbImg);
		return view;
	}

	@RequestMapping("centerUpdateAction")
	public String announcementUpdateAction(@RequestParam("common_code") String common_code,
			@RequestParam("POST_CODE") String post_code, Model model) {
		String view = centerService.updatePost(common_code, post_code, model);
		return view;
	}

	@RequestMapping("centerDeleteAction")
	public String announcementDeleteAction(@RequestParam("common_code") String common_code,
			@RequestParam("POST_CODE") String post_code) {
		String view = centerService.deletePost(common_code, post_code);
		return view;
	}

	@RequestMapping("centerComentAction")
	public String announcementComentAction(PostsDTO post) {
		String view = centerService.insertComent(post);
		return view;
	}

	@RequestMapping("centerRe_comentAction")
	public String announcementRe_comentAction(PostsDTO post) {
		String view = centerService.re_insertComent(post);
		return view;
	}
	
	@RequestMapping("centerComentUpdateAction")
	public String announcementComentUpdateAction(PostsDTO post) {
		String view = centerService.comentUpdate(post);
		return view;
	}

	@RequestMapping("centerGoodAction")
	public String announcementGoodAction(@RequestParam("common_code") String common_code, @RequestParam("CODE") String code,
			@RequestParam("MEM_CODE") String mem_code) {
		String view = goodService.centerUpdateGood(common_code, code, mem_code);
		return view;
	}

	//이벤트
	//이벤트
	@RequestMapping("event") //!!
	public String event(PagingVO page,
			@RequestParam("common_code") String common_code,
			@RequestParam(value = "cntPage", defaultValue = "1") String cntPage,
			@RequestParam(value = "search_type", defaultValue = "search_title") String search_type,
			@RequestParam(value = "search_text", defaultValue = "") String search_text, Model model) {
		String view = centerService.getPosts(page, common_code, cntPage, search_type, search_text, model);
		return view;
	}
	
	
	
	//김경환
    //admin
    @RequestMapping("adminPost")
    public String adminPost(PagingVO page,
				@RequestParam(value = "cntPage", defaultValue = "1") String cntPage,
				@RequestParam(value = "search_type", defaultValue = "search_title") String search_type,
				@RequestParam(value = "search_text", defaultValue = "") String search_text, Model model) {
			String view = postService.getadminPosts(page, cntPage, search_type, search_text, model);
			return view;
	}
    
    @RequestMapping("adminMem")
    public String adminMem(PagingVO paging
			, @RequestParam(value="cntPage", required=false)String cntPage
			, @RequestParam(value = "search_type", defaultValue = "search_title") String search_type
			, @RequestParam(value = "search_text", defaultValue = "") String search_text
			, Model model) {
   	 String view = mbrService.adminMemAll(paging, cntPage, search_type, search_text, model);
   	 return view;
    }
	
    @RequestMapping("adminTravCom") // 02-22 추가함(양)
    public String adminTravCom(PagingVO pageing, @RequestParam(value = "cntPage", defaultValue = "1") String cntPage, Model model) {
    	
    	String view = tComService.adminTravComs(pageing, cntPage, model);
    	
    	return view;
    }
    
    
    @RequestMapping("adminTravComsTravel") // 02-22 추가함(양)
    public String adminTravComsTravel(PagingVO pageing, @RequestParam(value = "cntPage", defaultValue = "1") String cntPage, @RequestParam("com_code") String com_code, Model model) {
    	
    	String view = travelService.getTravComsTravels(pageing, cntPage, com_code, model);
    	
    	return view;
    }
    
    //02.22 추가
    @RequestMapping("updateGrade")
    public String updateGrade(@RequestParam("mem_code") String mem_code, @RequestParam("grade_type") String common_code) {
    	String view = mbrService.updateGrade(mem_code, common_code);
    	return view;
    }
    
    
    // 02-23 추가(양)
    @RequestMapping("notAllowTravel")
    public String notAllowTravel(@RequestParam("common_code")String common_code, @RequestParam("trav_code")String trav_code, @RequestParam("cmd")String cmd) {
    	String view = travelService.travUpdateState(common_code, trav_code, cmd);
    	return view;
    }
    
    @RequestMapping("AllowTravel")
    public String AllowTravel(@RequestParam("common_code")String common_code, @RequestParam("trav_code")String trav_code, @RequestParam("cmd")String cmd) {
    	String view = travelService.travUpdateState(common_code, trav_code, cmd);
    	return view;
    }
    
}