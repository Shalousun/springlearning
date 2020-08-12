package com.boco.sunyu.controller;

import org.junit.Before;
import org.junit.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * controller restful接口单元测试
 * Created by ApplicationPower.
 * @author yu on 2017年12月23日 星期六  13:09:24.
 *
 */
public class SysUserControllerTest extends ControllerBaseTest {

	@Resource
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	/**
	 * 测试controller add接口
	 * @throws Exception
     */
	@Test
	public void testAdd() throws Exception {
		MvcResult result = mockMvc.perform(post("/studentOne/add")
				.contentType(MediaType.APPLICATION_JSON)
				.param("name","gdag04")
				.param("age","792")
				.param("address","oae6gn")
		).andExpect(status().isOk()).andDo(print()).andReturn();
		System.out.println("result:"+result.getResponse().getContentAsString());
	}

	/**
	 * 测试controller queryById接口
	 * @throws Exception
     */
	@Test
	public void testQueryById() throws Exception {
		MvcResult result = mockMvc.perform(get("/sysUser/query/{id}", 1)
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
        MvcResult result = mockMvc.perform(post("/sysUser/update")
         	.contentType(MediaType.APPLICATION_JSON)
         	
            .param("id","403")
            .param("staffId","719")
            .param("realName","fotu2l")
            .param("orgId","51")
            .param("departmentCode","765")
            .param("name","c6a8uq")
            .param("password","jjjwil")
            .param("problem","gdq393")
            .param("answer","l35cen")
            .param("insertOperator","fy87my")
            .param("insertDatetime","2017-12-23 13:09:24")
            .param("lastModifyOperator","3maztj")
            .param("lastModifyDatetime","2017-12-23 13:09:24")
            .param("delFlag","187")
            .param("memo","1yzy41")
            .param("post","9r3yvz")
        ).andExpect(status().isOk()).andDo(print()).andReturn();
        System.out.println("result:"+result.getResponse().getContentAsString());
	}

	/**
	 * 测试controller delete接口
	 * @throws Exception
     */
	@Test
	public void testDelete() throws Exception {
		MvcResult result = mockMvc.perform(get("/sysUser/delete/{id}", 1)
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
        MvcResult result = mockMvc.perform(get("/sysUser/page/{offset}/{limit}",1,10)
            .contentType(MediaType.APPLICATION_JSON)
            .param("param1", "pm1")
        ).andExpect(status().isOk()).andDo(print()).andReturn();
        System.out.println("result:"+result.getResponse().getContentAsString());
    }
}
