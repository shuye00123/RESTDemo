package com.shuye.framework.security.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.shuye.framework.security.TokenManager;
import com.shuye.framework.util.CodecUtil;
import com.shuye.framework.util.StringUtil;

/**
 * 默认令牌管理器
 *
 * @author huangyong
 * @since 1.0.0
 */
public class DefaultTokenManager implements TokenManager {

    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public String createToken(String username) {
        String token = CodecUtil.createUUID();
        tokenMap.put(token, username);
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        return !StringUtil.isEmpty(token) && tokenMap.containsKey(token);
    }
}
