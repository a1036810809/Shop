package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionParam;
import cn.techaction.vo.ActionParamVo;

public interface ActionParamService {
	/**
	 * 删除类型
	 * @param id
	 * @return
	 */
	public SverResponse<String> delParam(Integer id);
	/**
	 * 查找配件类型
	 * @param id
	 * @return
	 */
	public SverResponse<List<ActionParamVo>> findPartsType(Integer id);
	/**
	 * 查询顶级类型
	 * @return
	 */
	public SverResponse<List<ActionParam>> findParentType();
	/**
	 * 查找子节点
	 * @param id
	 * @return
	 */
	public SverResponse<List<ActionParam>> findChildren(Integer id);
	/**
	 * 更新类型
	 * @param name
	 * @param id
	 * @return
	 */
	public SverResponse<String> updateParam(String name,Integer id);
	/**
	 * 新增类型
	 * @param name
	 * @param parent_id
	 * @return
	 */
	public SverResponse<String> saveParam(String name,Integer parent_id);
	/**
	 * 获取全部分类信息
	 * @return
	 */
	public SverResponse<List<ActionParam>> findAllParamsP();
	/**
	 * 按路径获取
	 * @return
	 */
	public SverResponse<List<ActionParam>> findPathParam();
}
