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
import cn.hmjiaxin.model.PostLibrary;
import cn.hmjiaxin.model.SponsorAds;
import cn.hmjiaxin.model.WechatBasic;
import cn.hmjiaxin.service.CommonService;
import cn.hmjiaxin.service.PostLibraryService;
import cn.hmjiaxin.service.SponsorAdsService;
import cn.hmjiaxin.service.WechatBasicService;
import cn.hmjiaxin.util.StringUtil;

@RestController
public class AllotAdController {
	private WechatBasicService wechatBasicService;
	private SponsorAdsService sponsorAdsService;
	private PostLibraryService postLibraryService;

	@Autowired
	public AllotAdController(WechatBasicService wechatBasicService,
			SponsorAdsService sponsorAdsService,
			PostLibraryService postLibraryService) {
		super();
		this.wechatBasicService = wechatBasicService;
		this.sponsorAdsService = sponsorAdsService;
		this.postLibraryService = postLibraryService;
	}

	/**
	 * 展示下发消息库
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/postlibrary")
	// @ResponseBody
	public void test(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("businessId") int businessId,
			@RequestParam("number") String numberStr,
			@RequestParam("tag") String tag) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		int number = 100;
		if (numberStr != null && !"".equals(numberStr)
				&& !"all".equals(numberStr)) {
			number = Integer.parseInt(numberStr);
		}

		List<PostLibrary> list = postLibraryService.queryPostLibrary(
				businessId, number, tag);
		if (list.size() > 0) {
			for (PostLibrary post : list) {
				Map<String, String> eleMap = new HashMap<String, String>();
				eleMap.put("postId", post.getId() + "");
				eleMap.put("title", post.getTitle());
				String author = post.getAuthor();
				eleMap.put("author", "".equals(author) || author == null ? "无"
						: author);
				eleMap.put("sourceUrl", post.getSourceUrl());
				eleMap.put("digest", post.getDigest());
				eleMap.put("imageUrl", post.getImageUrl());
				resultList.add(eleMap);
			}
		}
		response.getWriter()
				.print(StringUtil.JSONCallBack(request, resultList));
	}

	/**
	 * 查询代表媒体
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/getmedia")
	public void queryWechatByType(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("types") String ids)
			throws IOException {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		if (ids == null || "".equals(ids)) {
			response.getWriter().print(
					StringUtil.JSONCallBack(request, resultList));
		} else {
			String[] idArrayStr = ids.split("[^\\d]");
			int[] idArray = new int[idArrayStr.length];
			for (int i = 0; i < idArrayStr.length; i++) {
				idArray[i] = Integer.parseInt(idArrayStr[i]);
			}

			List<WechatBasic> list = wechatBasicService
					.queryWechatByType(idArray);
			if (list.size() > 0) {
				for (WechatBasic wb : list) {
					Map<String, String> eleMap = new HashMap<String, String>();
					eleMap.put("mediaId", wb.getId() + "");
					eleMap.put("name", wb.getName());
					eleMap.put("fanQuantity", "10W+");
					eleMap.put("accountImageUrl", wb.getAccountImageUrl());
					eleMap.put("QRCode", wb.getAccountQrcode());
					resultList.add(eleMap);
				}
			}
			response.getWriter().print(
					StringUtil.JSONCallBack(request, resultList));
		}
	}

	/**
	 * 新增下发广告
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/addnewad")
	public void createNewAd(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("businessId") int businessId,// 企业id
			@RequestParam("postId") int postId,// 广告id
			@RequestParam("exceptMedia") String exceptMedia,// 删除媒体
			@RequestParam("mediaType") String mediaType,// 媒体类型
			@RequestParam("startDate") String startDate,// 广告生效时间
			@RequestParam("endDate") String endDate,// 广告失效时间
			@RequestParam("priceType") int priceType,// 出价类型
			@RequestParam("budget") BigDecimal budget// 预算
	// @RequestParam("userId") int userId// 用户id
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

		response.getWriter().print(StringUtil.JSONCallBack(request, flag));

	}

	/**
	 * 广告状态修改--停止
	 */
	public void changeAdStatus(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("adId") int adId,
			@RequestParam("status") int status) {
//		sponsorAdsService.stopAd(adId,status);
	}

}
