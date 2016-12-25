package com.nsyun.server.controllers.users;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nsyun.server.domain.users.Authority;
import com.nsyun.server.domain.users.User;
import com.nsyun.server.dto.common.PageQueryRequest;
import com.nsyun.server.dto.common.PageQueryResult;
import com.nsyun.server.services.users.UserService;

@RestController
@RequestMapping({ "/user" })
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/*
	 * @Autowired private Md5PasswordEncoder md5PasswordEncoder;
	 * 
	 * @Autowired private SecureRandom secureRandom;
	 */
	/**
	 * 根据会员ID查询会员信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {
			"/findUserById" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User findUserById(String id) throws Exception {
		User dbUser = null;
		try {
			if (!StringUtils.isEmpty(id)) {
				dbUser = this.userService.findUserById(id);
			}
			return dbUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = {"/checkRole" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> checkUserRole(String role,String id ) throws Exception {
	
		Map<String, String> resultMap = new HashMap<String, String>();
		User dbUser = null;
	try {
		if (!StringUtils.isEmpty(role)) {
			dbUser = this.userService.findUserById(id);
			Iterator it=dbUser.getRoles().iterator();
	 		String roles = "";
	 		while(it.hasNext())
	        {
	 			Authority fruit=(Authority)it.next();
	 			if(fruit.getName().equals("ROLE_ADMIN")){
	 				roles = fruit.getName();
	 			}
	 			
	        }
	 		if(!StringUtils.isEmpty(roles)){
	 			resultMap.put("success", "true");
	 		}else{
	 			resultMap.put("success", "false");
	 		}
			
		}else{
			resultMap.put("success", "false");
		}
		return resultMap;
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return null;
	}

	/**
	 * 保存和修改会员信息
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> save(User user) throws Exception {

		String msg1 = "用户名已存在";
		String msg2 = "手机号已存在";

		Map map = new HashMap();
		try {
			if (StringUtils.isEmpty(user.getId())) {

				Integer usernames = userService.findByUsername(user.getUsername());

				if (usernames > 0) {

					map.put("success", msg1);
					return map;
				}

				Integer mobiles = userService.findByMobile(user.getMobile());

				if (mobiles > 0) {

					map.put("success", msg2);
					return map;

				} else {

					userService.saveUser(user);

				}

			} else {

				Integer username1 = userService.findByUsername(user.getUsername(), user.getId() + "");
				Integer username2 = userService.findByUsername(user.getUsername());

				if (username1 > 0) {
					if (username2 > 1) {
						map.put("success", msg1);
						return map;
					}
				} else {
					if (username2 > 0) {
						map.put("success", msg1);
						return map;
					}
				}

				Integer mobile1 = userService.findByMobile(user.getMobile(), user.getId() + "");
				Integer mobile2 = userService.findByMobile(user.getMobile());

				if (mobile1 > 0) {
					if (mobile2 > 1) {
						map.put("success", msg2);
						return map;
					}
				} else {
					if (mobile2 > 0) {
						map.put("success", msg2);
						return map;
					}
				}
				userService.updateUser(user);
			}

			map.put("success", "true");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 创建会员信息分页
	 * 
	 * @param user
	 * @param pageRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageQueryUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PageQueryResult<User> pageQueryUsers(User user, PageQueryRequest pageRequest) throws Exception {
		try {
			Page<User> pageResult = userService.queryUserByPage(user, new PageRequest(pageRequest.getCurrentPage() - 1,
					pageRequest.getPageSize(), new Sort(Sort.Direction.ASC, "id")));

			PageQueryResult<User> result = new PageQueryResult<User>();
			result.setData(pageResult.getContent());
			result.setiTotalRecords(pageResult.getTotalElements());
			result.setiTotalDisplayRecords(pageResult.getTotalElements());
			result.setSuccess(true);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
	

	/**
	 * 根据会员ID删除会员信息
	 * 
	 * @param idStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/del" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> del(String idStr) throws Exception {
		{
			Map<String, String> resultMap = new HashMap<String, String>();
			try {
				String[] ids = idStr.split("-");
				for (String id : ids) {
					userService.delete(Long.parseLong(id));
				}

				resultMap.put("success", "true");
				return resultMap;
			} catch (Exception e) {
				resultMap.put("success", "false");
			}
			return resultMap;
		}
	}

	/**
	 *
	 * @Title:UserController @Description:
	 * 查询所有用户(不分页) @param: @return @param: @throws Exception @return:
	 * List<User> @throws
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> getUser() throws Exception {
		try {
			List<User> shops = userService.findUser();

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", shops);
			resultMap.put("success", "true");
			return resultMap;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 *
	 * @Title:UserController @Description:
	 * 查询所有用户(不分页) @param: @return @param: @throws Exception @return:
	 * List<User> @throws
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> changePassword(Long userId, String username, String password) throws Exception {

		Map<String, String> resultMap = new HashMap<String, String>();

		try {

			User userDb = userService.findUserById(String.valueOf(userId));

			User userIdOther = userService.findUserByUserName(username);

			if (null != userIdOther && !userIdOther.getId().equals(userDb.getId())) {
				resultMap.put("success", "false");
				resultMap.put("message", "该用户名存在！");
				return resultMap;
			}

			userDb.setUsername(username);
			// String endcodedPassword =
			// md5PasswordEncoder.encodePassword(userDb.getPassword(),
			// userDb.getSalt().longValue());
			userDb.setPassword(password);

			userService.saveUser(userDb);

			resultMap.put("success", "true");
			return resultMap;
		} catch (Exception e) {

			resultMap.put("success", "false");
			resultMap.put("message", "更新密码失败！");
			return resultMap;
		}
	}

	/**
	 * 
	 * @Title: UserController
	 * @Description: 用户登陆
	 * @param: @param user
	 * @param: @return
	 * @return: Map<String,String>
	 * @throws
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> userLogin(User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
				resultMap.put("code", "001"); // 用户名或密码为空
			} else {
				User dbUser = userService.findUserByMobile(user.getUsername());
				if (dbUser == null) {
					resultMap.put("code", "002"); // 用户不存在
				} else {
					if (dbUser.getMobile().trim().equals(user.getUsername().trim()) && dbUser.getPassword().trim().equals(user.getPassword().trim())) {
						resultMap.put("code", "003"); // 验证成功
						resultMap.put("data", dbUser); // 返回用户信息
					} else {
						resultMap.put("code", "004"); // 用户名或密码不正确
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	
	
}