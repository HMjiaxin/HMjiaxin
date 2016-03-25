package cn.hmjiaxin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.BusinessDao;
import cn.hmjiaxin.model.Business;

@Service
public class BusinessService {
	private BusinessDao businessDao;

	@Autowired
	public BusinessService(BusinessDao businessDao) {
		super();
		this.businessDao = businessDao;
	}

	public Business findById(int businessId) {
		// TODO Auto-generated method stub
		return businessDao.findById(businessId);
	}

/*	public List<Business> queryAllAdvertisers(int pageSize,int num,String userName) {
		List<Business> list=new ArrayList<Business>();
		Sort sort=new Sort(Sort.Direction.DESC,"name");
		Pageable pageable = new PageRequest(int, num,sort);
		if(userName==null||"".equals(userName)){
			Page<Business> businessPage=businessDao.queryAllAdvertisers(pageable,userName);
		}
		return null;
	}*/
	
}
