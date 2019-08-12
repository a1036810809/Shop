package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionOrderItem;
import cn.techaction.vo.ActionOrderItemVo;


public interface ActionOrderItemDao {
	/**
	 * 根据订单号查询订单子项
	 * @param orderNo
	 * @return
	 */
	public List<ActionOrderItemVo> findOrderItemsByOrderNo(Long orderNo);
	/**
	 * 根据订单号获得订单项
	 * @param orderNo
	 * @return
	 */
	public List<ActionOrderItem> getItemsByOrderNoP(Long orderNo);
	/**
	 * 批量插入订单项
	 * @param orderItems
	 */
	public int[] batchInsertP(List<ActionOrderItem> orderItems);
}
