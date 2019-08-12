package cn.techaction.dao;

import java.util.List;

import cn.techaction.pojo.ActionAddress;
import cn.techaction.vo.ActionAddressVo;

public interface ActionAddressDao {
	/**
	 * 通过id获取地址
	 * @param id
	 * @return
	 */
	public ActionAddressVo findAddressById(Integer id);
	/**
	 * 根据id查询收货人地址信息
	 * @param addrId
	 * @return
	 */
	public ActionAddress findAddrsByIdP(Integer addrId);
	/**
	 * 查询是否存在默认地址
	 * @param uid
	 * @return
	 */
	public int findDefaultAddrByUserIdP(Integer userId);
	/**
	 * 新增收货人地址信息
	 * @param addr
	 * @return
	 */
	public int insertAddressP(ActionAddress addr);
	/**
	 * 更新收件人地址信息
	 * @param addr
	 * @return
	 */
	public int updateAddressP(ActionAddress addr);
	/**
	 * 查询用户的收件人地址信息
	 * @param userId
	 * @return
	 */
	public List<ActionAddress> findAddrsByUserIdP(Integer userId);
	/**
	 * 读取用户默认地址
	 * @param userId
	 * @return
	 */
	public ActionAddress findDefaultAddrP(Integer userId);
	/**
	 * 查找用户收货地址信息
	 * @param id
	 * @return
	 */
	public List<ActionAddress> findAddressByIdP(Integer id);
}
