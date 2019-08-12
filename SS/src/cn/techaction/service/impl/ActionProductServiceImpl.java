package cn.techaction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionParamsDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionProductService;
import cn.techaction.utils.ConstUtil;
import cn.techaction.utils.PageBean;
import cn.techaction.vo.ActionProductFloorVo;
import cn.techaction.vo.ActionProductListVo;

@Service
public class ActionProductServiceImpl implements ActionProductService {
	@Autowired
	private ActionProductDao actionProductDao;
	@Autowired
	private ActionParamsDao actionParamsDao;
	
	public SverResponse<List<ActionProductListVo>> findProducts(ActionProduct product){
		if (product.getName()!=null) {
			product.setName("%"+product.getName()+"%");
		}
		//调用DAO
		List<ActionProduct> products = actionProductDao.findProductsNoPage(product);
		//将ActionProduct转换为业务实体对象
		List<ActionProductListVo> voList = Lists.newArrayList();
		for (ActionProduct p:products) {
			voList.add(transProductListVo(p));
		}
		return SverResponse.createRespBySuccess(voList);
	}
	/**
	 * 封装Vo对象
	 * @param product
	 * @return
	 */
	private ActionProductListVo transProductListVo(ActionProduct product) {
		ActionProductListVo vo = new ActionProductListVo();
		vo.setId(product.getId());
		vo.setName(product.getName());
		vo.setPrice(product.getPrice());
		vo.setStatus(product.getStatus());
		vo.setIconUrl(product.getIconUrl());
		vo.setHot(product.getHot());
		//特殊的
		vo.setStatusDesc(ConstUtil.ProductStatus.getStatusDesc(vo.getStatus()));
		vo.setHotStatus(ConstUtil.HotStatus.getHotDesc(vo.getHot()));
		vo.setProductCategory(actionParamsDao.findParamById(product.getProductId()).getName());
		vo.setPartsCategory(actionParamsDao.findParamById(product.getPartsId()).getName());
		return vo;
	}

	@Override
	public SverResponse<String> saveOrUpdateProduct(ActionProduct product) {
		// TODO Auto-generated method stub
		if (product==null) {
			return SverResponse.createByErrorMessage("对产品操作失败！");
		}
		//处理主图和子图的连接，从前端传递过来的图链接放在subImages里面
		//从第一个连接作为主图，其他作为子图】
		if (!StringUtils.isEmpty(product.getSubImages())) {
			String[] array = product.getSubImages().split(",");
			//选择第一个为主图
			product.setIconUrl(array[0]);
			String temp = product.getSubImages();
			int index = temp.indexOf(",");
			if (index!=-1) {
				product.setSubImages(temp.substring(index+1));
			}else {
				product.setSubImages(null);
			}
		}
		if (product.getId()!=null) {
			product.setUpdated(new Date());
			//调用Dao的修改方法
			int rs = actionProductDao.updateProduct(product);
			if (rs>0) {
				return SverResponse.createRespBySuccessMessage("对产品更新成功！");
			}
			return SverResponse.createByErrorMessage("对产品更新失败！");
		}else {
			product.setStatus(ConstUtil.ProductStatus.STATUS_NEW);
			product.setHot(ConstUtil.HotStatus.NORMAL_STATUS);
			product.setCreated(new Date());
			product.setUpdated(new Date());
			//调用dao的新增方法
			int rs = actionProductDao.insertProduct(product);
			if (rs>0) {
				return SverResponse.createRespBySuccessMessage("对产品新增成功！");
			}
			return SverResponse.createByErrorMessage("对产品新增失败！");
		}
	}

