package cn.itcast.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaUtil {
	private static EntityManagerFactory emf;

	static {
		emf = Persistence.createEntityManagerFactory("persistUnitName");
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
