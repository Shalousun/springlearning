package com.sunyu.prometheus.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * controller restful接口单元测试
 * @author yu on 2018/12/29.
 *
 */
public class ProvinceGatewayControllerTest extends ControllerBaseTest {

	/**
	 * 测试controller queryById接口
	 * @throws Exception
     */
	@Test
	public void testQueryById() throws Exception {
		MvcResult result = mockMvc.perform(get("/provinceGateway/query/{id}", 1)
 			.contentType(MediaType.APPLICATION_JSON)
 			.param("param1", "pm1")
 		).andExpect(status().isOk()).andDo(print()).andReturn();
		System.out.println("result:"+result.getResponse().getContentAsString());
	}

    /**
     * 测试controller queryPage分页接口
     * @throws Exception
     */
    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(get("/provinceGateway/page/{offset}/{limit}",1,10)
            .contentType(MediaType.APPLICATION_JSON)
            .param("param1", "pm1")
        ).andExpect(status().isOk()).andDo(print()).andReturn();
        System.out.println("result:"+result.getResponse().getContentAsString());
    }
}
