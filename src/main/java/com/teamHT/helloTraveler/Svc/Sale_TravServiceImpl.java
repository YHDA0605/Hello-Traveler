package com.teamHT.helloTraveler.Svc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.teamHT.helloTraveler.DAO.ICommonsDAO;
import com.teamHT.helloTraveler.DAO.ISale_TravControlDAO;
import com.teamHT.helloTraveler.DAO.ISale_TravDAO;
import com.teamHT.helloTraveler.DAO.ITravelControlDAO;
import com.teamHT.helloTraveler.DTO.Sale_TravDTO;
import com.teamHT.helloTraveler.DTO.TravelDTO;
import com.teamHT.helloTraveler.common.DatingVO;

@Service("saleService")
public class Sale_TravServiceImpl implements ISale_TravService {

	@Autowired
	private ISale_TravDAO saleDao;

	@Autowired
	private ISale_TravControlDAO saleCtrlDao;

	@Autowired
	public ICommonsDAO comDao;

	@Autowired
	private ITravelControlDAO traCtrlDao;

	// 수정해야함 02-18
	@Override
	@Transactional
	public String insertSale(Sale_TravDTO sale, List<String> day, String start_date, String end_date, Model model) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 날짜의 형태를 정해주는 포멧
		Calendar cal = Calendar.getInstance();
		
		TravelDTO trav = traCtrlDao.selectTravelOne(sale.getCommon_code(), sale.getTrav_code()); // 몇일짜리인지 찾기위해서 가져와야함
		
		List<Sale_TravDTO> sale_list = new ArrayList<Sale_TravDTO>(); // 인서트 할 DTO들을 넣을 리스트
		
		DatingVO dating = new DatingVO(format.parse(start_date), format.parse(end_date), day);
		
		List<Date> dateList = dating.getDateList(); // 해당하는 날짜들이 들어있는 리스트
		
		String code = saleDao.codeCount();
		int saleCode;

		if (code == null) {
			saleCode = 1;
		} else {
			saleCode = Integer.parseInt(code) + 1;
		}
		
		for (int i = 0; i < dateList.size(); i++) {
			Date startDate = dateList.get(i);
			Date endDate;
			Sale_TravDTO dto = new Sale_TravDTO();
			dto.setCommon_code(sale.getCommon_code());
			dto.setTrav_code(sale.getTrav_code());
			dto.setSale_code(String.valueOf(saleCode));
			dto.setStart_day(startDate);
			
			//종료날자 계산
			cal.setTime(startDate);
			cal.add(Calendar.DATE, trav.getTrav_nights());
			endDate = cal.getTime();
			
			dto.setEnd_day(endDate);
			dto.setSale_price(sale.getSale_price());
			dto.setCrt_num(0);
			dto.setMax_num(sale.getMax_num());
			dto.setSale_state('1');
			
			sale_list.add(dto);
			saleCode++;
		}
		
		saleCtrlDao.insertSaleList(sale_list);
		
		return "redirect:/saleTravelContent?common_code="+sale.getCommon_code()+"&trav_code="+sale.getTrav_code();

	}

	// 일단 여기까지


	@Override
	@Transactional
	public String getSale(String commonCode, String travCode, Model model) {
		TravelDTO travel = traCtrlDao.selectTravelOne(commonCode, travCode);
		List<Sale_TravDTO> saleList = saleCtrlDao.selectSaleList(commonCode,travCode);
		
		model.addAttribute("trav", travel);
		model.addAttribute("saleList", saleList);
		return "travel/saleTravelContent";
	}

	@Override
	@Transactional
	public String deleteSale(String commonCode, String travCode, String saleCode) {

		Sale_TravDTO sale = saleDao.selectOne(commonCode, travCode, saleCode);

		if (sale == null) {
			return "404";
		}

		sale.setSale_state('2');
		saleCtrlDao.deleteSale(sale);

		return "redirect:/travel?common_code=" + commonCode;
	}
	
	
	
	
	
	@Override
	public String saleEdit(Model model, String commonCode, String travCode, String saleCode) throws Exception {

//		Sale_TravDTO sale = saleDao.selectOne(commonCode, travCode, saleCode);
		Sale_TravDTO sale = saleCtrlDao.selectSaleOne(commonCode, travCode, saleCode);

		if (sale == null) {
			return "404";
		}

		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//Date start_day = format.parse(sale.getStart_day());
		//Date end_day = format.parse(sale.getEnd_day());

		//sale.setStart_day(new SimpleDateFormat("yyyy-MM-dd").format(start_day));
		//sale.setEnd_day(new SimpleDateFormat("yyyy-MM-dd").format(end_day));

		model.addAttribute("sale", sale);

		return "travel/travelCoWrite";
	}

}