	@Override
	public SverResponse<String> updateStatus(Integer productId, Integer status, Integer hot) {
		// TODO Auto-generated method stub
		if (productId == null || status == null || hot == null) {
			return SverResponse.createByErrorMessage("参数非法！");
		}
		ActionProduct product = new ActionProduct();
		product.setId(productId);
		product.setUpdated(new Date());
		//判断修改什么
		if (status==-1) {
			product.setHot(hot);
		}else if(hot==-1) {
			product.setStatus(status);
		}
		//调用dao的修改方法
		int rs = actionProductDao.updateProduct(product);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("修改商品状态成功！");
		}
		return SverResponse.createByErrorMessage("修改商状态失败！");
	}
	
	@Override
	public SverResponse<ActionProduct> findProductDetailById(Integer productId) {
		// TODO Auto-generated method stub
		if (productId==null) {
			return SverResponse.createByErrorMessage("参数非法！");
		}
		ActionProduct product = new ActionProduct();
		product.setId(productId);
		List<ActionProduct> products = actionProductDao.findProductsNoPage(product);
		if (products!=null&&products.size()>0) {
			if (products.get(0).getStatus()==ConstUtil.ProductStatus.STATUS_OFF_SALE) {
				return SverResponse.createByErrorMessage("商品已经下架！");
			}
			return SverResponse.createRespBySuccess(products.get(0));
		}
		return SverResponse.createByErrorMessage("商品不存在！");
	}
	@Override
	public SverResponse<PageBean<ActionProductListVo>> findProductsByPage(Integer pageNum, Integer pageSize,
			ActionProduct product) {
		// TODO Auto-generated method stub
		if (product.getName()!=null) {
			product.setName("%"+product.getName()+"%");
		}
		pageNum = pageNum==null ? 1 : pageNum;
		pageSize = pageSize==null ? 10 : pageSize;
		int total = actionProductDao.findProductsNoPage(product).size();
		PageBean<ActionProductListVo> pageBean = new PageBean<ActionProductListVo>(pageNum, pageSize, total);
		List<ActionProduct> products = actionProductDao.findProductsByPage(pageBean.getStartIndex(), pageSize, product);
		List<ActionProductListVo> voList = Lists.newArrayList();
		for (ActionProduct p:products) {
			voList.add(transProductListVo(p));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	
	@Override
	public SverResponse<List<ActionProduct>> findHotProductsP(Integer num) {
		//直接查询所需数据
		List<ActionProduct> products = actionProductDao.findHotProductsP(num);
		return SverResponse.createRespBySuccess(products);
	}

	@Override
	public SverResponse<ActionProductFloorVo> findFloorProductsP() {
		//创建vo对象
		ActionProductFloorVo vo = new ActionProductFloorVo();
		//1楼数据
		List<ActionProduct> products1 = actionProductDao.findProductsByProductCategoryP(ConstUtil.ProductType.TYPE_HNTJX);
		vo.setOneFloor(products1);
		//2楼数据
		List<ActionProduct> products2 = actionProductDao.findProductsByProductCategoryP(ConstUtil.ProductType.TYPE_JZQZJJX);
		vo.setTwoFloor(products2);
		//3楼数据
		List<ActionProduct> products3 = actionProductDao.findProductsByProductCategoryP(ConstUtil.ProductType.TYPE_GCQZJJX);
		vo.setThreeFloor(products3);
		//4楼数据
		List<ActionProduct> products4 = actionProductDao.findProductsByProductCategoryP(ConstUtil.ProductType.TYPE_LMJX);
		vo.setFourFloor(products4);
		return SverResponse.createRespBySuccess(vo);
	}

	@Override
	public SverResponse<ActionProduct> findProductDetailForPortalP(Integer productId) {
		//判断产品编号是否为空
		if(productId ==null) {
			return SverResponse.createByErrorMessage("产品编号不能为空");
		}
		//查询商品详情
		ActionProduct product = actionProductDao.findProductByIdP(productId);
		//判断产品是否下架
		if(product==null) {
			return SverResponse.createByErrorMessage("产品已经下架！");
		}
		if(product.getStatus()==ConstUtil.ProductStatus.STATUS_OFF_SALE) {
			return SverResponse.createByErrorMessage("产品已经下架！");
		}
		return SverResponse.createRespBySuccess(product);
	}

	@Override
	public SverResponse<PageBean<ActionProductListVo>> findProductsForProtalP(Integer productTypeId, Integer partsId,
			String name, int pageNum, int pageSize) {
		//创建对象
		ActionProduct product = new ActionProduct();
		int totalRecord = 0;
		//判断name是否为空
		if(name !=null && !name.equals("")) {
			product.setName(name);
		}
		if(productTypeId!=null) {
			product.setProductId(productTypeId);
		}
		if(partsId!=null) {
			product.setPartsId(partsId);;
		}
		//前端显示商品都为在售
		product.setStatus(2);
		//查找符合条件的总记录数
		totalRecord = actionProductDao.getTotalCountP(product);
		//创建分页对象
		PageBean<ActionProductListVo> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
		//读取数据 
		List<ActionProduct> products = actionProductDao.findProductsP(product,pageBean.getStartIndex(),pageSize);
		//封装vo
		List<ActionProductListVo> voList = Lists.newArrayList();
		for(ActionProduct p:products) {
			voList.add(transProductListVo(p));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
}
