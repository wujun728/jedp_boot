package com.xiaoleilu.hutool.convert.impl;

import com.xiaoleilu.hutool.convert.AbstractConverter;
import com.xiaoleilu.hutool.convert.Convert;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * int 类型数组转换器
 * @author Looly
 *
 */
public class IntArrayConverter extends AbstractConverter<int[]>{
	
	@Override
	protected int[] convertInternal(Object value) {
		final Integer[] result = Convert.convert(Integer[].class, value);
		return CollectionUtil.unWrap(result);
	}

}
