package com.sz.tool;

import com.sz.intf.IEntity;
import com.sz.intf.IEntityDataVO;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.util.CommonLogUtil;
import com.sz.util.JsonUtils;

public class UserRegMessageHandle extends RequestMessageHandle {

	@Override
	public String toNormalStr(IEntity entityVO) {
		String returnstr = JsonUtils.getJson(entityVO);

		return returnstr;
	}

	/**
	 * ����ת�����ַ���
	 */
	@Override
	public String toJsonDataStr(IEntityDataVO dataVO) {

		String returnstr = JsonUtils.getJson(dataVO);

		return returnstr;
	}

	/**
	 * �ַ���ת���ɶ���
	 */
	@Override
	public IEntity toBean(String jsonStr) {
		if (jsonStr == null) {
			return null;
		}
		UserRegRequestMessage userRegRequestMessage = JsonUtils.readValue(
				jsonStr, UserRegRequestMessage.class);

		CommonLogUtil.debug("�յ���Ϣ:" + userRegRequestMessage.getName());
		// ��Ϣ�����ʱ��
		long value = System.currentTimeMillis()
				- Long.valueOf(userRegRequestMessage.getTime());

		CommonLogUtil.debug("�յ���Ϣʱ��:" + value);

		return userRegRequestMessage.getUserInfoData();
	}

}
