package cn.hmjiaxin.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		int[] nom = new int[10];
		BigDecimal result[] = new BigDecimal[10];
		for (int i = 0; i < result.length; i++) {
			result[i] = new BigDecimal(nom[i]);
		}
		String a = sponsorAdsRecordsDao.queryReadRecords(adId, sponsorId);
		if (a == null) {
			return result;
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
		System.out.println();
		System.out
				.println("=====================================================");
		System.out.println(queryResultStr);
		System.out.println();
		System.out.println();
		if (queryResultStr == null) {
			return new BigDecimal[] { new BigDecimal("0"), new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0") };
		}
		String[] queryResult = queryResultStr.split(",");
		result = new BigDecimal[queryResult.length];
		for (int i = 0; i < queryResult.length; i++) {
			result[i] = new BigDecimal(queryResult[i]);
		}
		System.out.println(JSONArray.fromObject(result));
		return result;

	}

	public void queryRecord(int businessId) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Long day = 7L;

		List<String> queryResultList = sponsorAdsRecordsDao
				.querySevenDayRecord(businessId, day);
		List<String> postTimeList = new ArrayList<String>();
		List<String> postCount = new ArrayList<String>();
		List<String> intPageReadUser = new ArrayList<String>();
		List<String> intPageReadCount = new ArrayList<String>();
		for (int i = 0; i < queryResultList.size(); i++) {
			String[] eleStr = queryResultList.get(i).split(",");

			calendar.add(calendar.DATE, i + 1);
			Date oDate = calendar.getTime();
			String postTimeString = sdf.format(oDate);
			if (postTimeString.equals(eleStr[0])) {

				
			} else {
				
			}

		}

	}
}
