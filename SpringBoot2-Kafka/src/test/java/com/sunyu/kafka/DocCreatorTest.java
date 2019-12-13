package com.sunyu.kafka;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.ApiErrorCode;
import com.power.doc.model.CustomRespField;
import com.sunyu.kafka.enums.ErrorCodeEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @link https://github.com/shalousun/ApplicationPower/tree/master/api-doc
 * @author yu on 2018/11/09.
 */
public class DocCreatorTest {

   /**
    *  create api-doc
    */
   @Test
   public void testBuilderControllersApi() {
       ApiConfig config = new ApiConfig();
       config.setStrict(false);
       config.setAllInOne(true);
       config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);


       // change field
       config.setCustomResponseFields(
               CustomRespField.field().setName("code").setValue("00000")
       );

       // set error code list
       List<ApiErrorCode> errorCodeList = new ArrayList<>();
       for(ErrorCodeEnum codeEnum: ErrorCodeEnum.values()){
           ApiErrorCode errorCode = new ApiErrorCode();
           errorCode.setValue(codeEnum.getCode()).setDesc(codeEnum.getDesc());
           errorCodeList.add(errorCode);
       }
       config.setErrorCodes(errorCodeList);


       long start = System.currentTimeMillis();
       HtmlApiDocBuilder.builderControllersApi(config);
       long end = System.currentTimeMillis();
       DateTimeUtil.printRunTime(end, start);
   }
}
