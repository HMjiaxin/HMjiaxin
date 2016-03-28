package cn.hmjiaxin.controller.common;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.service.BusinessAccountService;
import cn.hmjiaxin.util.StringUtil;

@RestController
public class CommonContraller {
	private BusinessAccountService accountService;
	@Autowired
	public CommonContraller(BusinessAccountService accountService) {
		super();
		this.accountService = accountService;
	}

	/**
	 * 获取账户余额
	 */
	@RequestMapping("/getscore")
	public void getScore(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		HttpSession session = request.getSession();
//		int businessId = (Integer) session.getAttribute("");
		String idStr=request.getParameter("businessId");
		int businessId;
		if(idStr==null||"".equals(idStr)){
			businessId = 10001;
		}else{
			businessId=Integer.parseInt(request.getParameter("businessId"));
			
		}

		BigDecimal score=accountService.getScoreByBessinessId(businessId);
		String result=StringUtil.JSONCallBackUrl(request, score);
		response.getWriter().print(result);
	}
	
}
