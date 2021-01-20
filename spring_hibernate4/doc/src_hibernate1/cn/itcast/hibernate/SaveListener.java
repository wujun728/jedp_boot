package cn.itcast.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

import cn.itcast.hibernate.domain.User;

public class SaveListener implements SaveOrUpdateEventListener {

	public void onSaveOrUpdate(SaveOrUpdateEvent event)
			throws HibernateException {
		if (event.getObject() instanceof cn.itcast.hibernate.domain.User) {
			User user = (User) event.getObject();
			System.out.println("--- " + user.getId());
		}

	}

}
