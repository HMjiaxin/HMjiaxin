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
			HttpServletResponse response,
			@RequestParam("draw") String pageSizeStr) throws IOException {
		System.out.println(request.getRequestURL().toString());
		System.out.println(pageSizeStr);
		System.out.println(pageSizeStr+"___________________");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		int pageSize = 0;
		if (pageSizeStr == null || "".equals(pageSizeStr)) {
			pageSize = 1;
		} else {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		int num = 10;
		String key = "";
		List<BusinessAccountHistory> list = accountHistoryService.queryAll(
				pageSize, num, key);
		int totalCount = accountHistoryService.queryCount();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("draw", pageSizeStr);
		map.put("recordsTotal", totalCount);
		map.put("recordsFiltered", totalCount);
		List<List<String>> result = new ArrayList<List<String>>();
		if (list.size() > 0) {
			for (BusinessAccountHistory bah : list) {
				int businessId = bah.getBusinessId();
				BusinessAccount account = accountService
						.findAccountById(businessId);
				List<String> elementList = new ArrayList<String>();
				elementList.add(bah.getId() + "");
				elementList.add(businessId + "");
				elementList.add(account.getScore() + "");
				elementList.add(bah.getScore().toString());
				elementList.add(bah.getStatus() + "");
				elementList.add(sdf.format(bah.getCreatedDate()));
				elementList.add(sdf.format(bah.getLastUpdatedDate()));
				result.add(elementList);
			}
		}
		map.put("data", result);
		String res=JSONArray.fromObject(map).toString();
		res=res.substring(1,res.length()-1);
		System.out.println(res);
		response.getWriter().print(res);
	}

	@RequestMapping("/updateStatus")
	public void updateState(@RequestParam("status") int changeStatus,
			@RequestParam("id") int businessId,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		boolean result = accountHistoryService.updateStatus(changeStatus,
				businessId);
		if (result) {
			response.getWriter().print("修改成功");
		}else{
			response.getWriter().print("修改失败");
		}
	}
}
