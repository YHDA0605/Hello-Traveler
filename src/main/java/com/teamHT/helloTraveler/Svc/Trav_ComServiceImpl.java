package com.teamHT.helloTraveler.Svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DAO.IMembersDAO;
import com.teamHT.helloTraveler.DAO.ITrav_ComDAO;
import com.teamHT.helloTraveler.DTO.MembersDTO;
import com.teamHT.helloTraveler.DTO.Trav_ComDTO;
import com.teamHT.helloTraveler.common.PagingVO;

@Service("tComService")
public class Trav_ComServiceImpl implements ITrav_ComService {

	@Autowired
	private IMembersDAO mbrDao;
	
	@Autowired
	private ITrav_ComDAO tComDao;
	
	//회원가입
	@Override
	@Transactional
	public String registComMember(MembersDTO mbr, Trav_ComDTO tCom) {
		MembersDTO member = mbrDao.idCheck(mbr.getMem_id());
		
		if (member != null) {
			return "regist/registCompany";
		}

		//회원번호
		String maxCode = mbrDao.maxCode();
		int setMemCode = 0;

		if (maxCode == null) {
			setMemCode = 1;
		} else {
			setMemCode = Integer.parseInt(maxCode) + 1;
		}
		
		//여행사번호
		int maxComCode = tComDao.maxComCode();
		int setComCode = maxComCode + 1;
		
		mbr.setMem_code(String.valueOf(setMemCode));
		mbr.setMem_state('0');//탈퇴여부
		mbr.setMem_nick(tCom.getCom_name());//닉네임 여행사
		mbr.setCommon_code("10004");
		mbr.setGender('0');//여행사
		mbr.setMem_sns_id("없음");
		
		mbrDao.insert(mbr);
		
		tCom.setMem_code(mbr.getMem_code());
		tCom.setCom_code(String.valueOf(setComCode));
		tCom.setOpening("0");//영업중
		
		tComDao.insert(tCom);
		
		return "regist/registChk";
	}
	
	
	
	@Override
	public String adminTravComs(PagingVO paging, String cntPage, Model model) {
		int total = 0;
		
		if(cntPage == null) {
			cntPage = "1";
		}
	      
	    total = tComDao.maxComCode();
	    paging = new PagingVO(total, Integer.parseInt(cntPage));
	    List<Trav_ComDTO> comList = tComDao.selectTravComs(paging.getStart(), paging.getEnd());
		
		model.addAttribute("PAGE", paging);
		model.addAttribute("comList", comList);
		
		return "admin/adminTravCom";
	};
	
}