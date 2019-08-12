package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionProduct;

public interface ActionProductDao {
	/**
	 * 多条件查询商品信息
	 * @param condition
	 * @return
	 */
	public List<ActionProduct> findProductsNoPage(ActionProduct condition);
	/**
	 * 新增商品
	 * @param product
	 * @return
	 */
	public int insertProduct(ActionProduct product);
	/**
	 * 更新商品
	 * @param product
	 * @return
	 */
	public int updateProduct(ActionProduct product);
	/**
	 * 多条件查询商品信息(分页）
	 * @param condition
	 * @return
	 */
	public List<ActionProduct> findProductsByPage(Integer offset,Integer limit,ActionProduct condition);
	/**
	 * 查找热门商品
	 * @param num
	 * @return
	 */
	public List<ActionProduct> findHotProductsP(Integer num);
	/**
	 * 根据产品类型查询商品信息
	 * @param typeHntjx
	 * @return
	 */
	public List<ActionProduct> findProductsByProductCategoryP(Integer categoryId);
	/**
	 * 根据商品编号查询商品信息
	 * @param productId
	 * @return
	 */
	public ActionProduct findProductByIdP(Integer id);
	/**
	 * 根据条件查询总记录数
	 * @param product
	 * @return
	 */
	public Integer getTotalCountP(ActionProduct product);
	/**
	 * 根据条件分页查询
	 * @param product
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ActionProduct> findProductsP(ActionProduct product, int startIndex, int pageSize);
	/**
	 * 更新商品信息
	 * @param product
	 */
	public int updateProductP(ActionProduct product);
	/**
	 * 删除某个用户购物车中所有商品
	 * @param uid
	 */
	public int deleteCartProductP(Integer uid);
}
