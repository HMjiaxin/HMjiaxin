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
	

	@Query("select h from BusinessAccountHistory h,BusinessAccount a where h.business=a.business and h.business.isAds='0' and h.business.name like %?1%")
	Page<BusinessAccountHistory> queryDrawCashHistory(String key, Pageable pageable);
	@Query("select count(h) from BusinessAccountHistory h,BusinessAccount a where h.business=a.business and h.business.isAds='0' and h.business.name like %?1%")
	int queryDrawCashHistoryCount(String keyword);
	@Modifying 
	@Transactional
	@Query("update BusinessAccountHistory h set h.status= ?1 where h.id= ?2")
	void updateStatus(int changeStatus, int id);


	List<BusinessAccountHistory> queryByBusinessIdAndDescription(int businessId,String description,Pageable pageable);

	@Query("select count(h)from BusinessAccountHistory h where h.business.id=?1")
	int queryRechargeCouunt(int businessId);

}
