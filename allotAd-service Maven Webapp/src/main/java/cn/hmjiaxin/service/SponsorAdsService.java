package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.text.Bidi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;
import cn.hmjiaxin.dao.AccountDao;
import cn.hmjiaxin.dao.BusinessDao;
import cn.hmjiaxin.dao.PostLibraryDao;
import cn.hmjiaxin.dao.SponsorAdsDao;
import cn.hmjiaxin.model.Business;
import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.PostLibrary;
import cn.hmjiaxin.model.SponsorAds;
import cn.hmjiaxin.model.SponsorAdsRecords;

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
	private static final Logger logger = (Logger) LoggerFactory
			.getLogger(SponsorAdsService.class);
	/**
	 * 新增一条新的广告，并扣除预算
	 * @param businessId
	 * @param postId
	 * @param mediaType
	 * @param startDate
	 * @param endDate
	 * @param price
	 * @param priceType
	 * @param budget
	 * @return
	 */
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
			sAds.setBusinessWeight(new BigDecimal("1.00"));
			sAds.setCost(new BigDecimal("0.00"));
			sponsorAdsDao.save(sAds);// 添加一条新的广告

			commonService.insertAccountHistory(businessId, /*userId,*/0, budget,
					"申请广告预算", 0, "");
			return true;
		}
	}
	/**
	 * 查询广告
	 * @param businessId
	 * @param title
	 * @param pageSize
	 * @param length
	 * @param status
	 * @param column
	 * @param sortStr
	 * @return
	 */
	public List<SponsorAds> queryAds(int businessId, String title,
			int pageSize, int length, int status, int column, String sortStr) {
		String sortColumn = "";// 排序列;
		switch (column) {
		case 1:
			sortColumn = "createdDate";
			break;
		case 2:
			sortColumn = "createdDate";
			break;
		default:
			sortColumn = "createdDate";
			break;
		}
		if (title==null) {
			title="";
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
		if ("".equals(title)) {
			title="";
		}
		return sponsorAdsDao.queryAdsCount(businessId, title, status);
	}
	@Transactional
	public boolean stopPostAd(int adId, int status) {
		SponsorAds sponsorAd=sponsorAdsDao.findOne(adId);
		int statusOrig=sponsorAd.getStatus();
		logger.info("==================================开始================================");
		logger.info(status+"");
		if (status!=statusOrig) {
			return false;
		}else{
			logger.info("==================================修改金额================================");
			BigDecimal budget=sponsorAd.getBudget();//广告预算
			BigDecimal cost=sponsorAd.getCost();//花费
			BigDecimal balance=budget.subtract(cost);//余额
			BusinessAccount account=accountDao.findByBusinessId(sponsorAd.getBusiness().getId());
			BigDecimal score=account.getScore();//账户余额
			BigDecimal adScore=account.getAdScore();//广告花费
			account.setAdScore(adScore.add(cost));
			account.setScore(score.add(balance));
			sponsorAd.setStatus(3);
			//此处还需要删除发布的广告信息
			accountDao.save(account);
			sponsorAdsDao.save(sponsorAd);
			return true;
		}
		
	}
}
