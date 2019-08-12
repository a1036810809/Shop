package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionParam;

public interface ActionParamsDao {
	/**
	 * 根据id获取类型信息
	 * @param id
	 * @return
	 */
	public ActionParam findParamById(Integer id);
	/**
	 * 获取子节点
	 * @param id
	 * @return
	 */
	public List<ActionParam> findChildrenByParentId(Integer id);
	/**
	 * 删除类型
	 * @param id
	 * @return
	 */
	public int delParam(Integer id);
	/**
	 * 更新类型
	 * @param name
	 * @param id
	 * @return
	 */
	public int updateParam(String name,Integer id);
	/**
	 * 新增类型
	 * @param param
	 * @return
	 */
	public int saveParam(ActionParam param);
}
