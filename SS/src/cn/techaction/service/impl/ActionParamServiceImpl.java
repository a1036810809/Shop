package cn.techaction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionParamsDao;
import cn.techaction.dao.ActionProductDao;
import cn.techaction.pojo.ActionParam;
import cn.techaction.pojo.ActionProduct;
import cn.techaction.service.ActionParamService;
import cn.techaction.vo.ActionParamVo;

@Service
public class ActionParamServiceImpl implements ActionParamService {
	@Autowired
	private ActionParamsDao actionParamsDao;
	@Autowired
	private ActionProductDao actionProductDao;
	
	@Override
	public SverResponse<String> delParam(Integer id) {
		// TODO Auto-generated method stub
		if (actionParamsDao.findChildrenByParentId(id).size()>0) {
			return SverResponse.createByErrorMessage("请先删除子类型!");
		}
		ActionProduct pro = new ActionProduct();
		pro.setPartsId(id);
		if(actionProductDao.findProductsNoPage(pro).size()>0) {
			return SverResponse.createByErrorMessage("不能删除有商品的类型!");
		}
		int rs = actionParamsDao.delParam(id);
		if (rs>0) {
			return SverResponse.createRespBySuccess();
		}
		return SverResponse.createByErrorMessage("删除失败!");
	}
	
	@Override
	public SverResponse<List<ActionParamVo>> findPartsType(Integer id) {
		// TODO Auto-generated method stub
		List<ActionParamVo> pVo = findPartsTypeD(id);
		return SverResponse.createRespBySuccess(pVo);
	}
	
	private List<ActionParamVo> findPartsTypeD(Integer id){
		List<ActionParam> lists = actionParamsDao.findChildrenByParentId(id);
		List<ActionParamVo> pVo = Lists.newArrayList();
		for (ActionParam p:lists) {
			if (actionParamsDao.findChildrenByParentId(p.getId()).size()<=0) {
				pVo.add(transToParamVo(p));
			}else {
				pVo.addAll(findPartsTypeD(p.getId()));
			}
			
		}
		return pVo;
	}
	
	private ActionParamVo transToParamVo(ActionParam param) {
		ActionParamVo vo = new ActionParamVo();
		vo.setId(param.getId());
		vo.setName(param.getName());
		return vo;
	}

	@Override
	public SverResponse<List<ActionParam>> findParentType() {
		// TODO Auto-generated method stub
		return SverResponse.createRespBySuccess(actionParamsDao.findChildrenByParentId(0));
	}

	@Override
	public SverResponse<List<ActionParam>> findChildren(Integer id) {
		// TODO Auto-generated method stub
		return SverResponse.createRespBySuccess(actionParamsDao.findChildrenByParentId(id));
	}

	@Override
	public SverResponse<String> updateParam(String name, Integer id) {
		// TODO Auto-generated method stub
		if (name==null||id==null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		int rs = actionParamsDao.updateParam(name, id);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("产品参数修改成功!");
		}
		return SverResponse.createByErrorMessage("产品参数修改失败!");
	}

	@Override
	public SverResponse<String> saveParam(String name, Integer parent_id) {
		// TODO Auto-generated method stub
		if (name==null||parent_id==null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		ActionParam actionParam = new ActionParam();
		ActionParam parent = parent_id==0?new ActionParam():actionParamsDao.findParamById(parent_id);
		if (parent_id==0)
			parent.setLevel(-1);
		actionParam.setParent_id(parent_id);
		actionParam.setName(name);
		List<ActionParam> lists = actionParamsDao.findChildrenByParentId(parent_id);
		actionParam.setLevel(parent.getLevel()+1);
		actionParam.setStatus(true);
		actionParam.setSort_order(lists.size()+1);
		actionParam.setCreated(new Date());
		actionParam.setUpdated(new Date());
		int rs = actionParamsDao.saveParam(actionParam);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("产品新增成功!");
		}
		return SverResponse.createByErrorMessage("产品新增失败!");
	}
	
	@Override
	public SverResponse<List<ActionParam>> findAllParamsP() {
		//查找一级子节点
		List<ActionParam> paramList = actionParamsDao.findChildrenByParentId(0);
		//递归查询所有子节点
		for(ActionParam param : paramList) {
			findDirectChildrenP(param);
		}
		return SverResponse.createRespBySuccess(paramList);
	}
	//递归查找
	private void findDirectChildrenP(ActionParam parentParam) {
		//查找子节点
		List<ActionParam> paramList = actionParamsDao.findChildrenByParentId(parentParam.getId());
		parentParam.setChildren(paramList);
		for(ActionParam p:paramList) {
			findDirectChildrenP(p);
		}
	}

	@Override
	public SverResponse<List<ActionParam>> findPathParam() {
		// TODO Auto-generated method stub
		List<ActionParam> ans = actionParamsDao.findChildrenByParentId(0);
		List<ActionParam> res = Lists.newArrayList();
		for (ActionParam p:ans) {
			List<ActionParam> chilren = findParamByPath(p);
			res.add(p);
			if (chilren!=null) {
				res.addAll(chilren);
			}
		}
		return SverResponse.createRespBySuccess(res);
	}
	
	private List<ActionParam> findParamByPath(ActionParam p){
		List<ActionParam> ans = actionParamsDao.findChildrenByParentId(p.getId());
		List<ActionParam> res = Lists.newArrayList();
		if (ans.size()<=0) {
			return null;
		}
		for (int i=0;i<ans.size();i++) {
			ans.get(i).setName(p.getName()+"/"+ans.get(i).getName());
			res.add(ans.get(i));
		}
		for (ActionParam par:ans) {
			List<ActionParam> chilren = findParamByPath(par);
			if (chilren!=null) {
				res.addAll(chilren);
			}
		}
		return res;
	}
}
