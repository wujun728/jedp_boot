package name.luoyong.spring.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.luoyong.spring.jpa.entity.Stone;

import org.springframework.stereotype.Repository;

@Repository
public class StoneDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Stone stone) {
		em.persist(stone);
		System.out.println("persist stone");
	}
	
	public void delete(Long id) {
		em.createQuery("delete from Stone where id=:id").setParameter("id", id).executeUpdate();
	}

}
