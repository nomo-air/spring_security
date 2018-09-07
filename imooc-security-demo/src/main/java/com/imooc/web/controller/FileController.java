package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    // 文件上传路径
    private String folder = "/Users/zhengwanlei/github/spring_security/imooc-security-demo/src/main/java/com/imooc/web/controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception {
        log.info("{}", "{}", "{}", "文件名：" + file.getName(), "源文件名：" + file.getOriginalFilename(), "文件大小：" + file.getSize());
        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) { // JDK1.7新语法，把流写进try括号中，虚拟机会帮你自动关闭流
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

    }

}
