package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import cn.hmjiaxin.dao.AccountHistoryDao;
import cn.hmjiaxin.model.BusinessAccountHistory;

@Service
public class BusinessAccountHistoryService {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BusinessAccountHistoryService.class); 
	private AccountHistoryDao accountHistoryDao;
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public BusinessAccountHistoryService(AccountHistoryDao accountHistoryDao,JdbcTemplate jdbcTemplate) {
		super();
		this.accountHistoryDao = accountHistoryDao;
		this.jdbcTemplate=jdbcTemplate;
	}
	/**提现记录*/
	public List<BusinessAccountHistory> queryAllDrawCashHistory(int pageSize, int num,
			String key) {
		
		List<BusinessAccountHistory> list = new ArrayList<BusinessAccountHistory>();
		Sort sort=new Sort(Sort.Direction.DESC,"createdDate","lastUpdatedDate");
		Pageable pageable = new PageRequest(pageSize, num,sort);
		if (key == null) {
			key="";
		}
		Page<BusinessAccountHistory> accountHistories = accountHistoryDao
				.queryDrawCashHistory(key,pageable);
		list = accountHistories.getContent();
		return list;
	}
	/**提现记录数*/
	public int queryDrawCashHistoryCount(String keyword) {
		if(keyword==null){
			keyword="";
		}
			return accountHistoryDao.queryDrawCashHistoryCount(keyword);
		
	}
	/**修改提现状态*/
	public boolean updateStatus(int changeStatus, int id) {
		BusinessAccountHistory bah=accountHistoryDao.findOne(id);
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
	/**一条新的提现申请*/
	public boolean insertAccountHistory(final int businessId, final int userid,
			final BigDecimal drawCashScore, final String decision,
			final int state, final String ip) {
		logger.info("=====================insertAccountHistory================");
		logger.info("==================="+decision+"==========================");
		String sql = "call sp_qa_business_insertAccountHistory(?,?,?,?,?,?)";
		boolean result=jdbcTemplate.execute(sql, new CallableStatementCallback<Boolean>() {
			public Boolean doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, businessId);
				cs.setInt(2, userid);
				cs.setBigDecimal(3, drawCashScore);
				cs.setString(4, decision);
				cs.setInt(5, state);
				cs.setString(6, ip);
				cs.execute();
				return true;
			}

		});
		return result;
	}
	/**查询充值记录(单个用户)*/
	public List<BusinessAccountHistory> queryByBusinessId(int pageSize,int length,int businessId) {
		Sort sort=new Sort(Sort.Direction.DESC,"createdDate");
		Pageable pageable = new PageRequest(pageSize,length,sort);
		return accountHistoryDao.queryByBusinessIdAndDescriptionLike(businessId,"企业客户充值",pageable);
	}
	/**查询充值记录数(单个用户)*/
	public int queryRechargeCouunt(int businessId) {
		return accountHistoryDao.queryAccountHistoryCount(businessId, "企业客户充值");
	} 


	public List<BusinessAccountHistory> queryAccountHistory(int businessId,
			int pageSize, int length, String description) {
		Sort sort=new Sort(Sort.DEFAULT_DIRECTION.DESC,"createdDate");
		Pageable pageable=new PageRequest(pageSize,length,sort);
		return accountHistoryDao.queryByBusinessIdAndDescriptionLike(businessId,description, pageable);
	}
	public int queryAccountHistoryCount(int businessId, String description) {
		int count=accountHistoryDao.queryAccountHistoryCount(businessId, description);
		return count;
	}

}
