package cn.techaction.service;

import java.util.List;

import cn.techaction.common.SverResponse;
import cn.techaction.pojo.ActionAddress;

public interface ActionAddrService {
	/**
	 * 新增收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> addAddressP(ActionAddress addr);
	/**
	 * 更新收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> updateAddressP(ActionAddress addr);
	/**
	 * 查找某个用户的所有收货地址
	 * @param userId
	 * @return
	 */
	public SverResponse<List<ActionAddress>> findAddrsByUserIdP(Integer userId);
	/**
	 * 根据id删除收件人地址信息
	 * @param userId
	 * @param id
	 * @return
	 */
	public SverResponse<String> delAddressP(Integer userId, Integer id);
	/**
	 * 更新默认地址
	 * @param userId
	 * @param id
	 * @return
	 */
	public SverResponse<String> updateAddrDefaultStatusP(Integer userId, Integer id);
	/**
	 * 查找收货地址信息
	 * @param id
	 * @return
	 */
	public SverResponse<List<ActionAddress>> finAddressByIdP(Integer id);

}
