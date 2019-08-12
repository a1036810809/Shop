package cn.techaction.controller.backstage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.ResponseCode;
import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionParam;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionParamService;
import cn.techaction.service.UserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.vo.ActionParamVo;

@Controller
@RequestMapping("/mgr/param")
public class ActionParamBackController {
	@Autowired
	private UserService userService;
	@Autowired
	private ActionParamService actionParamService;
	
	@RequestMapping("/delparam.do")
	@ResponseBody
	public SverResponse<String> delParam(HttpSession session,Integer id){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionParamService.delParam(id);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/findpathparam.do")
	@ResponseBody
	public SverResponse<List<ActionParam>> findPathParam(HttpSession session){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionParamService.findPathParam();
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/findpartstype.do")
	@ResponseBody
	public SverResponse<List<ActionParamVo>> findPartsType(HttpSession session,Integer productTypeId){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionParamService.findPartsType(productTypeId);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/findptype.do")
	@ResponseBody
	public SverResponse<List<ActionParam>> findParentType(HttpSession session){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionParamService.findParentType();
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/findchildren.do")
	@ResponseBody
	public SverResponse<List<ActionParam>> findChildren(HttpSession session,Integer id){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionParamService.findChildren(id);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/updateparam.do")
	@ResponseBody
	public SverResponse<String> updateParam(HttpSession session,Integer id,String name){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionParamService.updateParam(name,id);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/saveparam.do")
	@ResponseBody
	public SverResponse<String> saveParam(HttpSession session,Integer parent_id,String name){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionParamService.saveParam(name,parent_id);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
}
