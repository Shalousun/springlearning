package com.sunyu.openapi;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.constants.DocLanguage;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.ApiErrorCodeDictionary;
import com.power.doc.model.CustomRespField;
import com.power.doc.model.SourceCodePath;
import com.sunyu.openapi.enums.ErrorCodeEnum;
import org.junit.Test;


/**
 * @author yu on 2020/11/28.
 * @link https://github.com/shalousun/smart-doc.git
 */
public class SmartDocTest {

    /**
     * create api-doc
     */
    @Test
    public void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setStrict(false);
        config.setAllInOne(true);
        config.setLanguage(DocLanguage.CHINESE);
        config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);
        config.setCreateDebugPage(true);
        // set java source path
        config.setSourceCodePaths(
                SourceCodePath.builder().setDesc("current project").setPath("src/main/java")
        );

        // change field
        config.setCustomResponseFields(
                CustomRespField.builder().setName("code").setValue("00000")
        );

        // set error code list
        config.setErrorCodeDictionaries(
                ApiErrorCodeDictionary.builder()
                        .setEnumClass(ErrorCodeEnum.class)
                        .setCodeField("code")
                        .setDescField("message")
        );

        long start = System.currentTimeMillis();
        HtmlApiDocBuilder.buildApiDoc(config);
        System.out.println(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
}
