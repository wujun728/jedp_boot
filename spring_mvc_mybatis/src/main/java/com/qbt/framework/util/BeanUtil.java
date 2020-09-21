package com.qbt.framework.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qbt.framework.emun.CommonEmun;
import com.qbt.framework.exception.BusinessException;

public class BeanUtil {
	
	protected static Logger logger = LogManager.getLogger(BeanUtil.class);
	
	 public static void copyProperties(Object dest, Object orig)
		        throws BusinessException {
		 try {
			BeanUtils.copyProperties(dest, orig);
		}  catch (Exception e) {
			logger.error(e);
			throw new BusinessException(CommonEmun.PARAMES_ERROR);
		}
	}
	
}
