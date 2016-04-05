package cn.hmjiaxin.controller.ad;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.SponsorAds;
import cn.hmjiaxin.model.WechatBasic;
import cn.hmjiaxin.service.CommonService;
import cn.hmjiaxin.service.SponsorAdsService;
import cn.hmjiaxin.service.WechatBasicService;
import cn.hmjiaxin.util.StringUtil;

@RestController
public class AllotAdController {
	private CommonService commonService;
	private WechatBasicService wechatBasicService;
	private SponsorAdsService sponsorAdsService;

	@Autowired
	public AllotAdController(CommonService commonService,
			WechatBasicService wechatBasicService,
			SponsorAdsService sponsorAdsService) {
		super();
		this.commonService = commonService;
		this.wechatBasicService = wechatBasicService;
		this.sponsorAdsService = sponsorAdsService;
	}

	ObjectMapper mapper = new ObjectMapper();

	/**
	 * 新增推广内容
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/addnewtask")
	public void createNewAd(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("businessId") int businessId,// 企业id
			@RequestParam("postId") int postId,// 广告id
			@RequestParam("mediaType") String mediaType,// 媒体类型
			@RequestParam("startDate") String startDate,// 广告生效时间
			@RequestParam("endDate") String endDate,// 广告失效时间
			@RequestParam("priceType") int priceType,// 出价类型
			@RequestParam("budget") BigDecimal budget// 预算
			//@RequestParam("userId") int userId// 用户id
	) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal price = new BigDecimal("0.038");// 广告价格
		Date start = new Date();
		Date end = new Date();
		try {
			if (startDate == null || "".equals(startDate)) {
				start = null;
			} else {
				start = sdf.parse(startDate);
			}
			if (endDate == null || "".equals(endDate)) {
				end = null;
			} else {
				end = sdf.parse(endDate);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		boolean flag = sponsorAdsService.addNewAd(businessId, postId,
				mediaType, start, end, price, priceType, budget);

		/*
		 * boolean flag = commonService.insertAccountHistory(businessId, userId,
		 * budget, "申请广告预算", 0, "");
		 * response.getWriter().print(StringUtil.JSONCallBack(request, flag));
		 */
		response.getWriter().print(StringUtil.JSONCallBack(request, flag));

	}

	/**
	 * 查询推广内容
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/sponsorads")
	@ResponseBody
	public String querySponsorAds(
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
		SimpleDateFormat sdf =new SimpleDateFormat();
		int column = 0;
		if (columnStr != null && !"".equals(columnStr)) {
			column = Integer.parseInt(columnStr);
		}
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		if(title==null){
			title="";
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
				System.out.println("------------------------");
				System.out.println();
				System.out.println(ad.getId());
				System.out.println();
				System.out.println("------------------------");
				String time=sdf.format(ad.getStartDate())+"至"+sdf.format(ad.getEndDate());
				Map<String, String> elementMap = new HashMap<String, String>();
				elementMap.put("adId", ad.getId()+"");
				elementMap.put("title", ad.getTitle());
				elementMap.put("time", time);
				elementMap.put("mediaType", "");
				elementMap.put("status", ad.getStatus()+"");
				elementMap.put("reachUser", "");
				elementMap.put("budget", ad.getBudget()+"");
				result.add(elementMap);
			}
		}
		map.put("data", result);
		return mapper.writeValueAsString(map);
	}
}
