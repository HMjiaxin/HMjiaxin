package cn.hmjiaxin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.SponsorAdsRecords;

public interface SponsorAdsRecordsDao extends
		CrudRepository<SponsorAdsRecords, Integer> {
	@Query("select sum(r.postCount) as postCount,sum(r.intPageReadUser)as intPageReadUser,sum(r.intPageReadCount)as intPageReadCount,"
			+ "sum(r.oriPageReadUser)as oriPageReadUser, sum(r.oriPageReadCount) as oriPageReadCount,"
			+ "sum(r.shareUser)as shareUser, sum(r.shareCount) as shareCount,"
			+ "sum(r.addToFavUser)as addToFavUser,sum(r.addToFavCount)as addToFavCount,"
			+ "sum(r.postCost)as postCost "
			+ "from SponsorAdsRecords r where r.ad.id=?1 and r.sponsor.id=?2 GROUP BY NULL")
	String queryReadRecords(int adId, int sponsorId);

	@Query("select sum(r.postCount) as postCount,sum(r.intPageReadUser)as intPageReadUser,sum(r.oriPageReadUser)as oriPageReadUser,"
			+ " sum(r.postCost)as postCost "
			+ "from SponsorAdsRecords r where  r.sponsor.id=?1 and (0=?2 or to_days(NOW())-to_days(r.postTime)<=?2)  GROUP BY NULL")
	String queryKeyIndex(int businessId, int day);

}
