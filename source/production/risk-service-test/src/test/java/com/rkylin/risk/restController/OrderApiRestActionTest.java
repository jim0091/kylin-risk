package com.rkylin.risk.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkylin.risk.order.bean.SimpleOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by cuixiaofang on 2016-4-1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring-dubbo-consumer-test.xml", "classpath:/spring-mvc.xml"})
public class OrderApiRestActionTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  private ObjectMapper mapper = new ObjectMapper();

  @Before
  public void setup() throws Exception {

    this.mockMvc = webAppContextSetup(this.wac).build();
  }


  @Test
  public void orderCheckTest() throws Exception {
    SimpleOrder orders = new SimpleOrder();
    orders.setOrderId("1345");
    orders.setAmount("50000");
    orders.setOrderTypeId("M20001");
    orders.setRootInstCd("M000005");
    orders.setProductId("P000011");
    orders.setOrderDate("20151010");
    orders.setOrderTime("20151010121212");
    orders.setUserId("123123");
    orders.setGoodsName("geli");
    orders.setStatusId("8");
    String orderStr = mapper.writeValueAsString(orders);
    System.out.println(orderStr);
    this.mockMvc.perform(post("/orderApi/orderCheck")
        .contentType(MediaType.APPLICATION_JSON)
        .content(orderStr))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));

  }
}
