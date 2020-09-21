package service.impl;

import java.util.List;
import mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import po.Actor;
import service.ActorService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service("actorservice")
public class ActorServiceImpl implements ActorService{
	@Autowired
	public ActorMapper actorMapper;
	    
	public Actor getActorByid(int id) {
		Actor a=actorMapper.getactorbyid(id);
		return a;
	}

	public List<Actor> getActors() {
		List<Actor> l=actorMapper.getAllactors();
		return l;
	}

	public void UpdateActor(Actor a) {
		actorMapper.updateActor(a);
	}

	public void SaveActor(Actor a) {
		actorMapper.insertActor(a);
	}

	public void Delete(int id) {
		actorMapper.delete(id);
	}


}
