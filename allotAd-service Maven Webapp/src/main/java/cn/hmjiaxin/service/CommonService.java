package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.AccountDao;
import cn.hmjiaxin.dao.WechatAccountTypeDao;
import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.WechatAccountType;

@Service
public class CommonService {
	private AccountDao accountDao;
	private WechatAccountTypeDao wechatAccountTypeDao;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CommonService(AccountDao accountDao, JdbcTemplate jdbcTemplate,
			WechatAccountTypeDao wechatAccountTypeDao) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.accountDao = accountDao;
		this.wechatAccountTypeDao = wechatAccountTypeDao;
	}

	/** 一条新的提现申请 */
	public boolean insertAccountHistory(final int businessId, /*final int userid,*/
			final BigDecimal drawCashScore, final String decision,
			final int state, final String ip) {
		String sql = "call sp_qa_business_insertAccountHistory(?,?,?,?,?,?)";
		boolean result=jdbcTemplate.execute(sql, new CallableStatementCallback<Boolean>() {

			public Boolean doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {

				cs.setInt(1, businessId);
				/*cs.setInt(2, userid);*/
				cs.setBigDecimal(2, drawCashScore);
				cs.setString(3, decision);
				cs.setInt(4, state);
				cs.setString(5, ip);
				cs.execute();
				return true;
			}

		});
		/*
		 * String sql = "call sp_qa_business_insertAccountHistory(?,?,?,?,?,?)";
		 * boolean result = jdbcTemplate.execute(sql, new
		 * CallableStatementCallback() {
		 * 
		 * public Object doInCallableStatement(CallableStatement cs) throws
		 * SQLException, DataAccessException { cs.setInt(1, businessId);
		 * cs.setInt(2, userid); cs.setBigDecimal(3, drawCashScore);
		 * cs.setString(4, decision); cs.setInt(5, state); cs.setString(6, ip);
		 * cs.execute(); return true; } });
		 */
		// return result;
		return result;
	}

	/**
	 * 查询账户余额
	 * 
	 * @param businessId
	 * @return
	 */
	public BigDecimal getScoreByBessinessId(int businessId) {
		BusinessAccount ba = accountDao.findByBusinessId(businessId);
		BigDecimal score = ba.getScore();
		return score;
	}

	/**
	 * 获取所有微信类型
	 * 
	 * @return
	 */
	public List<WechatAccountType> getWechatType() {
		return (List<WechatAccountType>) wechatAccountTypeDao.findAll();
	}
}
