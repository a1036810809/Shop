package cn.techaction.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionAddressDao;
import cn.techaction.dao.ActionCartDao;
import cn.techaction.dao.ActionOrderDao;
import cn.techaction.dao.ActionOrderItemDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionCart;
import cn.techaction.pojo.ActionOrder;
import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionOrderService;
import cn.techaction.utils.CalcUtil;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.DateUtils;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderItemVo;
import cn.techaction.vo.ActionOrderVo;
@Service
public class ActionOrderServiceImpl implements ActionOrderService {
	@Autowired
	private ActionOrderDao actionOrderDao;
	@Autowired
	private ActionOrderItemDao actionOrderItemDao;
	@Autowired
	private ActionAddressDao actionAddressDao;
	@Autowired
	private ActionProductDao actionProductDao;
	@Autowired
	private ActionCartDao actionCartDao;

	@Override
	public SverResponse<List<ActionOrderVo>> findOrdersNoPage(Long orderNo) {
		// TODO Auto-generated method stub
		List<ActionOrder> orders = actionOrderDao.findOrdersNoPage(orderNo);
		List<ActionOrderVo> oVos = Lists.newArrayList();
		for(ActionOrder order:orders) {
			oVos.add(transToVo(order,false));
		}
		return SverResponse.createRespBySuccess(oVos);
	}
	
	private ActionOrderVo transToVo(ActionOrder order,boolean hasAddress) {
		ActionOrderVo vo = new ActionOrderVo();
		vo.setOrderNo(order.getOrderNo());
		vo.setAmount(order.getAmount());
		vo.setType(order.getType());
		vo.setTypeDesc(ConstUtil.PaymentType.getTypeDesc(vo.getType()));
		vo.setFreight(order.getFreight());
		vo.setStatus(order.getStatus());
		vo.setStatusDesc(ConstUtil.OrderStatus.getStatusDesc(vo.getStatus()));
		vo.setPaymentTime(DateUtils.date2Str(order.getPaymentTime()));
		vo.setDeliveryTime(DateUtils.date2Str(order.getDeliveryTime()));
		vo.setFinishTime(DateUtils.date2Str(order.getFinishTime()));
		vo.setCloseTime(DateUtils.date2Str(order.getCloseTime()));
		vo.setCreated(DateUtils.date2Str(order.getCreated()));
		vo.setOrderItems(actionOrderItemDao.findOrderItemsByOrderNo(vo.getOrderNo()));
		vo.setAddrId(order.getAddrId());
		vo.setAddress(hasAddress?actionAddressDao.findAddressById(vo.getAddrId()):null);
		vo.setDeliveryName(actionAddressDao.findAddressById(vo.getAddrId()).getName());
		return vo;
	}
	
	private ActionOrderItemVo transToItemVo(ActionOrderItem item) {
		ActionOrderItemVo vo = new ActionOrderItemVo();
		vo.setOrderNo(item.getOrderNo());
		vo.setGoodsId(item.getGoodsId());
		vo.setGoodsName(item.getGoodsName());
		vo.setIconUrl(item.getIconUrl());
		vo.setCurPrice(item.getPrice());
		vo.setQuantity(item.getQuantity());
		vo.setTotalPrice(item.getTotalPrice());
		vo.setCreated(null);
		return vo;
	}
	
	//封装订单Vo
	private ActionOrderVo createOrderVoP(ActionOrder order, List<ActionOrderItem> orderItems) {
		ActionOrderVo orderVo = new ActionOrderVo();
		List<ActionOrderItemVo> items = Lists.newArrayList();
		orderVo = transToVo(order, false);
		for (ActionOrderItem i:orderItems) {
			items.add(transToItemVo(i));
		}
		orderVo.setOrderItems(items);
		return orderVo;
	}

