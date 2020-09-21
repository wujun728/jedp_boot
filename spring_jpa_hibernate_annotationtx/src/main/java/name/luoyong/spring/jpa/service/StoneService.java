package name.luoyong.spring.jpa.service;

import name.luoyong.spring.jpa.dao.StoneDao;
import name.luoyong.spring.jpa.entity.Stone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoneService {
	
	@Autowired
	private StoneDao stoneDao;
	
	@Transactional
	public void save(Stone stone) {
		stoneDao.save(stone);
	}
	
	@Transactional
	public void delete(Long id) {
		stoneDao.delete(id);
	}

}
