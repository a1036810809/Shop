package cn.techaction.controller.protal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.UserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.vo.ActionUserVo;

@Controller
@RequestMapping("/user")
public class ActionUserPortalController {
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/do_login.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> doLogin(String account,String password,HttpSession session){
		SverResponse<User> response = userService.doLogin(account,password);
		if(response.isSuccess()) {
			//登陆成功，将用户信息存入session
			session.setAttribute(ConstUtil.CUR_USER, response.getData());
		}
		return response;
	}
	/**
	 * 获取用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getuserinfo.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<User> userinfo(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null){
			return SverResponse.createByErrorMessage("无法获取用户信息！");
		}
		return SverResponse.createRespBySuccess(user);
	}
	/**
	 * 用户登出
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/do_logout.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> doLogout(HttpSession session){
		session.invalidate();
		SverResponse<User> response =SverResponse.createRespBySuccess();
		return response;
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/do_register.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> registerUser(User user){
		return userService.doRegisterP(user);
	}
	/**
	 * 检验注册信息
	 * @param info
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/do_check_info.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> check_info(String info,String type){
		return userService.checkValidationP(info, type);
	}
	
	/**
	 * 验证用户，获得用户对象
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getUserByAccount.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> getUserByAccount(String account){
		return userService.findUserByAccountP(account);
	}
	
	/**
	 * 验证用户密码提示问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	@RequestMapping(value="/checkuserasw.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> checkUserAnswer(String account,String question,String asw){
		return userService.checkUserAnswerP(account,question,asw);
	}
	
	/**
	 * 重置密码
	 * @param userId
	 * @param newpwd
	 * @return
	 */
	@RequestMapping(value="/resetpassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> resetPassword(Integer userId,String newpwd){
		return userService.resetPasswordP(userId,newpwd);
	}
	
	/**
	 * 修改用户个人资料
	 * @param session
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value="/updateuserinfo.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> updateUserInfo(HttpSession session,ActionUserVo userVo){
		User curUser = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(curUser==null) {
			return SverResponse.createByErrorMessage("用户尚未登录！");
		}
		userVo.setId(curUser.getId());
		userVo.setAccount(curUser.getAccount());
		SverResponse<User> resp = userService.updateUserInfo(userVo);
		if(resp.isSuccess()) {
			//重写session
			session.setAttribute(ConstUtil.CUR_USER, resp.getData());
		}
		return resp;	
	}
	/**
	 * 密保问题验证
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getuserquestion.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> getuser_question_check(String account){
		return userService.getUserQuestionP(account);
	}
	/**
	 * 登录用户修改密码
	 * @param session
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	@RequestMapping(value="/updatepassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> updatePassword(HttpSession session,String newpwd,String oldpwd){
		//将我们的session取出
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorMessage("请登录后在修改密码！");
		}
		SverResponse<String> result = userService.updatePasswordP(user,newpwd,oldpwd);
		//修改之后清空session，准备重新登录
		if(result.isSuccess()) {
			session.removeAttribute(ConstUtil.CUR_USER);
		}
		return result;
	}
}
