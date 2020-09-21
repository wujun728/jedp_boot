package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Actor;
import po.MSG;
import service.ActorService;

@Controller
public class ActorController {
	@Autowired
	private ActorService actorservice;
	
	@RequestMapping(value="/actors",method = RequestMethod.GET)
	@ResponseBody
	public List<Actor> getactorlist(){
		List<Actor> list=actorservice.getActors();
		return  list;
	}
	
	@RequestMapping(value="/actors/{id}",method = RequestMethod.PUT,consumes="application/json")
	@ResponseBody
	public Actor updateactor(@PathVariable("id") int id,@RequestBody Actor actor){
		actor.setId(id);
		actorservice.UpdateActor(actor);
		return actor;
	}
	
	@RequestMapping(value="/actors/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Actor getactorbyid(@PathVariable("id") int id){
		Actor a=actorservice.getActorByid(id);
		return a;
	}
	
	@RequestMapping(value="/actors",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public Actor add(@RequestBody Actor actor){
			actorservice.SaveActor(actor);
			return actor;//a即为被保存好的对象，直接返回已经拥有新主键
	}
	
	@RequestMapping(value="/actors/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public MSG delete(@PathVariable("id") int id){
		actorservice.Delete(id);
		MSG msg=new MSG();
		msg.setStatus("200");
		return msg;
	}
	
	@RequestMapping(value="rest",method = RequestMethod.GET)
	public String rest(){
		return "rest";
	}
	
	
}
