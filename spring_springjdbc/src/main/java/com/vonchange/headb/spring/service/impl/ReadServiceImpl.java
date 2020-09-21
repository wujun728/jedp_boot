package com.vonchange.headb.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.vonchange.headb.core.map.HeaMap;
import com.vonchange.headb.core.page.IPage;
import com.vonchange.headb.core.page.impl.PageImpl;
import com.vonchange.headb.spring.dao.IHeaDao;
import com.vonchange.headb.spring.service.IReadService;

public abstract class ReadServiceImpl<T> implements IReadService<T> {
	public abstract IHeaDao<T> getDao();//

	public final List<T> pageToList(HeaMap parameter, Integer pageSize) {
		IPage<T> page = new PageImpl<T>(pageSize, 1);
		page = getDao().findPage(page, parameter);
		List<T> list = new ArrayList<T>();
		list.addAll(page.getEntities());
		if (page.getPageCount() > 1) {
			for (int i = 2; i <= page.getPageCount(); i++) {
				page = new PageImpl<T>(pageSize, i);
				page = getDao().findPage(page, parameter);
				list.addAll(page.getEntities());
			}
		}
		return list;
	}

	public final T findById(Object id) {
		return getDao().findById(id);
	}

	public final T findFirst(HeaMap parameter) {
		return getDao().findFirst(parameter);
	}

	public final List<T> findList(HeaMap parameter) {
		return getDao().findList(parameter);
	}

	public final IPage<T> findPage(IPage<T> page, HeaMap parameter) {
		return getDao().findPage(page, parameter);
	}
}
