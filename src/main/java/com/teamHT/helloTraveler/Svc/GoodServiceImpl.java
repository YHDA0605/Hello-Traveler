package com.teamHT.helloTraveler.Svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamHT.helloTraveler.DAO.IGoodDAO;
import com.teamHT.helloTraveler.DTO.GoodDTO;

@Service("goodService")
public class GoodServiceImpl implements IGoodService{
	@Autowired
	public IGoodDAO goodDao;
	//공통코드 보고 위치에 맞게 돌아가야함
	@Override
	@Transactional
	public String updateGood(String common_code, String code, String mem_code) {
		//있는지 검사
		GoodDTO dto = goodDao.selectOne(common_code, code, mem_code);
		String maxCode = goodDao.maxCode();
		int count = 0;
		if (maxCode.equals("0")) {
			count = 1;
		} else {
			count = Integer.parseInt(maxCode) + 1;
		}
		//없다면
		if(dto != null) {
			if("1".equals(dto.getGood_state())) {
				dto.setGood_state("0");//추천 아직이거나 취소
				goodDao.update(dto);
			}else {
				dto.setGood_state("1");//추천 한거
				goodDao.update(dto);
			}
		}else {
			GoodDTO dto2 = new GoodDTO();
			dto2.setCommon_code(common_code);
			dto2.setCode(code);
			dto2.setMem_code(mem_code);
			dto2.setGood_code(String.valueOf(count));
			dto2.setGood_state("1");
			goodDao.insert(dto2);
		}
		return "redirect:/postContent?common_code="+common_code+"&POST_CODE="+code;
	}
	
	@Override
	@Transactional
	public String centerUpdateGood(String common_code, String code, String mem_code) {
		//있는지 검사
		GoodDTO dto = goodDao.selectOne(common_code, code, mem_code);
		String maxCode = goodDao.maxCode();
		int count = 0;
		if (maxCode.equals("0")) {
			count = 1;
		} else {
			count = Integer.parseInt(maxCode) + 1;
		}
		//없다면
		if(dto != null) {
			if("1".equals(dto.getGood_state())) {
				dto.setGood_state("0");//추천 아직이거나 취소
				goodDao.update(dto);
			}else {
				dto.setGood_state("1");//추천 한거
				goodDao.update(dto);
			}
		}else {
			GoodDTO dto2 = new GoodDTO();
			dto2.setCommon_code(common_code);
			dto2.setCode(code);
			dto2.setMem_code(mem_code);
			dto2.setGood_code(String.valueOf(count));
			dto2.setGood_state("1");
			goodDao.insert(dto2);
		}
		return "redirect:/centerContent?common_code="+common_code+"&POST_CODE="+code;
	}

}
