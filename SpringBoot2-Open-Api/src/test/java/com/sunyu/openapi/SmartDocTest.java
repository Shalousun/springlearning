package com.sunyu.openapi;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.ApiErrorCodeDictionary;
import com.power.doc.model.CustomRespField;
import com.power.doc.model.SourceCodePath;
import com.sunyu.openapi.enums.ErrorCodeEnum;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
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
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }

    @Test
    public void createJapi(){
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\workstation\\springlearning\\SpringBoot2-Open-Api"); // root project path
        config.setProjectName("ProjectName"); // project name
        config.setApiVersion("V1.0");       // api version
        config.setDocsPath("src/main/resources/static/doc/"); // api docs target path
        config.setAutoGenerate(Boolean.TRUE);  // auto generate
        Docs.buildHtmlDocs(config); // execute to generate
    }
}
