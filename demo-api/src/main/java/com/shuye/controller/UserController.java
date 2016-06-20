/**
 * 
 */
package com.shuye.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shuye.bean.UserBean;
import com.shuye.framework.bean.PagingParam;
import com.shuye.framework.bean.PagingResult;
import com.shuye.framework.bean.Response;
import com.shuye.framework.jdbc.paging.Paging;
import com.shuye.framework.security.IgnoreSecurity;
import com.shuye.framework.security.TokenManager;
import com.shuye.param.UserParam;
import com.shuye.result.UserResult;
import com.shuye.service.UserService;

/**
 * @author shuye
 *
 */
@RestController
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
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Response listUser(PagingParam pagingParam){
		int pageNumber = pagingParam.getPagingNumber();
		int pageSize = pagingParam.getPageSize();
		String whereCondition = pagingParam.getWhereCondition("username");
		String orderBy = pagingParam.getOrderBy();
		Paging<UserResult> resultpaging = userService.getUserPaging(pageNumber, pageSize, whereCondition, orderBy);
		return new Response().success(new PagingResult<>(resultpaging));
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Response getUser(@PathVariable("id") String id){
		UserResult user = userService.getUser(id);
		return new Response().success(user);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public Response updateUser(
			@PathVariable("id") String id,
			@RequestBody @Valid UserParam userParam){
		String username = userParam.getUsername();
		String password = userParam.getPassword();
		userService.updateUser(id, username, password);
		return new Response().success();
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public Response deleteUser(@PathVariable("id") String id){
		userService.deleteUser(id);
		return new Response().success();
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Response createUser(@RequestBody @Valid UserParam userParam){
		String username = userParam.getUsername();
		String password = userParam.getPassword();
		userService.createUser(username, password);
		return new Response().success();
	}
}
