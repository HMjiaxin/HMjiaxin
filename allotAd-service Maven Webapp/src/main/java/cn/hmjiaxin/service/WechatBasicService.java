package cn.hmjiaxin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.WechatBasicDao;
import cn.hmjiaxin.model.WechatBasic;
@Service
public class WechatBasicService {
	private WechatBasicDao wechatBasicDao;

	@Autowired
	public WechatBasicService(WechatBasicDao wechatBasicDao) {
		super();
		this.wechatBasicDao = wechatBasicDao;
	}
	public List<WechatBasic> queryWechatByType(int[] typeId){
		Pageable pageable=new PageRequest(0, 100);
		List<WechatBasic> list=wechatBasicDao.queryWechatByType(pageable,typeId);
		return list;
	}
	
}
