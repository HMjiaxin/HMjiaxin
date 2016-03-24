package cn.hmjiaxin.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.AccountDao;
import cn.hmjiaxin.dao.AccountHistoryDao;
import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.BusinessAccountHistory;

@Service
public class WithdrawCashService {
	private AccountDao accountDao;
	private AccountHistoryDao accountHistoryDao;

	@Autowired
	public WithdrawCashService(AccountDao accountDao,
			AccountHistoryDao accountHistoryDao) {
		super();
		this.accountDao = accountDao;
		this.accountHistoryDao = accountHistoryDao;
	}

	public List<BusinessAccountHistory> queryAllDrawCashList(int pageSize, int num,
			String key) {
		List<BusinessAccountHistory> list = new ArrayList<BusinessAccountHistory>();
//		int count = (int) accountHistoryDao.count();
		if (key == null || "".equals(key)) {
			Sort sort=new Sort(Sort.Direction.DESC,"createdDate","lastUpdatedDate");
			Pageable pageable = new PageRequest(pageSize - 1, num,sort);
			Page<BusinessAccountHistory> accountHistories = accountHistoryDao
					.queryDrawCashHistory(pageable);
			list = accountHistories.getContent();
			System.out.println("查询结果"+list.size());
		} else {

		}
		return list;
	}

	public BusinessAccount findBusinessAccountById(int businessId) {
		BusinessAccount ba= accountDao.findByBusinessId(businessId);
		return ba;
	}

}
