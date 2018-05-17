package top.imyzt.ms.common;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import top.imyzt.ms.MsApplication;

/**
 *
 * <p>
 *     Junit Test基类
 * </p>
 *
 * @author imyzt
 * @date 2018年5月4日22:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MsApplication.class)
@WebAppConfiguration
//@Transactional  //开启此注解,测试数据会自动回滚
public abstract class BaseJunitTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public void defaultMockMvc(String url) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

