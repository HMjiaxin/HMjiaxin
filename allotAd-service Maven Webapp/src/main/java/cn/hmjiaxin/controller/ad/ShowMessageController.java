package cn.hmjiaxin.controller.ad;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hmjiaxin.model.PostLibrary;
import cn.hmjiaxin.model.SponsorAds;
import cn.hmjiaxin.model.WechatBasic;
import cn.hmjiaxin.service.CommonService;
import cn.hmjiaxin.service.PostLibraryService;
import cn.hmjiaxin.service.SponsorAdsRecordsService;
import cn.hmjiaxin.service.SponsorAdsService;
import cn.hmjiaxin.service.WechatBasicService;
import cn.hmjiaxin.util.StringUtil;

@RestController
public class ShowMessageController {
	private PostLibraryService postLibraryService;
	private WechatBasicService wechatBasicService;
	private CommonService commonService;
	private SponsorAdsService sponsorAdsService;
	private SponsorAdsRecordsService sponsorAdsRecordsService;

	@Autowired
	public ShowMessageController(PostLibraryService postLibraryService,
			WechatBasicService wechatBasicService, CommonService commonService,
			SponsorAdsService sponsorAdsService,
			SponsorAdsRecordsService sponsorAdsRecordsService) {
		super();
		this.postLibraryService = postLibraryService;
		this.wechatBasicService = wechatBasicService;
		this.commonService = commonService;
		this.sponsorAdsService = sponsorAdsService;
		this.sponsorAdsRecordsService = sponsorAdsRecordsService;
	}

	ObjectMapper mapper = new ObjectMapper();

	/**
	 * 查询推广内容
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/adsrecords")
	public void querySponsorAds(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("businessId") int businessId,
			@RequestParam("draw") String draw,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("status") int status,
			@RequestParam(value = "order[0][column]", required = false) String columnStr,
			@RequestParam(value = "order[0][dir]", required = false) String sort)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		SimpleDateFormat sdf = new SimpleDateFormat();
		int column = 0;
		if (columnStr != null && !"".equals(columnStr)) {
			column = Integer.parseInt(columnStr);
		}
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		List<SponsorAds> ads = sponsorAdsService.queryAds(businessId, title,
				pageSize, length, status, column, sort);
		int totalCount = sponsorAdsService.queryAdsCounts(businessId, title,
				status);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (ads.size() > 0) {
			for (SponsorAds ad : ads) {
				// 查询触达人数/阅读数
				BigDecimal [] readRecords=sponsorAdsRecordsService.queryReadRecords(ad.getId(),
						businessId);

				String time = sdf.format(ad.getStartDate()) + "至"
						+ sdf.format(ad.getEndDate());
				Map<String, String> elementMap = new HashMap<String, String>();
				elementMap.put("postCount",readRecords[0]+"");//下发总数
				elementMap.put("intPageReadUser",readRecords[1]+"");// 图文页阅读人数
				elementMap.put("intPageReadCount",readRecords[2]+"");// 图文页阅读次数
				elementMap.put("oriPageReadUser",readRecords[3]+"");// 原文页的阅读人数
				elementMap.put("oriPageReadCount",readRecords[4]+"");// 原文页的阅读次数
				elementMap.put("shareUser",readRecords[5]+"");// 分享人数
				elementMap.put("shareCount",readRecords[6]+"");// 分享次数
				elementMap.put("addToFavUser",readRecords[7]+"");// 收藏人数
				elementMap.put("addToFavCount",readRecords[8]+"");// 收藏次数
				elementMap.put("postCost",readRecords[9]+"");// 总消耗
				String mediaType=ad.getMediaType();//媒体类型
				if (!"".equals(mediaType)) {
					mediaType=commonService.getMediaType(mediaType);
				}
				elementMap.put("adId", ad.getId() + "");
				elementMap.put("title", ad.getTitle());
				elementMap.put("time", time);
				elementMap.put("mediaType", mediaType);
				elementMap.put("status", ad.getStatus() + "");
//				elementMap.put("reachUser", "");
				elementMap.put("budget", ad.getBudget() + "");
				result.add(elementMap);
			}
		}
		map.put("data", result);
		response.getWriter().print(StringUtil.JSONCallBackForDataTables(request, map));
	}

	/**
	 * 展示关键指标
	 */
	@RequestMapping("/keyindex")
	public void keyIndex(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam("businessId") int businessId,
			@RequestParam("days") String days) {
		if (days == null || "".equals(days) ) {
			days="7";
		}
		
		BigDecimal[] queryResult=sponsorAdsRecordsService.querykeyIndex(businessId,days);
		
		System.out.println();
	}
}
