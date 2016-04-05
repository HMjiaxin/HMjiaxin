package cn.hmjiaxin.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.Business;

public interface BusinessDao extends CrudRepository<Business, Integer>{

	Business findById(int businessId);

}
