package cn.hmjiaxin.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cn.hmjiaxin.model.PostLibrary;

public interface PostLibraryDao extends CrudRepository<PostLibrary, Integer> {
	@Query("select p from PostLibrary p where p.business.id=?1 ")
	List<PostLibrary> queryPostLibrary(int businessId, Pageable pageable);

	@Query("select p from PostLibrary p,PostTag pt where p.business.id=?1 and p.id=pt.post.id and pt.contentTag.tagName like %?2%")
	List<PostLibrary> queryPostLibraryByTag(int businessId, String tag,
			Pageable pageable);

}
