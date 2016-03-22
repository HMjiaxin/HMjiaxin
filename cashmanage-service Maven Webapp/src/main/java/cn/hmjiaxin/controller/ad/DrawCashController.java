package cn.hmjiaxin.controller.ad;

import java.io.IOException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.BusinessAccountHistory;
import cn.hmjiaxin.service.BusinessAccountHistoryService;
import cn.hmjiaxin.service.BusinessAccountService;
import cn.hmjiaxin.service.WithdrawCashService;

@RestController
public class DrawCashController {

	private static final HashMap String = null;
	private BusinessAccountHistoryService accountHistoryService;
	private BusinessAccountService accountService;

	@Autowired
	public DrawCashController(BusinessAccountService accountService,
			BusinessAccountHistoryService accountHistoryService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
	}

	/**
	 * 查询提现纪录
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/drawcashlist")
	public void drawCashList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("draw") String draw,
			@RequestParam("start") int start, @RequestParam("length") int length)
			throws IOException {
		System.out.println(request.getRequestURL().toString());
		String jsonCallback = request.getParameter("callback");
		System.out.println(jsonCallback);
		System.out.println(draw);
		System.out.println(start + "___________________");
		System.out.println(length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		String key = "";
		List<BusinessAccountHistory> list = accountHistoryService.queryAll(
				pageSize, length, key);
		int totalCount = accountHistoryService.queryCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<Map<String, String> > result = new ArrayList<Map<String, String> >();
		if (list.size() > 0) {
			for (BusinessAccountHistory bah : list) {
				int businessId = bah.getBusinessId();
				BusinessAccount account = accountService
						.findAccountById(businessId);
				// List<String> elementList = new ArrayList<String>();
				Map<String, String> elementMap = new HashMap<String, String>();
				elementMap.put("businessName", "企业名称");
				elementMap.put("weChat", "微信名称");
				elementMap.put("id", "企业名称");
				elementMap.put("businessName", bah.getId()+"");
				elementMap.put("businessId", businessId+"");
				elementMap.put("score", account.getScore()+"");
				elementMap.put("drawCashScore", bah.getStatus() + "");
				elementMap.put("status", bah.getStatus() + "");
				Date createDate = bah.getCreatedDate();
				if (createDate == null) {
					elementMap.put("createDate","");
				} else {
					elementMap.put("createDate",sdf.format(bah.getCreatedDate()));
				}
				elementMap.put("lastUpdatedDate",sdf.format(bah.getLastUpdatedDate()));
				result.add(elementMap);
			}
		}
		map.put("data", result);
		String res = JSONArray.fromObject(map).toString();
		res = res.substring(1, res.length() - 1);
		System.out.println(jsonCallback + "(" + res + ")");
		response.getWriter().print(jsonCallback + "(" + res + ")");
	}

	@RequestMapping("/updateStatus")
	public void updateStatus(@RequestParam("status") int changeStatus,
			@RequestParam("id") int id, HttpServletResponse response,HttpServletRequest request)
			throws IOException {
		System.out.println("+++++++++++++++");
		System.out.println(request.getRequestURL().toString());
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		boolean result = accountHistoryService.updateStatus(changeStatus,
				id);
		if (result) {
			response.getWriter().print("修改成功");
		} else {
			response.getWriter().print("修改失败");
		}
	}
}
