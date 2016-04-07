package cn.hmjiaxin.controller.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.model.Business;
import cn.hmjiaxin.model.BusinessAccount;
import cn.hmjiaxin.model.BusinessAccountHistory;
import cn.hmjiaxin.model.ReturnResult;
import cn.hmjiaxin.service.BusinessAccountHistoryService;
import cn.hmjiaxin.service.BusinessAccountService;
import cn.hmjiaxin.service.BusinessService;
import cn.hmjiaxin.util.StringUtil;

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

	@Autowired
	public RechargeFadContraller(
			BusinessAccountHistoryService accountHistoryService,
			BusinessAccountService accountService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
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
	public void rechargeList(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("draw") String draw,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("userName") String userName,
			@RequestParam(value = "order[0][column]", required = false) String columnStr,
			@RequestParam(value = "order[0][dir]", required = false) String dir)
			throws IOException {
		DecimalFormat df = new DecimalFormat("0.00");
		int column = 0;
		if (columnStr != null && !"".equals(columnStr)) {
			column = Integer.parseInt(columnStr);
		}
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		List<BusinessAccount> list = accountService.queryAllAdvertisers(
				pageSize, length, column, dir, userName);// 查询出所有的广告主账号
		int totalCount = accountService.queryAdvertiserCouunt(userName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (list.size() > 0) {
			for (BusinessAccount ba : list) {
				Map<String, String> elementMap = new HashMap<String, String>();
				elementMap.put("businessId", ba.getBusiness().getId() + "");
				elementMap.put("businessName", ba.getBusiness().getName());
				elementMap.put("contactPerson", ba.getBusiness()
						.getContactPerson());
				elementMap.put("score", df.format(ba.getScore()));
				result.add(elementMap);
			}
		}
		map.put("data", result);
		String returnStr = StringUtil.JSONCallBackUrl(request, map);
		response.getWriter().print(returnStr);
	}

	/**
	 * 展示账户历史纪录
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/rechargehistorylist")
	public void showAccountHistory(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("draw") String draw,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("businessId") int businessId) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("0.00");
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
				elementMap.put("score", df.format(bah.getScore()));
				Date createDate = bah.getCreatedDate();
				if (createDate == null) {
					elementMap.put("createDate", "");
				} else {
					elementMap.put("createDate",
							sdf.format(bah.getCreatedDate()));
				}
				result.add(elementMap);
			}
		}
		map.put("data", result);
		String returnStr = StringUtil.JSONCallBackUrl(request, map);
		response.getWriter().print(returnStr);
	}

	/**
	 * 给广告主充值
	 * 
	 * @param request
	 * @param response
	 * @param businessId
	 * @param score
	 * @throws IOException
	 */
	@RequestMapping("/recharge")
	public void recharge(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("businessId") int businessId,
			@RequestParam("score") BigDecimal score) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		boolean flag = accountService.saveAccount(businessId, 0, score,
				"企业客户充值");
		ReturnResult rr = new ReturnResult(flag ? 1 : 0, "成功");
		response.getWriter().print(StringUtil.JSONCallBackUrl(request, rr));
	}

}
