package com.qbt.framework.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.NestedRuntimeException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;

/**
 * <p>名称：DynamicDataSourceProcessor.java</p>
 * <p>描述：</p>
 * <pre>
 * 	
 *    1.supports : 只读情况：读从库
					写+读情况：写主库，读主库
					优势：可以防止写完后可能读不到刚才写的数据
	  2：NOT_SUPPORTS: 只读情况：读从库
						写+读情况：写主库，读从库
 * </pre>
 * @author 鲍建明
 * @date 2014年8月18日 下午5:36:49
 * @version 1.0.0
 */
public class DynamicDataSourceProcessor implements BeanPostProcessor{

	protected static Logger logger = LogManager.getLogger(DynamicDataSourceProcessor.class);
	
	    
	
	private static Map<String, Boolean> readMethodMap = new HashMap<String, Boolean>();
	
	
	private  boolean IS_SUPPORTS = Boolean.FALSE;				//默认不强迫选择读
	
	
	/**
	 * 
	 * <p>描述：设置读写分离的策略</p>
	 * <pre>
	 *    1.supports : 只读情况：读从库
					写+读情况：写主库，读主库
					优势：可以防止写完后可能读不到刚才写的数据
	  2：NOT_SUPPORTS: 只读情况：读从库
						写+读情况：写主库，读从库
	 * </pre>
	 * @param iS_SUPPORTS
	 */
	public  void setIS_SUPPORTS(Boolean iS_SUPPORTS) {
		IS_SUPPORTS = iS_SUPPORTS;
	}


	/**
	 * 
	 * <p>描述：AOP切入根据方法名和读写策略选择相应的数据源</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object changeDataSource(ProceedingJoinPoint pjp) throws Throwable {
		
		String methodName = pjp.getSignature().getName();
		if (isChangeRead(methodName)) {
			DynamicDataSourceHolder.setRead();
		} else {
			DynamicDataSourceHolder.setWrite();
		}
		try {
			return pjp.proceed();
		} finally {
			DynamicDataSourceHolder.clear();
		}
	}


	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean instanceof NameMatchTransactionAttributeSource){
			try {
				NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = (NameMatchTransactionAttributeSource)bean;
				Field field = nameMatchTransactionAttributeSource.getClass().getDeclaredField("nameMap");
				field.setAccessible(true);
				Map<String, TransactionAttribute> map = (Map<String, TransactionAttribute>) field.get(bean);
				for(Entry<String, TransactionAttribute> entry : map.entrySet() ){
					RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute)entry.getValue();
				    //仅对read-only的处理
	                if(!attr.isReadOnly()) {
	                    continue;
	                }
	                
	                String methodName = entry.getKey();
	                Boolean isForceChoiceRead = Boolean.FALSE;
	                if( IS_SUPPORTS ) {
	                    //不管之前操作是写，默认强制从读库读 （设置为NOT_SUPPORTED即可）
	                    //NOT_SUPPORTED会挂起之前的事务
	                    attr.setPropagationBehavior(Propagation.NOT_SUPPORTED.value());
	                    isForceChoiceRead = Boolean.TRUE;
	                } else {
	                    //否则 设置为SUPPORTS（这样可以参与到写事务）
	                    attr.setPropagationBehavior(Propagation.SUPPORTS.value());
	                }
	                logger.debug("read/write transaction process  method:{} force read:{}", methodName, isForceChoiceRead);
	                readMethodMap.put(methodName, isForceChoiceRead);
				}
			} catch (Exception  e) {
				logger.error("读写分离设置失败", e);
				throw new ReadWriteDataSourceTransactionException("process read/write transaction error", e);
			}
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}
	
	
	/**
	 * 
	 * <p>描述：是否选择读库</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param methodName
	 * @return
	 */
	private boolean isChangeRead(String methodName) {
		String bestNameMatch = null;
		for (String mappedName : readMethodMap.keySet()) {
			if (isMatch(methodName, mappedName)) {
				bestNameMatch = mappedName;
				break;
			}
		}
		Boolean isForceChoiceRead = readMethodMap.get(bestNameMatch);
        //表示强制选择 读 库
        if(isForceChoiceRead == Boolean.TRUE) {
            return true;
        }
        
        //如果之前选择了写库 现在还选择 写库
        if(DynamicDataSourceHolder.isWrite()) {
            return false;
        }
        
        //表示应该选择读库
        if(isForceChoiceRead != null) {
            return true;
        }
        //默认选择 写库
        return false;
	}
	
	
	 /**
	  * 
	  * <p>描述：方法名匹配</p>
	  * <pre>
	  *    
	  * </pre>
	  * @param methodName
	  * @param mappedName
	  * @return
	  */
	protected boolean isMatch(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}

	private class ReadWriteDataSourceTransactionException extends
			NestedRuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6554230316746573118L;

		public ReadWriteDataSourceTransactionException(String message,
				Throwable cause) {
			super(message, cause);
		}
	}
	
	

	
}
