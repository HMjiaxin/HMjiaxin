package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.AccountDao;
import cn.hmjiaxin.model.BusinessAccount;

@Service
public class BusinessAccountService {
	private AccountDao accountDao;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BusinessAccountService(AccountDao accountDao,
			JdbcTemplate jdbcTemplate) {
		super();
		this.accountDao = accountDao;
		this.jdbcTemplate = jdbcTemplate;
	}

	public BusinessAccount findAccountById(int businessId) {
		BusinessAccount ba = accountDao.findByBusinessId(businessId);
		return ba;
	}

	public BigDecimal getScoreByBessinessId(int businessId) {
		BusinessAccount ba = accountDao.findByBusinessId(businessId);
		BigDecimal score = ba.getScore();
		return score;
	}

	/**
	 * 查询广告主账户
	 * 
	 * @param userName
	 * @return
	 */
	public List<BusinessAccount> queryAllAdvertisers(int pageSize, int length,
			int column, String dir, String userName) {
		String sortColumn = "";// 排序列;
		switch (column) {
		case 1:
			sortColumn = "business.contactPerson";
			break;
		case 2:
			
			sortColumn = "score";
			break;
		default:
			sortColumn = "business.name";
			break;
		}
		Direction direction = Sort.DEFAULT_DIRECTION.DESC;
		if ("asc".equals(dir)) {
			direction = Sort.DEFAULT_DIRECTION.ASC;
		}
		List<BusinessAccount> accounts = new ArrayList<BusinessAccount>();
		Sort sort = new Sort(direction, sortColumn);
		Pageable pageable = new PageRequest(pageSize, length, sort);
		if (userName == null) {
			userName = "";
		}
		accounts = accountDao.queryAllAdvertisers(pageable, userName);
		return accounts;
	}

	public int queryAdvertiserCouunt(String userName) {
		if (userName == null) {
			userName = "";
		}
		return accountDao.queryAdvertiserCouunt(userName);
	}

	/**
	 * 加钱
	 * 
	 * @param businessId
	 * @param userId
	 * @param Score
	 */
	public boolean saveAccount(final int businessId, final int userId,
			final BigDecimal score, final String description) {
		String sql = "call sp_qa_business_saveAccount(?,?,?,?)";
		boolean result=jdbcTemplate.execute(sql,new CallableStatementCallback<Boolean>() {

			public Boolean doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, businessId);
				cs.setInt(2, userId);
				cs.setBigDecimal(3, score);
				cs.setString(4, description);
				cs.execute();
				return true;
			}
		});
//		boolean result = this.jdbcTemplate.execute(sql,
//				new CallableStatementCallback() {
//
//					public Object doInCallableStatement(CallableStatement cs)
//							throws SQLException, DataAccessException {
//						cs.setInt(1, businessId);
//						cs.setInt(2, userId);
//						cs.setBigDecimal(3, score);
//						cs.setString(4, description);
//						cs.execute();
//						return true;
//					}
//
//				});
		return result;
	}

}
