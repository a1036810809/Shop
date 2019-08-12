package cn.techaction.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.techaction.dao.ActionParamsDao;
import cn.techaction.pojo.ActionParam;

@Repository
public class ActionParamsDaoImpl implements ActionParamsDao {
	@Resource
	private QueryRunner queryRunner;
	
	@Override
	public ActionParam findParamById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select id,parent_id,name,status,sort_order,level,created,updated from action_params where id=?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionParam>(ActionParam.class),id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ActionParam> findChildrenByParentId(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select id,parent_id,name,status,sort_order,level,created,updated from action_params where parent_id=? order by sort_order";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionParam>(ActionParam.class),id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int delParam(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from action_params where id=?";
		try {
			return queryRunner.update(sql,id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateParam(String name, Integer id) {
		// TODO Auto-generated method stub
		String sql = "update action_params set name=?,updated=? where id=?";
		List<Object> param=new ArrayList<>();
		param.add(name);
		param.add(new Date());
		param.add(id);
		try {
			return queryRunner.update(sql,param.toArray());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveParam(ActionParam param) {
		// TODO Auto-generated method stub
		String sql = "insert into action_params("+
				"parent_id,name,sort_order,status,level,created,updated) values(?,?,?,?,?,?,?)";
		List<Object> para=new ArrayList<>();
		para.add(param.getParent_id());
		para.add(param.getName());
		para.add(param.getSort_order());
		para.add(param.isStatus());
		para.add(param.getLevel());
		para.add(param.getCreated());
		para.add(param.getUpdated());
		try {
			return queryRunner.update(sql,para.toArray());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
