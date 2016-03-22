package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.AccountHistoryDao;
import cn.hmjiaxin.model.BusinessAccountHistory;

@Service
public class BusinessAccountHistoryService {
	private AccountHistoryDao accountHistoryDao;
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public BusinessAccountHistoryService(AccountHistoryDao accountHistoryDao,JdbcTemplate jdbcTemplate) {
		super();
		this.accountHistoryDao = accountHistoryDao;
		this.jdbcTemplate=jdbcTemplate;
	}

	public List<BusinessAccountHistory> queryAll(int pageSize, int num,
			String key) {
		List<BusinessAccountHistory> list = new ArrayList<BusinessAccountHistory>();
		if (key == null || "".equals(key)) {
			Sort sort=new Sort(Sort.Direction.DESC,"createdDate","lastUpdatedDate");
			Pageable pageable = new PageRequest(pageSize, num,sort);
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

	public void insertAccountHistory(final int businessId, final int userid,
			final BigDecimal drawCashScore, final String decision, final int state, final String ip) {
		String sql="call sp_qa_business_insertAccountHistory(?,?,?,?,?,?)";
		this.jdbcTemplate.execute(sql,new CallableStatementCallback(){

			public Object doInCallableStatement(CallableStatement cs)
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
		/*jdbcTemplate.execute(new CallableStatementCreator() {
			
			public CallableStatement createCallableStatement(Connection arg0)
					throws SQLException {
				
				
			}
		});*/
	}

}
