package cn.hmjiaxin.controller.system;

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

import cn.hmjiaxin.model.Business;
import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.BusinessAccountHistory;
import cn.hmjiaxin.model.SocialConnection;
import cn.hmjiaxin.service.BusinessAccountHistoryService;
import cn.hmjiaxin.service.BusinessAccountService;
import cn.hmjiaxin.service.BusinessService;
import cn.hmjiaxin.service.SocialConnectionService;
import cn.hmjiaxin.service.WithdrawCashService;

/**
 * 管理员提现管理
 * 
 * @author rabbit
 *
 */
@RestController
public class DrawCashController {

	private BusinessAccountHistoryService accountHistoryService;
	private BusinessAccountService accountService;
	private BusinessService businessService;
	private SocialConnectionService socialConnectionService;

	@Autowired
	public DrawCashController(BusinessAccountService accountService,
			BusinessAccountHistoryService accountHistoryService,
			BusinessService businessService,SocialConnectionService socialConnectionService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
		this.businessService = businessService;
		this.socialConnectionService=socialConnectionService;
	}

	/**
	 * 查询提现纪录
	 * 
	 * @param draw
	 *            请求次数
	 * @param start
	 *            开始位置
	 * @param length
	 *            长度
	 * @param keyWord
	 *            关键字
	 * @throws IOException
	 */
	@RequestMapping("/drawcashlist")
	public void drawCashList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("draw") String draw,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("keyword") String keyword)
			throws IOException {
		String jsonCallback = request.getParameter("callback");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		List<BusinessAccountHistory> list = accountHistoryService.queryAllDrawCashHistory(
				pageSize, length, keyword);
		int totalCount = accountHistoryService.queryDrawCashHistoryCount(keyword);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (list.size() > 0) {
			for (BusinessAccountHistory bah : list) {
				Business business=bah.getBusiness();
				int businessId=business.getId();
				BusinessAccount account = accountService
						.findAccountById(businessId);
				List<SocialConnection> scList=socialConnectionService.findByBusinessId(businessId);
				String sc="";
				for(SocialConnection sConnection:scList){
					sc+=sConnection.getSocialProfileName()+",";
				}
				Map<String, String> elementMap = new HashMap<String, String>();
				elementMap.put("businessName", business.getName());
				elementMap.put("weChat", sc.substring(0,sc.length()-1));
				elementMap.put("id", bah.getId() + "");
				elementMap.put("contactPerson", business.getContactPerson());
				elementMap.put("businessId", businessId + "");
				elementMap.put("score", account.getScore() + "");
				elementMap.put("drawCashScore", bah.getStatus() + "");
				elementMap.put("status", bah.getStatus() + "");
				Date createDate = bah.getCreatedDate();
				if (createDate == null) {
					elementMap.put("createDate", "");
				} else {
					elementMap.put("createDate",
							sdf.format(bah.getCreatedDate()));
				}
				elementMap.put("lastUpdatedDate",
						sdf.format(bah.getLastUpdatedDate()));
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
			@RequestParam("id") int id, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		String jsonCallback = request.getParameter("callback");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		boolean result = accountHistoryService.updateStatus(changeStatus, id);
		response.getWriter().print(jsonCallback + "(" + result + ")");
	}
}
