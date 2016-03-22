package cn.hmjiaxin.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.BusinessAccount;

public interface AccountDao extends CrudRepository<BusinessAccount, Long>{

	BusinessAccount findByBusinessId(int businessId);
	
}
