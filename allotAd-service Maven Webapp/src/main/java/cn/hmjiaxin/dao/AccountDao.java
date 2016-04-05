package cn.hmjiaxin.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.Business;
import cn.hmjiaxin.model.BusinessAccount;

public interface AccountDao extends CrudRepository<BusinessAccount, Long>{

	BusinessAccount findByBusinessId(int businessId);

	@Query("select a from BusinessAccount a where a.business.isAds='1' and a.business.name like %?1%")
	List<BusinessAccount> queryAllAdvertisers(Pageable pageable,String userName);
	@Query("select count(a) from BusinessAccount a where a.business.isAds='1' and a.business.name like %?1%")
	int queryAdvertiserCouunt(String userName);

	BigDecimal findScoreByBusiness(Business business);
	
	
}
