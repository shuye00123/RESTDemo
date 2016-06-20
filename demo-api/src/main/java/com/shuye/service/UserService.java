/**
 * 
 */
package com.shuye.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;

import com.shuye.constant.Message;
import com.shuye.framework.jdbc.ServiceException;
import com.shuye.framework.jdbc.dao.DataAccessor;
import com.shuye.framework.jdbc.paging.Paging;
import com.shuye.framework.util.DateUtil;
import com.shuye.result.UserResult;

/**
 * @author shuye
 *
 */
@Service
public class UserService {
	@Autowired
	private DataAccessor da;
	@Autowired
	private IdGenerator ig;
	
	public boolean login(String userName, String password){
		UserResult u = da.selectOne("UserMapper.login", userName);
		if(u.getPassword()==password){
			return true;
		}
		return false;
	}
	
	public Paging<UserResult> getUserPaging(int pageNumber, int pageSize, String whereCondition, String orderBy){
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("whereCondition", whereCondition);
        paramMap.put("orderBy", orderBy);
        return da.selectPaging("UserMapper.selectUserPaging", paramMap, pageNumber, pageSize);
	}
	
	public UserResult getUser(String id){
		UserResult u = da.selectOne("UserMapper.selectUserById", id);
		if(u == null){
			throw new ServiceException(Message.QUERY_FAILURE);
		}
		return u;
	}
	
	@Transactional
	public void createUser( String username, String password){
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", ig.generateId());
		paramMap.put("userName", username);
		paramMap.put("password", password);
		paramMap.put("createdTime", DateUtil.getCurrentDateTime());
		da.insert("UserMapper.insertUser", paramMap);
	}
	
	@Transactional
	public void updateUser(String id, String username, String password){
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("userName", username);
		paramMap.put("password", password);
		da.insert("UserMapper.insertUser", paramMap);
	}
	
	@Transactional
	public void deleteUser(String id){
		da.delete("UserMapper.daleteUser", id);
	}
}
