package cn.hmjiaxin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.hmjiaxin.dao.PostLibraryDao;
import cn.hmjiaxin.model.PostLibrary;

@Service
public class PostLibraryService {
	private PostLibraryDao postLibraryDao;

	@Autowired
	public PostLibraryService(PostLibraryDao postLibraryDao) {
		super();
		this.postLibraryDao = postLibraryDao;
	}

	public List<PostLibrary> queryPostLibrary(int businessId, int number,String tag) {
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION.DESC, "createdDate");
		List<PostLibrary> postList=new ArrayList<PostLibrary>();
		int page = 0;
		Pageable pageable = new PageRequest(page, number, sort);
		if("".equals(tag)||tag==null){
		 postList=postLibraryDao.queryPostLibrary(businessId, pageable);
		}else{
			postList=postLibraryDao.queryPostLibraryByTag(businessId,tag, pageable);
		}
		return postList;
	}

}
