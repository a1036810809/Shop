package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.vo.ActionUserVo;

public interface UserService {
	
	/**
	 * 用户登陆
	 * @param account
	 * @param password
	 * @return
	 */
	public SverResponse<User> doLogin(String account,String password);	
	/**
	 * 判断用户是否是管理员
	 * @param user
	 * @return
	 */
	public SverResponse<String> isAdmin(User user);
	/**
	 * 获得所有的用户信息
	 * @return
	 */
	public SverResponse<List<ActionUserVo>> findUserList();
	/**
	 * 根据用户id获得用户对象
	 * @param id
	 * @return
	 */
	public SverResponse<ActionUserVo> findUser(Integer id);
	/**
	 * 更新用户信息
	 * @param userVo
	 * @return
	 */
	public SverResponse<User> updateUserInfo(ActionUserVo userVo);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public SverResponse<String> delUser(Integer id);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public SverResponse<String> doRegisterP(User user);	
	/**
	 * 信息校验验证
	 * @param str
	 * @param type
	 * @return
	 */
	public SverResponse<String> checkValidationP(String str,String type);
	/**
	 * 校验用户问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public SverResponse<String> checkUserAnswerP(String account, String question, String asw);
	/**
	 * 重置密码
	 * @param userId
	 * @param newpwd
	 * @return
	 */
	public SverResponse<String> resetPasswordP(Integer userId, String password);
	/**
	 * 重设密码
	 * @param user
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	public SverResponse<String> updatePasswordP(User user, String newPassword, String oldPassword);
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public SverResponse<User> findUserInfoP(Integer id);
	/**
	 * 根据用户名获得用户对象
	 * @param account
	 * @return
	 */
	public SverResponse<User> findUserByAccountP(String account);
	/**
	 * 获取用户密码问题
	 * @param account
	 * @return
	 */
	public SverResponse<String> getUserQuestionP(String account);
}
