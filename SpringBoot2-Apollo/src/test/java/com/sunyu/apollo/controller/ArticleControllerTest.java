package com.sunyu.apollo.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * controller restful接口单元测试
 * @author yu on 2018/11/12.
 *
 */
public class ArticleControllerTest extends ControllerBaseTest {

	/**
	 * 测试controller add接口
	 * @throws Exception
     */
	@Test
	public void testAdd() throws Exception {
		MvcResult result = mockMvc.perform(post("/article/add")
 			.contentType(MediaType.APPLICATION_JSON)
            .param("articleId","150")
            .param("title","hwrzf5")
            .param("category","8k4z2x")
 		).andExpect(status().isOk()).andDo(print()).andReturn();
		System.out.println("result:"+result.getResponse().getContentAsString());
	}

	/**
	 * 测试controller queryById接口
	 * @throws Exception
     */
	@Test
	public void testQueryById() throws Exception {
		MvcResult result = mockMvc.perform(get("/article/query/{id}", 1)
 			.contentType(MediaType.APPLICATION_JSON)
 			.param("param1", "pm1")
 		).andExpect(status().isOk()).andDo(print()).andReturn();
		System.out.println("result:"+result.getResponse().getContentAsString());
	}

	/**
	 * 测试controller update接口
	 * @throws Exception
     */
	@Test
	public void testUpdate() throws Exception {
        MvcResult result = mockMvc.perform(post("/article/update")
         	.contentType(MediaType.APPLICATION_JSON)
         	
            .param("articleId","150")
            .param("title","hwrzf5")
            .param("category","8k4z2x")
        ).andExpect(status().isOk()).andDo(print()).andReturn();
        System.out.println("result:"+result.getResponse().getContentAsString());
	}

	/**
	 * 测试controller delete接口
	 * @throws Exception
     */
	@Test
	public void testDelete() throws Exception {
		MvcResult result = mockMvc.perform(get("/article/delete/{id}", 1)
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
        MvcResult result = mockMvc.perform(get("/article/page/{offset}/{limit}",1,10)
            .contentType(MediaType.APPLICATION_JSON)
            .param("param1", "pm1")
        ).andExpect(status().isOk()).andDo(print()).andReturn();
        System.out.println("result:"+result.getResponse().getContentAsString());
    }
}
