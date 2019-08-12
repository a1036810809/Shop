package cn.techaction.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionProduct;

@Repository
public class ActionProductDaoImpl implements ActionProductDao {
	@Resource
	private QueryRunner queryRunner;
	private String str = "id," + 
			"name," + 
			"product_id as productId," + 
			"parts_id as partsId," + 
			"icon_url as iconUrl," + 
			"sub_images as subImages," + 
			"detail," + 
			"spec_param as specParam," + 
			"price," + 
			"stock," + 
			"status," + 
			"is_hot as hot," + 
			"created," + 
			"updated";
	@Override
	public List<ActionProduct> findProductsNoPage(ActionProduct condition) {
		// TODO Auto-generated method stub
		String sql = "select " + str + " from action_products where 1=1 ";
		List<Object> params = new ArrayList<>();
		if (condition.getId()!=null) {
			sql += " and id=? ";
			params.add(condition.getId());
		}
		if (condition.getName()!=null) {
			sql += " and name like ? ";
			params.add(condition.getName());
		}
		if (condition.getStatus()!=null) {
			sql += " and status=? ";
			params.add(condition.getStatus());
		}
		if (condition.getProductId()!=null) {
			sql += " and product_id=? ";
			params.add(condition.getProductId());
		}
		if (condition.getPartsId()!=null) {
			sql += " and parts_id=? ";
			params.add(condition.getPartsId());
		}
		sql += "order by created,id desc";
		try {
			System.out.println(sql);
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class),params.toArray());
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public int insertProduct(ActionProduct product) {
		// TODO Auto-generated method stub
		String sql = "insert into action_products("+
				"name," + 
				"product_id," + 
				"parts_id," + 
				"icon_url," + 
				"sub_images," + 
				"detail," + 
				"spec_param," + 
				"price," + 
				"stock," + 
				"status," + 
				"is_hot," + 
				"created," + 
				"updated" +	
				") values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(product.getName());
		params.add(product.getProductId());
		params.add(product.getPartsId());
		params.add(product.getIconUrl());
		params.add(product.getSubImages());
		params.add(product.getDetail());
		params.add(product.getSpecParam());
		params.add(product.getPrice());
		params.add(product.getStock());
		params.add(product.getStatus());
		params.add(product.getHot());
		params.add(product.getCreated());
		params.add(product.getUpdated());
		try {
			return queryRunner.update(sql,params.toArray());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int updateProduct(ActionProduct product) {
		// TODO Auto-generated method stub
		String sql = "update action_products set ";
		List<Object> params = new ArrayList<>();
		if(product.getName()!=null) {
			sql += "name=?,";
			params.add(product.getName());
		}
		if(product.getProductId()!=null) {
			sql += "product_id=?,";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql += "parts_id=?,";
			params.add(product.getPartsId());
		}
		if(product.getIconUrl()!=null) {
			sql += "icon_url=?,";
			params.add(product.getIconUrl());
		}
		if(product.getSubImages()!=null) {
			sql += "sub_images=?,";
			params.add(product.getSubImages());
		}
		
		if(product.getDetail()!=null) {
			sql += "detail=?,";
			params.add(product.getDetail());
		}
		
		if(product.getSpecParam()!=null) {
			sql += "spec_param=?,";
			params.add(product.getSpecParam());
		}
		if(product.getPrice()!=null) {
			sql += "price=?,";
			params.add(product.getPrice());
		}
		if(product.getStock()!=null) {
			sql += "stock=?,";
			params.add(product.getStock());
		}
		if(product.getStatus()!=null) {
			sql += "status=?,";
			params.add(product.getStatus());
		}
		if(product.getHot()!=null) {
			sql += "is_hot=?,";
			params.add(product.getHot());
		}
		if(product.getCreated()!=null) {
			sql += "created=?,";
			params.add(product.getCreated());
		}
		sql += "updated=? where id=?";
		params.add(product.getUpdated());
		params.add(product.getId());
		try {
			return queryRunner.update(sql,params.toArray());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<ActionProduct> findProductsByPage(Integer offset,Integer limit,ActionProduct condition) {
		// TODO Auto-generated method stub
		String sql = "select " + str + " from action_products where 1=1 ";
		List<Object> params = new ArrayList<>();
		if (condition.getId()!=null) {
			sql += " and id=? ";
			params.add(condition.getId());
		}
		if (condition.getName()!=null) {
			sql += " and name like ? ";
			params.add(condition.getName());
		}
		if (condition.getStatus()!=null) {
			sql += " and status=? ";
			params.add(condition.getStatus());
		}
		if (condition.getProductId()!=null) {
			sql += " and product_id=? ";
			params.add(condition.getProductId());
		}
		if (condition.getPartsId()!=null) {
			sql += " and parts_id=? ";
			params.add(condition.getPartsId());
		}
		sql += "order by created,id desc limit ?,?";
		params.add(offset);
		params.add(limit);
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class),params.toArray());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<ActionProduct> findHotProductsP(Integer num) {
		String sql = "select "+ this.str +" from action_products where is_hot=1 and status=2 order by updated,id desc";
		if(num !=null) {
			sql+=" limit 0,?";
		}
		try {
			if(num!=null) {
				return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class), num);
			}else {
				return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public List<ActionProduct> findProductsByProductCategoryP(Integer categoryId) {
		String sql = "select " +this.str+ " from action_products where product_id = ? and status=2 order by updated desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class), categoryId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public ActionProduct findProductByIdP(Integer id) {
		String sql = "select " +this.str+ " from action_products where id = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionProduct>(ActionProduct.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public Integer getTotalCountP(ActionProduct product) {
		String sql ="select count(id) as num from action_products where 1=1 ";
		List<Object> params = new ArrayList<>();
		if(product.getId()!=null) {
			sql+=" and id = ?";
			params.add(product.getId());
		}
		if(product.getName()!=null) {
			sql+=" and name like ?";
			params.add("%"+product.getName()+"%");
		}
		if(product.getStatus()!=null) {
			sql+=" and status = ?";
			params.add(product.getStatus());
		}
		if(product.getProductId()!=null) {
			sql+=" and product_id = ?";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql+=" and parts_id = ?";
			params.add(product.getPartsId());
		}
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"), params.toArray()).get(0).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ActionProduct> findProductsP(ActionProduct product, int startIndex, int pageSize) {
		String sql = "select " +this.str+ " from action_products where 1=1";
		List<Object> params = new ArrayList<>();
		if(product.getId()!=null) {
			sql+=" and id = ?";
			params.add(product.getId());
		}
		if(product.getName()!=null) {
			sql+=" and name like ?";
			params.add("%"+product.getName()+"%");
		}
		if(product.getStatus()!=null) {
			sql+=" and status = ?";
			params.add(product.getStatus());
		}
		if(product.getProductId()!=null) {
			sql+=" and product_id = ?";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql+=" and parts_id = ?";
			params.add(product.getPartsId());
		}
		sql+=" limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class), params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int updateProductP(ActionProduct product) {
		String sql = "update action_products set updated = ?";
		List<Object> params = new ArrayList<>();
		params.add(product.getUpdated());
		if(!StringUtils.isEmpty(product.getName())) {
			sql +=",name = ?";
			params.add(product.getName());
		}
		if(product.getProductId()!=null) {
			sql +=",product_id=?";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql +=",parts_id=?";
			params.add(product.getPartsId());
		}
		if(product.getPrice()!=null) {
			sql +=",price = ?";
			params.add(product.getPrice());
		}
		if(product.getStock()!=null) {
			sql +=",stock = ?";
			params.add(product.getStock());
		}
		if(!StringUtils.isEmpty(product.getIconUrl())) {
			sql +=",icon_url = ?";
			params.add(product.getIconUrl());
		}
		if(!StringUtils.isEmpty(product.getSubImages())) {
			sql +=",sub_images = ?";
			params.add(product.getSubImages());
		}
		if(product.getStatus()!=null) {
			sql +=",status = ?";
			params.add(product.getStatus());
		}
		if(!StringUtils.isEmpty(product.getDetail())) {
			sql +=",detail = ?";
			params.add(product.getDetail());
		}
		if(!StringUtils.isEmpty(product.getSpecParam())) {
			sql +=",spec_param = ?";
			params.add(product.getSpecParam());
		}
		if(product.getHot()!=null) {
			sql +=",is_hot = ?";
			params.add(product.getHot());
		}
		sql+= " where id = ?";
		params.add(product.getId());
		
		try {
			return queryRunner.update(sql,params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteCartProductP(Integer uid) {
		String sql = "delete from action_carts where id = ?";
		try {
			return queryRunner.update(sql,uid);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
}
