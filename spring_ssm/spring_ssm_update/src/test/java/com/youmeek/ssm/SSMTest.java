package com.youmeek.ssm;

import com.youmeek.ssm.pojo.Event;
import com.youmeek.ssm.pojo.EventDetail;
import com.youmeek.ssm.pojo.SysUser;
/*import com.youmeek.ssm.service.App2;*/
import com.youmeek.ssm.service.EventService;
import com.youmeek.ssm.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
// 在Maven的pom.xml中设置相对路径
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class SSMTest {

	private static Logger logger = LoggerFactory.getLogger(SSMTest.class);

	@Resource
	private SysUserService sysUserService;

	@Autowired
	private EventService eventService;

	@Autowired
	ApplicationContext ctx;



	@Test
	public void test1() {
		SysUser sysUser = sysUserService.getById(1L);
		System.out.println("--------------------------------" + sysUser.toString());
	}

	@Test
	public void test2() {
		Event mm = eventService.getEventById(1);
	}

	@Test
	public void test3() {
		List<Event> mm = eventService.getAllEvent();
	}

	@Test
	public void testGetEventDetailByManAndEventId() {
		List<EventDetail> mm = eventService.getEventDetailByManAndEventId(1,1);
	}

	/*@Test
	public void test4() {
		App2 app2 = (App2) ctx.getBean("app2");
		app2.test();
	}*/

	@Test
	public void testGetEventDetailByEventId() {
		List<EventDetail> mm = eventService.getEventDetailByEventId(1);
		/*System.out.println("--------------------------------" + mm.toString());*/
		logger.info(String.valueOf(mm));
	}
}
