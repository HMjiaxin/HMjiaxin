package cn.hmjiaxin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.SponsorAdsDao;

@Service
public class SponsorAdsService {
	private SponsorAdsDao sponsorAdsDao;

	@Autowired
	public SponsorAdsService(SponsorAdsDao sponsorAdsDao) {
		super();
		this.sponsorAdsDao = sponsorAdsDao;
	}

}
