package cn.techaction.controller.backstage;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.google.common.collect.Lists;

import cn.techaction.common.ResponseCode;
import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.pojo.User;
import cn.techaction.service.ActionProductService;
import cn.techaction.service.UserService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductListVo;
import cn.techaction.vo.FileVo;

@Controller
@RequestMapping("/mgr/product")
public class ActionProductBackController {
	@Autowired
	private ActionProductService actionProductService;
	@Autowired
	private UserService userService;
	
	/**
	 * 查询商品/多条件查询
	 * @param session
	 * @param product
	 * @return
	 */
	@RequestMapping("/productlist.do")
	@ResponseBody
	public SverResponse<List<ActionProductListVo>> findProducts(HttpSession session,ActionProduct product){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获取产品信息
			return actionProductService.findProducts(product);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	/**
	 * 保存商品信息
	 * @param session
	 * @param product
	 * @return
	 */
	@RequestMapping("/saveproduct.do")
	@ResponseBody
	public SverResponse<String> saveProduct(HttpSession session,ActionProduct product){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法保存产品信息
			return actionProductService.saveOrUpdateProduct(product);
		}	
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/setstatus.do")
	@ResponseBody
	public SverResponse<String> modifyStatus(HttpSession session,Integer productId,Integer status,Integer hot){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法更新产品信息
			return actionProductService.updateStatus(productId, status, hot);
		}
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<ActionProduct> findProductDetailById(HttpSession session,Integer productId){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获得产品详细信息
			return actionProductService.findProductDetailById(productId);
		}
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/searchproducts.do")
	@ResponseBody
	public SverResponse<PageBean<ActionProductListVo>> findProductsByPage(HttpSession session,Integer pageNum,Integer pageSize,ActionProduct product){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获得产品详细信息
			return actionProductService.findProductsByPage(pageNum,pageSize,product);
		}
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
	
	@RequestMapping("/pic_upload.do")
	@ResponseBody
	public SverResponse<List<FileVo>> upload(HttpSession session,DefaultMultipartHttpServletRequest multipartRequest){
		List<FileVo> fileVos = Lists.newArrayList();
		if (multipartRequest==null) {
			return SverResponse.createByErrorMessage("文件未选择!");
		}
		Iterator<String> iterator = multipartRequest.getFileNames();
		while (iterator.hasNext()) {
			List<MultipartFile> files = multipartRequest.getFiles(iterator.next());
			for (MultipartFile file:files) {
				if (!file.isEmpty()) {
					try {
			            String path = session.getServletContext().getRealPath("/upload");;
			            String originalFileName = file.getOriginalFilename();
			            // 新的图片名称
			            String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
			            // 新的图片
			            File newFile = new File(path,newFileName);
			            // 将内存中的数据写入磁盘
			            file.transferTo(newFile);
			            FileVo fileVo = new FileVo();
			            fileVo.setUrl("/upload/"+newFileName);
			            fileVos.add(fileVo);
					}catch(Exception e) {
						return SverResponse.createByErrorMessage("文件上传错误!");
					}
				}else {
					return SverResponse.createByErrorMessage("文件未选择!");
				}
			}
		}
		return SverResponse.createRespBySuccess(fileVos);
	}
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public SverResponse<FileVo> uploadFile(HttpSession session,MultipartFile file){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作！");
		}
		//2.用户是否未管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法获得产品详细信息
			if (!file.isEmpty()) {
				try {
		            String path = session.getServletContext().getRealPath("/upload");;
		            String originalFileName = file.getOriginalFilename();
		            // 新的图片名称
		            String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
		            // 新的图片
		            File newFile = new File(path,newFileName);
		            // 将内存中的数据写入磁盘
		            file.transferTo(newFile);
		            FileVo fileVo = new FileVo();
		            fileVo.setUrl("/upload/"+newFileName);
		            return SverResponse.createRespBySuccess(fileVo);
				}catch(Exception e) {
					return SverResponse.createByErrorMessage("文件上传错误!");
				}
			}
			return SverResponse.createByErrorMessage("文件未选择!");
		}
		return SverResponse.createByErrorMessage("你无操作权限!");
	}
}
