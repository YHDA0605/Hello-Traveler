package com.teamHT.helloTraveler.Svc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DAO.ICommonsDAO;
import com.teamHT.helloTraveler.DAO.IRecruitsControlDAO;
import com.teamHT.helloTraveler.DAO.IRecruitsDAO;
import com.teamHT.helloTraveler.DTO.CommonsDTO;
import com.teamHT.helloTraveler.DTO.RecruitsDTO;
import com.teamHT.helloTraveler.common.PagingVO;

@Service("recuService")
public class RecruitsServiceImpl implements IRecruitsService {
	
	@Autowired
	private IRecruitsDAO recuDao;
	
	@Autowired
	private IRecruitsControlDAO recuCtrlDao;
	
	@Autowired
	public ICommonsDAO comDao;
	
	@Override
	public String getRecrus(Model model, PagingVO paging, String cntPage, String common_code, String search_type, String search_text) {
		CommonsDTO common = comDao.getCommon(common_code);
		int total = 0;
		String search_common_code = "";
		String search_nick = "";
		String search_title = "";
		
		if(search_type.equals("search_title")) {
			search_title = search_text;
		}else if(search_type.equals("search_nick")){
			search_nick = search_text;
		}
		
		if(cntPage == null) {
			cntPage = "1";
		}

		if(common_code.substring(3, 5).equals("00")) {
			search_common_code = common_code.substring(0, 3);
		}else {
			search_common_code = common_code;
		}
		
		total = recuCtrlDao.countRecruits(search_common_code, search_title, search_nick);
		paging = new PagingVO(total, Integer.parseInt(cntPage));
		List<RecruitsDTO> recuList = recuCtrlDao.selectRecruitsList(search_common_code, paging.getStart(), paging.getEnd(), search_title, search_nick);
		
		model.addAttribute("COMMON", common);
		model.addAttribute("paging", paging);
		model.addAttribute("recuList", recuList);
		model.addAttribute("TYPE", search_type);
		model.addAttribute("TEXT", search_text);
		
		return "recruit/recruit";
	}
	

	@Override
	public String getRecru(Model model, String commonCode, String recuCode) throws ParseException {
		
		RecruitsDTO recu = recuCtrlDao.selectRecruitOne(commonCode, recuCode);
		
		recu.setRecu_hit(recu.getRecu_hit()+1);
		recuDao.update(recu);
		
		model.addAttribute("recu", recu);
		
		return "recruit/recruitContent";
	}
	
	
	@Override
	public String insertRecu(RecruitsDTO recu) throws Exception {
		
		if(("").equals(recu.getRecu_code())) {
			String code = recuDao.maxRecuCode();
			
			int recuCode;
			
			if(code == null) {
				recuCode = 1;
			}else {
				recuCode = Integer.parseInt(code) + 1;
			}
			
			recu.setCommon_code(recu.getCommon_code());
			recu.setRecu_code(String.valueOf(recuCode));
			recu.setMem_code(recu.getMem_code());
			recu.setRecu_title(recu.getRecu_title());
			recu.setRecu_cont(recu.getRecu_cont());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start_date = format.parse(recu.getRecu_start_date());
			Date end_date = format.parse(recu.getRecu_end_date());
			Date deadline = format.parse(recu.getRecu_deadline());
			
			recu.setRecu_start_date(new SimpleDateFormat("yyyyMMdd").format(start_date));
			recu.setRecu_end_date(new SimpleDateFormat("yyyyMMdd").format(end_date));
			recu.setRecu_deadline(new SimpleDateFormat("yyyyMMdd").format(deadline));
			
			recu.setRecu_apply_n(0);
			recu.setRecu_max_n(recu.getRecu_max_n());
			recu.setRecu_common_code("31001");
			recu.setRecu_hit(0);
			
			recuDao.insert(recu);
			
		}else {
			
			RecruitsDTO temp = recuDao.selectOne(recu.getCommon_code(), recu.getRecu_code());
			temp.setRecu_title(recu.getRecu_title());
			temp.setRecu_cont(recu.getRecu_cont());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start_date = format.parse(recu.getRecu_start_date());
			Date end_date = format.parse(recu.getRecu_end_date());
			Date deadline = format.parse(recu.getRecu_deadline());
			
			temp.setRecu_start_date(new SimpleDateFormat("yyyyMMdd").format(start_date));
			temp.setRecu_end_date(new SimpleDateFormat("yyyyMMdd").format(end_date));
			temp.setRecu_deadline(new SimpleDateFormat("yyyyMMdd").format(deadline));
			
			temp.setRecu_max_n(recu.getRecu_max_n());
			
			recuDao.update(temp);
		}
		
		
		return "redirect:/recruitContent?common_code="+recu.getCommon_code()+"&recu_code="+recu.getRecu_code();
		
	}
	
