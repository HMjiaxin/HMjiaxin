package cn.hmjiaxin.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hmjiaxin.model.ReturnResult;
import cn.hmjiaxin.model.User;
import cn.hmjiaxin.service.common.UserService;
import cn.hmjiaxin.util.StringUtil;

/**
 * 登陆登出
 * 
 * @author wangchengang
 *
 */
@RestController
public class LoginOutController {
	private UserService userService;

	@Autowired
	public LoginOutController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping("/getAllUser")
	public void getAllUser(HttpServletResponse response) throws IOException {
		List<User> list = userService.getAll();
		for (User u : list) {
			System.out.println(u.getUserName());
			System.out.println(u.getUserPassword());
		}
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(list).toString());
	}

	/**
	 * 登陆验证
	 * @param userName
	 *            用户名
	 * @param userPassword
	 *            密码
	 * @throws IOException
	 */
	@RequestMapping("/login")
	public void login(HttpServletResponse response,HttpServletRequest request,
			@RequestParam("inputPhone") String userPhone,
			@RequestParam("inputPassword") String userPassword,
			@RequestParam(value="userType",required=false )String userType)
			throws IOException {
		System.out.println(userPhone);
		System.out.println(userPassword);
		System.out.println(userType);
		userPassword = StringUtil.encodeMD5(userPassword);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		ReturnResult returnResult=new ReturnResult();
		if (StringUtils.isBlank(userPhone)) {
			returnResult.setStatus(0);
			returnResult.setMsg("手机号不能为空");
		} else {
			User user = userService.queryUserByPhone(userPhone);
			if (user == null) {
				returnResult.setStatus(0);
				returnResult.setMsg("用户不存在");
			} else if (!userPassword.equals(user.getUserPassword())) {
				returnResult.setStatus(0);
				returnResult.setMsg("密码错误");
			} else if(!userType.equals(userType)){
				returnResult.setStatus(0);
				returnResult.setMsg("账户类型错误");
			}else{
				HttpSession session =request.getSession();
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userPhone", user.getUserPhone());
				returnResult.setStatus(1);
				returnResult.setMsg("登陆成功");
			}
		}
		response.getWriter().print(JSONArray.fromObject(returnResult).toString());
	}

	@RequestMapping("/code")
	public void getCode(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		int width = 100;// 定义图片的width
		int height = 30;// 定义图片的height
		int codeCount = 4;// 定义图片上显示验证码的个数
		int xx = 15;//文字间距
		int fontHeight = 22;//文字大小
		int codeY = 21;//文字高度
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' };
		//定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		//Graphics2D gd = buffImg.createGraphics();
		//Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		//将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		//创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		//设置字体。
		gd.setFont(font);
		//画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		//随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		//randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		//随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			//得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(36)]);
			//产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			//用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * xx, codeY);

			//将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		//将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		System.out.println(randomCode);
		session.setAttribute("code", randomCode.toString());
		//禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		//将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}

	@RequestMapping("/checkCode")
	public void checkCode(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("code") String reqCode)
			throws IOException {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		if (code == null) {
			response.getWriter().print(false);
		} else if (reqCode.equals(code)) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}

	}

	
}
