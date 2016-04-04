package cn.hmjiaxin.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.WechatBasic;

public interface WechatBasicDao extends CrudRepository<WechatBasic, Integer>{
	@Query("select w from WechatBasic w where w.accountType in (?1)")
	List<WechatBasic> queryWechatByType(Pageable pageable,int[] parseInt);

}
