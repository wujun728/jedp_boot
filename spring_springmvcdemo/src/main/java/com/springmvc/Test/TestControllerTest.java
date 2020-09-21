package com.springmvc.test;

import com.springmvc.config.WebMVCConfig;
import com.springmvc.service.DemoService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zsq on 16/12/21.
 * @WebAppConfiguration 声明加载 Application context 并指定资源位置
 *
 * @ContextConfiguration 用来加载 Application context 其中 classes 属性用来加载配置类
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMVCConfig.class})
@WebAppConfiguration(value = "src/main/resources")
public class TestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private DemoService demoService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MockHttpSession mockHttpSession;

    @Autowired
    MockHttpServletRequest mockHttpServletRequest;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /*@Test
    public void testNormal() throws Exception {
        mockMvc.perform(get("/normal")).andExpect(status().isOk())
                .andExpect(view().name("/page"))
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))
                .andExpect(model().attribute("msg", demoService.saySomething()));
    }*/
}
