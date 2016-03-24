package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.AccountDao;
import cn.hmjiaxin.model.BusinessAccount;
@Service
public class BusinessAccountService {
	private AccountDao accountDao;
	@Autowired
	public BusinessAccountService(AccountDao accountDao) {
		super();
		this.accountDao = accountDao;
	}

	public BusinessAccount findAccountById(int businessId) {
		BusinessAccount ba= accountDao.findByBusinessId(businessId);
		return ba;
	}

	public BigDecimal getScoreByBessinessId(int businessID) {
		BusinessAccount ba= accountDao.findByBusinessId(businessID);
		BigDecimal score=ba.getScore();
		
		return null;
	}
	/**
	 * 查询广告主账户
	 * @param userName
	 * @return
	 */
	public List<BusinessAccount> queryAllAdvertisers(String userName) {
		// TODO Auto-generated method stub
//		List<BusinessAccount> list=accountDao.findAll();
		return null;
	}

}
