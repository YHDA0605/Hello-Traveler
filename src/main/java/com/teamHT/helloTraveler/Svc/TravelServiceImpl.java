package com.teamHT.helloTraveler.Svc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DAO.ICommonsDAO;
import com.teamHT.helloTraveler.DAO.IFilesDAO;
import com.teamHT.helloTraveler.DAO.ITrav_ComDAO;
import com.teamHT.helloTraveler.DAO.ITravelControlDAO;
import com.teamHT.helloTraveler.DAO.ITravelDAO;
import com.teamHT.helloTraveler.DTO.CommonsDTO;
import com.teamHT.helloTraveler.DTO.FilesDTO;
import com.teamHT.helloTraveler.DTO.Trav_ComDTO;
import com.teamHT.helloTraveler.DTO.TravelDTO;
import com.teamHT.helloTraveler.common.PagingVO;
@Service("travelService")
public class TravelServiceImpl implements ITravelService {
	
	@Autowired
	private ITravelDAO travDao;
	
	@Autowired
	private ITravelControlDAO traCtrlDao;
	
	@Autowired
	private ITrav_ComDAO travcomDao;
	
	@Autowired
	public ICommonsDAO comDao;
	
	@Autowired
	public IFilesDAO fileDao;
	
	@Override
	@Transactional
	public String insertTrav(TravelDTO trav, HttpServletRequest request, String dbImg) {
		
		HttpSession session = request.getSession(true);
		String mem_code = (String) session.getAttribute("CODE");
		
		int code = traCtrlDao.max_trav_code(trav.getCommon_code())+1;
		
		if (("").equals(trav.getTrav_code())) {
			
			TravelDTO dto = new TravelDTO();
			
			Trav_ComDTO travCom = travcomDao.getComcode(mem_code);
			
			
			
			dto.setCom_code(travCom.getCom_code());
			dto.setCommon_code(trav.getCommon_code());
			dto.setTrav_code(String.valueOf(code));
			dto.setTrav_name(trav.getTrav_name());
			dto.setTrav_price(trav.getTrav_price());
			dto.setTrav_nights(trav.getTrav_nights());
			dto.setTrav_desc(trav.getTrav_desc());
			dto.setTrav_cont(trav.getTrav_cont());
			dto.setTrav_state('1');
			
			travDao.insert(dto);
			
		} else {
			
			TravelDTO dto = travDao.sltOne(trav.getCommon_code(), trav.getTrav_code());
			
			dto.setTrav_name(trav.getTrav_name());
			dto.setTrav_price(trav.getTrav_price());
			dto.setTrav_nights(trav.getTrav_nights());
			dto.setTrav_desc(trav.getTrav_desc());
			dto.setTrav_cont(trav.getTrav_cont());
			
			travDao.update(dto);
			
		}
		
		// 이미지 넣는 로직
		if(dbImg != "") {
		FilesDTO file = new FilesDTO();
		String maxCode = fileDao.maxCode(trav.getCommon_code());
		int filecode = 0;

		if (maxCode == null) {
			filecode = 1;
		} else {
			filecode = Integer.parseInt(maxCode) + 1;
		}

		file.setCommon_code(trav.getCommon_code());
		file.setCode(String.valueOf(code));
		file.setFile_code(String.valueOf(filecode));
		file.setFile_name(dbImg);

		fileDao.insert(file);
		}
		
		return "redirect:/mypagetravel?mem_code="+mem_code; //마이페이지 안의 상품 등록 목록으로 ... 
	}

	@Override
	public String travList(String memCode, PagingVO paging, String cntPage, Model model) {
		
		int total = traCtrlDao.countTravelCom(memCode);
		
		if(cntPage == null) {
			cntPage = "1";
		}
		
		paging = new PagingVO(total, Integer.parseInt(cntPage));
		
		List<TravelDTO> travList = traCtrlDao.selectTravelComList(memCode, paging.getStart(), paging.getEnd());
		
		Trav_ComDTO travCom = travcomDao.getComcode(memCode);
		
		model.addAttribute("trav", travCom);
		model.addAttribute("paging", paging);
		model.addAttribute("travList", travList);
		
		return "myPage/myTravel";
	}
	
