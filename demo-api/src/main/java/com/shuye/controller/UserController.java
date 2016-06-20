/**
 * 
 */
package com.shuye.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shuye.bean.UserBean;
import com.shuye.framework.bean.Response;
import com.shuye.framework.security.IgnoreSecurity;
import com.shuye.framework.security.TokenManager;
import com.shuye.param.UserParam;
import com.shuye.service.UserService;

/**
 * @author shuye
 *
 */
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private  TokenManager tokenManager;
	
	@IgnoreSecurity
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Response login(@Valid UserParam param){
		String username = param.getUsername();
		String password = param.getPassword();
		boolean result = userService.login(username, password);
		if(result){
			String token = tokenManager.createToken(username);
			UserBean userBean = new UserBean();
			userBean.setToken(token);
			userBean.setUsername(username);
			return new Response().success(userBean);
		}else{
			return new Response().failure("login_failure");
		}
	}
}
