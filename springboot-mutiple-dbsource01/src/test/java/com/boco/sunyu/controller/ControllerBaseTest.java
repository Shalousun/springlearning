package com.boco.sunyu.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ApplicationPower.
 * @author yu on 2017年12月23日 星期六  13:09:24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement(proxyTargetClass = true)
@AutoConfigureMockMvc
public class ControllerBaseTest {

}