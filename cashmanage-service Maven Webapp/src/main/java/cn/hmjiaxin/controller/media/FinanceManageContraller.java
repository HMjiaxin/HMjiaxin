package cn.hmjiaxin.controller.media;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.model.BusinessAccountHistory;
import cn.hmjiaxin.service.BusinessAccountHistoryService;
import cn.hmjiaxin.service.BusinessAccountService;

/**
 * 媒体住财务管理
 * 
 * @author rabbit
 *
 */
@RestController
public class FinanceManageContraller {
	private BusinessAccountHistoryService accountHistoryService;
	private BusinessAccountService accountService;

	@Autowired
	public FinanceManageContraller(
			BusinessAccountHistoryService accountHistoryService,
			BusinessAccountService accountService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
	}

	/**
	 * 查询账户历史纪录
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/account/history")
	public void showAccountHistory(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("draw") String draw,
			@RequestParam("start") int start,
			@RequestParam("length") int length, @RequestParam("type") int type// 查询类型0所有记录1提现记录2收入纪录
	) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// HttpSession session = request.getSession();
		// int businessId = (Integer) session.getAttribute("");
		int businessId = 10001;
		// type=1;//查询类型 0 所有记录 1提现记录 2 收入纪录
		String description = "";
		switch (type) {
		case 1:
			description = "提现";
			break;
		case 2:
			description = "广告收入";
		default:
			break;
		}
		String jsonCallback = request.getParameter("callback");
		int pageSize = 0;
		if (length != 0) {
			pageSize = start / length;
		}
		int totalCount = accountHistoryService.queryAccountHistoryCount(
				businessId, description);
		List<BusinessAccountHistory> list = accountHistoryService
				.queryAccountHistory(businessId, pageSize, length, description);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		if (list.size() > 0) {
			for (BusinessAccountHistory bah : list) {
				Map<String, String> eleMap = new HashMap<String, String>();
				eleMap.put("id", bah.getId() + "");
				eleMap.put("Score", bah.getScore() + "");
				eleMap.put("description", bah.getDescription());
				eleMap.put("createdDate", sdf.format(bah.getCreatedDate()));
				result.add(eleMap);
			}
		}
		map.put("data", result);
		response.getWriter().print(
				jsonCallback + "(" + JSONArray.fromObject(map) + ")");

	}
	/**提现申请*/
	@RequestMapping("/drawcashapply")
	public void drawCashApply(@RequestParam("businessId")int businessId,@RequestParam("Score")BigDecimal Score){
		accountHistoryService.insertAccountHistory(businessId, 0, Score, "提现", 0, "");
	}
	
	@RequestMapping("/getscore")
	public void getScore(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int businessId = (Integer) session.getAttribute("");
		businessId = 10001;
		BigDecimal score=accountService.getScoreByBessinessId(businessId);
		response.getWriter().print(score);
	}
}
