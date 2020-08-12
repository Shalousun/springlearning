package com.sunyu.curator;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.ApiErrorCode;
import com.power.doc.model.CustomRespField;
import com.power.doc.model.SourceCodePath;
import com.sunyu.curator.enums.ErrorCode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @link https://github.com/shalousun/ApplicationPower/tree/master/api-doc
 * @author yu on 2019/07/08.
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

        // set error code list
        List<ApiErrorCode> errorCodeList = new ArrayList<>();
        for(ErrorCode codeEnum: ErrorCode.values()){
            ApiErrorCode errorCode = new ApiErrorCode();
            errorCode.setValue(codeEnum.getCode()).setDesc(codeEnum.getMessage());
            errorCodeList.add(errorCode);
        }
        config.setErrorCodes(errorCodeList);


        long start = System.currentTimeMillis();
        ApiDocBuilder.buildApiDoc(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }
}
