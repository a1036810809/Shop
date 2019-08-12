package cn.techaction.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionCartDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionCart;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionCartService;
import cn.techaction.utils.CalcUtil;
import cn.techaction.vo.ActionCartVo;
import cn.techaction.vo.ActionCartsListVo;
@Service
public class ActionCartServiceImpl implements ActionCartService {
	@Autowired
	private ActionCartDao aCartDao;
	@Autowired
	private ActionProductDao aProductDao;

	@Override
	public SverResponse<String> saveOrUpdateP(Integer userId, Integer productId, Integer count) {
		//验证参数是否正确
		if(userId==null || productId==null || count==null){
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//查看用户的购物车中是否存在商品
		ActionCart actionCart = aCartDao.findCartByUserAndProductIdP(userId,productId);
		if(actionCart==null) {
			//不存在则新增
			ActionCart cart = new ActionCart();
			cart.setUserId(userId);
			cart.setProductId(productId);
			cart.setQuantity(count);
			cart.setCreated(new Date());
			cart.setUpdated(new Date());
			aCartDao.insertCartP(cart);
		}else {
			//存在则数量增加
			int cartCount =actionCart.getQuantity()+count;
			actionCart.setQuantity(cartCount);
			aCartDao.updateCartByIdP(actionCart);
		}		
		return SverResponse.createRespBySuccessMessage("商品已成功加入到购物车！");
	}

	@Override
	public SverResponse<ActionCartVo> findAllCartsP(Integer userId) {
		if(userId==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//查找该用户购物车中的商品
		List<ActionCart> list = aCartDao.findCartByUserP(userId);
		//封装actioncartvo对象
		ActionCartVo cartVo =createCartVoP(list);
		return SverResponse.createRespBySuccess(cartVo);
	}
	/**
	 * 封装购物车Vo对象
	 * @param list
	 * @return
	 */
	private ActionCartVo createCartVoP(List<ActionCart> carts) {
		ActionCartVo cartVo =new ActionCartVo();
		List<ActionCartsListVo> list = Lists.newArrayList();
		//购物车商品总价格
		BigDecimal cartTotalPrice = new BigDecimal("0");
		if(CollectionUtils.isNotEmpty(carts)) {
			for(ActionCart cart:carts ) {
				//转换对象
				ActionCartsListVo listVo =new ActionCartsListVo();
				listVo.setId(cart.getId());
				listVo.setUserId(cart.getUserId());
				listVo.setProductId(cart.getProductId());
				listVo.setChecked(cart.getChecked());
				//封装商品信息
				ActionProduct product = aProductDao.findProductByIdP(listVo.getProductId());
				if(product!=null) {
					listVo.setName(product.getName());
					listVo.setStatus(product.getStatus());
					listVo.setPrice(product.getPrice());
					listVo.setStock(product.getStock());;;
					listVo.setIconUrl(product.getIconUrl());
					//判断库存
					int buyCount=0;
					if(product.getStock()>=cart.getQuantity()) {
						buyCount=cart.getQuantity();
					}else {
						buyCount = product.getStock();
						//更新购物车中商品数量
						ActionCart updateCart = new ActionCart();
						updateCart.setId(cart.getId());
						updateCart.setQuantity(buyCount);
						//更新选中状态
						updateCart.setChecked(cart.getChecked());
						aCartDao.updateCartByIdP(updateCart);
					}
					listVo.setQuantity(buyCount);
					//计算购物车中某商品总价格
					BigDecimal totalPrice = CalcUtil.mul(listVo.getPrice().doubleValue(), listVo.getQuantity().doubleValue());
					listVo.setTotalPrice(totalPrice);	
					if(cart.getChecked() ==1) {
						//计算购物车选中状态商品的总价格
						cartTotalPrice = CalcUtil.add(cartTotalPrice.doubleValue(), listVo.getTotalPrice().doubleValue());
					}
				}
				list.add(listVo);
			}
		}
		cartVo.setLists(list);
		cartVo.setTotalPrice(cartTotalPrice);
		return cartVo;
	}

	@Override
	public SverResponse<String> clearCartP(Integer userId) {
		//判断参数正确
		if(userId == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//清空购物车，判断正确
		int rs = aCartDao.deleteCartByUserIdP(userId);
		if(rs >0) {
			return SverResponse.createRespBySuccessMessage("成功清空购物车！");
		}
		return SverResponse.createByErrorMessage("清空购物车失败");
	}

	@Override
	public SverResponse<ActionCartVo> updateCartP(Integer userId, Integer productId, Integer count, Integer checked) {
		//判断参数
		if(userId==null || productId==null || count ==null) {
			return SverResponse.createRespBySuccessMessage("参数错误！");
		}
		//更新购物车信息
		ActionCart actionCart = new ActionCart();
		actionCart.setUserId(userId);
		actionCart.setProductId(productId);
		actionCart.setQuantity(count);
		actionCart.setChecked(checked);
		aCartDao.updateCartByUserIdAndProductIdP(actionCart);
		//返回所有购物车信息
		return findAllCartsP(userId);
	}

	@Override
	public SverResponse<ActionCartVo> deleteCartP(Integer userId, Integer productId) {
		//判断参数
		if(userId ==null || productId ==null ) {
			return SverResponse.createRespBySuccessMessage("参数错误！");
		}
		//删除商品
		int rs = aCartDao.deleteCartsP(userId,productId);
		if(rs >0) {
			//返回所有购物车信息
			return this.findAllCartsP(userId);
		}
		return SverResponse.createRespBySuccessMessage("删除商品失败！");
	}

	@Override
	public SverResponse<Integer> getCartCountP(Integer userId) {
		//判断参数
		if(userId == null) {
			return SverResponse.createRespBySuccessMessage("参数错误！");
		}
		int count = aCartDao.getCartCountByUserIdP(userId);
		return SverResponse.createRespBySuccess(Integer.valueOf(count));
	}


	
}
