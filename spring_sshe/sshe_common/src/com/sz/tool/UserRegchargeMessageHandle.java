package com.sz.tool;

import com.sz.intf.IEntity;
import com.sz.intf.IEntityDataVO;
import com.sz.message.request.UserRechargeRequestMessage;
import com.sz.util.CommonLogUtil;
import com.sz.util.JsonUtils;

/**
 * ��ҳ�ֵ�ӿ�
 * 
 * 
 * <b>��Ŀ����: CommonVO </b><br/>
 * <b>������: </b><br/>
 * <b>�� �� ��: </b> zhouxj <br/>
 * <b>����ʱ��: </b> 2013-6-21 ����6:05:57 <br/>
 * <b>�� �� ��: </b><br/>
 * <b>�޸�ʱ��: </b><br/>
 * <b>�޸ı�ע: </b><br/>
 * <b>JDK �汾: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class UserRegchargeMessageHandle extends RequestMessageHandle {

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
		UserRechargeRequestMessage userRechargeRequestMessage = JsonUtils
				.readValue(jsonStr, UserRechargeRequestMessage.class);

		CommonLogUtil.debug("�յ���Ϣ:" + userRechargeRequestMessage.getName());
		// ��Ϣ�����ʱ��
		long value = System.currentTimeMillis()
				- Long.valueOf(userRechargeRequestMessage.getTime());

		CommonLogUtil.debug("�յ���Ϣʱ��:" + value);
		
		return userRechargeRequestMessage.getUserRechargeData();
	}

}
