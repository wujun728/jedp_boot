package com.sz.tool;

import com.sz.handle.IJsonHandle;
import com.sz.intf.IEntity;
import com.sz.intf.IEntityDataVO;
import com.sz.intf.IEntityVO;

/**
 * ���ص���Ϣ������
 * 
 * <b>��Ŀ����: CommonVO </b><br/>
 * <b>������: </b><br/>
 * <b>�� �� ��: </b> zhouxj <br/>
 * <b>����ʱ��: </b> 2013-2-4 ����6:23:59 <br/>
 * <b>�� �� ��: </b><br/>
 * <b>�޸�ʱ��: </b><br/>
 * <b>�޸ı�ע: </b><br/>
 * <b>JDK �汾: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public abstract class ReturnMessageHandle implements IJsonHandle {
	public String toJsonStr(IEntity entity) {
		String str = "";
		if (entity instanceof IEntityDataVO) {
			str = toJsonDataStr((IEntityDataVO) entity);

		} else if (entity instanceof IEntityVO) {
			str = toNormalStr(entity);
		}
     
		return str;
	}
	
	public abstract IEntity toBean(String jsonStr);

	public abstract String toNormalStr(IEntity entityVO);

	public abstract String toJsonDataStr(IEntityDataVO dataVO);

}
