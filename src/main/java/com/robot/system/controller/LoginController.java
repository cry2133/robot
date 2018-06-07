package com.robot.system.controller;

import com.robot.common.annotation.Log;
import com.robot.common.controller.BaseController;
import com.robot.common.domain.Tree;
import com.robot.common.utils.LocaleMessageSourceService;
import com.robot.common.utils.MD5Utils;
import com.robot.common.utils.R;
import com.robot.common.utils.ShiroUtils;
import com.robot.system.domain.MenuDO;
import com.robot.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;

	@GetMapping({ "/", "" })
	String welcome(Model model,String lang) {
		model.addAttribute("lang", lang);
		return "redirect:/login";
	}

	
	/**
	 * 切换语言
	 * @param request
	 * @param response
	 * @param lang
	 * @return
	 */
	@RequestMapping("/changeSessionLanauage")
    public String changeSessionLanauage(HttpServletRequest request,HttpServletResponse response,Model model,String lang,int status){
           LocaleResolver localeResolver =RequestContextUtils.getLocaleResolver(request);
           if("zh".equals(lang)){
                  localeResolver.setLocale(request, response, new Locale("zh","CN"));
           }else if("en".equals(lang)){
                  localeResolver.setLocale(request, response, new Locale("en","US"));
           }
           model.addAttribute("lang", lang);
           if(status==1){	//	登录页切换
        	   return"redirect:/login";
           }else{
        	   return"redirect:/index";
           }
    }
	
	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		String msg = localeMessageSourceService.getMessage("welcome");
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("welcome", msg);
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

	@GetMapping("/403")
	String error403() {
		return "403";
	}

}
