package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductFloorVo;
import cn.techaction.vo.ActionProductListVo;

public interface ActionProductService {
	/**
	 * 多条件查询
	 * @param product
	 * @return
	 */
	public SverResponse<List<ActionProductListVo>> findProducts(ActionProduct product);
	/**
	 * 保存商品信息(新增、修改)
	 * @param product
	 * @return
	 */
	public SverResponse<String> saveOrUpdateProduct(ActionProduct product);
	/**
	 * 更新商品状态：上下架、是否热销
	 * @param productId
	 * @param status
	 * @param hot
	 * @return
	 */
	public SverResponse<String> updateStatus(Integer productId,Integer status,Integer hot);
	/**
	 * 通过id获取产品详细信息
	 * @param productId
	 * @return
	 */
	public SverResponse<ActionProduct> findProductDetailById(Integer productId);
	/**
	 * 分页查询产品
	 * @param pageNum
	 * @param pageSize
	 * @param product
	 * @return
	 */
	public SverResponse<PageBean<ActionProductListVo>> findProductsByPage(Integer pageNum,Integer pageSize,ActionProduct product);
	/**
	 * 门户：查找热门商品
	 * @param num 查找的数量
	 * @return
	 */
	public SverResponse<List<ActionProduct>> findHotProductsP(Integer num);
	/**
	 * 门户：获得首页所有楼层数据
	 * @return
	 */
	public SverResponse<ActionProductFloorVo> findFloorProductsP();
	/**
	 * 门户：根据商品编号查找商品信息
	 * @param productId
	 * @return
	 */
	public SverResponse<ActionProduct> findProductDetailForPortalP(Integer productId);
	/**
	 * 门户：根据产品类型和配件类型查找商品信息（模糊查询）
	 * @param productTypeId
	 * @param partsId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionProductListVo>> findProductsForProtalP(Integer productTypeId, Integer partsId,
			String name, int pageNum, int pageSize);
}
