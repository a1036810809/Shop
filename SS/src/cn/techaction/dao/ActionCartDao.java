package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionCart;

public interface ActionCartDao {
	/**
	 * 查找用户购物车中的商品信息
	 * @param uid
	 * @return
	 */
	public List<ActionCart> findCartByUserP(Integer uid);
	/**
	 * 根据用户id和产品id查询购物车
	 * @param userId
	 * @param productId
	 * @return
	 */
	public ActionCart findCartByUserAndProductIdP(Integer userId, Integer productId);
	/**
	 * 保存购物车 
	 * @param cart
	 */
	public int insertCartP(ActionCart cart);
	/**
	 * 更新购物车中的商品数量
	 * @param actionCart
	 * @return
	 */
	public int updateCartByIdP(ActionCart actionCart);
	/**
	 * 删除某个用户购物车当中的所有商品
	 * @param userId
	 * @return
	 */
	public int deleteCartByUserIdP(Integer userId);
	/**
	 * 更新购物车中商品数量
	 * @param actionCart
	 * @return
	 */
	public int updateCartByUserIdAndProductIdP(ActionCart actionCart);
	/**
	 *	删除购物车中的商品信息
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int deleteCartsP(Integer userId, Integer productId);
	/**
	 * 获取当前用户购物车中的商品数量
	 * @param userId
	 * @return
	 */
	public int getCartCountByUserIdP(Integer userId);

}
