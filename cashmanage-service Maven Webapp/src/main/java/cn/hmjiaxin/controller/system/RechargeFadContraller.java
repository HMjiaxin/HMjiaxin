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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.BusinessAccountHistory;
import cn.hmjiaxin.service.BusinessAccountHistoryService;
import cn.hmjiaxin.service.BusinessAccountService;
/**
 * 管理员充值管理
 * @author rabbit
 *
 */
@RestController
public class RechargeFadContraller {
	private BusinessAccountHistoryService accountHistoryService;
	private BusinessAccountService accountService;
	@Autowired
	public RechargeFadContraller(
			BusinessAccountHistoryService accountHistoryService,
			BusinessAccountService accountService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
	}
	
	/**
	 * 用户列表
	 * @param draw 请求次数
	 * @param start 开始位置
	 * @param length 长度
	 * @param userName 用户名
	 * @throws IOException
	 */
	@RequestMapping("/rechargelist")
	public void rechargeList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("draw") String draw,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("userName") String userName)
			throws IOException {
		String jsonCallback = request.getParameter("callback");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		List<BusinessAccount>list=accountService.queryAllAdvertisers(userName);
//		List<BusinessAccount>list=accountService.quertAllRechargeHistory(pageSize, length, userName);
//		List<BusinessAccountHistory> list = accountHistoryService.queryAll(
//				pageSize, length, userName);
//		int totalCount = accountHistoryService.queryCount(userName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
//		map.put("recordsTotal", totalCount);
//		map.put("recordsFiltered", totalCount);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (list.size() > 0) {}
		map.put("data", result);
		String res = JSONArray.fromObject(map).toString();
		res = res.substring(1, res.length() - 1);
		System.out.println(jsonCallback + "(" + res + ")");
		response.getWriter().print(jsonCallback + "(" + res + ")");
	}
}
