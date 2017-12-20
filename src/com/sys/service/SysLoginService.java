package com.sys.service;

import com.sys.dao.SysUserLoginDao;
import com.sys.model.SysUserLogin;
import com.util.code.RandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.util.redis.RedisClient;
import com.util.redis.RedisKeyUtil;
import com.util.string.StringUtil;

@Service
public class SysLoginService {
	@Autowired
	private RedisClient<String, String> redisClient;
	@Autowired
	private SysUserLoginDao sysUserLoginDao;

	/**
	 * 校验登录
	 *
	 * @param username
	 * @param password
	 * @param verifyCode
	 * @return
	 */
	public String checkLogin(String username, String password, String verifyCode) {
		return null;
	}


	/**
	 * 增加密码输入错误次数，放到redist里面
	 *
	 * @param username
	 */
	public void addVerifyCodeNum(String username) {
		String needVcKey = RedisKeyUtil.VERIFYERROR_KEY + username;
		String needVerifyCode = redisClient.get(needVcKey);
		if (StringUtil.isNullOrEmpty(needVerifyCode)) {
			redisClient.set(RedisKeyUtil.VERIFYERROR_KEY + username, "1", 60 * 60);//
		} else {
			redisClient.set(RedisKeyUtil.VERIFYERROR_KEY + username, String.valueOf(Integer.parseInt(needVerifyCode) + 1), 60 * 60);//
		}
	}



	/**
	 * 生成图形验证码
	 *
	 * @param username
	 * @return
	 */
	public String generateVerifyCode(String username) {
		String randomCode = RandomCodeUtil.createRandomNum(4);//生成四位随机数
		redisClient.set(RedisKeyUtil.VERIFYCODE_KEY + username, randomCode, 30 * 60);//
		return randomCode;
	}

	/**
	 * 登录成功后，删除redis里面的错误次数和验证码
	 *
	 * @param username
	 */
	public void clearLoginVerifyInfo(String username) {
		String needVcKey = RedisKeyUtil.VERIFYERROR_KEY + username;//错误次数
		String randomCode = RandomCodeUtil.createRandomNum(4);//生成四位随机数
		redisClient.del(needVcKey);
		redisClient.del(randomCode);
	}

	/**
	 * 登录时新增登录信息
	 *
	 * @param sysUserLogin
	 * @return
	 */
	@Async
	public void add(SysUserLogin sysUserLogin) {
		sysUserLoginDao.add(sysUserLogin);
	}

}
