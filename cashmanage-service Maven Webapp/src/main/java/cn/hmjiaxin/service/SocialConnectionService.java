package cn.hmjiaxin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.SocialConnectionDao;
import cn.hmjiaxin.model.SocialConnection;

@Service
public class SocialConnectionService {
	private SocialConnectionDao socialConnectionDao;

	@Autowired
	public SocialConnectionService(SocialConnectionDao socialConnectionDao) {
		super();
		this.socialConnectionDao = socialConnectionDao;
	}

	public List<SocialConnection> findByBusinessId(int businessId) {
		// TODO Auto-generated method stub
		return socialConnectionDao.findByBusinessId(businessId);
	}

}
