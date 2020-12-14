package com.sunyu.openapi.controller;

import com.power.common.model.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传测试
 * @author yu 2019/10/31.
 */
@RestController
public class FileUploadController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    /**
     * 上传单个文件
     * @param userId 用户id
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    public CommonResult upload(String userId, @RequestParam MultipartFile file) {
        LOGGER.info("file:{}",file.getOriginalFilename());
        return CommonResult.ok();
    }

    /**
     * 批量上传文件
     * @param file 文件
     * @return
     */
    @PostMapping("/batchUpload")
    public CommonResult batchFileUpload(MultipartFile[] file) {
        for (MultipartFile file1:file){
            LOGGER.info("file:{}",file1.getOriginalFilename());
        }
        return CommonResult.ok();
    }
}