	@Override
	public SverResponse<PageBean<ActionOrderVo>> searchOrder(Integer pageNum, Integer pageSize, Long orderNo) {
		// TODO Auto-generated method stub
		if (orderNo==null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
		List<ActionOrder> orders = actionOrderDao.findOrdersNoPage(orderNo);
		PageBean<ActionOrderVo> pageBean = new PageBean<>(pageNum, pageSize, orders.size());
		List<ActionOrder> lists = actionOrderDao.searchOrders(pageBean.getStartIndex(), pageSize, orderNo);
		List<ActionOrderVo> oVos = Lists.newArrayList();
		for(ActionOrder order:lists) {
			oVos.add(transToVo(order,true));
		}
		pageBean.setData(oVos);
		return SverResponse.createRespBySuccess(pageBean);
	}

	@Override
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
		List<ActionOrder> orders = actionOrderDao.findOrdersNoPage(null);
		PageBean<ActionOrderVo> pageBean = new PageBean<>(pageNum, pageSize, orders.size());
		List<ActionOrder> lists = actionOrderDao.findOrders(pageBean.getStartIndex(), pageSize);
		List<ActionOrderVo> oVos = Lists.newArrayList();
		for(ActionOrder order:lists) {
			oVos.add(transToVo(order,false));
		}
		pageBean.setData(oVos);
		return SverResponse.createRespBySuccess(pageBean);
	}

