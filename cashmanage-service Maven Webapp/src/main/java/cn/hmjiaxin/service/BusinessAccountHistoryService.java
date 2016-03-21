package cn.hmjiaxin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.AccountHistoryDao;
import cn.hmjiaxin.model.BusinessAccountHistory;

@Service
public class BusinessAccountHistoryService {
	private AccountHistoryDao accountHistoryDao;

	@Autowired
	public BusinessAccountHistoryService(AccountHistoryDao accountHistoryDao) {
		super();
		this.accountHistoryDao = accountHistoryDao;
	}

	public List<BusinessAccountHistory> queryAll(int pageSize, int num,
			String key) {
		List<BusinessAccountHistory> list = new ArrayList<BusinessAccountHistory>();
		if (key == null || "".equals(key)) {
			Sort sort=new Sort(Sort.Direction.DESC,"createdDate","lastUpdatedDate");
			Pageable pageable = new PageRequest(pageSize - 1, num,sort);
			Page<BusinessAccountHistory> accountHistories = accountHistoryDao
					.findAll(pageable);
			list = accountHistories.getContent();
			System.out.println("查询结果"+list.size());
		} else {

		}
		return list;
	}

	public int queryCount() {
		return accountHistoryDao.queryCount();
	}

	public boolean updateStatus(int changeStatus, int id) {
		BusinessAccountHistory bah=accountHistoryDao.findOne(id);
		System.out.println(":::::"+bah);
		if(bah==null){
			return false;
		}
		int presentState=bah.getStatus();
		//判断当前状态和修改状态
		if(presentState<changeStatus){
			
		}
		accountHistoryDao.updateStatus(changeStatus,id);
		return true;
	}

	public List<BusinessAccountHistory> findBybusinessId(int businessId) {
		return accountHistoryDao.findByBusinessId(businessId);
//		return null;
	}

}