	//
	@Override
	public String deleteRecu(String commonCode, String recuCode) {
		RecruitsDTO recu = recuDao.selectOne(commonCode, recuCode);
		
		if(recu == null) {
			return "404";
		}
		
		recu.setRecu_common_code("31003");
		recuCtrlDao.delete(recu);
		
		return "redirect:/recruit?common_code="+commonCode;
	}


	@Override
	public String recuritEdit(Model model, String common_code, String recu_code) throws Exception {
		RecruitsDTO recu = recuDao.selectOne(common_code, recu_code);
		
		if(recu == null) {
			return "404";
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		Date start_date = format.parse(recu.getRecu_start_date());
		Date end_date = format.parse(recu.getRecu_end_date());
		Date deadline = format.parse(recu.getRecu_deadline());
		
		recu.setRecu_start_date(new SimpleDateFormat("yyyy-MM-dd").format(start_date));
		recu.setRecu_end_date(new SimpleDateFormat("yyyy-MM-dd").format(end_date));
		recu.setRecu_deadline(new SimpleDateFormat("yyyy-MM-dd").format(deadline));
		
		String code = common_code.substring(0, 3);
		
		CommonsDTO common = comDao.getCommon(code+"00");
		List<CommonsDTO> commonList = comDao.findByLikeCommonCode(code);
		commonList.remove(0);
		
		model.addAttribute("recu", recu);
		model.addAttribute("common", common);
		model.addAttribute("common_list", commonList);
		
		return "recruit/recruitWrite";
	}
	
	//모집 종료 02-17 추가
	@Override
	public String recuritEnd(Model model, String common_code, String recu_code) throws Exception {
		RecruitsDTO recu = recuDao.selectOne(common_code, recu_code);
		
		if(recu == null) {
			return "404";
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		Date start_date = format.parse(recu.getRecu_start_date());
		Date end_date = format.parse(recu.getRecu_end_date());
		Date deadline = format.parse(recu.getRecu_deadline());
		
		recu.setRecu_start_date(new SimpleDateFormat("yyyy-MM-dd").format(start_date));
		recu.setRecu_end_date(new SimpleDateFormat("yyyy-MM-dd").format(end_date));
		recu.setRecu_deadline(new SimpleDateFormat("yyyy-MM-dd").format(deadline));
		
		String code = common_code.substring(0, 3);
		
		CommonsDTO common = comDao.getCommon(code+"00");
		List<CommonsDTO> commonList = comDao.findByLikeCommonCode(code);
		commonList.remove(0);
		
		model.addAttribute("recu", recu);
		model.addAttribute("common", common);
		model.addAttribute("common_list", commonList);
		
		return "recruit/recruitWrite";
	}


	@Override
	public String reruitWriteForm(Model model, String common_code) {
		
		String code = common_code.substring(0, 3);
		
		CommonsDTO common = comDao.getCommon(code+"00");
		List<CommonsDTO> commonList = comDao.findByLikeCommonCode(code);
		commonList.remove(0);
		
		model.addAttribute("common", common);
		model.addAttribute("common_list", commonList);
		
		return "recruit/recruitWrite";
	}
	
	@Override
	public void selectIndex(Model model) {
		List<RecruitsDTO> newlist = recuCtrlDao.newIndex();
		
		model.addAttribute("NEWLIST",newlist);
	}

}
