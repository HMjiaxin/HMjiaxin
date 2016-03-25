package cn.hmjiaxin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.SocialConnection;

public interface SocialConnectionDao extends CrudRepository<SocialConnection, Integer>{

	List<SocialConnection> findByBusinessId(int businessId);

}
