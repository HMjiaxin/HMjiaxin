package cn.hmjiaxin.controller.ad;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * 新增推广内容
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/addnewtask")
	public void createNewAd(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("businessId") int businessId,//企业id
			@RequestParam("postId")int  postId,//广告id
			@RequestParam("mediaType")String mediaType,//媒体类型
			@RequestParam("startDate") String startDate,//广告生效时间
			@RequestParam("endDate") String endDate,//广告失效时间
			@RequestParam("priceType")int priceType,//出价类型
			@RequestParam("budget") BigDecimal budget,//预算
			@RequestParam("userId") int userId//用户id
			) throws IOException {
		BigDecimal price=new BigDecimal("0.038");//广告价格
		
		boolean flag = commonService.insertAccountHistory(businessId, userId,
				budget, "申请广告预算", 0, "");
		response.getWriter().print(StringUtil.JSONCallBack(request, flag));

	}

}
