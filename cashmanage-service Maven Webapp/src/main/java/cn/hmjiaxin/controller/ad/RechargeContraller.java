package cn.hmjiaxin.controller.ad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.service.BusinessAccountHistoryService;
import cn.hmjiaxin.service.BusinessAccountService;
/**
 * 广告主自充值/调用支付宝、银行
 * @author wangchengang
 *
 */
@RestController
public class RechargeContraller {
	private BusinessAccountHistoryService accountHistoryService;
	private BusinessAccountService accountService;
	@Autowired
	public RechargeContraller(
			BusinessAccountHistoryService accountHistoryService,
			BusinessAccountService accountService) {
		super();
		this.accountHistoryService = accountHistoryService;
		this.accountService = accountService;
	}
	
	@RequestMapping
	public void rechargeList(HttpServletResponse response,HttpServletRequest request){
				
	}
}
