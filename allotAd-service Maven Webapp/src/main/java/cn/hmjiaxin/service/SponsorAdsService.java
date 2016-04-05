package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hmjiaxin.dao.AccountDao;
import cn.hmjiaxin.dao.BusinessDao;
import cn.hmjiaxin.dao.PostLibraryDao;
import cn.hmjiaxin.dao.SponsorAdsDao;
import cn.hmjiaxin.model.Business;
import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.PostLibrary;
import cn.hmjiaxin.model.SponsorAds;

@Service
public class SponsorAdsService {
	private SponsorAdsDao sponsorAdsDao;
	private BusinessDao businessDao;
	private PostLibraryDao postLibraryDao;
	private CommonService commonService;
	private AccountDao accountDao;

	@Autowired
	public SponsorAdsService(SponsorAdsDao sponsorAdsDao,
			CommonService commonService, BusinessDao businessDao,
			PostLibraryDao postLibraryDao, AccountDao accountDao) {
		super();
		this.sponsorAdsDao = sponsorAdsDao;
		this.businessDao = businessDao;
		this.postLibraryDao = postLibraryDao;
		this.commonService = commonService;
		this.accountDao = accountDao;

	}

	@Transactional
	public boolean addNewAd(int businessId, int postId, String mediaType,
			Date startDate, Date endDate, BigDecimal price, int priceType,
			BigDecimal budget/*, int userId*/) {
		Business business = businessDao.findOne(businessId);
		BigDecimal score = accountDao.findScoreByBusiness(business);
		if (score.subtract(budget).doubleValue() < 0) {
			return false;
		} else {
			PostLibrary post = postLibraryDao.findOne(postId);

			int refCount = price.multiply(price).intValue();// 参考下发次数
			SponsorAds sAds = new SponsorAds();
			sAds.setTitle(post.getTitle());
			sAds.setBusiness(business);
			sAds.setPost(post);
			sAds.setStartDate(startDate);
			sAds.setEndDate(endDate);
			sAds.setPrice(price);
			sAds.setPriceType(priceType);
			sAds.setMediaType(mediaType);
			sAds.setBudget(budget);
			sAds.setStatus(1);
			sAds.setRefCount(refCount);
			sAds.setCreatedDate(new Date());
			sponsorAdsDao.save(sAds);// 添加一条新的广告

			commonService.insertAccountHistory(businessId, /*userId,*/ budget,
					"申请广告预算", 0, "");
			return true;
		}
	}

	public List<SponsorAds> queryAds(int businessId, String title,
			int pageSize, int length, int status, int column, String sortStr) {
		String sortColumn = "";// 排序列;
		switch (column) {
		case 1:
			break;
		case 2:
			break;
		default:
			sortColumn = "createdDate";
			break;
		}
		Direction direction = Sort.DEFAULT_DIRECTION.DESC;
		if ("asc".equals(sortStr)) {
			direction = Sort.DEFAULT_DIRECTION.ASC;
		}
		List<SponsorAds> sponsorAds = new ArrayList<SponsorAds>();
		Sort sort = new Sort(direction, sortColumn);
		Pageable pageable = new PageRequest(pageSize, length, sort);
		sponsorAds = sponsorAdsDao
				.queryAds(pageable, businessId, title, status);
		return sponsorAds;
	}

	public int queryAdsCounts(int businessId, String title, int status) {

		return sponsorAdsDao.queryAdsCount(businessId, title, status);
	}
}
