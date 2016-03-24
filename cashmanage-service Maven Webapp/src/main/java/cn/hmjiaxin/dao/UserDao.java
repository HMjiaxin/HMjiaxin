package cn.hmjiaxin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.User;

public interface UserDao extends CrudRepository<User, Long>{
	//User findByUserName(String userName);
//	List<User> getAll();
//
//	User queryUserByName(String userName);

	//User findByUserPhone(String userPhone);
	
}
