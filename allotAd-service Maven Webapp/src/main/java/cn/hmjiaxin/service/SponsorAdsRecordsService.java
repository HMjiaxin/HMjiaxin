package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.SponsorAdsRecordsDao;

@Service
public class SponsorAdsRecordsService {
	private SponsorAdsRecordsDao sponsorAdsRecordsDao;

	@Autowired
	public SponsorAdsRecordsService(SponsorAdsRecordsDao sponsorAdsRecordsDao) {
		super();
		this.sponsorAdsRecordsDao = sponsorAdsRecordsDao;
	}

	/**
	 * 查询阅读/触达信息
	 * 
	 * @param adId
	 *            广告id
	 * @param sponsorId
	 *            广告主id
	 */
	public BigDecimal[] queryReadRecords(int adId, int sponsorId) {
		BigDecimal result[] = new BigDecimal[10];
		String a = sponsorAdsRecordsDao.queryReadRecords(adId, sponsorId);
		if (a == null) {
			return new BigDecimal[10];
		}
		String[] s = a.split(",");
		for (int i = 0; i < s.length; i++) {
			result[i] = new BigDecimal(s[i]);

		}
		return result;
	}

	public BigDecimal[] querykeyIndex(int businessId, String days) {
		BigDecimal[] result;
		int day;
		if ("all".equals(days)) {
			day = 0;
		} else {
			day = Integer.parseInt(days);
		}
		String queryResultStr = sponsorAdsRecordsDao.queryKeyIndex(businessId,
				day);
		if (queryResultStr == null) {
			return new BigDecimal[4];
		}
		String[] queryResult = queryResultStr.split(",");
		result = new BigDecimal[queryResult.length];
		for (int i = 0; i < queryResult.length; i++) {
			result[i] = new BigDecimal(queryResult[i]);
		}
		return result;

	}
}
