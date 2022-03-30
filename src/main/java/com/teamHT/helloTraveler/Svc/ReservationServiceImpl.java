package com.teamHT.helloTraveler.Svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DAO.IReservationControlDAO;
import com.teamHT.helloTraveler.DAO.IReservationDAO;
import com.teamHT.helloTraveler.DAO.ISale_TravDAO;
import com.teamHT.helloTraveler.DTO.ReservationDTO;
import com.teamHT.helloTraveler.DTO.Sale_TravDTO;
import com.teamHT.helloTraveler.common.PagingVO;

@Service("resvService")
public class ReservationServiceImpl implements IReservationService{
	
	@Autowired
	private IReservationDAO resvDao;
	
	@Autowired
	private IReservationControlDAO resvCtrlDao;
	
	@Autowired
	private ISale_TravDAO saleDao;
	
	// 예약 추가 서비스
	@Override
	@Transactional
	public String insertResc(ReservationDTO resv) {
		//세일DTO에 현재인원을 먼저 업데이트 하고 그 이후에 예약테이블에 추가
		Sale_TravDTO sale_trav = saleDao.selectOne(resv.getCommon_code(), resv.getTrav_code(), resv.getSale_code());
		
		int nowCrtNumber = sale_trav.getCrt_num() + resv.getPerson_num();
		
		if(nowCrtNumber == sale_trav.getMax_num()) {
			sale_trav.setSale_state('2');
		}
		sale_trav.setCrt_num(nowCrtNumber);
		
		saleDao.update(sale_trav);

		int sale_code = resvDao.maxRecvCode(resv.getMem_code());
		resv.setRes_code(String.valueOf(sale_code+1));
		resvDao.insert(resv);
		
		return "redirect:/myReservation?mem_code="+resv.getMem_code();
	}
	
	
	// 나의 예약 목록 보기
	public String myReservationList(String mem_code, PagingVO paging, String cntPage, Model model) {
		
		int total = 0;
		
		if(cntPage == null) {
			cntPage = "1";
		}
		
		
		total = resvCtrlDao.maxMyRecvs(mem_code);
		paging = new PagingVO(total, Integer.parseInt(cntPage));
		List<ReservationDTO> resvList = resvCtrlDao.selectMyResvList(mem_code, paging.getStart(), paging.getEnd());
		
		model.addAttribute("paging", paging);
		model.addAttribute("resvList", resvList);
		
		return "myPage/myReservation";
	}
	
}
