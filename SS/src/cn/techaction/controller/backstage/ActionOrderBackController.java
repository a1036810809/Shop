package cn.techaction.controller.backstage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.techaction.common.ResponseCode;
import cn.techaction.common.SverResponse;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionOrderService;
import cn.techaction.service.UserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

@Controller
@RequestMapping("/mgr/order")
public class ActionOrderBackController {
	@Autowired
	private ActionOrderService actionOrderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findorders_nopages.do")
	@ResponseBody
	public SverResponse<List<ActionOrderVo>> findOrdersNoPage(HttpSession session,Long orderNo){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionOrderService.findOrdersNoPage(orderNo);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/search.do")
	@ResponseBody
	public SverResponse<PageBean<ActionOrderVo>> searchOrders(HttpSession session,Integer pageNum,Integer pageSize,Long orderNo){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionOrderService.searchOrder(pageNum,pageSize,orderNo);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/findorders.do")
	@ResponseBody
	public SverResponse<PageBean<ActionOrderVo>> findOrders(HttpSession session,Integer pageNum,Integer pageSize){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionOrderService.findOrders(pageNum,pageSize);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<ActionOrderVo> getDetail(HttpSession session,Long orderNo){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionOrderService.getDetail(orderNo);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
}
