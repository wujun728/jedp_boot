package com.sz.handle;

import com.sz.intf.IEntity;

/**
 * json �����ݴ���ӿ�
 * 
 * <b>��Ŀ����: CommonVO </b><br/>
 * <b>������: </b><br/>
 * <b>�� �� ��: </b> zhouxj <br/>
 * <b>����ʱ��: </b> 2013-2-1 ����7:45:48 <br/>
 * <b>�� �� ��: </b><br/>
 * <b>�޸�ʱ��: </b><br/>
 * <b>�޸ı�ע: </b><br/>
 * <b>JDK �汾: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public interface IJsonHandle extends IHandle {
	
	/**
	 * ת��Ϊ json �ַ��� toJsonStr (����������������������� �C ��ѡ)
	 * 
	 * @param entity
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public String toJsonStr(IEntity entity);

	/**
	 * ת��Ϊ���ݽṹ toBean
	 * 
	 * @param jsonStr
	 * @return IEntity
	 * @exception
	 * @since 1.0.0
	 */
	public IEntity toBean(String jsonStr);

}
