package cn.hmjiaxin.controller.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.model.WechatAccountType;
import cn.hmjiaxin.service.CommonService;
import cn.hmjiaxin.util.StringUtil;

@RestController
public class CommonContraller {
	private CommonService commonService;

	@Autowired
	public CommonContraller(CommonService commonService) {
		super();
		this.commonService = commonService;
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
		// int businessId = (Integer) session.getAttribute("");
		String idStr = request.getParameter("businessId");
		int businessId;
		if (idStr == null || "".equals(idStr)) {
			businessId = 10001;
		} else {
			businessId = Integer.parseInt(request.getParameter("businessId"));

		}

		BigDecimal score = commonService.getScoreByBessinessId(businessId);
		String result = StringUtil.JSONCallBack(request, score);
		response.getWriter().print(result);
	}

	/**
	 * 获取微信所有账户类型
	 * @throws IOException 
	 */
	@RequestMapping("/wechattype")
	public void getWechatAccountType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		List<WechatAccountType> lsit = commonService.getWechatType();
		response.getWriter().print(StringUtil.JSONCallBack(request, lsit));
	}

}
