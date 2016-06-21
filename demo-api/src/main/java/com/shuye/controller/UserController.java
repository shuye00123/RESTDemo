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
import com.shuye.param.UserParam;
import com.shuye.result.UserResult;
import com.shuye.service.UserService;
import com.xxx.api.bean.PagingParam;
import com.xxx.api.bean.PagingResult;
import com.xxx.api.bean.Response;
import com.xxx.api.jdbc.paging.Paging;
import com.xxx.api.security.IgnoreSecurity;
import com.xxx.api.security.TokenManager;

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
		String userName = param.getUserName();
		String password = param.getPassword();
		System.out.println(userName);
		boolean result = userService.login(userName, password);
		if(result){
			String token = tokenManager.createToken(userName);
			UserBean userBean = new UserBean();
			userBean.setToken(token);
			userBean.setUsername(userName);
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
		String userName = userParam.getUserName();
		String password = userParam.getPassword();
		userService.updateUser(id, userName, password);
		return new Response().success();
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public Response deleteUser(@PathVariable("id") String id){
		userService.deleteUser(id);
		return new Response().success();
	}
	@IgnoreSecurity
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Response createUser(@RequestBody @Valid UserParam userParam){
		String userName = userParam.getUserName();
		String password = userParam.getPassword();
		userService.createUser(userName, password);
		return new Response().success();
	}
}
