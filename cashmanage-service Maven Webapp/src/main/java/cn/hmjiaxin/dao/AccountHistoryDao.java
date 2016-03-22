package cn.hmjiaxin.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.BusinessAccountHistory;

public interface AccountHistoryDao extends CrudRepository<BusinessAccountHistory, Integer>{
	List<BusinessAccountHistory> findByBusinessId(int businessID);
	
	@Query("select h from BusinessAccountHistory h,BusinessAccount a where h.businessId=a.businessId ")//limit ?1,?2")
	Page<BusinessAccountHistory> findAll(Pageable pageable);//,@Param("queryKey") String queryKey);
	@Query("select count(h) from BusinessAccountHistory h,BusinessAccount a where h.businessId=a.businessId ")
	int queryCount();
	
	@Modifying 
	@Transactional
	@Query("update BusinessAccountHistory h set h.status= ?1 where h.id= ?2")
	void updateStatus(int changeStatus, int id);
}
