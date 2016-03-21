package cn.hmjiaxin.controller.media;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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

@RestController
public class DrawCashApplyContraller {
	private BusinessAccountHistoryService accountHistoryService;
	private BusinessAccountService accountService;

	@Autowired
	public DrawCashApplyContraller(
			BusinessAccountHistoryService accountHistoryService,
			BusinessAccountService accountService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
	}

	@RequestMapping("/applylist")
	public void drawCashApplyList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int businessId = (Integer) session.getAttribute("");
		businessId = 10001;
		List<BusinessAccountHistory> list = accountHistoryService
				.findBybusinessId(businessId);
		// System.out.println();
		response.getWriter().print(JSONArray.fromObject(list));
	}

	@RequestMapping("/getscore")
	public void getAccount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {// @RequestParam("businessid")String
											// businessid){
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		int businessId = (Integer) session.getAttribute("");
		businessId = 10001;
		BigDecimal score=accountService.getScoreByBessinessId(businessId);
		response.getWriter().print(score);
	}
}