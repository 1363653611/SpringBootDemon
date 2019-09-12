package com.zbcn.mvc.service;

import com.zbcn.mvc.config.MyConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.server.MockWebSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.xml.sax.helpers.DefaultHandler;

import static org.junit.Assert.*;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName DemonServiceTest.java
 * @Description 单元测试
 * @createTime 2019年09月01日 11:37:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyConfig.class})
@WebAppConfiguration("src/main/resources")//申明加载的是一个webApplicationContext，并且指定资源加载路径
public class DemonServiceTest {
    /**
     * 模拟mvc 对象
     */
    private MockMvc mockMvc;

    private MockHttpSession mockHttpSession;

    private MockHttpServletRequest request;

    @Autowired
    private DemonService demonService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        this.mockMvc =  MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saySomething() {
    }

    @Test
    public void testNormalController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/normal"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("page"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/classes/views/page.jsp"))
        .andExpect(MockMvcResultMatchers.model().attribute("msg",demonService.saySomething()));
    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/testRest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(demonService.saySomething()));
    }
}