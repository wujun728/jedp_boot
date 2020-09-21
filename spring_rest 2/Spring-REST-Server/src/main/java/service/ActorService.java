package service;

import java.util.List;
import po.Actor;

public interface ActorService {
		List<Actor> getActors();
		Actor getActorByid(int id);
		void UpdateActor(Actor a);
		void SaveActor(Actor a);
		void Delete(int id);
}