	@Override
	public String saleTravList(PagingVO paging, String cntPage, String common_code, Model model) {
		CommonsDTO common = comDao.getCommon(common_code);
		int total = 0;
		
		String search_common_code = "";
		
		if(cntPage == null) {
			cntPage = "1";
		}
		
		if(common_code.substring(3, 5).equals("00")) {
			search_common_code = common_code.substring(0, 3);
		}else {
			search_common_code = common_code;
		}
		
		total = traCtrlDao.countTravel(search_common_code);
		paging = new PagingVO(total, Integer.parseInt(cntPage));
		List<TravelDTO> travList = traCtrlDao.selectTravelList(search_common_code, paging.getStart(), paging.getEnd());
		
		model.addAttribute("COMMON", common);
		model.addAttribute("paging", paging);
		model.addAttribute("travList", travList);
		
		return "travel/travel"; 
	}
	
	

	@Override
	@Transactional
	public String getTrav(String commonCode, String TravCode, Model model) {
		TravelDTO trav = traCtrlDao.selectTravelOne(commonCode, TravCode);
		
		model.addAttribute("trav", trav);
		
		return "travel/travelContent";
	}
	
	
	@Override
	@Transactional
	   public String writeSale(String commonCode, String TravCode, Model model) {
	      TravelDTO trav = traCtrlDao.selectSaleOne(commonCode, TravCode);
	      
	      model.addAttribute("trav",trav);
	      
	      return "travel/travelCoWrite";
	   }
	
	@Override
	@Transactional
	public String deleteTrav(String commonCode, String travCode, HttpServletRequest request) {
		TravelDTO trav = travDao.sltOne(commonCode, travCode);
		
		if(trav == null) {
			return "404";
		}
		
		HttpSession session = request.getSession(true);
		String mem_code = (String) session.getAttribute("CODE");
		
		trav.setTrav_state('2');
		traCtrlDao.deleteTravel(trav);
		
		return "redirect:/mypagetravel?memCode="+mem_code;
	}

	@Override
	@Transactional
	public String travelEdit(Model model, String commonCode, String travCode) {
		TravelDTO trav = travDao.sltOne(commonCode, travCode);

		if (trav == null) {
			return "404";
		}

		model.addAttribute("trav", trav);

		return "travel/travelWrite";
	}

	
	
	// 02 - 22 추가
	@Override
	public String getTravComsTravels(PagingVO paging, String cntPage, String com_code, Model model) {
		
		int total  = traCtrlDao.countTravComsTravelsCount(com_code);
		if(cntPage == null) {
			cntPage = "1";
		}
		
		paging = new PagingVO(total, Integer.parseInt(cntPage));
		List<TravelDTO> travList = traCtrlDao.countTravComsTravels(paging.getStart(), paging.getEnd(), com_code);
				
		model.addAttribute("PAGE", paging);
		model.addAttribute("travList", travList);
		
		return "admin/adminTravComsTravel";
		
	}
	
	////////////////////////////////////////////////0222 인덱스용 수/정!!////////////////////////////////////////////////
	@Override
	public void selectTravelIndex(Model model) {
		List<TravelDTO> travList = traCtrlDao.selectTravelIndex();

		model.addAttribute("travList", travList);
	}
	
	
	// 02-23 추가(양)
	@Override
	public String travUpdateState(String common_code, String trav_code, String cmd) {
		TravelDTO trav = travDao.sltOne(common_code, trav_code);
		
		if("not".equals(cmd)) {
			trav.setTrav_state('3');
		}
		
		if("allow".equals(cmd)) {
			trav.setTrav_state('2');
		}
		
		travDao.update(trav);
		
		return "redirect:/travelContent?common_code="+common_code+"&trav_code="+trav_code;
	}
}
