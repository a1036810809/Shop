package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionOrderVo;

public interface ActionOrderService {
	/**
	 * 获取订单
	 * @param orderNo
	 * @return
	 */
	public SverResponse<List<ActionOrderVo>> findOrdersNoPage(Long orderNo);
	/**
	 * 查询订单
	 * @param pageNum
	 * @param pageSize
	 * @param orderNo
	 * @return
	 */
	public SverResponse<PageBean<ActionOrderVo>> searchOrder(Integer pageNum,Integer pageSize,Long orderNo);
	/**
	 * 获取订单列表（分页）
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer pageNum,Integer pageSize);
	/**
	 * 获取订单详情
	 * @param orderNo
	 * @return
	 */
	public SverResponse<ActionOrderVo> getDetail(Long orderNo);
	/**
	 * 订单分页列表
	 * @param id
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionOrderVo>> findOrdersP(Integer uid, Integer status, int pageNum, int pageSize);
	/**
	 * 取消订单
	 * @param id
	 * @param orderNo
	 * @return
	 */
	public SverResponse<String> cancelOrderP(Integer uid, Long orderNo);
	/**
	 * 根据编号获取订单详情
	 * @param id
	 * @param orderNo
	 * @return
	 */
	public SverResponse<ActionOrderVo> findOrderDetailP(Integer uid, Long orderNo);
	/**
	 * 生成订单
	 * @param id
	 * @param addrId
	 * @return
	 */
	public SverResponse<ActionOrderVo> generateOrderP(Integer uid, Integer addrId);
	/**
	 * 付款
	 * @param orderNo
	 * @return
	 */
	public SverResponse<String> payOrder(Long orderNo);
}
