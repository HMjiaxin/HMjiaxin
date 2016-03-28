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

import cn.hmjiaxin.model.Business;
import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.BusinessAccountHistory;
import cn.hmjiaxin.service.BusinessAccountHistoryService;
import cn.hmjiaxin.service.BusinessAccountService;
import cn.hmjiaxin.service.BusinessService;

/**
 * 管理员充值管理
 * 
 * @author rabbit
 *
 */
@RestController
public class RechargeFadContraller {
	private BusinessAccountHistoryService accountHistoryService;
	private BusinessAccountService accountService;
	private BusinessService businessService;

	@Autowired
	public RechargeFadContraller(
			BusinessAccountHistoryService accountHistoryService,
			BusinessAccountService accountService,
			BusinessService businessService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
		this.businessService = businessService;
	}

	/**
	 * 广告主用户列表
	 * 
	 * @param draw
	 *            请求次数
	 * @param start
	 *            开始位置
	 * @param length
	 *            长度
	 * @param userName
	 *            用户名
	 * @throws IOException
	 */
	@RequestMapping("/rechargelist")
	public void rechargeList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("draw") String draw,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("userName") String userName) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String jsonCallback = request.getParameter("callback");
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		List<BusinessAccount> list = accountService.queryAllAdvertisers(
				pageSize, length, userName);// 查询出所有的广告主账号
		int totalCount = accountService.queryAdvertiserCouunt(userName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (list.size() > 0) {
			for (BusinessAccount ba : list) {
				Map<String, String> elementMap = new HashMap<String, String>();
				elementMap.put("businessId", ba.getBusiness().getId()+"");
				elementMap.put("businessName", ba.getBusiness().getName());
				elementMap.put("contactPerson", ba.getBusiness()
						.getContactPerson());
				elementMap.put("score", ba.getScore() + "");
				result.add(elementMap);
			}
		}
		map.put("data", result);
		String res = JSONArray.fromObject(map).toString();
		res = res.substring(1, res.length() - 1);
		response.getWriter().print(jsonCallback + "(" + res + ")");
	}

	/**
	 * 展示账户历史纪录
	 * @throws IOException 
	 */
	@RequestMapping("/rechargehistorylist")
	public void showAccountHistory(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("draw") String draw,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("businessId") int businessId) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String jsonCallback = request.getParameter("callback");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		List<BusinessAccountHistory> list = accountHistoryService
				.queryByBusinessId(pageSize, length, businessId);
		int totalCount = accountHistoryService.queryRechargeCouunt(businessId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (list.size() > 0) {
			for (BusinessAccountHistory bah : list) {
				Map<String, String> elementMap = new HashMap<String, String>();

				elementMap.put("businessName", bah.getBusiness().getName());
				elementMap.put("description", bah.getDescription());
				elementMap.put("score", bah.getScore() + "");
				Date createDate = bah.getCreatedDate();
				if (createDate == null) {
					elementMap.put("createDate", "");
				} else {
					elementMap.put("createDate", bah.getCreatedDate() + "");
				}
				// elementMap.put("", value);
				result.add(elementMap);
			}
		}
		map.put("data", result);
		String res = JSONArray.fromObject(map).toString();
		res = res.substring(1, res.length() - 1);
		response.getWriter().print(jsonCallback + "(" + res + ")");
	}
	
	@RequestMapping("/recharge")
	public void recharge(@RequestParam("businessId")int businessId,@RequestParam("Score")int Score){
		accountService.saveAccount(businessId, 0, Score, "企业客户充值");}
}
