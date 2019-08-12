package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionOrder;

public interface ActionOrderDao {
	/**
	 * 根据用户id获得订单信息
	 * @param uid
	 * @return
	 */
	public List<ActionOrder> findOrderByUid(Integer uid);
	/**
	 * 根据订单号查询订单
	 * @param orderNo
	 * @return
	 */
	public List<ActionOrder> findOrdersNoPage(Long orderNo);
	/**
	 * 分页查找
	 * @param offset
	 * @param limit
	 * @param orderNo
	 * @return
	 */
	public List<ActionOrder> searchOrders(Integer offset,Integer limit,Long orderNo);
	/**
	 * 分页获取订单列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<ActionOrder> findOrders(Integer offset,Integer limit);
	/**
	 * 获取用户订单总数（各种状态下的）
	 * @param uid
	 * @param status
	 * @return
	 */
    public int getTotalRecordP(Integer uid, Integer status);
    /**
     * 获取用户订单分页列表
     * @param uid
     * @param status
     * @param startIndex
     * @param pageSize
     */
	public List<ActionOrder> findOrdersP(Integer uid, Integer status, int startIndex, int pageSize);
	/**
	 * 根据用户和订单编号查询订单信息
	 * @param uid
	 * @param orderNo
	 * @return
	 */
	public ActionOrder findOrderByUserAndOrderNoP(Integer uid, Long orderNo);
	/**
	 * 更新订单信息
	 * @param updateOrder
	 * @return
	 */
	public int updateOrderP(ActionOrder updateOrder);
	/**
	 * 保存订单信息
	 * @param order
	 * @return
	 */
	public int insertOrderP(ActionOrder order);
}