	@Override
	public SverResponse<ActionOrderVo> getDetail(Long orderNo) {
		// TODO Auto-generated method stub
		if (orderNo==null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		List<ActionOrder> orders = actionOrderDao.findOrdersNoPage(orderNo);
		return SverResponse.createRespBySuccess(transToVo(orders.get(0),true));
	}
	
	@Override
	public SverResponse<PageBean<ActionOrderVo>> findOrdersP(Integer uid, Integer status, int pageNum, int pageSize) {
		//判断uid是否为空
		if(uid == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//查找符合条件的总记录数
		int totalRecord = actionOrderDao.getTotalRecordP(uid,status);
		//创建分页封装对象
		PageBean<ActionOrderVo> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
		//读取数据
		List<ActionOrder> orders = actionOrderDao.findOrdersP(uid,status,pageBean.getStartIndex(),pageSize);
		//封装vo
		List<ActionOrderVo> voList =Lists.newArrayList();
		for(ActionOrder order:orders) {
			voList.add(transToVo(order,false));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	
	@Override
	public SverResponse<String> cancelOrderP(Integer uid, Long orderNo) {
		//查询订单
		ActionOrder order = actionOrderDao.findOrderByUserAndOrderNoP(uid,orderNo);
		//判断订单是否存在
		if(order == null) {
			return SverResponse.createByErrorMessage("该用户订单不存在，或已删除！");
		}
		//判断订单是否已经付款
		if(order.getStatus() == ConstUtil.OrderStatus.ORDER_PAID) {
			return SverResponse.createByErrorMessage("该订单已经付款，无法取消！");
		}
		//判断状态修改订单信息
		ActionOrder updateOrder = new ActionOrder();
		updateOrder.setId(order.getId());
		updateOrder.setUpdated(new Date());
		if(order.getStatus() == 1) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
			int row = actionOrderDao.updateOrderP(updateOrder);
			if(row > 0) {
				return SverResponse.createRespBySuccessMessage("订单已经取消！");
			}
		}
		if(order.getStatus() == 3) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
			int row = actionOrderDao.updateOrderP(updateOrder);
			if(row > 0) {
				return SverResponse.createRespBySuccessMessage("订单已经确认收货！");
			}
		}
		return SverResponse.createByErrorMessage("失败！");
	}

	@Override
	public SverResponse<ActionOrderVo> findOrderDetailP(Integer uid, Long orderNo) {
		//判断参数是否正确
		if(uid == null || orderNo == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//查找订单，封装
		ActionOrder order = actionOrderDao.findOrderByUserAndOrderNoP(uid, orderNo);
		if(order == null) {
			return SverResponse.createByErrorMessage("该用户订单不存在，或已删除！");
		}
		ActionOrderVo orderVo = transToVo(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SverResponse<ActionOrderVo> generateOrderP(Integer uid, Integer addrId) {
		//提取购物车中商品信息
		List<ActionCart> carts = actionCartDao.findCartByUserP(uid);
		//计算购物车中每件商品总价格并生成订单项
		SverResponse resp = this.cart2OrderItemP(uid,carts);
		if(!resp.isSuccess()) {
			return resp;
		}
		//取出订单项中的价格计算订单总价格
		List<ActionOrderItem> orderItems = (List<ActionOrderItem>) resp.getData();
		BigDecimal totalPrice = this.calcOrderTotalPriceP(orderItems);
		//生成订单，插入数据
		ActionOrder order =saveOrderP(uid,addrId,totalPrice);
		if(order == null) {
			return SverResponse.createByErrorMessage("订单产生错误，请检查后重新提交！");
		}
		if(CollectionUtils.isEmpty(orderItems)) {
			return SverResponse.createByErrorMessage("订单项为空，请选择要购买的商品！");
		}
		//批量插入订单项
		for(ActionOrderItem orderItem:orderItems) {
			//为订单项设置订单主键
			orderItem.setOrderNo(order.getOrderNo());
		}
		actionOrderItemDao.batchInsertP(orderItems);
		//减少商品表中库存
		for(ActionOrderItem orderItem:orderItems) {
			ActionProduct product = actionProductDao.findProductByIdP(orderItem.getGoodsId());
			//减少库存
			product.setStock(product.getStock() - orderItem.getQuantity());
			product.setUpdated(new Date());
			//更新库存
			actionProductDao.updateProductP(product);
		}	
		//清空购物车
		actionProductDao.deleteCartProductP(uid);
		//封装返回前端
		ActionOrderVo orderVo = createOrderVoP(order, orderItems);
		return SverResponse.createRespBySuccess(orderVo);
	}
	

	/**
	 * 保存订单
	 * @param uid
	 * @param addrId
	 * @param totalPrice
	 * @return
	 */
	private ActionOrder saveOrderP(Integer uid, Integer addrId, BigDecimal totalPrice) {
		ActionOrder order = new ActionOrder();
		//生成订单号
		long currentTime = System.currentTimeMillis();
		Long orderNo = currentTime + new Random().nextInt(100);
		order.setOrderNo(orderNo);
		order.setStatus(ConstUtil.OrderStatus.ORDER_NO_PAY);//默认未付款
		order.setType(ConstUtil.PaymentType.PAY_ON_LINE); //在线支付
		order.setFreight(0);
		order.setAmount(totalPrice);
		order.setAddrId(addrId);
		order.setUid(uid);
		order.setUpdated(new Date());
		order.setCreated(new Date());
		//插入订单
		int rs = actionOrderDao.insertOrderP(order);
		if(rs > 0) {
			return order;
		}
		return null;
	}
	
	/**
	 * 计算订单总价格
	 * @param orderItems
	 * @return
	 */
	private BigDecimal calcOrderTotalPriceP(List<ActionOrderItem> orderItems) {
		BigDecimal totalPrice = new BigDecimal("0");
		for(ActionOrderItem item:orderItems) {
			totalPrice = CalcUtil.add(totalPrice.doubleValue(),item.getTotalPrice().doubleValue());
		}
		return totalPrice;
	}

	/**
	 * 将购物车中商品封装为订单项
	 * @param uid
	 * @param carts
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private SverResponse cart2OrderItemP(Integer uid, List<ActionCart> carts) {
		List<ActionOrderItem> items = Lists.newArrayList();
		//判断购物车是否为空
		if(CollectionUtils.isEmpty(carts)) {
			return SverResponse.createByErrorMessage("购物车为空，请选择要购买的商品");
		}
		for(ActionCart cart:carts) {
			//查看购物车的商品状态
			ActionProduct product = actionProductDao.findProductByIdP(cart.getProductId());
			//查看商品状态
			if(ConstUtil.ProductStatus.STATUS_ON_SALE != product.getStatus()) {
				//如果商品不是上架 在售 ，则返回提示信息
				return SverResponse.createByErrorMessage("商品"+product.getName() +"已经下架，不能在线购买！");
			}
			//查看库存
			if(cart.getQuantity() >product.getStock()) {
				return SverResponse.createByErrorMessage("商品"+product.getName() +"库存不足！");
			}
			//封装订单项
			ActionOrderItem orderItem = new ActionOrderItem();
			orderItem.setUid(uid);
			orderItem.setGoodsName(product.getName());
			orderItem.setGoodsId(product.getId());
			orderItem.setIconUrl(product.getIconUrl());
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(cart.getQuantity());
			orderItem.setTotalPrice(CalcUtil.mul(orderItem.getPrice().doubleValue(), orderItem.getQuantity().doubleValue()));
			orderItem.setCreated(new Date());
			orderItem.setUpdated(new Date());
			items.add(orderItem);
		}
		return SverResponse.createRespBySuccess(items);
	}

	@Override
	public SverResponse<String> payOrder(Long orderNo) {
		// TODO Auto-generated method stub
		if (orderNo==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		ActionOrder o = actionOrderDao.findOrdersNoPage(orderNo).get(0);
		o.setStatus(2);
		if (actionOrderDao.updateOrderP(o)!=0) {
			return SverResponse.createRespBySuccess("付款成功!");
		}
		return SverResponse.createByErrorMessage("支付失败！");
	}
}
