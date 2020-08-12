package com.boco.sunyu.controller;

import com.boco.sunyu.config.DataSourceConfig;
import com.boco.sunyu.config.DataSourceOneConfig;
import com.boco.sunyu.config.DataSourceTwoConfig;
import com.boco.sunyu.config.TransactionManagerConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ApplicationPower.
 * @author yu on 2017年12月23日 星期六  13:09:24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DataSourceOneConfig.class, DataSourceTwoConfig.class, TransactionManagerConfig.class })
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
@AutoConfigureMockMvc
public class ControllerBaseTest extends AbstractTransactionalJUnit4SpringContextTests {

}