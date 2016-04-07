package cn.hmjiaxin.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.BusinessAccountHistory;

public interface AccountHistoryDao extends CrudRepository<BusinessAccountHistory, Integer>{
	List<BusinessAccountHistory> findByBusinessId(int businessID);
	
	/**查询提现记录(所有账户)*/
	@Query("select h from BusinessAccountHistory h,BusinessAccount a where h.business=a.business and h.business.isAds='0' and h.business.name like %?1% and h.description='提现'")
	Page<BusinessAccountHistory> queryDrawCashHistory(String key, Pageable pageable);
	/**查询提现数量(所有账户)*/
	@Query("select count(h) from BusinessAccountHistory h,BusinessAccount a where h.business=a.business and h.business.isAds='0' and h.business.name like %?1%  and h.description='提现'")
	int queryDrawCashHistoryCount(String keyword);
	
	/**更新提现状态*/
	@Modifying 
	@Transactional
	@Query("update BusinessAccountHistory h set h.status= ?1 where h.id= ?2")
	void updateStatus(int changeStatus, int id);

	/**查询账户历史纪录(单一账户)*/
	@Query("select h from BusinessAccountHistory h where h.business.id=?1 and h.description =?2")
	List<BusinessAccountHistory> queryByBusinessIdAndDescriptionLike(int businessId,String description,Pageable pageable);
	/**查询账户历史纪录数(单一账户)*/
	@Query("select count(h)from BusinessAccountHistory h where h.business.id=?1 and h.description like %?2%")
	int queryAccountHistoryCount(int businessId,String description);
}
