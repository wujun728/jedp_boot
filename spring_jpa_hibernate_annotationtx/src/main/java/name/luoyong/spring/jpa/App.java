package name.luoyong.spring.jpa;

import name.luoyong.spring.jpa.entity.Stone;
import name.luoyong.spring.jpa.service.StoneService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");

		StoneService stoneService = (StoneService) ctx.getBean(StoneService.class);

		Stone stone = new Stone();
		stone.setName("Big Stone");
		stoneService.save(stone);
		
		//stoneService.delete(1L);
	}

}
