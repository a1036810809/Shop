package cn.techaction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.techaction.common.SverResponse;
import cn.techaction.dao.ActionAddressDao;
import cn.techaction.pojo.ActionAddress;
import cn.techaction.service.ActionAddrService;
@Service
public class ActionAddrServiceImpl implements ActionAddrService {
	@Autowired
	private ActionAddressDao addressDao;

	@Override
	public SverResponse<String> addAddressP(ActionAddress addr) {
		//判断参数
		if(addr ==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//判断已有地址中是否含有默认地址，如果没有则本条地址未默认地址，否则为一般地址
		int count = addressDao.findDefaultAddrByUserIdP(addr.getUid());
		if(count==0) {
			addr.setDefault_addr(1);
		}else {
			addr.setDefault_addr(0);
		}
		addr.setCreated(new Date());
		addr.setUpdated(new Date());
		int rs = addressDao.insertAddressP(addr);
		if(rs>0) {
			return SverResponse.createRespBySuccessMessage("地址新增成功！");
		}
		return SverResponse.createByErrorMessage("地址新增失败！");
	}

	@Override
	public SverResponse<String> updateAddressP(ActionAddress addr) {
		//判断参数
		if(addr ==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		addr.setUpdated(new Date());
		int rs = addressDao.updateAddressP(addr);
		if(rs>0) {
			return SverResponse.createRespBySuccessMessage("地址更新成功！");
		}
		return SverResponse.createByErrorMessage("地址更新失败！");
	}

	@Override
	public SverResponse<List<ActionAddress>> findAddrsByUserIdP(Integer userId) {
		//判断参数
		if(userId ==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		List<ActionAddress> list = addressDao.findAddrsByUserIdP(userId);
		return SverResponse.createRespBySuccess(list);
	}

	@Override
	public SverResponse<String> delAddressP(Integer userId, Integer id) {
		//判断参数
		if(id ==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//删除地址，对del_state字段做更新
		ActionAddress address = new ActionAddress();
		address.setId(id);
		address.setDel_state(1);
		address.setUpdated(new Date());
		int rs = addressDao.updateAddressP(address);
		if(rs > 0) {
			return SverResponse.createRespBySuccessMessage("地址删除成功！");
		}
		return SverResponse.createByErrorMessage("地址删除失败！");
	}

	@Override
	public SverResponse<String> updateAddrDefaultStatusP(Integer userId, Integer id) {
		//判断参数
		if(id ==null || userId == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//读取原来默认地址
		ActionAddress oldAddr = addressDao.findDefaultAddrP(userId);
		if(oldAddr!=null) {
			//取消默认地址
			oldAddr.setDefault_addr(0);
			oldAddr.setUpdated(new Date());
			if(addressDao.updateAddressP(oldAddr)<=0) {
				return SverResponse.createByErrorMessage("默认地址设置失败！");
			}
		}
		//设置新的默认地址
		ActionAddress newAddr = new ActionAddress();
		newAddr.setDefault_addr(1);
		newAddr.setId(id);
		newAddr.setUpdated(new Date());
		if(addressDao.updateAddressP(newAddr)<=0) {
			return SverResponse.createByErrorMessage("默认地址设置失败！");
		}
		return SverResponse.createRespBySuccessMessage("默认地址设置成功！");
	}

	@Override
	public SverResponse<List<ActionAddress>> finAddressByIdP(Integer id) {
		if(id==null){
			return SverResponse.createByErrorMessage("参数错误!");
		}
		List<ActionAddress> list = addressDao.findAddressByIdP(id);
		return SverResponse.createRespBySuccess(list);
	}

}
