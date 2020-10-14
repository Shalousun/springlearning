package com.sunyu.learn;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.sunyu.learn.enums.ErrorCodeEnum;
import com.power.doc.model.ApiConfig;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.model.ApiErrorCode;
import com.power.doc.model.CustomRespField;
import com.power.doc.model.SourceCodePath;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @link https://github.com/shalousun/smart-doc.git
 * @author yusun4 on 2020/10/14.
 */
public class SmartDocTest {

   /**
    *  create api-doc
    */
   @Test
   public void testBuilderControllersApi() {
       ApiConfig config = new ApiConfig();
       config.setStrict(true);
       config.setAllInOne(true);
       config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);
       // set java source path
       config.setSourceCodePaths(
               SourceCodePath.path().setDesc("current project").setPath("src/main/java")
       );

       // change field
       config.setCustomResponseFields(
               CustomRespField.field().setName("code").setValue("00000")
       );

       // set error code list
       config.setErrorCodeDictionaries(
               ApiErrorCodeDictionary.dict()
                       .setEnumClass(ErrorCodeEnum.class)
                       .setCodeField("code")
                       .setDescField("desc")
       );

       long start = System.currentTimeMillis();
       HtmlApiDocBuilder.buildApiDoc(config);
       long end = System.currentTimeMillis();
       DateTimeUtil.printRunTime(end, start);
   }
}
