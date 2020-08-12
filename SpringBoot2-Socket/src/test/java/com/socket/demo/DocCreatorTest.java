package com.socket.demo;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.CustomRespField;
import com.power.doc.model.SourceCodePath;
import org.junit.Test;


/**
 * @link https://github.com/shalousun/ApplicationPower/tree/master/api-doc
 * @author yu on 2019/01/17.
 */
public class DocCreatorTest {

    /**
     *  create api-doc
     */
    @Test
    public void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setStrict(false);
        config.setServerUrl("http://spring.boot2.apollo.com");
        config.setOutPath("d:\\md");
        // set java source path
        config.setSourceCodePaths(
                SourceCodePath.path().setDesc("current project").setPath("src/main/java")
        );

        // change field
        config.setCustomResponseFields(
                CustomRespField.field().setName("code").setValue("00000")
        );
        long start = System.currentTimeMillis();
        ApiDocBuilder.buildApiDoc(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
}
