package cn.hmjiaxin.controller.ad;

import java.io.IOException;
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

import cn.hmjiaxin.model.PostLibrary;
import cn.hmjiaxin.model.WechatBasic;
import cn.hmjiaxin.service.PostLibraryService;
import cn.hmjiaxin.service.WechatBasicService;
import cn.hmjiaxin.util.StringUtil;

@RestController
public class ShowMessage {
	private PostLibraryService postLibraryService;
	private WechatBasicService wechatBasicService;

	@Autowired
	public ShowMessage(PostLibraryService postLibraryService,
			WechatBasicService wechatBasicService) {
		super();
		this.postLibraryService = postLibraryService;
		this.wechatBasicService = wechatBasicService;
	}

	/**
	 * 展示下发消息库
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/postlibrary")
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
	 * 查询类别代表媒体
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
					eleMap.put("mediaId", wb.getId()+"");
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
}
