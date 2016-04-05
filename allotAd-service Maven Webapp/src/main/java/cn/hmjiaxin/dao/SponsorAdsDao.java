package cn.hmjiaxin.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.SponsorAds;

public interface SponsorAdsDao extends CrudRepository<SponsorAds, Integer> {
	@Query("select a from SponsorAds a where a.business.id=?1 and a.title like %?2% and a.status=?3")
	List<SponsorAds> queryAds(Pageable pageable, int businessId, String title,
			int status);
	@Query("select count(a) from SponsorAds a where a.business.id=?1 and a.title like %?2% and a.status=?3")
	int queryAdsCount(int businessId, String title, int status);

}
